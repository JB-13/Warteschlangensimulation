
package Systeme.MMnVerlustsystem;

import Formulae.ErlangBlock;
import Formulae.Teilnehmer;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

class MyView extends JFrame implements ActionListener {
    private MyModel model;
    private JLabel lRateA = new JLabel("Ankunftsrate");
    private JLabel lRateB = new JLabel("Bedienrate");
    private JLabel lN = new JLabel("Anzahl Bedieneinheiten");
    private JLabel lAccuracy = new JLabel("Abbruch-Genauigkeit");
    private JTextField tRateA = new JTextField("5");
    private JTextField tRateB = new JTextField("10");
    private JTextField tN = new JTextField("3");
    private JTextField tAccuracy = new JTextField("3");
    private JButton start;
    private JLabel outSimulation;
    private JLabel outFormulae;
    private JLabel outAvgParticipants;
    private JLabel outFormulae2;

    public MyView(MyModel m) {
        super("M/M/n - Verlustsystem");
        this.model = m;
        this.setDefaultCloseOperation(3);
        JPanel westPanel = new JPanel(new GridLayout(4, 2));
        westPanel.add(this.lRateA);
        westPanel.add(this.tRateA);
        westPanel.add(this.lRateB);
        westPanel.add(this.tRateB);
        westPanel.add(this.lN);
        westPanel.add(this.tN);
        westPanel.add(this.lAccuracy);
        westPanel.add(this.tAccuracy);
        this.add(westPanel, "West");
        this.start = new JButton("Starten");
        this.add(this.start, "South");
        this.start.addActionListener(this);
        this.outSimulation = new JLabel();
        this.outFormulae = new JLabel();
        this.outAvgParticipants = new JLabel();
        this.outFormulae2 = new JLabel();
        JPanel eastPanel = new JPanel(new GridLayout(0, 1));
        eastPanel.add(this.outSimulation);
        eastPanel.add(this.outFormulae);
        eastPanel.add(this.outAvgParticipants);
        eastPanel.add(this.outFormulae2);
        this.add(eastPanel, "East");
        this.setLocation(300, 250);
        this.setSize(800, 200);
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent evt) {
        this.model.init(Double.parseDouble(this.tRateA.getText()), Double.parseDouble(this.tRateB.getText()), Integer.parseInt(this.tN.getText()), Integer.parseInt(this.tAccuracy.getText()));
        this.model.run();
    }

    public void update() {
        this.outSimulation.setText("Verlust-Wahrscheinlichkeit: " + this.model.getBlockProba());
        this.outSimulation.setFont(new Font("Helvetica", 1, 12));
        double formulaeResult = ErlangBlock.B(this.model.getRateA() / this.model.getRateB(), this.model.getN());
        this.outFormulae.setText("ErlangB-Formel-Wert: " + formulaeResult);
        this.outFormulae.setFont(new Font("Helvetica", 1, 12));
        this.outAvgParticipants.setText("Durchschnittliche Anzahl von Teilnehmern: " + this.model.getAvgParticipants());
        this.outAvgParticipants.setFont(new Font("Helvetica", 1, 12));
        double teilnehmerResult = Teilnehmer.berechneDurchsTeilnehmer(this.model.getRateA(), this.model.getRateB(), this.model.getN());
        this.outFormulae2.setText("Teilnehmer-Formel-Wert: " + teilnehmerResult);
        this.outFormulae2.setFont(new Font("Helvetica", 1, 12));
    }
}
