package DView.Analysis;

import Model.Services.CsvReaderService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CsvAnalysisEncounterLengthWindow extends JFrame {
    private double median=0;
    private double average =0;
    private int max =0;
    private int min = 0;

    public CsvAnalysisEncounterLengthWindow(double[] input) {
        this.median = input[0];
        this.average = input[1];
        this.max = (int) input[2];
        this.min = (int) input[3];

        this.median=Math.round(this.median * 100.0) / 100.0;
        this.average=Math.round(this.average * 100.0) / 100.0;

        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/emergencityIcon.png"));
        setIconImage(icon);

        setTitle("Verweildauer-Auswertung");
        setSize(310, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        initUI();
    }
    private void initUI() {
        getContentPane().setLayout(null);

        JLabel medianLabel = new JLabel("Median:              "+this.median);
        medianLabel.setBounds(30,20,400,40);
        medianLabel.setFont(medianLabel.getFont().deriveFont(25f));
        getContentPane().add(medianLabel);

        JLabel avgLabel = new JLabel("Durchschnitt:    "+this.average);
        avgLabel.setBounds(30,80,400,40);
        avgLabel.setFont(avgLabel.getFont().deriveFont(25f));
        getContentPane().add(avgLabel);

        JLabel maxLabel = new JLabel("Maximum:           "+this.max);
        maxLabel.setBounds(30,140,400,40);
        maxLabel.setFont(maxLabel.getFont().deriveFont(25f));
        getContentPane().add(maxLabel);

        JLabel minLabel = new JLabel("Minimum:             "+this.min);
        minLabel.setBounds(30,200,400,40);
        minLabel.setFont(minLabel.getFont().deriveFont(25f));
        getContentPane().add(minLabel);

        JPanel thickLine = new JPanel();
        thickLine.setBackground(Color.BLACK);
        thickLine.setBounds(20, 260, 250, 2);
        getContentPane().add(thickLine);

        JButton detailsButton = new JButton("Detail-Ansicht");
        detailsButton.setBounds(20,290,250,40);

        detailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CsvReaderService crs = new CsvReaderService();
                ArrayList<Integer> encounterLengthDetails = crs.readIntegerList("Data/Cache/AnalysisCache/encounterLengthTemporary.csv");
                SwingUtilities.invokeLater(() -> {
                    CsvAnalysisEncounterLengthDetailWindow viewer = new CsvAnalysisEncounterLengthDetailWindow(encounterLengthDetails);
                    viewer.setVisible(true);
                });
            }
        });

        getContentPane().add(detailsButton);



    }

    public static void main(String[] args) {
        double[] test = {20,20.6,40,1};
        SwingUtilities.invokeLater(() -> {
            CsvAnalysisEncounterLengthWindow viewer = new CsvAnalysisEncounterLengthWindow(test);
            viewer.setVisible(true);
        });
    }



}
