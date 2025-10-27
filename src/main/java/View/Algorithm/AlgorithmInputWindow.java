package View.Algorithm;

import Controller.Algorithm.AlgorithmInputController;
import Model.Algorithms.Algorithm;
import Model.Algorithms.LogikAlgorithm;
import Model.Components.Person;
import Model.Components.Pillar;
import Model.Services.CsvReaderService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class AlgorithmInputWindow extends JFrame {
    private final JLabel personHeader = new JLabel("Personen CSV");
    private final JTextField pathFieldOne = new JTextField();
    private final JButton browseButtonOne = new JButton("Datei suchen");
    private final JLabel pillarHeader = new JLabel("Säulen CSV");
    private final JTextField pathFieldTwo = new JTextField();
    private final JButton browseButtonTwo = new JButton("Datei suchen");
    private final JLabel textPartOne = new JLabel("Erreichbarkeits-Radius:                                  m");
    private final JTextField distanceField = new JTextField();
    private final JLabel textPartTwo = new JLabel("Ziel-Zeitffenster:                                                min");
    private final JTextField timeField = new JTextField();
    private final JLabel textPartThree = new JLabel("Offset:");
    private final JTextField offsetField = new JTextField();
    private final JLabel textPartFour = new JLabel("Sampling:");
    private final JTextField samplingField = new JTextField();
    private final JCheckBox cAbsolut = new JCheckBox();
    private final JCheckBox cRelativ = new JCheckBox();
    private final JLabel textPartFive = new JLabel("Anzahl Säulen:");
    private final JTextField pillarCount = new JTextField("");
    private final JLabel textPartSix = new JLabel("Stück");
    private final JLabel textPartSeven = new JLabel("Abdeckungs Quote:");
    private final JTextField coveragePerc = new JTextField("");
    private final JLabel textPartEight = new JLabel("Prozent");
    private final JCheckBox cLogik = new JCheckBox();
    private final JCheckBox cMeta = new JCheckBox();
    private final JCheckBox cLineareOptimierung = new JCheckBox();
    private final JLabel textPartNine = new JLabel("Logik");
    private final JLabel textPartTen = new JLabel("Lattice Search");
    private final JLabel textPartEleven = new JLabel("Lineare Optimierung");
    private final JButton startButton = new JButton("Start");

    public AlgorithmInputWindow() {
        setTitle("Algorithm Start");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        initUI();
    }

    private void initUI() {
        getContentPane().setLayout(null);

        //----------------------------PERSON-SPECIFICATION---------------------
        personHeader.setBounds(20,20,120,20);
        personHeader.setFont(personHeader.getFont().deriveFont(15f));
        getContentPane().add(personHeader);

        pathFieldOne.setText("Data/TestingData/AlgorithmTest/LogikAlgorithmTest/logikAlgorithmTest2.csv");
        pathFieldOne.setBounds(160, 40, 200, 40);
        pathFieldOne.setEditable(true);
        getContentPane().add(pathFieldOne);

        browseButtonOne.setBounds(20, 40, 120, 40);
        getContentPane().add(browseButtonOne);

        //------------------------------PILLAR-SPECIFICATION-------------------
        pillarHeader.setBounds(20,100,120,20);
        pillarHeader.setFont(pillarHeader.getFont().deriveFont(15f));
        getContentPane().add(pillarHeader);

        pathFieldTwo.setText("Data/TestingData/ReadTest/pillarReadTest2.csv");
        pathFieldTwo.setBounds(160, 120, 200, 40);
        pathFieldTwo.setEditable(true);
        getContentPane().add(pathFieldTwo);

        browseButtonTwo.setBounds(20, 120, 120, 40);
        getContentPane().add(browseButtonTwo);

        //-------------------------------SEPERATION-LINE-1-----------------------
        JPanel thickLine1 = new JPanel();
        thickLine1.setBackground(Color.BLACK);
        thickLine1.setBounds(10, 174, 368, 2);
        getContentPane().add(thickLine1);

        //-----------------------------DISTANCE-SPECIFICATION---------------------
        textPartOne.setBounds(20,180,360,40);
        textPartOne.setFont(textPartOne.getFont().deriveFont(15f));
        getContentPane().add(textPartOne);

        distanceField.setText("20");
        distanceField.setBounds(240, 190, 80, 20);
        distanceField.setEditable(true);
        getContentPane().add(distanceField);

        //-----------------------------TIME-WINDOW-SPECIFICATION-----------------
        textPartTwo.setBounds(20,220,360,40);
        textPartTwo.setFont(textPartOne.getFont().deriveFont(15f));
        getContentPane().add(textPartTwo);

        //timeField.setText("5");
        timeField.setBounds(240, 230, 80, 20);
        timeField.setEditable(true);
        getContentPane().add(timeField);

        textPartThree.setBounds(20,260,360,40);
        textPartThree.setFont(textPartOne.getFont().deriveFont(15f));
        getContentPane().add(textPartThree);

        //offsetField.setText("3");
        offsetField.setBounds(100, 270, 80, 20);
        offsetField.setEditable(true);
        getContentPane().add(offsetField);

        //---------------------------SAMPLING-SPECIFICATION-------------------
        textPartFour.setBounds(195,260,360,40);
        textPartFour.setFont(textPartOne.getFont().deriveFont(15f));
        getContentPane().add(textPartFour);

        samplingField.setText("1");
        samplingField.setBounds(280, 270, 80, 20);
        samplingField.setEditable(true);
        getContentPane().add(samplingField);

        //-------------------------------SEPERATION-LINE-2-----------------------
        JPanel thickLine2 = new JPanel();
        thickLine2.setBackground(Color.BLACK);
        thickLine2.setBounds(10, 313, 368, 2);
        getContentPane().add(thickLine2);

        //------------------------KIND-OF-OPTIMIZATION-SPECIFICATION--------------
        cAbsolut.setBounds(20,330, 20,20);
        cAbsolut.setSelected(true);
        getContentPane().add(cAbsolut);

        cRelativ.setBounds(210,330, 20,20);
        getContentPane().add(cRelativ);

        cAbsolut.addActionListener(e -> {
            if (cAbsolut.isSelected()){
                cRelativ.setSelected(false);
            }
        });
        cRelativ.addActionListener(e -> {
            if (cRelativ.isSelected()){
                cAbsolut.setSelected(false);
            }
        });

        textPartFive.setBounds(44,320,360,40);
        textPartFive.setFont(textPartOne.getFont().deriveFont(15f));
        getContentPane().add(textPartFive);

        pillarCount.setBounds(22,360,80,30);
        pillarCount.setFont(textPartOne.getFont().deriveFont(15f));
        pillarCount.setText("3");
        getContentPane().add(pillarCount);

        textPartSix.setBounds(110,350,360,40);
        textPartSix.setFont(textPartOne.getFont().deriveFont(15f));
        getContentPane().add(textPartSix);

        textPartSeven.setBounds(232,320,360,40);
        textPartSeven.setFont(textPartOne.getFont().deriveFont(15f));
        getContentPane().add(textPartSeven);

        coveragePerc.setBounds(212,360,80,30);
        coveragePerc.setFont(textPartOne.getFont().deriveFont(15f));
        getContentPane().add(coveragePerc);

        textPartEight.setBounds(299,350,360,40);
        textPartEight.setFont(textPartOne.getFont().deriveFont(15f));
        getContentPane().add(textPartEight);

        //----------------------LINE-WORK---------------------------
        JPanel thickLine3 = new JPanel();
        thickLine3.setBackground(Color.BLACK);
        thickLine3.setBounds(188, 322, 2, 78);
        getContentPane().add(thickLine3);

        JPanel thickLine4 = new JPanel();
        thickLine4.setBackground(Color.BLACK);
        thickLine4.setBounds(10, 406, 368, 2);
        getContentPane().add(thickLine4);

        //--------------KIND-OF-ALGORITHM-SPECIFICATION-------------
        cLogik.setBounds(180,430, 20,20);
        cLogik.setSelected(true);
        getContentPane().add(cLogik);

        cMeta.setBounds(180,470, 20,20);
        getContentPane().add(cMeta);

        cLineareOptimierung.setBounds(180,510, 20,20);
        getContentPane().add(cLineareOptimierung);

        cLogik.addActionListener(e -> {
            if (cLogik.isSelected()){
                cMeta.setSelected(false);
                cLineareOptimierung.setSelected(false);
            }
        });
        cMeta.addActionListener(e -> {
            if (cMeta.isSelected()){
                cLogik.setSelected(false);
                cLineareOptimierung.setSelected(false);
            }
        });
        cLineareOptimierung.addActionListener(e -> {
            if (cLineareOptimierung.isSelected()){
                cLogik.setSelected(false);
                cMeta.setSelected(false);
            }
        });

        textPartNine.setBounds(20,420,360,40);
        textPartNine.setFont(textPartOne.getFont().deriveFont(15f));
        getContentPane().add(textPartNine);

        textPartTen.setBounds(20,460,360,40);
        textPartTen.setFont(textPartOne.getFont().deriveFont(15f));
        getContentPane().add(textPartTen);

        textPartEleven.setBounds(20,500,360,40);
        textPartEleven.setFont(textPartOne.getFont().deriveFont(15f));
        getContentPane().add(textPartEleven);

        startButton.setBounds(235,450,120,60);
        startButton.setFont(textPartOne.getFont().deriveFont(18f));
        getContentPane().add(startButton);

    }



    //--------------------EXPORT-FÜR-CsvAnaylsisController-------------
    public void addBrowseOneListener(ActionListener l){ browseButtonOne.addActionListener(l); }
    public void addBrowseTwoListener(ActionListener l){ browseButtonTwo.addActionListener(l); }
    public void addStartListener(ActionListener l){ startButton.addActionListener(l); }

    public String getPathFieldOne(){return pathFieldOne.getText();}
    public void setPathFieldOne(String msg){pathFieldOne.setText(msg);}

    public String getPathFieldTwo(){return pathFieldTwo.getText();}
    public void setPathFieldTwo(String msg){pathFieldTwo.setText(msg);}

    public String getDistance(){return distanceField.getText();}

    public boolean getCRelativ(){return cRelativ.isSelected();}
    public boolean getCAbsolut(){return cAbsolut.isSelected();}

    public String getPillarCount(){return pillarCount.getText();}
    public String getCoverage(){return coveragePerc.getText();}

    public boolean getCLogik(){return cLogik.isSelected();}
    public boolean getCLatticeSearch(){return cMeta.isSelected();}
    public boolean getCLineareOptimierung(){return cLineareOptimierung.isSelected();}

    public void showError(String msg){ JOptionPane.showMessageDialog(this, msg, "Fehler", JOptionPane.ERROR_MESSAGE); }

    //--------------------------DEBUGGING--------------------------
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AlgorithmInputWindow viewer = new AlgorithmInputWindow();
            new AlgorithmInputController(viewer);
            viewer.setVisible(true);
        });
    }



}
