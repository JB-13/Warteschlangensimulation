
package Systeme.MMnVerlustsystem;

import MySystem.Service;
import SimulationQueue.Clock;
import SimulationQueue.EventQueue;
import Statistics.InputStatistics;
import Statistics.OutputStatistics;
import Events.VerlustEvents.ArrivalEvent;
import Events.VerlustEvents.DurationEvent;

public class MyModel {
    private MyView view;
    private double rateA;
    private double rateB;
    private int n;
    private int accuracy;
    private InputStatistics genA;
    private InputStatistics genB;
    private Service service;
    private OutputStatistics blockProba;
    private Clock clock;
    private EventQueue queue;

    public MyModel() {
    }

    void addView(MyView v) {
        this.view = v;
    }

    public void init(double rateA, double rateB, int n, int accuracy) {
        this.rateA = rateA;
        this.rateB = rateB;
        this.n = n;
        this.accuracy = accuracy;
    }

    public void run() {
        this.genA = new InputStatistics(this.rateA);
        this.genB = new InputStatistics(this.rateB);
        this.service = new Service(this.n);
        this.blockProba = new OutputStatistics();
        this.clock = new Clock();
        this.queue = new EventQueue();
        new DurationEvent(0.0, this.clock, this.service, 0);
        this.queue.insertEvent(new ArrivalEvent(this.genA.draw(), this.clock, this.queue, this.genA, this.genB, this.blockProba, this.service));

        do {
            for(int rounds = 0; rounds < 10000; ++rounds) {
                this.queue.nextEvent().action();
            }
        } while(!this.blockProba.stop(this.accuracy));

        this.view.update();
    }

    public double getRateA() {
        return this.rateA;
    }

    public double getRateB() {
        return this.rateB;
    }

    public int getN() {
        return this.n;
    }

    public double getBlockProba() {
        return this.blockProba.getBlockR();
    }

    public double getAvgParticipants() {
        return this.blockProba.getAvgParticipants(clock);
    }
}
