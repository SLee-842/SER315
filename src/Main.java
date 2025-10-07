import Composite.*;
import Singleton.*;
import java.time.LocalDate;

// Simple Racer class for OUR DEMO

class Racer {
    private final String id;
    private final String name;
    private int podiums;
    private int category;

    public Racer(String id, String name, int podiums, int category) {
        this.id = id;
        this.name = name;
        this.podiums = podiums;
        this.category = category;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public int getPodiums() { return podiums; }
    public int getCategory() { return category; }

    public void addPodium() { podiums++; }
    public void upgrade() { category--; } // Lower number = higher rank
}

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Mario Kart - Composite + Singleton DEMO ===\n");

        // --------------------------------------------------------
        // Step 1: Organizer builds the race (Composite pattern)
        // --------------------------------------------------------
        System.out.println("[Step 1] Princess Peach organizes the championship structure...");
        Race race = new Race("Mushroom Cup", LocalDate.now().plusDays(15), true)
                .add(new Stage("Stage 1 - Luigi Circuit")
                        .add(new RaceType("Time Trial"))
                        .add(new RaceType("Grand Prix")))
                .add(new Stage("Stage 2 - Bowser's Castle")
                        .add(new RaceType("Battle Mode")));
        race.printDetails("");
        System.out.println("Peach has finalized the Mushroom Cup event schedule.\n");

        // --------------------------------------------------------
        // Step 2: Organizer enters race results (Singleton DB)
        // --------------------------------------------------------
        System.out.println("[Step 2] Peach records race results into the official race database...");
        DataRepository db = DataRepository.getInstance();
        Racer mario = new Racer("R001", "Mario", 4, 3); // 4 podiums before event

        db.addRace("CUP001", race.getName());
        db.addRacer(mario.getId(), mario.getName());
        db.addResult("CUP001", "Finished: 2nd Place");

        System.out.println("Results recorded! Mario gains another podium for the Mushroom Cup.");
        mario.addPodium();

        // --------------------------------------------------------
        // Step 3: System checks if race is official
        // --------------------------------------------------------
        System.out.println("\n[Step 3] Lakitu verifies the race status...");
        if (!race.isOfficial()) {
            System.out.println("Race is not official. No trophy or rank upgrades allowed.");
            return;
        } else {
            System.out.println("Race confirmed official. Proceeding to rank evaluation.");
        }

        // --------------------------------------------------------
        // Step 4: Evaluate Category Upgrade
        // --------------------------------------------------------
        System.out.println("\n[Step 4] Evaluating Mario's rank in the Mushroom Cup...");
        System.out.println("Checking stats:");
        System.out.println("- Total Podiums: " + mario.getPodiums());
        System.out.println("- Current Rank Tier: " + mario.getCategory());

        if (mario.getPodiums() >= 5) {
            mario.upgrade();
            System.out.println("Result: Mario has achieved enough podiums!");
            System.out.println("Mario levels up to Rank Tier " + mario.getCategory() + "!");
        } else {
            System.out.println("Result: Mario still needs more podium finishes to upgrade.");
        }

        // --------------------------------------------------------
        // Step 5: Notify Racer (simple console message)
        // --------------------------------------------------------
        System.out.println("\n[Step 5] NotificationSystem sends the official notification to the racer..."); // Filler for DEMO, Not implemented
        if (mario.getPodiums() >= 5) {
            System.out.println("Message to Mario:");
            System.out.println("  Congratulations, Mario! You've been promoted to Rank Tier " + mario.getCategory() + ".");
            System.out.println("  Princess Peach will see you at the next cup ceremony!");
        } else {
            System.out.println("Message to Mario:");
            System.out.println("  You have " + mario.getPodiums() + " podiums so far. Keep racing to unlock the next rank!");
        }

        // --------------------------------------------------------
        // Step 6: Repository Snapshot
        // --------------------------------------------------------
        System.out.println("\n[Step 6] Koopa Troopa reviews the central race records...");
        System.out.println("Cup ID: CUP001 -> " + db.getRace("CUP001"));
        System.out.println("Racer ID: R001 -> " + db.getRacer("R001"));
        System.out.println("Results for CUP001 -> " + db.getResult("CUP001"));

        System.out.println("\n=== End of Mario Kart DEMO ===");
    }
}
