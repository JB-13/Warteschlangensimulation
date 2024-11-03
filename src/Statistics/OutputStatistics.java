
package Statistics;

import SimulationQueue.Clock;

public class OutputStatistics {
    private double n;
    private double sum;
    private double square;
    private double Servicezeit;

    public OutputStatistics() {
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


    //Servicezeit erhöhen
    public void setServicezeit(double time) {
        Servicezeit += time;
    }


    public double getBlockR() {
        return this.sum / this.n;
    }

    public double getAvgParticipants(Clock clock) {
        return Servicezeit / clock.getClock();
    }
}
