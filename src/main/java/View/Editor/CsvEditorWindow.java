package View.Editor;

import Model.Services.CsvUpdateService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class CsvEditorWindow extends JFrame {
    private int colCount = 0;
    private JTextField pathField;
    private JTable table;
    private DefaultTableModel tableModel;
    private String displayedFile = "";
    private String cacheFile1 = "Data/Cache/EditorCache/temporary1.csv";
    private String cacheFile2 = "Data/Cache/EditorCache/temporary2.csv";

    public CsvEditorWindow() {
        setTitle("CSV-Editor");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        initUI();
    }

    private void initUI() {
        getContentPane().setLayout(null);

        pathField = new JTextField();
        pathField.setBounds(150,4,500,40);
        pathField.setEditable(true);

        JButton browseButton = new JButton("Datei suchen");
        browseButton.setBounds(0,3,150,40);

        JButton loadButton = new JButton("CSV laden");
        loadButton.setBounds(650,3,150,40);

        getContentPane().add(browseButton);
        getContentPane().add(pathField);
        getContentPane().add(loadButton);

        tableModel = new DefaultTableModel();
        table = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(table);
        tableScrollPane.setBounds(0,50,800,200);

        browseButton.addActionListener((ActionEvent e) -> {
            File startDir = new File("Data");
            JFileChooser fileChooser = new JFileChooser(startDir);
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                pathField.setText(selectedFile.getAbsolutePath());
            }
        });

        loadButton.addActionListener((ActionEvent e) -> {
            String filePath = pathField.getText();
            if (!filePath.isEmpty()) {
                loadCSVToTable(filePath);
                displayedFile=filePath;
            } else {
                JOptionPane.showMessageDialog(this, "Es wurde noch keine Datei ausgewählt", "Fehler", JOptionPane.ERROR_MESSAGE);
            }
        });
        getContentPane().add(tableScrollPane);

        JLabel deleteLabel = new JLabel("Lösche folgende Splate: ");
        deleteLabel.setBounds(10,270,160,40);

        JTextField deleteCol = new JTextField();
        deleteCol.setBounds(180,270,40,40);

        JButton deleteButton = new JButton("Löschen");
        deleteButton.setBounds(0,320, 220,40);
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!deleteCol.getText().isEmpty()||(Integer.parseInt(deleteCol.getText()) < 0)) {
                    String cacheFile = "";
                    if (!displayedFile.equals(cacheFile1)) {
                        cacheFile = cacheFile1;
                    } else {
                        cacheFile = cacheFile2;
                    }
                    CsvUpdateService cus = new CsvUpdateService();
                    cus.deleteColumn(displayedFile, cacheFile, Integer.parseInt(deleteCol.getText()));
                    loadCSVToTable(cacheFile);
                    deleteCol.setText("");
                    displayedFile = cacheFile;
                }else{
                    JOptionPane.showMessageDialog(CsvEditorWindow.this, "Es wurde eine invalide/keine Spalte zum löschen ausgewählt", "Fehler", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        getContentPane().add(deleteLabel);
        getContentPane().add(deleteCol);
        getContentPane().add(deleteButton);

        JLabel switchLabel = new JLabel("Tausch-Spalten: ");
        switchLabel.setBounds(300,270,110,40);

        JTextField switchCol1 = new JTextField();
        switchCol1.setBounds(420,270,40,40);

        JTextField switchCol2 = new JTextField();
        switchCol2.setBounds(470,270,40,40);

        JButton switchButton = new JButton("Tauschen");
        switchButton.setBounds(290,320, 220,40);
        switchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cacheFile = "";
                if(!displayedFile.equals(cacheFile1)){
                    cacheFile=cacheFile1;
                }else{
                    cacheFile=cacheFile2;
                }
                CsvUpdateService cus = new CsvUpdateService();
                cus.switchColumns(displayedFile,cacheFile,Integer.parseInt(switchCol1.getText()),Integer.parseInt(switchCol2.getText()));
                loadCSVToTable(cacheFile);
                switchCol1.setText("");
                switchCol2.setText("");
                displayedFile=cacheFile;
            }
        });


        getContentPane().add(switchLabel);
        getContentPane().add(switchCol1);
        getContentPane().add(switchCol2);
        getContentPane().add(switchButton);

        JTextField safeFile = new JTextField();
        safeFile.setBounds(580, 270, 100,40);

        JButton safeFileSearch = new JButton("Datei suchen");
        safeFileSearch.setBounds(690, 270, 110,40);
        safeFileSearch.addActionListener((ActionEvent e) -> {
            File startDir = new File("data");
            JFileChooser fileChooser = new JFileChooser(startDir);
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                safeFile.setText(selectedFile.getAbsolutePath());
            }
        });

        JButton safeButton = new JButton("Speichern");
        safeButton.setBounds(580,320, 220,40);
        safeButton.addActionListener((ActionEvent e) -> {
            if(!safeFile.getText().isEmpty()) {
                CsvUpdateService cus = new CsvUpdateService();
                cus.copyCSV(displayedFile, safeFile.getText() + "\\editedCsvFile.csv");
                safeFile.setText("");
            }else{
                JOptionPane.showMessageDialog(this, "Es wurde noch keine Datei ausgewählt", "Fehler", JOptionPane.ERROR_MESSAGE);
            }
        });

        getContentPane().add(safeFile);
        getContentPane().add(safeFileSearch);
        getContentPane().add(safeButton);

        JSeparator lineOne = new JSeparator(SwingConstants.VERTICAL);
        lineOne.setBounds(255,250,2,150);
        getContentPane().add(lineOne);

        JSeparator lineTwo = new JSeparator(SwingConstants.VERTICAL);
        lineTwo.setBounds(545,250,2,150);
        getContentPane().add(lineTwo);
    }

    private void loadCSVToTable(String filePath) {
        tableModel.setRowCount(0);
        tableModel.setColumnCount(0);

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int rowCount = 0;
            while ((line = reader.readLine()) != null && rowCount < 20) {
                String[] values = line.split(",");
                if (rowCount == 0) {
                    for (String col : values) {
                        tableModel.addColumn(col.trim());
                    }
                } else {
                    tableModel.addRow(values);
                }
                rowCount++;
            }
            this.colCount= line.split(",").length;
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Fehler beim Datei-Lesen", "Fehler", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CsvEditorWindow viewer = new CsvEditorWindow();
            viewer.setVisible(true);
        });
    }
}
