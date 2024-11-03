
package Systeme.MMnWarteschlangensystem;

import MySystem.Service;
import SimulationQueue.Clock;
import SimulationQueue.EventQueue;
import Statistics.InputStatistics;
import Statistics.OutputStatisticsW;
import Events.WarteschlangenEvents.ArrivalEventW;
import Events.WarteschlangenEvents.DurationEventW;

public class MyModel {
    private MyView view;
    private double rateA;
    private double rateB;
    private int n;
    private int accuracy;
    private InputStatistics genA;
    private InputStatistics genB;
    private Service service;
    private OutputStatisticsW warteProba;
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
        this.warteProba = new OutputStatisticsW();
        this.clock = new Clock();
        this.queue = new EventQueue();
        new DurationEventW(0.0, this.clock, this.service, 0, this.queue, this.warteProba, this.genB);
        this.queue.insertEvent(new ArrivalEventW(this.genA.draw(), this.clock, this.queue, this.genA, this.genB, this.warteProba, this.service));

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
