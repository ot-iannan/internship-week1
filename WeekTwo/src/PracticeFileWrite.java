import java.io.FileWriter;
import java.io.IOException;

public class PracticeFileWrite {
    public static void main(String[] args){
        try{
            FileWriter writer= new FileWriter("notes.txt");
            writer.write ("Hello, this is Irene using a saved file");
            writer.close();
            System.out.println("File written successfully");

        }catch(IOException e){
            System.out.println("Error with writing file");
        }
    }
}
