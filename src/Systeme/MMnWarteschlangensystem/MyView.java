
package Systeme.MMnWarteschlangensystem;

import Formulae.ErlangBlock;
import Formulae.Teilnehmer;
import Formulae.Zeit;
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
    private JLabel outSimulation1;
    private JLabel outSimulation2;
    private JLabel outSimulation3;
    private JLabel outSimulation4;
    private JLabel outSimulation5;
    private JLabel outSimulation6;
    private JLabel outSimulation7;
    private JLabel outSimulation8;
    private JLabel outFormulae1;
    private JLabel outFormulae2;
    private JLabel outFormulae3;
    private JLabel outFormulae4;
    private JLabel outFormulae5;
    private JLabel outFormulae6;
    private JLabel outFormulae7;
    private JLabel outFormulae8;

    public MyView(MyModel m) {
        super("M/M/n - Warteschlangensystem");
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
        this.outSimulation1 = new JLabel();
        this.outFormulae1 = new JLabel();
        this.outSimulation2 = new JLabel();
        this.outFormulae2 = new JLabel();
        this.outSimulation3 = new JLabel();
        this.outFormulae3 = new JLabel();
        this.outSimulation4 = new JLabel();
        this.outFormulae4 = new JLabel();
        this.outSimulation5 = new JLabel();
        this.outFormulae5 = new JLabel();
        this.outSimulation6 = new JLabel();
        this.outFormulae6 = new JLabel();
        this.outSimulation7 = new JLabel();
        this.outFormulae7 = new JLabel();
        this.outSimulation8 = new JLabel();
        this.outFormulae8 = new JLabel();
        JPanel eastPanel = new JPanel(new GridLayout(0, 1));
        eastPanel.add(this.outSimulation1);
        eastPanel.add(this.outFormulae1);
        eastPanel.add(this.outSimulation2);
        eastPanel.add(this.outFormulae2);
        eastPanel.add(this.outSimulation3);
        eastPanel.add(this.outFormulae3);
        eastPanel.add(this.outSimulation4);
        eastPanel.add(this.outFormulae4);
        eastPanel.add(this.outSimulation5);
        eastPanel.add(this.outFormulae5);
        eastPanel.add(this.outSimulation6);
        eastPanel.add(this.outFormulae6);
        eastPanel.add(this.outSimulation7);
        eastPanel.add(this.outFormulae7);
        eastPanel.add(this.outSimulation8);
        eastPanel.add(this.outFormulae8);
        this.add(eastPanel, "East");
        this.setLocation(300, 250);
        this.setSize(800, 400);
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent evt) {
        this.model.init(Double.parseDouble(this.tRateA.getText()), Double.parseDouble(this.tRateB.getText()), Integer.parseInt(this.tN.getText()), Integer.parseInt(this.tAccuracy.getText()));
        this.model.run();
    }

    public void update() {
        this.outSimulation1.setText("Warte-Wahrscheinlichkeit (PWarte): " + this.model.getPWarte());
        this.outSimulation1.setFont(new Font("Helvetica", 1, 12));
        double PWarteResult = ErlangBlock.C(this.model.getRateA() / this.model.getRateB(), this.model.getn());
        this.outFormulae1.setText("ErlangC-Formel-Wert: " + PWarteResult);
        this.outFormulae1.setFont(new Font("Helvetica", 1, 12));
        this.outSimulation2.setText("Anforderungen im System (N): " + this.model.getN());
        this.outSimulation2.setFont(new Font("Helvetica", 1, 12));
        double NResult = Teilnehmer.berechneDurchsTeilnehmerW(this.model.getRateA(), this.model.getRateB(), this.model.getn());
        this.outFormulae2.setText("Anforderungen-Formel-Wert: " + NResult);
        this.outFormulae2.setFont(new Font("Helvetica", 1, 12));
        this.outSimulation3.setText("Belegte Bedieneinheiten (NBE): " + this.model.getNBE());
        this.outSimulation3.setFont(new Font("Helvetica", 1, 12));
        double NBEResult = this.model.getRateA() / this.model.getRateB();
        this.outFormulae3.setText("Belegte Bedieneinheiten-Formel-Wert: " + NBEResult);
        this.outFormulae3.setFont(new Font("Helvetica", 1, 12));
        this.outSimulation4.setText("Wartende Anforderungen (NQueue): " + this.model.getNQueue());
        this.outSimulation4.setFont(new Font("Helvetica", 1, 12));
        double NQueueResult = Teilnehmer.wartendeTeilnehmer(this.model.getRateA(), this.model.getRateB(), this.model.getn());
        this.outFormulae4.setText("Wartende Anforderungen-Formel-Wert: " + NQueueResult);
        this.outFormulae4.setFont(new Font("Helvetica", 1, 12));
        this.outSimulation5.setText("Verweildauer im System (T): " + this.model.getT());
        this.outSimulation5.setFont(new Font("Helvetica", 1, 12));
        double TResult = Zeit.T(this.model.getRateA(), this.model.getRateB(), this.model.getn());
        this.outFormulae5.setText("Verweildauer im System-Formel-Wert: " + TResult);
        this.outFormulae5.setFont(new Font("Helvetica", 1, 12));
        this.outSimulation6.setText("Verweildauer in der Bedieneinheit (TBE): " + this.model.getTBE());
        this.outSimulation6.setFont(new Font("Helvetica", 1, 12));
        double TBEResult = 1.0 / this.model.getRateB();
        this.outFormulae6.setText("Verweildauer in der Bedieneinheit-Formel-Wert: " + TBEResult);
        this.outFormulae6.setFont(new Font("Helvetica", 1, 12));
        this.outSimulation7.setText("Verweildauer in der Warteschlange (TQueue): " + this.model.getTQueue());
        this.outSimulation7.setFont(new Font("Helvetica", 1, 12));
        double TQueueResult = Zeit.TQueue(this.model.getRateA(), this.model.getRateB(), this.model.getn());
        this.outFormulae7.setText("Verweildauer in der Warteschlange-Wert: " + TQueueResult);
        this.outFormulae7.setFont(new Font("Helvetica", 1, 12));
        this.outSimulation8.setText("Verweildauer in der Warteschlange isoliert (TOnlyQueue): " + this.model.getTOnlyQueue());
        this.outSimulation8.setFont(new Font("Helvetica", 1, 12));
        double TOnlyQueueResult = Zeit.TOnlyQueue(this.model.getRateA(), this.model.getRateB(), this.model.getn());
        this.outFormulae8.setText("Verweildauer in der Warteschlange isoliert-Formel-Wert: " + TOnlyQueueResult);
        this.outFormulae8.setFont(new Font("Helvetica", 1, 12));
    }
}
