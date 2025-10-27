package View.Analysis;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.ArrayList;

public class CsvAnalysisEncounterLengthDetailWindow extends JFrame {
    private ArrayList<Integer> values = new ArrayList<>();

    public CsvAnalysisEncounterLengthDetailWindow(ArrayList<Integer> input) {
        this.values=input;

        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/emergencityIcon.png"));
        setIconImage(icon);

        setTitle("Verweildauer-Details");
        setSize(310, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        String[] columnNames = {"Verweildauer","Häufigkeit"};

        DefaultTableModel model = new DefaultTableModel(columnNames,0);

        int count = 1;
        for(Integer i : values){
            model.addRow(new String[]{Integer.toString(count),Integer.toString(i)});
            count++;
        }

        JTable table = new JTable(model);
        table.setRowHeight(30);
        table.setFont(new Font("SansSerif", Font.PLAIN, 16));

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("SansSerif", Font.BOLD, 18));

        JScrollPane scrollPane = new JScrollPane(table);
        this.add(scrollPane, BorderLayout.CENTER);
        this.pack();


        //initUI();
    }
    private void initUI() {
        getContentPane().setLayout(null);

        DefaultTableModel tableModel = new DefaultTableModel();
        JTable table = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(table);
        tableScrollPane.setBounds(0,50,300,200);

        tableModel.setRowCount(0);
        tableModel.setColumnCount(2);
        tableModel.addRow(new String[]{"Verweildauer","Häufigkeit"});
        int count = 1;
        for(Integer i : values){
            tableModel.addRow(new String[]{Integer.toString(count),Integer.toString(i)});
            count++;
        }
        getContentPane().add(table);

    }

    public static void main(String[] args) {
        ArrayList<Integer> test = new ArrayList<>();
        test.add(1);
        test.add(10);
        test.add(200);
        test.add(89);
        test.add(12);
        test.add(3);
        test.add(44);
        test.add(2321);
        SwingUtilities.invokeLater(() -> {
            CsvAnalysisEncounterLengthDetailWindow viewer = new CsvAnalysisEncounterLengthDetailWindow(test);
            viewer.setVisible(true);
        });
    }

}
