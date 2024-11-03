package Events.WarteschlangenEvents;

import MySystem.Service;
import SimulationQueue.Clock;
import SimulationQueue.Event;
import SimulationQueue.EventQueue;
import SimulationQueue.EventType;
import Statistics.InputStatistics;
import Statistics.OutputStatisticsW;

public class DurationEventW extends Event {
    private static Service service;
    private int resourceId;
    private static EventQueue queue;
    private static OutputStatisticsW stats;
    private static InputStatistics genB;

    public DurationEventW(double time, Clock c, Service s, int position, EventQueue q, OutputStatisticsW out, InputStatistics inB) {
        super(time, c);
        service = s;
        this.resourceId = position;
        queue = q;
        stats = out;
        genB = inB;
    }

    public DurationEventW(double time, int position) {
        super(time);
        this.resourceId = position;
    }

    public EventType type() {
        return EventType.DURATION;
    }

    public void action() {
        super.action();
        service.free(this.resourceId);

        // Überprüfen ob Anforderungen in Warteschlange sind
        if (!queue.isWaitingQueueEmpty()) {
            service.reserve(resourceId); //Bedieneinheit reservieren

            Event nextEvent = queue.pollNextWaitingEvent(); //Nächste Anforderung aus Warteschlange ziehen
            double newFinished = genB.draw() + clock.getClock();
            queue.insertEvent(new DurationEventW(newFinished, clock, service, resourceId, queue, stats, genB));

            stats.setWartezeit(clock.getClock()-nextEvent.getActionTime()); //Queuezeit erhöhen
            stats.setServicezeit(newFinished-clock.getClock()); //Servicezeit erhöhen

        }
    }
}
