package View.Analysis;

import Controller.Analysis.CsvAnalysisController;
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
    private final JLabel personHeader = new JLabel("Personen CSV");
    private final JTextField pathFieldOne = new JTextField();
    private final JButton browseButtonOne = new JButton("Datei suchen");
    private final JLabel pillarHeader = new JLabel("Säulen CSV");
    private final JTextField pathFieldTwo = new JTextField();
    private final JButton browseButtonTwo = new JButton("Datei suchen");
    private final JLabel textPartOne = new JLabel("Erreichbarkeits-Radius:                                  m");
    private final JTextField distanceField = new JTextField();
    private final JButton analyseReachabilityButton = new JButton("Analysiere Erreichbarekeit");
    private final JButton analyseEncounterLengthButton = new JButton("Analysiere Verweildauer");


    public CsvAnalysisWindow() {
        setTitle("CSV Analysis");
        setSize(400, 420);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        //setResizable(false);
        //setMaximizedBounds(this.getBounds());

        initUI();
    }

    private void initUI() {
        getContentPane().setLayout(null);

        //-------------PERSON-SPECIFICATION---------------------------------------------
        personHeader.setBounds(20,20,120,20);
        personHeader.setFont(personHeader.getFont().deriveFont(15f));
        getContentPane().add(personHeader);

        pathFieldOne.setBounds(160, 40, 200, 40);
        pathFieldOne.setEditable(true);
        pathFieldOne.setText("Data/TestingData/AnalysisTest/reachabilityTestPeople.csv");
        getContentPane().add(pathFieldOne);

        browseButtonOne.setBounds(20, 40, 120, 40);
        getContentPane().add(browseButtonOne);

        //-------------PILLAR-SPECIFICATION---------------------------------------------
        pillarHeader.setBounds(20,100,120,20);
        pillarHeader.setFont(pillarHeader.getFont().deriveFont(15f));
        getContentPane().add(pillarHeader);

        pathFieldTwo.setBounds(160, 120, 200, 40);
        pathFieldTwo.setEditable(true);
        pathFieldTwo.setText("Data/TestingData/ReadTest/pillarReadTest1.csv");
        getContentPane().add(pathFieldTwo);

        browseButtonTwo.setBounds(20, 120, 120, 40);
        getContentPane().add(browseButtonTwo);

        //-------------DISTANCE-SPECIFICATION---------------------------------------------
        textPartOne.setBounds(20,180,360,40);
        textPartOne.setFont(textPartOne.getFont().deriveFont(15f));
        getContentPane().add(textPartOne);

        distanceField.setBounds(240, 180, 80, 40);
        distanceField.setEditable(true);
        distanceField.setText("20");
        getContentPane().add(distanceField);

        //-------------REACHABILITY-ANALYSIS---------------------------------------------
        analyseReachabilityButton.setBounds(20, 260, 340,40);
        getContentPane().add(analyseReachabilityButton);

        //-------------ENCOUNTER-LENGTH-ANALYSIS---------------------------------------------
        analyseEncounterLengthButton.setBounds(20, 320, 340,40);
        getContentPane().add(analyseEncounterLengthButton);

        //-------------LINE-WORK---------------------------------------------
        JPanel thickLine = new JPanel();
        thickLine.setBackground(Color.BLACK);
        thickLine.setBounds(10, 237, 368, 2);
        getContentPane().add(thickLine);

    }
    //--------------------EXPORT-FÜR-CsvAnaylsisController-------------
    public void addBrowseOneListener(ActionListener l){ browseButtonOne.addActionListener(l); }
    public void addBrowseTwoListener(ActionListener l){ browseButtonTwo.addActionListener(l); }
    public void addReachabilityListener(ActionListener l){ analyseReachabilityButton.addActionListener(l); }
    public void addEncounterLengthListener(ActionListener l){ analyseEncounterLengthButton.addActionListener(l); }

    public void setPathFieldOne(String msg){pathFieldOne.setText(msg);}
    public String getPathFieldOne(){return pathFieldOne.getText();}
    public void setPathFieldTwo(String msg){pathFieldTwo.setText(msg);}
    public String getPathFieldTwo(){return pathFieldTwo.getText();}
    public String getDistance(){return distanceField.getText();}

    public void showError(String msg){ JOptionPane.showMessageDialog(this, msg, "Fehler", JOptionPane.ERROR_MESSAGE); }

    //---------------------------DEBUGGING-----------------------------
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CsvAnalysisWindow viewer = new CsvAnalysisWindow();
            new CsvAnalysisController(viewer);
            viewer.setVisible(true);
        });
    }
}
