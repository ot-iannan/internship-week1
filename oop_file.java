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

