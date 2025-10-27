package View.Comparator;

import Controller.Editor.CsvEditorController;
import View.Editor.CsvEditorWindow;

import javax.swing.*;
import java.awt.*;

public class ComparatorWindow extends JFrame{
    private final JLabel solutionLabelOne = new JLabel("Lösungs-Säulen 1 CSV");
    private final JButton browseButtonOne = new JButton("Suchen");
    private final JTextField solutionTextFieldOne = new JTextField();

    private final JLabel solutionLabelTwo = new JLabel("Lösungs-Säulen 2 CSV");
    private final JButton browseButtonTwo = new JButton("Suchen");
    private final JTextField solutionTextFieldTwo = new JTextField();

    private final JPanel seperationLineOne = new JPanel();

    private final JLabel personLabel = new JLabel("Personen CSV");
    private final JButton browseButtonThree = new JButton("Suchen");
    private final JTextField personTextField = new JTextField();

    private final JPanel seperationLineTwo = new JPanel();

    private final JLabel distanceLabel = new JLabel("Erreichbarkeits-Radius:                                                            m");
    private final JTextField distanceTextField = new JTextField();

    private final JPanel seperationLineThree = new JPanel();

    private  final JCheckBox cTimeWindow = new JCheckBox();
    private final JLabel timeWindowLabel = new JLabel("Ziel-Zeitfenster:                                                              min");
    private final JTextField timeWindowTextField = new JTextField();

    private final JPanel seperationLineFour = new JPanel();

    private final JButton startButton = new JButton("Auswertung");


    public ComparatorWindow() {
        setTitle("Solution-Comparator");
        setSize(400, 540);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        initUI();
    }
    public void initUI(){
        getContentPane().setLayout(null);

        solutionLabelOne.setBounds(20,20,200,20);
        getContentPane().add(solutionLabelOne);
        browseButtonOne.setBounds(20,40,120,40);
        getContentPane().add(browseButtonOne);
        solutionTextFieldOne.setBounds(160,40,200,40);
        solutionTextFieldOne.setEditable(true);
        getContentPane().add(solutionTextFieldOne);

        solutionLabelTwo.setBounds(20,100,200,20);
        getContentPane().add(solutionLabelTwo);
        browseButtonTwo.setBounds(20,120,120,40);
        getContentPane().add(browseButtonTwo);
        solutionTextFieldTwo.setBounds(160,120,200,40);
        solutionTextFieldTwo.setEditable(true);
        getContentPane().add(solutionTextFieldTwo);

        seperationLineOne.setBounds(20,180,340,1);
        seperationLineOne.setBackground(Color.BLACK);
        getContentPane().add(seperationLineOne);

        personLabel.setBounds(20,200,200,20);
        getContentPane().add(personLabel);
        browseButtonThree.setBounds(20,220,120,40);
        getContentPane().add(browseButtonThree);
        personTextField.setBounds(160,220,200,40);
        personTextField.setEditable(true);
        getContentPane().add(personTextField);

        seperationLineTwo.setBounds(20,280,340,1);
        seperationLineTwo.setBackground(Color.BLACK);
        getContentPane().add(seperationLineTwo);

        distanceLabel.setBounds(20,300,340,20);
        getContentPane().add(distanceLabel);
        distanceTextField.setBounds(250,300,80,20);
        getContentPane().add(distanceTextField);

        seperationLineThree.setBounds(20,340,340,1);
        seperationLineThree.setBackground(Color.BLACK);
        getContentPane().add(seperationLineThree);

        cTimeWindow.setBounds(20,360,20,20);
        getContentPane().add(cTimeWindow);
        timeWindowLabel.setBounds(50,360,340,20);
        getContentPane().add(timeWindowLabel);
        timeWindowTextField.setBounds(250,360,80,20);
        getContentPane().add(timeWindowTextField);

        seperationLineFour.setBounds(20,400,340,1);
        seperationLineFour.setBackground(Color.BLACK);
        getContentPane().add(seperationLineFour);

        startButton.setBounds(60,420,260,50);
        getContentPane().add(startButton);
    }

    //--------------------------------------------------------DEBUGGING---------------
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ComparatorWindow viewer = new ComparatorWindow();
            //new CsvEditorController(viewer);
            viewer.setVisible(true);
        });
    }
}
