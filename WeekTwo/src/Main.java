public class Main {
    public static void main(String[] args) {
        if (args.length == 0){
            System.out.println("No command provided. Try: help");
            return;
        }
        String command = args [0];
        if (command.equals ("help")){
            System.out.println("Available commands:");
            System.out.println(" add <description> -add a new task");
            System.out.println("list               -show all tasks");
            System.out.println("complete <id>      - mark a task as done");
            System.out.println(" help              - show this message");

        } else if (command.equals ("add")){
            if (args.length<2){
                System.out.println("Missing description. Usage: add <description>");
                return;
            }
            String description = args[1];
            System.out.println("Task added:" + description);
        } else if (command.equals("list")){
            System.out.println("Listing all tasks... (not connected to storage yet)");

        } else if (command.equals ("complete")){
            if (args.length <2){
                System.out.println("Missing task id. Usage: complete <id>");
                return;
            }
            String id = args[1];
            System.out.println("Marking task" + id+ " as complete...(not connected to storage yet");

        }
        else {
            System.out.println("Unknown command:" + command);
            System.out.println("Try: help");
        }
    }
}