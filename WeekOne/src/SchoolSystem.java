public class SchoolSystem{
    public static void main(String[] args) {
        Person student = new Student("Alice", "Computer Science");
        student.introduce();//Polymorphism: the same method name can be used for different types of objects, and the correct method is called based on the object type

        Person teacher = new Teacher("Bob", 50000);
        teacher.introduce();

        if (teacher instanceof Payable){
            PayrollRunner runner = new PayrollRunner((Payable) teacher);
            runner.run();
        }


    }
}

//Abstraction and Encapsulation
abstract class Person {
    private String name;

    public Person(String name) {
        this.name = name;// constructor: special method to that runs automatically when a new object is created
        //sets object's starting values
        //this.name- take the value that was passed in private name and store it inside my own name box.
    }

    public String getName() {
        return name;
    }

    abstract void introduce();
}


//Inheritance and Encapsulation
class Student extends Person{
    private String course;


    public Student(String name, String course){
        super(name); //super keyword is used to call the constructor of the parent class
        this.course = course;
    }

    void introduce(){
        System.out.println("Hi, my name is " + getName() + " and I am studying " + course);
    }



}

interface Payable{
    void pay();
}

class Teacher extends Person implements Payable{
    private double salary;

    public Teacher(String name, double salary){
        super(name);
        this.salary = salary;

    }

    void introduce(){

        System.out.println("Hi, my name is " + getName() + " and I am a teacher");
    }

    public void pay(){
        System.out.println(getName() + " is paid " + salary);
    }
}

class PayrollRunner {
    private Payable payableItem;

    public PayrollRunner (Payable payableItem){
        this.payableItem= payableItem;

    }

    public void run(){
        payableItem.pay();
    }
}


//S- each class has exactly one job within its scope.
//O- I can make open for extension and even add a new class and it doesnt affect any other class (closed for modification).
//L- subclasses can be used in place of their parent class without breaking the code.
//I- I can create an interface and implement it in multiple classes without affecting the other classes.
//D- PayrollRunner depends only on the Payable interface, not on Teacher directly.
//It can pay any class that implements Payable, without ever being modified.


