import java.util.concurrent.TimeUnit;

// Enum representing the traffic light signals and their durations
enum LightState {
    RED(5),    // Red light stays on for 5 seconds
    GREEN(5),  // Green light stays on for 5 seconds
    YELLOW(2); // Yellow light stays on for 2 seconds

    private final int durationInSeconds;

    LightState(int durationInSeconds) {
        this.durationInSeconds = durationInSeconds;
    }

    public int getDuration() {
        return durationInSeconds;
    }

    // Method to determine the next light in the sequence
    public LightState getNext() {
        switch (this) {
            case RED: return GREEN;
            case GREEN: return YELLOW;
            case YELLOW: return RED;
            default: return RED;
        }
    }
}

public class TrafficLightSimulator {
    private LightState currentState;

    public TrafficLightSimulator() {
        // Start the traffic light on RED
        this.currentState = LightState.RED;
    }

    public void startSimulation(int cycles) {
        System.out.println("🚦 Starting Traffic Light Simulation...\n");

        for (int i = 1; i <= cycles; i++) {
            System.out.println("--- Cycle " + i + " ---");

            // Print the current status and appropriate action rule
            System.out.print("[" + currentState + "] -> ");
            printAction(currentState);

            // Simulate the passage of time for the light's duration
            try {
                for (int secondsLeft = currentState.getDuration(); secondsLeft > 0; secondsLeft--) {
                    System.out.print(secondsLeft + "... ");
                    TimeUnit.SECONDS.sleep(1);
                }
                System.out.println("\nTime's up!");
            } catch (InterruptedException e) {
                System.out.println("Simulation interrupted.");
                Thread.currentThread().interrupt();
                return;
            }

            // Transition to the next color state
            currentState = currentState.getNext();
            System.out.println();
        }

        System.out.println("🏁 Simulation completed.");
    }

    private void printAction(LightState state) {
        switch (state) {
            case RED:
                System.out.println("❌ STOP! The light is Red.");
                break;
            case YELLOW:
                System.out.println("⚠️ SLOW DOWN / PREPARE TO STOP! The light is Yellow.");
                break;
            case GREEN:
                System.out.println("✅ GO! The light is Green.");
                break;
        }
    }

    public static void main(String[] args) {
        TrafficLightSimulator simulator = new TrafficLightSimulator();

        // Run the simulation for 3 full light cycles
        simulator.startSimulation(3);
    }
}
