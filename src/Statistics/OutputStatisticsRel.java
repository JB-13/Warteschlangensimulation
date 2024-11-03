//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package Statistics;

public class OutputStatisticsRel implements OutStat {
    private double n;
    private double sumRel;
    private double squareRel;

    public OutputStatisticsRel() {
    }

    public void update(double sample) {
        ++this.n;
        if (this.n == 1.0) {
            this.sumRel = sample;
            this.squareRel = Math.pow(sample, 2.0);
        } else {
            double factor = (this.n - 1.0) / this.n;
            this.sumRel = factor * this.sumRel + sample / this.n;
            this.squareRel = factor * this.squareRel + Math.pow(sample, 2.0) / this.n;
        }
    }

    public boolean stop(int accuracy) {
        double totalVariance = 1.0 / this.n * (this.squareRel - Math.pow(this.sumRel, 2.0));
        double standardDeviation = Math.sqrt(totalVariance);
        return standardDeviation < Math.pow(10.0, (double)(-accuracy));
    }

    public void print() {
        System.out.println("Die Blockierungsrate beträgt: " + this.sumRel);
    }
}
