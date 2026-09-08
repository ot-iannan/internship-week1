public class Task {
    private int id;
    private String description;
    private boolean done;
    private String priority;

    public Task(int id,String description, String priority){
        this.id=id;
        this.description=description;
        this.done=false;
        this.priority=priority;
    }

    public int getId(){
        return id;
    }

    public String getDescription(){
        return description;
    }

    public boolean isDone(){
        return done;
    }

    public String getPriority() {
        return priority;
    }

    public void markComplete(){
        done=true;
    }
}
