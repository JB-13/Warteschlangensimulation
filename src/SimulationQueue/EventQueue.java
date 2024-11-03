package SimulationQueue;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class EventQueue {
    private List<Event> queue = new LinkedList<>();
    private Queue<Event> waitingQueue = new LinkedList<>(); //Warteschlange

    public EventQueue() {
    }

    public Event nextEvent() {
        return this.queue.remove(this.queue.size() - 1);
    }

    public void insertEvent(Event e) {
        for (int i = 0; i < this.queue.size(); ++i) {
            if (e.getActionTime() >= this.queue.get(i).getActionTime()) {
                this.queue.add(i, e);
                return;
            }
        }
        this.queue.add(this.queue.size(), e);
    }

    // Überprüfen ob die Warteschlange leer ist
    public boolean isWaitingQueueEmpty() {
        return waitingQueue.isEmpty();
    }

    // Hinzufügen eines Events zur Warteschlange
    public void addToWaitingQueue(Event e) {
        waitingQueue.add(e);
    }

    // Entfernen und Zurückgeben des nächsten Events aus der Warteschlange
    public Event pollNextWaitingEvent() {
        return waitingQueue.poll();
    }

    // Maximale Warteschlangenlänge überprüfen
    public boolean QueueLengthMax(int queueLimit) {
        return waitingQueue.size() < queueLimit;
    }

}
