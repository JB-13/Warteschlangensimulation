
package SimulationQueue;

public abstract class Event {
    protected double actionTime;
    protected static Clock clock;

    public Event(double time, Clock clo) {
        this.actionTime = time;
        clock = clo;
    }

    public Event(double time) {
        this.actionTime = time;
    }

    public void action() {
        clock.setClock(this.actionTime);
    }

    public double getActionTime() {
        return this.actionTime;
    }

    public abstract EventType type();

    public int compareTo(Event other) {
        return Double.compare(this.actionTime, other.actionTime);
    }
}
