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
}
