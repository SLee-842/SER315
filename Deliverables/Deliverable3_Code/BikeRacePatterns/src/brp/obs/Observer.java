package brp.obs;

public interface Observer {
	void notify(EventType event, brp.domain.Race race, String extra);
}
