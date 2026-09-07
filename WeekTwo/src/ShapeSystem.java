import java.util.ArrayList;

public class ShapeSystem {
    public static void main (String[] args){
        ArrayList<Shape> shapes= new ArrayList<>();
        shapes.add(new Circle(5));
        shapes.add(new Rectangle(4,8));

        for (int i=0; i<shapes.size(); i++){
            Shape shape = shapes.get(i);
            System.out.println("Area:" + shape.area());
        }
        System.out.println("Using generic utility:");
        printAll(shapes);

        ArrayList<String> names= new ArrayList<>();
        names.add("Ama");
        names.add("Kwame");
        printAll(names);

    }
    public static <T> void printAll (ArrayList<T> list){
        for (int i=0; i <list.size(); i++){
            System.out.println(list.get(i));

        }
    }
}

interface Shape{
    double area();

}

class Circle implements Shape{
    private double radius;

    public Circle(double radius){
        this.radius= radius;
    }

    public double area(){
        return Math.PI * radius *radius;

    }
}

class Rectangle implements Shape{
    private double width;
    private double height;

    public Rectangle(double width, double height){
        this.width= width;
        this.height=height;
    }

    public double area(){
        return width*height;
    }
}