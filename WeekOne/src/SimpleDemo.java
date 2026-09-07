// 1. THE CLASS (The Room/Object we want to control)
class HotelGymKey {

    // This variable stores our ONE and ONLY key card
    private static HotelGymKey singleKeyCard = null;

    // A private constructor locks the door so NO ONE can make a new gym
    private HotelGymKey() {
        System.out.println("[SYSTEM]: Building the gym and making the FIRST key card.");
    }

    // This is the Global Desk Method where you ask for the card
    public static HotelGymKey getTheKey() {

        // If we don't have a key card yet, make one!
        if (singleKeyCard == null) {
            singleKeyCard = new HotelGymKey();
        }

        // Hand over the key card
        return singleKeyCard;
    }

    // A simple action anyone can do once they have the key
    public void openGymDoor() {
        System.out.println("[ACTION]: Gym door unlocked! You can now work out.");
    }
}

// 2. THE STORY (Running the code)
public class SimpleDemo {
    public static void main(String[] args) {

        System.out.println("Guest 1 walks up to the front desk ");
        HotelGymKey guest1Key = HotelGymKey.getTheKey();
        guest1Key.openGymDoor();

        System.out.println("\n Guest 2 walks up to the front desk");
        HotelGymKey guest2Key = HotelGymKey.getTheKey();
        guest2Key.openGymDoor();

        System.out.println("\n The Truth Check ");
        if (guest1Key == guest2Key) {
            System.out.println("[PROVED]: Guest 1 and Guest 2 are holding the EXACT SAME key card!");
        }
    }
}

//The Lock: Point out that private HotelGymKey() means the hotel door is locked.
// No guest can use the new keyword to magically create a new gym out of thin air.
// Guest 1 (The First Call): When Guest 1 asks for the key, the system realizes singleKeyCard == null (meaning the counter is empty).
// It prints "Building the gym..." because it has to set everything up for the first time.
// Guest 2 (The Reuse): When Guest 2 asks for the key, notice how the text "Building the gym..." does not print again.
// The desk clerk checks, sees the key card already exists, and hands Guest 2 the exact same card Guest 1 used.

//Thread-Safety. If this app runs in a multi-threaded environment and two separate threads make that 'First Call' at the exact same millisecond, they will both see that the instance is missing.
// They will both run the creation step, creating two copies and silently breaking our Singleton guarantee.
// To fix this, we implement Double-Checked Locking. This ensures that only one thread can enter the creation block at a time, protecting our architecture."