import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PracticeStructuredRead {
    public static void main(String[] args){
        try(Scanner reader = new Scanner(new File("tasks.txt"))){ //try-with-resources (using try with parenthesis) tells Java to automatically close it when the block ends.
            // You'll notice reader.close(); is gone — you don't write it yourself anymore, Java handles it.
            // That's the entire difference; everything else stays the same.
            //File file = new File("tasks.txt");
            //Scanner reader = new Scanner(file);

            while (reader.hasNextLine()){
                String line = reader.nextLine();
                String[] parts= line.split("\\|");

                if (parts.length ==3){
                    System.out.println(line);

                } else {
                    System.out.println("Skipping bad line:" +line);
                }

            }
            //reader.close();


        }catch (FileNotFoundException e){
            System.out.println("tasks.txt not found");
        }
    }

}
