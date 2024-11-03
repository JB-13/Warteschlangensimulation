
package Statistics;

import SimulationQueue.Clock;

public class OutputStatisticsH {
    private double n, sum, square, Servicezeit,
            Wartezeit, QueueAnforderung,
            ServiceAnforderung, WarteschlangenBlock;

    public OutputStatisticsH() {
    }

    public void update(double sample) {
        ++this.n;
        this.sum += sample;
        this.square += Math.pow(sample, 2.0);
    }

    public boolean stop(int accuracy) {
        double singleVariance = this.square / this.n - Math.pow(this.sum / this.n, 2.0);
        double totalVariance = 1.0 / this.n * singleVariance;
        double standardDeviation = Math.sqrt(totalVariance);
        return standardDeviation < Math.pow(10.0, (-accuracy));
    }


    //Wartezeit erhöhen
    public void setWartezeit(double zeit) {
        this.Wartezeit += zeit;
        this.QueueAnforderung++;
    }

    //Servicezeit erhöhen
    public void setServicezeit(double zeit) {
        this.Servicezeit += zeit;
        this.ServiceAnforderung++;
    }

    //Überschritten counter
    public void WarteschlangeÜberschritten() {
        ++this.WarteschlangenBlock;
    }



    public double PWarteschlangeÜberschritten() {
        return this.WarteschlangenBlock / this.n;
    }

    public double PBlock() {
        return this.WarteschlangenBlock / this.n;
    }

    public double PWarte() {
        return QueueAnforderung / n;
    }

    public double N(Clock clock) {
        return this.NQueue(clock) + this.NBE(clock);
    }

    public double NBE(Clock clock) {
        return Servicezeit / clock.getClock();
    }

    public double NQueue(Clock clock) {
        return Wartezeit / clock.getClock();
    }

    public double T() {
        return TBE() + this.TQueue();
    }

    public double TBE() {
        return Servicezeit / ServiceAnforderung;
    }

    public double TQueue() {
        return Wartezeit / n;
    }

    public double TOnlyQueue() {
        return Wartezeit / QueueAnforderung;
    }
}
