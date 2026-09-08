public class SingletonDemo {

    // Holds the single shared instance
    // volatile prevents instruction reordering during object creation,
    // ensuring other threads always see a fully constructed object
    private static volatile SingletonDemo instance;
    private int value=0;
    // Private constructor prevents external instantiation
    private SingletonDemo() {}

    // Global access point to get the Singleton instance
    public static SingletonDemo getInstance() {

        // First check without locking (fast path)
        if (instance == null) {

            // Lock only when the instance might need to be created
            synchronized (SingletonDemo.class) {

                // Second check inside the lock (prevents double creation)
                if (instance == null) {
                    instance = new SingletonDemo();
                }
            }
        }

        return instance;
    }
    public void setValue(int value){
        this.value=value;
    }

    public int getValue(){
        return value;
    }
}