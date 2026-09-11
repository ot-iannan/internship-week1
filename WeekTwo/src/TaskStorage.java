import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskStorage {
    private String filename = "tasks.txt";

    public void save(ArrayList<Task> tasks) {
        try {
            FileWriter writer = new FileWriter(filename);
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                String doneText = task.isDone() ? "true" : "false";
                writer.write(task.getId() + "|" + task.getDescription() + "|" + doneText + "|" + task.getPriority() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Could not save tasks: " + e.getMessage());
        }
    }

    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            File file = new File(filename);
            Scanner reader = new Scanner(file);

            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String[] parts = line.split("\\|");

                if (parts.length == 4) {
                    int id = Integer.parseInt(parts[0]);
                    String description = parts[1];
                    boolean done = parts[2].equals("true");
                    String priority = parts[3];

                    Task task = new Task(id, description, priority);
                    if (done) {
                        task.markComplete();
                    }
                    tasks.add(task);
                }
            }

            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("No saved tasks found. Starting fresh.");
        }

        return tasks;
    }
}