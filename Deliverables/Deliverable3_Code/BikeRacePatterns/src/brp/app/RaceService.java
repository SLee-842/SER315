package brp.app;

import brp.domain.Race;
import brp.obs.EventType;
import brp.obs.NotificationHub;

public class RaceService {
    private final NotificationHub hub = NotificationHub.getInstance();

    public void publish(Race race){
        //persist/validate would go here
        hub.detectRaceEvent(EventType.PUBLISHED, race, null);
    }

    public void update(Race race){
        hub.detectRaceEvent(EventType.UPDATED, race, null);
    }

    public void cancel(Race race){
        hub.detectRaceEvent(EventType.CANCELED, race, null);
    }

    public void notifyUpgrade(String racerName, Race race){
        hub.detectRaceEvent(EventType.RACER_UPGRADED, race, "Racer: " + racerName);
    }
}

