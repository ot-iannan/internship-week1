import java.util.Scanner;

public class variables {
    public static void main(String[] args) {
        int myNum = 5+ 6;               // Integer (whole number)
        float myFloatNum = 5.99f *2;   // Floating point number
        char myLetter = 'D';         // Character
        boolean myBool = true;       // Boolean
        String myText = "Hello"; 

        if (myNum > 20){
            System.out.println("myNum is greater than 20");
        } else {
            System.out.println("myNum is less than or equal to 20");
        }

        System.out.println(myNum + ", " + myFloatNum + ", " + myLetter + ", " + myBool + ", " + myText);    // String
        Scanner scanner = new Scanner(System.in); 
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();   
        System.out.println("Hello, " + name + "!");
        

        int num=7;

        //String answer= (num >=9) ? "Young man" : "child";
        //System.out.println(answer);

        
    }
}
