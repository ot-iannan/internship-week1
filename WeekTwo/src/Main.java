import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        TaskManager manager = new TaskManager();
        TaskStorage storage = new TaskStorage();

        ArrayList<Task> savedTasks = storage.load();
        manager.loadTasks(savedTasks);

        if (args.length == 0) {
            System.out.println("No command provided. Try: help");
            return;
        }

        String command = args[0];

        if (command.equals("help")) {
            System.out.println("Available commands:");
            System.out.println("  add <description> [priority]  - add a new task");
            System.out.println("  list [pending]                 - show tasks");
            System.out.println("  complete <id>                  - mark a task as done");
            System.out.println("  help                           - show this message");

        } else if (command.equals("add")) {
            if (args.length < 2) {
                System.out.println("Missing description. Usage: add <description>");
                return;
            }
            String description = args[1];
            if (description.trim().isEmpty()) {
                System.out.println("Description cannot be empty.");
                return;
            }
            String priority = args.length >= 3 ? args[2] : "normal";
            manager.addTask(description, priority);
            storage.save(manager.getAllTasks());
            System.out.println("Task added: " + description + " [priority: " + priority + "]");

        } else if (command.equals("list")) {
            manager.sortByPriority();
            ArrayList<Task> tasks = manager.getAllTasks();
            boolean onlyPending = args.length >= 2 && args[1].equals("pending");

            if (tasks.size() == 0) {
                System.out.println("No tasks yet.");
            } else {
                for (int i = 0; i < tasks.size(); i++) {
                    Task task = tasks.get(i);
                    if (onlyPending && task.isDone()) {
                        continue;
                    }
                    String status = task.isDone() ? "done" : "not done";
                    System.out.println(task.getId() + ". " + task.getDescription() + " [" + status + ", priority: " + task.getPriority() + "]");
                }
            }

        } else if (command.equals("complete")) {
            if (args.length < 2) {
                System.out.println("Missing task id. Usage: complete <id>");
                return;
            }
            int id;
            try {
                id = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                System.out.println("Invalid id. Please provide a number.");
                return;
            }

            boolean success = manager.completeTask(id);
            if (success) {
                storage.save(manager.getAllTasks());
                System.out.println("Task " + id + " marked as complete.");
            } else {
                System.out.println("No task found with id " + id);
            }

        } else {
            System.out.println("Unknown command: " + command);
            System.out.println("Try: help");
        }
    }
}