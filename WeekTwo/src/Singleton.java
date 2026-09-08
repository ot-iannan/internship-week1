public class Singleton {
    public static void main(String[] args) {
        SingletonDemo a = SingletonDemo.getInstance();
        SingletonDemo b = SingletonDemo.getInstance();

        a.setValue(42);
        System.out.println(a == b);   // true, same object
        System.out.println(b.getValue());
    }
}