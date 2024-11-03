
package Systeme.MMnHybridsystem;

import Events.HybrideEvents.ArrivalEventMMn;
import Events.HybrideEvents.DurationEventMMn;
import MySystem.Service;
import SimulationQueue.Clock;
import SimulationQueue.EventQueue;
import Statistics.InputStatistics;
import Statistics.OutputStatisticsH;

public class MyModel {
    private MyView view;
    private double rateA;
    private double rateB;
    private int n;
    private int accuracy;
    private int maxqueuelength;
    private InputStatistics genA;
    private InputStatistics genB;
    private Service service;
    private OutputStatisticsH warteProba;
    private Clock clock;
    private EventQueue queue;

    public MyModel() {
    }

    void addView(MyView v) {
        this.view = v;
    }

    public void init(double rateA, double rateB, int n, int accuracy, int maxqueuelength) {
        this.rateA = rateA;
        this.rateB = rateB;
        this.n = n;
        this.accuracy = accuracy;
        this.maxqueuelength = maxqueuelength;
    }

    public void run() {
        this.genA = new InputStatistics(this.rateA);
        this.genB = new InputStatistics(this.rateB);
        this.service = new Service(this.n);
        this.warteProba = new OutputStatisticsH();
        this.clock = new Clock();
        this.queue = new EventQueue();
        new DurationEventMMn(0.0, this.clock, this.service, 0, this.queue, this.warteProba, this.genB);
        this.queue.insertEvent(new ArrivalEventMMn(this.genA.draw(), this.clock, this.queue, this.genA, this.genB, this.warteProba, this.service, this.maxqueuelength));

        do {
            for(int rounds = 0; rounds < 10000; ++rounds) {
                this.queue.nextEvent().action();
            }
        } while(!this.warteProba.stop(this.accuracy));

        this.view.update();
    }

    public double getRateA() {
        return this.rateA;
    }

    public double getRateB() {
        return this.rateB;
    }

    public int getn() {
        return this.n;
    }

    public double getPQueueLengthExceeded() {
        return this.warteProba.PWarteschlangeÜberschritten();
    }

    public double getPWarte() {
        return this.warteProba.PWarte();
    }

    public double getN() {
        return this.warteProba.N(clock);
    }

    public double getNBE() {
        return this.warteProba.NBE(clock);
    }

    public double getNQueue() {
        return this.warteProba.NQueue(clock);
    }

    public double getT() {
        return this.warteProba.T();
    }

    public double getTBE() {
        return this.warteProba.TBE();
    }

    public double getTQueue() {
        return this.warteProba.TQueue();
    }

    public double getTOnlyQueue() {
        return this.warteProba.TOnlyQueue();
    }
}
