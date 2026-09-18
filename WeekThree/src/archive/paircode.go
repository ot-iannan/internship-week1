package main

import (
	"net/http"
	"strconv"
	"sync"

	"github.com/gin-gonic/gin"
)

type Task struct {
	ID          int    `json:"id"`
	Description string `json:"description"`
	Done        bool   `json:"done"`
}

var (
	tasks  = []Task{}
	nextID = 1
	lock   = sync.Mutex{}
)

func main() {
	r := gin.Default()

	// Health check
	r.GET("/health", func(c *gin.Context) {
		c.JSON(http.StatusOK, gin.H{"status": "ok"})
	})

	// Create Task
	r.POST("/tasks", func(c *gin.Context) {
		var input struct {
			Description string `json:"description"`
		}

		if err := c.BindJSON(&input); err != nil {
			c.JSON(http.StatusBadRequest, gin.H{
				"error": gin.H{
					"code":    "INVALID_JSON",
					"message": "invalid JSON",
				},
			})
			return
		}

		if input.Description == "" {
			c.JSON(http.StatusBadRequest, gin.H{
				"error": gin.H{
					"code":    "VALIDATION_ERROR",
					"message": "description is required and cannot be empty",
				},
			})
			return
		}

		lock.Lock()
		newTask := Task{
			ID:          nextID,
			Description: input.Description,
			Done:        false,
		}
		nextID++
		tasks = append(tasks, newTask)
		lock.Unlock()

		c.JSON(http.StatusCreated, newTask)
	})

	// List all tasks
	r.GET("/tasks", func(c *gin.Context) {
		lock.Lock()
		defer lock.Unlock()

		c.JSON(http.StatusOK, tasks)
	})

	// Get task by ID
	r.GET("/tasks/:id", func(c *gin.Context) {
		idStr := c.Param("id") // get the ID from the URL

		id, err := strconv.Atoi(idStr)
		if err != nil {
			c.JSON(http.StatusBadRequest, gin.H{
				"error": gin.H{
					"code":    "INVALID_ID",
					"message": "id must be a number",
				},
			})
			return
		}

		lock.Lock()
		defer lock.Unlock()

		for _, task := range tasks {
			if task.ID == id {
				c.JSON(http.StatusOK, task)
				return
			}
		}

		// If we reach here, the task was not found
		c.JSON(http.StatusNotFound, gin.H{
			"error": gin.H{
				"code":    "NOT_FOUND",
				"message": "task not found",
			},
		})
	})

	r.Run(":8081")
}
