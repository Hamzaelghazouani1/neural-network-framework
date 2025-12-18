package ma.enset.glsid.observer;

public class Dashboard implements NetworkObserver {
    @Override
    public void update(String event) {
        System.out.println("[Dashboard] New Event Received: " + event);
    }
}
