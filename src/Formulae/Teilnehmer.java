
package Formulae;

public class Teilnehmer {
    public Teilnehmer() {
    }

    public static double berechneDurchsTeilnehmer(double rateA, double rateB, int n) {
        double Verkehrslast = rateA / rateB;
        double Blockierungsrate = ErlangBlock.B(Verkehrslast, n);
        return rateA * (1.0 - Blockierungsrate) * (1.0 / rateB);
    }

    public static double berechneDurchsTeilnehmerW(double rateA, double rateB, int n) {
        double belegteBedieneinheiten = rateA / rateB;
        double wartendeAnforderungen = ErlangBlock.C(belegteBedieneinheiten, n) * (belegteBedieneinheiten / ((double)n - belegteBedieneinheiten));
        return belegteBedieneinheiten + wartendeAnforderungen;
    }

    public static double wartendeTeilnehmer(double rateA, double rateB, int n) {
        double belegteBedieneinheiten = rateA / rateB;
        return ErlangBlock.C(belegteBedieneinheiten, n) * (belegteBedieneinheiten / ((double)n - belegteBedieneinheiten));
    }

}
