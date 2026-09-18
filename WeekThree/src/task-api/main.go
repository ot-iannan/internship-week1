package main

import (
	"database/sql"
	"fmt"
	"net/http"
	"strconv"

	"github.com/gin-gonic/gin"
)

var db *sql.DB

func main() {
	if err := loadEnv("../db/.env"); err != nil {
		fmt.Println("Could not load .env:", err)
	}

	conn, err := connectDB()
	if err != nil {
		fmt.Println("Could not connect to database:", err)
		return
	}
	db = conn
	defer db.Close()

	r := gin.Default()

	r.POST("/tasks", createTask)
	r.GET("/tasks", listTasks)
	r.GET("/tasks/:id", getTask)
	r.PUT("/tasks/:id", updateTask)

	r.PATCH("/tasks/:id/complete", completeTask)
	r.DELETE("/tasks/:id", deleteTask)
	r.PATCH("/tasks/:id/move", moveTask)

	fmt.Println("API running on http://localhost:8081")
	r.Run(":8081")
}

func createTask(c *gin.Context) {
	var t Task
	if err := c.ShouldBindJSON(&t); err != nil {
		c.JSON(http.StatusBadRequest, gin.H{"error": "invalid_json"})
		return
	}
	if t.Title == "" {
		c.JSON(http.StatusBadRequest, gin.H{"error": "title_required"})
		return
	}

	query := `INSERT INTO tasks (project_id, title, description)
	          VALUES ($1, $2, $3) RETURNING id, completed, created_at, updated_at`
	err := db.QueryRow(query, t.ProjectID, t.Title, t.Description).
		Scan(&t.ID, &t.Completed, &t.CreatedAt, &t.UpdatedAt)
	if err != nil {
		c.JSON(http.StatusBadRequest, gin.H{"error": err.Error()})
		return
	}
	c.JSON(http.StatusCreated, t)
}

func listTasks(c *gin.Context) {
	query := "SELECT id, project_id, title, description, completed, created_at, updated_at FROM tasks"

	if c.Query("completed") == "true" {
		query += " WHERE completed = TRUE"
	} else if c.Query("completed") == "false" {
		query += " WHERE completed = FALSE"
	}

	sortBy := c.Query("sort")
	if sortBy == "title" || sortBy == "created_at" {
		query += " ORDER BY " + sortBy
	}

	limit := 20
	page := 1
	if l, err := strconv.Atoi(c.Query("limit")); err == nil && l > 0 {
		limit = l
	}
	if p, err := strconv.Atoi(c.Query("page")); err == nil && p > 0 {
		page = p
	}
	offset := (page - 1) * limit
	query += fmt.Sprintf(" LIMIT %d OFFSET %d", limit, offset)

	rows, err := db.Query(query)
	if err != nil {
		c.JSON(http.StatusInternalServerError, gin.H{"error": "query_failed"})
		return
	}
	defer rows.Close()

	tasks := []Task{}
	for rows.Next() {

		var t Task
		rows.Scan(&t.ID, &t.ProjectID, &t.Title, &t.Description, &t.Completed, &t.CreatedAt, &t.UpdatedAt)
		tasks = append(tasks, t)
	}
	c.JSON(http.StatusOK, tasks)
}

func getTask(c *gin.Context) {
	id := c.Param("id")
	var t Task
	err := db.QueryRow(
		"SELECT id, project_id, title, description, completed, created_at, updated_at FROM tasks WHERE id = $1", id,
	).Scan(&t.ID, &t.ProjectID, &t.Title, &t.Description, &t.Completed, &t.CreatedAt, &t.UpdatedAt)

	if err == sql.ErrNoRows {
		c.JSON(http.StatusNotFound, gin.H{"error": "not_found"})
		return
	}
	c.JSON(http.StatusOK, t)
}

func updateTask(c *gin.Context) {
	id := c.Param("id")
	var t Task
	if err := c.ShouldBindJSON(&t); err != nil {
		c.JSON(http.StatusBadRequest, gin.H{"error": "invalid_json"})
		return
	}

	result, err := db.Exec(
		"UPDATE tasks SET title=$1, description=$2, updated_at=NOW() WHERE id=$3",
		t.Title, t.Description, id,
	)
	if err != nil {
		c.JSON(http.StatusBadRequest, gin.H{"error": err.Error()})
		return
	}
	rows, _ := result.RowsAffected()
	if rows == 0 {
		c.JSON(http.StatusNotFound, gin.H{"error": "not_found"})
		return
	}
	c.JSON(http.StatusOK, gin.H{"status": "updated"})
}

func completeTask(c *gin.Context) {
	id := c.Param("id")
	result, err := db.Exec("UPDATE tasks SET completed=TRUE, updated_at=NOW() WHERE id=$1", id)
	if err != nil {
		c.JSON(http.StatusInternalServerError, gin.H{"error": "update_failed"})
		return
	}
	rows, _ := result.RowsAffected()
	if rows == 0 {
		c.JSON(http.StatusNotFound, gin.H{"error": "not_found"})
		return
	}
	c.JSON(http.StatusOK, gin.H{"status": "completed"})
}

func deleteTask(c *gin.Context) {
	id := c.Param("id")
	result, err := db.Exec("DELETE FROM tasks WHERE id=$1", id)
	if err != nil {

		c.JSON(http.StatusInternalServerError, gin.H{"error": "delete_failed"})
		return
	}
	rows, _ := result.RowsAffected()
	if rows == 0 {
		c.JSON(http.StatusNotFound, gin.H{"error": "not_found"})
		return
	}
	c.JSON(http.StatusOK, gin.H{"status": "deleted"})
}

func moveTask(c *gin.Context) {
	id := c.Param("id")
	newProjectID := c.Query("project_id")

	tx, err := db.Begin()
	if err != nil {
		c.JSON(http.StatusInternalServerError, gin.H{"error": "transaction_failed"})
		return
	}

	var exists bool
	err = tx.QueryRow("SELECT EXISTS(SELECT 1 FROM projects WHERE id=$1)", newProjectID).Scan(&exists)
	if err != nil || !exists {
		tx.Rollback()
		c.JSON(http.StatusBadRequest, gin.H{"error": "target_project_not_found"})
		return
	}

	_, err = tx.Exec("UPDATE tasks SET project_id=$1, updated_at=NOW() WHERE id=$2", newProjectID, id)
	if err != nil {
		tx.Rollback()

		c.JSON(http.StatusInternalServerError, gin.H{"error": "move_failed"})
		return
	}

	tx.Commit()
	c.JSON(http.StatusOK, gin.H{"status": "moved"})
}
