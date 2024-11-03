
package Events.VerlustEvents;

import MySystem.Service;
import SimulationQueue.Clock;
import SimulationQueue.Event;
import SimulationQueue.EventQueue;
import SimulationQueue.EventType;
import Statistics.InputStatistics;
import Statistics.OutputStatistics;

public class ArrivalEvent extends Event {
    private static EventQueue queue;
    private static InputStatistics genA;
    private static InputStatistics genB;
    private static OutputStatistics blockStat;
    private static Service service;

    public ArrivalEvent(double time, Clock c, EventQueue q, InputStatistics inA, InputStatistics inB, OutputStatistics out, Service s) {
        super(time, c);
        queue = q;
        genA = inA;
        genB = inB;
        blockStat = out;
        service = s;
    }

    public ArrivalEvent(double time) {
        super(time);
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
            blockStat.update(1.0);

        //Bedieneinheit frei
        } else {
            blockStat.update(0.0);
            newBorn = genB.draw() + clock.getClock(); //Generierung der Bearbeitungszeit
            queue.insertEvent(new DurationEvent(newBorn, numStat)); //DurationEvent in Ereigniswarteschlange

            blockStat.setServicezeit(newBorn-clock.getClock()); //Servicezeit erhöhen
        }

        newBorn = genA.draw() + clock.getClock(); //Generierung der Bearbeitungszeit
        queue.insertEvent(new ArrivalEvent(newBorn)); //ArrivalEvent in Ereigniswarteschlange
    }
}
