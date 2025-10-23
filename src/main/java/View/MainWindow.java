package View;

import Model.Components.CsvEditorController;
import View.Editor.CsvEditorWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame {
    public MainWindow() {
        setTitle("Haupt Menü");
        setSize(314, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JButton openEditorButton = new JButton("CSV Editor");
        openEditorButton.setBounds(10,10,280,40);
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
        getContentPane().add(openEditorButton);

        JButton openAnaylsisButton = new JButton("CSV Analyse");
        openAnaylsisButton.setBounds(10,60,280,40);
        openAnaylsisButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                    //org.example.View.CsvAnalysis viewer = new org.example.View.CsvAnalysis();
                    //viewer.setVisible(true);

                });
            }
        });
        getContentPane().add(openAnaylsisButton);

        JButton openAlgorithmButton = new JButton("Verteilungs-Algorithmus");
        openAlgorithmButton.setBounds(10,110,280,40);
        openAlgorithmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                    //org.example.View.AlgorithmCalculation viewer = new org.example.View.AlgorithmCalculation();
                    //viewer.setVisible(true);
                });
            }
        });
        getContentPane().add(openAlgorithmButton);

        JButton openCompareButton = new JButton("Lösungs Vergleich");
        openCompareButton.setBounds(10,160,280,40);
        openCompareButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        getContentPane().add(openCompareButton);

        JButton openExportButton = new JButton("Lösungs Unity Export");
        openExportButton.setBounds(10,210,280,40);
        openExportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        getContentPane().add(openExportButton);


        JButton openAddOnButton = new JButton("Sicherheits-Leuchtturm");
        openAddOnButton.setBounds(10,260,280,40);
        openAddOnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        getContentPane().add(openAddOnButton);


    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainWindow viewer = new MainWindow();
            viewer.setVisible(true);

        });
    }
}
