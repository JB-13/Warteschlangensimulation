
package Formulae;

public class Zeit {
    public Zeit() {
    }

    public static double T(double rateA, double rateB, int n) {
        return TQueue(rateA, rateB, n) + 1.0 / rateB;
    }

    public static double TQueue(double rateA, double rateB, int n) {
        return Teilnehmer.wartendeTeilnehmer(rateA, rateB, n) / rateA;
    }

    public static double TOnlyQueue(double rateA, double rateB, int n) {
        return (1.0 / rateA) * ((rateA / rateB) / ((double)n - (rateA / rateB)));
    }
}
