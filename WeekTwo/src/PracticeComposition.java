public class PracticeComposition {
    public static void main(String[] args){
        Engine engine = new Engine();
        Radio radio= new Radio();
        Car car = new Car(engine,radio);
        car.drive();
    }
}

class Engine{
    void start(){
        System.out.println("Engine starting");
    }
}

class Radio{
    void turnOn(){
        System.out.println("Turning radio on..");
    }
}

class Car {
    private Engine engine;
    private Radio radio;

    public Car(Engine engine,Radio radio){
        this.engine=engine;
        this.radio= radio;
    }

    void drive(){
        engine.start();
        radio.turnOn();
        System.out.println("Car driving");
    }

}