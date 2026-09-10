import java.io.FileWriter;
import java.io.IOException;

public class PracticeFileWrite {
    public static void main(String[] args){
        try {
            someMethod();
        } catch (IOException e) {
            System.err.println("error");
        } finally {
            System.err.println("finally");
        }
    }

    static void someMethod() throws IOException {
        FileWriter writer= new FileWriter("notes.txt");
        writer.write ("Hello, this is Irene using a saved file");
        writer.close();
        System.out.println("File written successfully");
    }
}
