import java.io.FileWriter;
import java.io.IOException;

public class PracticeStructuredWrite {
    public static void main(String[] args){
        try{
            FileWriter writer= new FileWriter("tasks.txt");
            writer.write("1|Finish work| false\n");  //structured local data into 3 distinct pieces:( id, description, status)
            writer.write("2|Buy stuff| true\n");
            writer.close();
            System.out.println("Tasks saved successfully");

        }catch (IOException e){
            System.out.println("Error in saving file");
        }
    }
}
