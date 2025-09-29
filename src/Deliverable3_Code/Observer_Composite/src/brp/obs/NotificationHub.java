package brp.obs;

import java.util.ArrayList;
import java.util.List;

import brp.domain.Race;

public final class NotificationHub {
    private static final NotificationHub INSTANCE = new NotificationHub();
    private final List<Observer> observers = new ArrayList<>();

    private NotificationHub(){}

    public static NotificationHub getInstance(){
        return INSTANCE;
    }

    public void register(Observer o){
        if (o != null && !observers.contains(o)) observers.add(o);
    }

    public void unregister(Observer o){
        observers.remove(o);
    }

    public void notifyAllObservers(EventType event, Race race, String extra){
        for (Observer o : observers){
            o.notify(event, race, extra);
        }
    }
    
    public void detectRaceEvent(EventType event, Race race, String extra){
        // (update internal state here if you add any later)
        notifyAllObservers(event, race, extra);
    }
}

