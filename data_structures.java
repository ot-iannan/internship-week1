import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class data_structures {
    public static void main (String[] args){
        String[] cars = {"Volvo", "BMW", "Ford", "Mazda"};
        System.out.println(cars[0]); // Fixed size list that holds multiple values of the same type only

        ArrayList<String> cars2= new ArrayList<>();
        cars2.add("Volvo");
        cars2.add("BMW");
        cars2.add("Ford");

        System.out.println(cars2); //Dynamic size list that holds multiple values of the same type only (ArrayList)
        System.out.println(cars2.get(0)); //Accessing the first element of the ArrayList

        HashMap<String, Integer> myNumbers = new HashMap<>();
        myNumbers.put("Volvo", 3);
        myNumbers.put("BMW", 5);
        System.out.println(myNumbers); //Dynamic size list that holds multiple values of different types (HashMap)
        System.out.println(myNumbers.get("Volvo")); //Accessing the value of the key "Volvo" in the HashMap
        System.out.println(myNumbers.get("BMW")); //Accessing the value of the key "BMW" in the HashMap 

        HashSet<String> mySet = new HashSet<>();
        mySet.add("Volvo");
        mySet.add("BMW");
        mySet.add("Volvo"); //Duplicate values are not allowed in HashSet
        System.out.println(mySet); //Dynamic size list that holds multiple values of the same type only (HashSet)

    }



}

