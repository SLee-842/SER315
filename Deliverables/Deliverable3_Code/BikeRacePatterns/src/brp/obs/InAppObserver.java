package brp.obs;

import brp.domain.Race;

public class InAppObserver implements Observer {
    private final String userName;
    public InAppObserver(String userName){ this.userName = userName; }

    @Override
    public void notify(EventType event, Race race, String extra) {
        String raceName = (race == null ? "(no race)" : race.getName());
        System.out.println("[IN-APP @" + userName + "] " + event + ": " + raceName + (extra == null ? "" : " | " + extra));
    }
}
