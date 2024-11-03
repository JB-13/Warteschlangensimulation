package Formulae;

public class ErlangBlock {
    public ErlangBlock() {
    }

    public static double B(double Verkehrslast, int Bedieneinheiten) {
        double nenner = Math.pow(Verkehrslast, Bedieneinheiten) / factorial(Bedieneinheiten);
        double zähler = 0.0;

        for(int k = 0; k <= Bedieneinheiten; ++k) {
            zähler += Math.pow(Verkehrslast, k) / factorial(k);
        }

        return nenner / zähler;
    }

    public static double C(double Verkehrslast, int Bedieneinheiten) {
        double nenner = Math.pow(Verkehrslast, Bedieneinheiten) / factorial(Bedieneinheiten) / (1.0 - Verkehrslast / (double)Bedieneinheiten);
        double zähler = 0.0;

        for(int k = 0; k < Bedieneinheiten; ++k) {
            zähler += Math.pow(Verkehrslast, k) / factorial(k);
        }

        zähler += nenner;
        return nenner / zähler;
    }

    private static double factorial(int n) {
        if (n != 0 && n != 1) {
            double result = 1.0;

            for(int i = 2; i <= n; ++i) {
                result *= i;
            }

            return result;
        } else {
            return 1.0;
        }
    }
}
