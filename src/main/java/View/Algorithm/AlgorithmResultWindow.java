package View.Algorithm;

import Model.Components.Pillar;
import Model.Services.CsvReaderService;
import Model.Services.CsvWriterService;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;

public class AlgorithmResultWindow extends JFrame {
    private ArrayList<Pillar> values = new ArrayList<>();

    private DefaultTableModel model;
    private JTable table;
    private final JTextField filepathField = new JTextField();
    private final JButton browsingButton = new JButton("Datei Suchen");
    private final JButton savingButton = new JButton("Speichern");

    public AlgorithmResultWindow(ArrayList<Pillar> input) {
        this.values=input;

        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/emergencityIcon.png"));
        setIconImage(icon);

        setTitle("Algorithm Result");
        setSize(800, 510);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        initTable();
        initUI();
    }

    private void initTable(){
        String[] columnNames = {"ID","Longitude", "Latitude", "Schatten"};

        this.model = new DefaultTableModel(columnNames,0);

        for(Pillar i : values){
            model.addRow(new String[]{String.valueOf(i.getPillarID()), String.valueOf(i.getLongitude()), String.valueOf(i.getLatitude()),i.getShadow().toString()});
        }

        this.table = new JTable(model);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        table.setRowHeight(30);
        table.setFont(new Font("SansSerif", Font.PLAIN, 16));

        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("SansSerif", Font.BOLD, 18));

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0,0,788,400);
        getContentPane().add(scrollPane);
    }

    private void initUI(){
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
                String outputFile = filepathField.getText() + "/AlgorithmResultPillars.csv";
                CsvWriterService cws = new CsvWriterService();
                cws.writePillarsToFile(values,outputFile);
                filepathField.setText("");
            }else{
                JOptionPane.showMessageDialog(this, "Es wurde noch keine Datei ausgewählt", "Fehler", JOptionPane.ERROR_MESSAGE);
            }
        });


    }

    public static void main(String[] args) {
        CsvReaderService crs = new CsvReaderService();
        ArrayList<Pillar> test = crs.readPillarsFromFile("Data/TestingData/ReadTest/pillarReadTest1.csv");
        SwingUtilities.invokeLater(() -> {
            AlgorithmResultWindow viewer = new AlgorithmResultWindow(test);
            viewer.setVisible(true);
        });
    }
}
