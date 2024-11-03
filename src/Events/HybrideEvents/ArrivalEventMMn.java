
package Events.HybrideEvents;

import MySystem.Service;
import SimulationQueue.Clock;
import SimulationQueue.Event;
import SimulationQueue.EventQueue;
import SimulationQueue.EventType;
import Statistics.InputStatistics;
import Statistics.OutputStatisticsH;

public class ArrivalEventMMn extends Event {
    private static EventQueue queue;
    private static InputStatistics genA;
    private static InputStatistics genB;
    private static OutputStatisticsH stats;
    private static Service service;
    private static int maxQueueLength;

    public ArrivalEventMMn(double time, Clock c, EventQueue q, InputStatistics inA, InputStatistics inB, OutputStatisticsH out, Service s, int maxQueueLength) {
        super(time, c);
        queue = q;
        genA = inA;
        genB = inB;
        stats = out;
        service = s;
        ArrivalEventMMn.maxQueueLength = maxQueueLength;
    }

    public EventType type() {
        return EventType.ARRIVAL;
    }

    public void action() {
        super.action();
        int numStat = service.reserve();
        double newBorn;

        //Keine Bedieneinheit frei
        if (numStat == -1) {
            stats.update(1.0);

            //Warteschlangenlänge überschritten
            if (!queue.QueueLengthMax(maxQueueLength)) {
                stats.WarteschlangeÜberschritten(); //Anforderung hätte Warteschlange überschritten
                queue.addToWaitingQueue(this);

            //Warteschlangenlänge ausreichend
            } else {
                queue.addToWaitingQueue(this);
            }
        //Bedieneinheit frei
        } else{
            stats.update (0.0);

            newBorn = genB.draw() + clock.getClock(); //Generierung der Bearbeitungszeit
            queue.insertEvent(new DurationEventMMn(newBorn, clock, service, numStat, queue, stats, genB)); //DurationEvent in Ereigniswarteschlange

            stats.setServicezeit(newBorn-clock.getClock()); //Servicezeit erhöhen
        }

        newBorn = genA.draw() + clock.getClock(); //Generierung der Bearbeitungszeit
        queue.insertEvent(new ArrivalEventMMn(newBorn, clock, queue, genA, genB, stats, service, maxQueueLength)); //ArrivalEvent in Ereigniswarteschlange
    }
}
