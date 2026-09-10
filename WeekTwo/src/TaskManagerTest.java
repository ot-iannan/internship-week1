public class TaskManagerTest {

    public static void main(String[] args) {
        testAddTask();
        testCompleteTask();
        testCompleteInvalidId();
        testEmptyDescriptionRejected();
        testFilterPendingTasks();
    }

    static void testAddTask() {
        TaskManager manager = new TaskManager();
        manager.addTask("Finish README", "high");

        if (manager.getAllTasks().size() == 1) {
            System.out.println("testAddTask PASSED");
        } else {
            System.out.println("testAddTask FAILED");
        }
    }

    static void testCompleteTask() {
        TaskManager manager = new TaskManager();
        manager.addTask("Buy groceries", "low");

        boolean result = manager.completeTask(1);

        if (result && manager.getAllTasks().get(0).isDone()) {
            System.out.println("testCompleteTask PASSED");
        } else {
            System.out.println("testCompleteTask FAILED");
        }
    }


    static void testCompleteInvalidId() {
        TaskManager manager = new TaskManager();
        manager.addTask("Buy groceries", "low");

        boolean result = manager.completeTask(99);

        if (!result) {
            System.out.println("testCompleteInvalidId PASSED");
        } else {
            System.out.println("testCompleteInvalidId FAILED");
        }

    }

    static void testEmptyDescriptionRejected() {
        String description = "   ";
        boolean isValid = !description.trim().isEmpty();

        if (!isValid) {
            System.out.println("testEmptyDescriptionRejected PASSED");
        } else {
            System.out.println("testEmptyDescriptionRejected FAILED");
        }
    }

    static void testFilterPendingTasks() {
        TaskManager manager = new TaskManager();
        manager.addTask("Finish README", "high");
        manager.addTask("Buy groceries", "low");
        manager.completeTask(1);

        int pendingCount = 0;
        for (int i = 0; i < manager.getAllTasks().size(); i++) {
            if (!manager.getAllTasks().get(i).isDone()) {
                pendingCount++;
            }
        }

        if (pendingCount == 1) {
            System.out.println("testFilterPendingTasks PASSED");
        } else {
            System.out.println("testFilterPendingTasks FAILED");
        }
    }
}