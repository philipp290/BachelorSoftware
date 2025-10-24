package View.Analysis;

import Model.Components.Pillar;
import Model.Services.CsvAnalysisService;
import Model.Services.CsvReaderService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class CsvAnalysisWindow extends JFrame {
    public CsvAnalysisWindow() {
        setTitle("CSV Analysis");
        setSize(400, 420);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        initUI();
    }

    private void initUI() {
        getContentPane().setLayout(null);

        JLabel personHeader = new JLabel("Personen CSV");
        personHeader.setBounds(20,20,120,20);
        personHeader.setFont(personHeader.getFont().deriveFont(15f));

        JTextField pathFieldOne = new JTextField();
        pathFieldOne.setText("Data/TestingData/AnalysisTest/reachabilityTestPeople.csv");
        pathFieldOne.setBounds(160, 40, 200, 40);
        pathFieldOne.setEditable(true);

        JButton browseButtonOne = new JButton("Datei suchen");
        browseButtonOne.setBounds(20, 40, 120, 40);

        getContentPane().add(browseButtonOne);
        getContentPane().add(pathFieldOne);
        getContentPane().add(personHeader);

        JLabel pillarHeader = new JLabel("Säulen CSV");
        pillarHeader.setBounds(20,100,120,20);
        pillarHeader.setFont(pillarHeader.getFont().deriveFont(15f));

        JTextField pathFieldTwo = new JTextField();
        //Debugging
        pathFieldTwo.setText("Data/TestingData/ReadTest/pillarReadTest1.csv");
        pathFieldTwo.setBounds(160, 120, 200, 40);
        pathFieldTwo.setEditable(true);

        JButton browseButtonTwo = new JButton("Datei suchen");
        browseButtonTwo.setBounds(20, 120, 120, 40);

        getContentPane().add(browseButtonTwo);
        getContentPane().add(pathFieldTwo);
        getContentPane().add(pillarHeader);

        JLabel textPartOne = new JLabel("Erreichbarkeits-Radius:                                  m");
        textPartOne.setBounds(20,180,360,40);
        textPartOne.setFont(textPartOne.getFont().deriveFont(15f));
        getContentPane().add(textPartOne);


        JTextField distanceField = new JTextField();
        distanceField.setText("20");
        distanceField.setBounds(240, 180, 80, 40);
        distanceField.setEditable(true);
        getContentPane().add(distanceField);

        browseButtonOne.addActionListener((ActionEvent e) -> {
            File startDir = new File("data");
            JFileChooser fileChooser = new JFileChooser(startDir);
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                pathFieldOne.setText(selectedFile.getAbsolutePath());
            }
        });

        browseButtonTwo.addActionListener((ActionEvent e) -> {
            File startDir = new File("data");
            JFileChooser fileChooser = new JFileChooser(startDir);
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                pathFieldTwo.setText(selectedFile.getAbsolutePath());
            }
        });

        JButton analyseReachabilityButton = new JButton("Analysiere Erreichbarekeit");
        analyseReachabilityButton.setBounds(20, 260, 340,40);

        analyseReachabilityButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CsvAnalysisService cas = new CsvAnalysisService();
                double[] reachability = cas.reachabilityAnalysis(pathFieldOne.getText(),pathFieldTwo.getText(), Integer.parseInt(distanceField.getText()));
                SwingUtilities.invokeLater(() -> {
                    CsvAnalysisReachabilityWindow viewer = new CsvAnalysisReachabilityWindow(reachability);
                    viewer.setVisible(true);
                });
            }
        });

        getContentPane().add(analyseReachabilityButton);

        JButton analyseEncounterLengthButton = new JButton("Analysiere Verweildauer");
        analyseEncounterLengthButton.setBounds(20, 320, 340,40);

        analyseEncounterLengthButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CsvReaderService crs = new CsvReaderService();
                ArrayList<Pillar> pillars = crs.readPillarsFromFile(pathFieldTwo.getText());
                CsvAnalysisService cas = new CsvAnalysisService();
                double[] encounterdata = cas.encounterLengthAnalysis(pathFieldOne.getText(),pillars, Integer.valueOf(distanceField.getText()));
                SwingUtilities.invokeLater(() -> {
                    CsvAnalysisEncounterLengthWindow viewer = new CsvAnalysisEncounterLengthWindow(encounterdata);
                    viewer.setVisible(true);
                });
            }
        });

        getContentPane().add(analyseEncounterLengthButton);

        JPanel thickLine = new JPanel();
        thickLine.setBackground(Color.BLACK);
        thickLine.setBounds(10, 237, 368, 2);
        getContentPane().add(thickLine);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CsvAnalysisWindow viewer = new CsvAnalysisWindow();
            viewer.setVisible(true);

        });
    }
}
