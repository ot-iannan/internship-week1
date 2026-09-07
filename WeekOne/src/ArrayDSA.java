//given an array of numbers, find the largest one
public class ArrayDSA {
    static int findMax (int[] numbers){
        int max = numbers[0];
        for (int i=1; i< numbers.length; i++){
            if (numbers[i]>max){
                max= numbers[i];
            }
        }
        return max;
    }

    public static void main(String[] args){
        int[] testData = {4, 8, 15, 16, 23, 42, 3};
        int result= findMax (testData);
        System.out.println ("Max number:" + result);


        if (result == 42){  //test if result is true

            System.out.println("Test passed");
        } else{
            System.out.println("Test failed");
        }
    }

}
