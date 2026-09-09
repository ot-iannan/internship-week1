public class PracticeUncheckedException {
    public static void main(String[] args){
        try{
            int number= Integer.parseInt("banana");
            System.out.println("Number is:" +number);

        }catch (NumberFormatException e){
            System.out.println("Not a valid number. Try again");
        }
    }
}
//Unlike FileNotFoundException, Java doesnt force you to catch this.
//Code will still compile.
//Hence what makes it unchecked.