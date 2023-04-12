public class EventListener extends Thread{

    private String messageToListenFor;
    private String messageToReplyWith;
    private Tracker eventTracker;

    public EventListener(String message, String reply) {
        this.messageToListenFor = message;
        this.messageToReplyWith = reply;
        this.eventTracker = EventTracker.getInstance();
    }

    public EventListener(String message, String reply, Tracker tracker) {
        this.messageToListenFor = message;
        this.messageToReplyWith = reply;
        this.eventTracker = tracker;
    }

    public void run() {
//        In the while loop, if should reply returns true,
//        the Tracker should have its "handle" method called
//        passing in an instance of EventHandler.
//        The Handler should print out the reply
        while (!readyToQuit()) {
            if (shouldReply()) {
                reply();
            }
        }
    }

    public Boolean readyToQuit() {
        return eventTracker.has("quit");
    }

    public Boolean shouldReply() {
        return eventTracker.has(messageToListenFor);
    }

    public void reply() {
        eventTracker.handle(messageToListenFor, () -> {
            System.out.println(messageToReplyWith);
        });
    }
}