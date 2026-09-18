import java.util.Scanner;

public class GradeCalculator {
    public static void main(String [] args){
        Scanner scanner = new Scanner (System.in);

        System.out.println("Enter student score:");
        int score= scanner.nextInt();

        String grade;
        if (score >=90){
            grade= ("A");

        } else if (score>= 80) {
            grade= ("B");

        }else if (score>= 70) {
            grade=("C");
        }else if (score>= 60) {
            grade=("D");

        }else{
            grade=("F");
        }

        System.out.println("Grade:" + grade);

        int total=0;
        int count=0;

        for (int i=0; i <3; i++){
            System.out.println("Enter the score:"+ (i +1)+ "for average:" );
            int s=scanner.nextInt();
            total+= s;
            count+=1;
        }

        double average= (double)total/count;
        System.out.println("Result:"+ average);

    }

}
