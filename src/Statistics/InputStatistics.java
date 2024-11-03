
package Statistics;

import java.util.Scanner;

public class InputStatistics {
    private double lambda;

    public InputStatistics(double lam) {
        this.lambda = lam;
    }

    public double reverseDistribution(double y) {
        return -(Math.log(1.0 - y) / this.lambda);
    }

    public double draw() {
        return this.reverseDistribution(Math.random());
    }

    public static void main(String[] args) {
        System.out.print("Gib die mittlere Bedienzeit ein:   ");
        Scanner in = new Scanner(System.in);
        double mean = in.nextDouble();
        System.out.println();
        InputStatistics generator = new InputStatistics(1.0 / mean);

        for(int i = 1; i < 25; ++i) {
            System.out.println("Der gezogene Wert beträgt: " + generator.draw());
        }

    }
}
