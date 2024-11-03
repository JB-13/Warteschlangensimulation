
package Events.VerlustEvents;

import MySystem.Service;
import SimulationQueue.Clock;
import SimulationQueue.Event;
import SimulationQueue.EventType;

public class DurationEvent extends Event {
    private static Service service;
    private int ressourceId;

    public DurationEvent(double time, Clock c, Service s, int position) {
        super(time, c);
        service = s;
        this.ressourceId = position;
    }

    public DurationEvent(double time, int position) {
        super(time);
        this.ressourceId = position;
    }

    public EventType type() {
        return EventType.DURATION;
    }

    public void action() {
        super.action();
        service.free(this.ressourceId);
    }
}
