import java.lang.reflect.Array;
import java.util.ArrayList;

public class TaskManager {
    private ArrayList<Task> tasks= new ArrayList<>();
    private int nextId=1;

    public void addTask(String description, String priority){
        Task task = new Task (nextId, description,priority);
        tasks.add(task);
        nextId++;
    }

    public ArrayList<Task> getAllTasks(){
        return tasks;
    }

    public boolean completeTask(int id){
        for (int i=0; i<tasks.size(); i++){
            Task task = tasks.get(i);
            if (task.getId() == id){
                task.markComplete();
                return true;

            }
        }
        return false;
    }
    public void loadTasks(ArrayList<Task> loadedTasks) {
        tasks = loadedTasks;
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getId() >= nextId) {
                nextId = tasks.get(i).getId() + 1;
            }
        }
    }

    public void sortByPriority() {
        for (int i = 0; i < tasks.size(); i++) {
            for (int j = 0; j < tasks.size() - 1 - i; j++) {
                if (tasks.get(j).getPriority().compareTo(tasks.get(j + 1).getPriority()) > 0) {
                    Task temp = tasks.get(j);
                    tasks.set(j, tasks.get(j + 1));
                    tasks.set(j + 1, temp);
                }
            }
        }
    }
}
