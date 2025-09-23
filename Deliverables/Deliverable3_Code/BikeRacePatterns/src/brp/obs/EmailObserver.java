package brp.obs;

import brp.domain.Race;

public class EmailObserver implements Observer {
    private final String email;
    public EmailObserver(String email){ this.email = email; }

    @Override
    public void notify(EventType event, Race race, String extra) {
        String raceName = (race == null ? "(no race)" : race.getName());
        System.out.println("[EMAIL → " + email + "] " + event + ": " + raceName + (extra == null ? "" : " | " + extra));
    }
}
