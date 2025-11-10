package DView.Algorithm;

import Model.Services.CsvUpdateService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class AlgorithmExportWindow extends JFrame {
    private String filePath ="";
    private JTextArea textArea = new JTextArea();
    private final JTextField filepathField = new JTextField();
    private final JButton browsingButton = new JButton("Datei Suchen");
    private final JButton savingButton = new JButton("Speichern");

    public AlgorithmExportWindow(String filePath) {
        this.filePath = filePath;

        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/emergencityIcon.png"));
        setIconImage(icon);

        setTitle("Algorithm Calculation Export");
        setSize(800, 510);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        initScrollPane();
        initUI();
    }
    private void initScrollPane(){

        textArea.setEditable(false);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));

        JScrollPane scrollPane = new JScrollPane(
                textArea,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS
        );

        try (BufferedReader reader = new BufferedReader(new FileReader(this.filePath))) {
            textArea.read(reader, null);
        } catch (IOException e) {
            textArea.setText("Fehler beim Laden der Datei:\n" + e.getMessage());
        }
        scrollPane.setBounds(0,0,788,400);
        getContentPane().add(scrollPane);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void initUI(){
        filepathField.setBounds(200,410,400,50);
        filepathField.setFont(new Font("SansSerif", Font.BOLD, 16));
        getContentPane().add(filepathField);

        browsingButton.setBounds(0,410,200,50);
        browsingButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        getContentPane().add(browsingButton);

        savingButton.setBounds(600,410,200,50);
        savingButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        getContentPane().add(savingButton);

        browsingButton.addActionListener((ActionEvent e) -> {
            File startDir = new File("data");
            JFileChooser fileChooser = new JFileChooser(startDir);
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                filepathField.setText(selectedFile.getAbsolutePath());
            }
        });

        savingButton.addActionListener((ActionEvent e) -> {
            if(!filepathField.getText().isEmpty()) {
                String outputFile = filepathField.getText() + "/AlgorithmCalculationExport.txt";
                CsvUpdateService cus = new CsvUpdateService();
                cus.copyCSV(filePath,outputFile);
                filepathField.setText("");
            }else{
                JOptionPane.showMessageDialog(this, "Es wurde noch keine Datei ausgewählt", "Fehler", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AlgorithmExportWindow viewer = new AlgorithmExportWindow("Data/Cache/LinearOptimizationCache/loExport.txt");
            viewer.setVisible(true);
        });
    }
}
