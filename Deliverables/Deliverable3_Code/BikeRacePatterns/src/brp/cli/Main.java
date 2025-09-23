package brp.cli;

import java.time.LocalDate;

import brp.app.RaceService;
import brp.domain.Race;
import brp.domain.RaceType;
import brp.domain.Stage;
import brp.obs.EmailObserver;
import brp.obs.InAppObserver;
import brp.obs.NotificationHub;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Bike Racing Registration System — Patterns Demo ===");

        // 1) Composite: build a small Race tree (Race -> Stages -> RaceTypes)
        Race race = new Race("Tour of Texas", LocalDate.now().plusDays(30), true)
            .add(new Stage("Stage 1")
                    .add(new RaceType("Time Trial"))
                    .add(new RaceType("Road Race")))
            .add(new Stage("Stage 2")
                    .add(new RaceType("Criterium")));

        // Show structure (proves Composite)
        race.printDetails("");

        // 2) Observer (+ Singleton): register observers to the NotificationHub
        NotificationHub hub = NotificationHub.getInstance();
        hub.register(new EmailObserver("alice@example.com"));
        hub.register(new InAppObserver("BobRacer"));

        // 3) Integration: service triggers events that the hub broadcasts
        RaceService svc = new RaceService();
        svc.publish(race);  // notifies all observers
        svc.update(race);   // notifies all observers
        svc.cancel(race);   // notifies all observers

        System.out.println("=== End ===");
    }
}

