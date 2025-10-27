package View;

import Controller.Algorithm.AlgorithmInputController;
import Controller.Analysis.CsvAnalysisController;
import Controller.Editor.CsvEditorController;
import View.Algorithm.AlgorithmInputWindow;
import View.Analysis.CsvAnalysisWindow;
import View.Editor.CsvEditorWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame {
    public MainWindow() {
        setTitle("Haupt Menü");
        setSize(314, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/emergencityIcon.png"));
        setIconImage(icon);

        initUI();
    }
    public void initUI(){
        JButton openEditorButton = new JButton("CSV Editor");
        openEditorButton.setBounds(10,10,280,40);
        getContentPane().add(openEditorButton);

        JButton openAnaylsisButton = new JButton("CSV Analyse");
        openAnaylsisButton.setBounds(10,60,280,40);
        getContentPane().add(openAnaylsisButton);

        JButton openAlgorithmButton = new JButton("Verteilungs-Algorithmus");
        openAlgorithmButton.setBounds(10,110,280,40);
        getContentPane().add(openAlgorithmButton);

        JButton openCompareButton = new JButton("Lösungs Vergleich");
        openCompareButton.setBounds(10,160,280,40);
        getContentPane().add(openCompareButton);

        JButton openExportButton = new JButton("Lösungs Unity Export");
        openExportButton.setBounds(10,210,280,40);
        getContentPane().add(openExportButton);


        JButton openAddOnButton = new JButton("Sicherheits-Leuchtturm");
        openAddOnButton.setBounds(10,260,280,40);
        getContentPane().add(openAddOnButton);

        //---------------------------BUTTON-FUNCTION--------------------
        openEditorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                    CsvEditorWindow editView = new CsvEditorWindow();
                    new CsvEditorController(editView);
                    editView.setVisible(true);
                });
            }
        });
        openAnaylsisButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                    CsvAnalysisWindow viewer = new CsvAnalysisWindow();
                    new CsvAnalysisController(viewer);
                    viewer.setVisible(true);

                });
            }
        });
        openAlgorithmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                    AlgorithmInputWindow viewer = new AlgorithmInputWindow();
                    new AlgorithmInputController(viewer);
                    viewer.setVisible(true);
                });
            }
        });
        openCompareButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        openExportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        openAddOnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }


    //--------------------------------------DEBUGGING--------------------------
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainWindow viewer = new MainWindow();
            viewer.setVisible(true);

        });
    }
}
