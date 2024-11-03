package Events.WarteschlangenEvents;

import MySystem.Service;
import SimulationQueue.Clock;
import SimulationQueue.Event;
import SimulationQueue.EventQueue;
import SimulationQueue.EventType;
import Statistics.InputStatistics;
import Statistics.OutputStatisticsW;

public class ArrivalEventW extends Event {
    private static EventQueue queue;
    private static InputStatistics genA;
    private static InputStatistics genB;
    private static OutputStatisticsW stats;
    private static Service service;

    public ArrivalEventW(double time, Clock c, EventQueue q, InputStatistics inA, InputStatistics inB, OutputStatisticsW out, Service s) {
        super(time, c);
        queue = q;
        genA = inA;
        genB = inB;
        stats = out;
        service = s;
    }

    public EventType type() {
        return EventType.ARRIVAL;
    }

    public void action() {
        super.action();
        int numStat = service.reserve();

        // Keine Bedieneinheit frei, Anforderung zur Warteschlange hinzufügen
        if (numStat == -1) {
            stats.update (1.0);
            queue.addToWaitingQueue(this);

        // Bedieneinheit frei, Anforderung sofort bedienen
        } else {
            stats.update (0.0);
            double newFinished = genB.draw() + clock.getClock(); //Generierung der Bearbeitungszeit
            queue.insertEvent(new DurationEventW(newFinished, clock, service, numStat, queue, stats, genB)); //DurationEvent in Ereigniswarteschlange

            stats.setServicezeit(newFinished-clock.getClock()); //Servicezeit erhöhen
        }

        double newArrival = genA.draw() + clock.getClock(); //Generierung der Bearbeitungszeit
        queue.insertEvent(new ArrivalEventW(newArrival, clock, queue, genA, genB, stats, service)); //ArrivalEvent in Ereigniswarteschlange
    }
}

