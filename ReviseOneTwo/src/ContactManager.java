import java.util.ArrayList;
import java.util.HashMap;


public class ContactManager {
    public static <T> void printAll(ArrayList<T> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }

    public static void main(String[] args){
        ArrayList<Contact> contacts = new ArrayList<>();
        contacts.add(new Contact("Adwoa", "0244134673"));
        contacts.add(new Contact("Phelis", "0937583947"));

        System.out.println("All contacts");
        printAll(contacts);

        HashMap<String,String> people= new HashMap<>();
        people.put("Adwoa", "0244134673");
        people.put("Phelis", "0937583947");

        System.out.println("Looking up Adwoa:" + people.get("Adwoa"));

        ArrayList<String> names = new ArrayList<>();
        names.add("Ama");
        names.add("Kwame");
        printAll(names);


    }




}

class Contact{
    private String name;
    private String phone;

    public Contact (String name, String phone){
        this.name= name;
        this.phone= phone;
    }

    public String getName(){
        return name;
    }

    public String getPhone(){
        return phone;
    }
    public String toString(){
        return name + " - " + phone;
    }

}

