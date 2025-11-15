package UFView.Analysis;

import Model.Components.Pillar;
import Model.Components.Shadow;


import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.util.ArrayList;

public class UFAnalysisCoverageWindow extends JFrame {
    private JTable table;
    private DefaultTableModel model;

    private JLabel maxLabel;
    private JLabel maxField;

    private JLabel minLabel;
    private JLabel minField;

    private JLabel avgLabel;
    private JLabel avgField;

    private JLabel zerosLabel;
    private JLabel zerosField;

    private JPanel top;
    private JPanel bottom;
    private JPanel left;
    private JPanel right;

    private final Color defaultColor;
    private final Color hoverColor;
    private final Color clickColor;
    private final Font mainFont;

    private ArrayList<Pillar> values;
    private double[] analysisValue;
    public UFAnalysisCoverageWindow(ArrayList<Pillar> con, double[] av){
        this.values = con;
        this.analysisValue = av;

        defaultColor = new Color(220, 220, 220);
        hoverColor = new Color(170, 170, 170);
        clickColor = new Color(130, 130, 130);

        mainFont = new Font("Arial", Font.BOLD, 16);

        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/emergencityIcon.png"));
        setIconImage(icon);
        setTitle("Analyse");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel content = new JPanel(null);
        content.setBackground(Color.WHITE);
        content.setPreferredSize(new Dimension(520, 350));
        setContentPane(content);

        initUI();
        initTable();
        pack();
        setLocationRelativeTo(null);

    }

    private void initUI(){
        top = new JPanel();
        top.setBackground(Color.BLACK);
        top.setBounds(20,229,480,2);
        getContentPane().add(top);

        left = new JPanel();
        left.setBackground(Color.BLACK);
        left.setBounds(20,229,2,100);
        getContentPane().add(left);

        right = new JPanel();
        right.setBackground(Color.BLACK);
        right.setBounds(498,229,2,100);
        getContentPane().add(right);

        maxLabel = new JLabel("Maximum");
        maxLabel.setBounds(30,240,80,30);
        maxLabel.setFont(mainFont);
        getContentPane().add(maxLabel);

        maxField = new JLabel(String.valueOf((int)analysisValue[0]));
        maxField.setBounds(110,240,60,30);
        maxField.setFont(mainFont);
        maxField.setHorizontalAlignment(SwingConstants.CENTER);
        maxField.setVerticalAlignment(SwingConstants.CENTER);
        maxField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        getContentPane().add(maxField);

        minLabel = new JLabel("Minimum");
        minLabel.setBounds(195,240,80,30);
        minLabel.setFont(mainFont);
        getContentPane().add(minLabel);

        minField = new JLabel(String.valueOf((int)analysisValue[1]));
        minField.setBounds(275,240,60,30);
        minField.setFont(mainFont);
        minField.setHorizontalAlignment(SwingConstants.CENTER);
        minField.setVerticalAlignment(SwingConstants.CENTER);
        minField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        getContentPane().add(minField);

        avgLabel = new JLabel("Average");
        avgLabel.setBounds(360,240,80,30);
        avgLabel.setFont(mainFont);
        getContentPane().add(avgLabel);

        avgField = new JLabel(String.valueOf(Math.round(analysisValue[2]*100.0)/100.0));
        avgField.setBounds(430,240,60,30);
        avgField.setFont(mainFont);
        avgField.setHorizontalAlignment(SwingConstants.CENTER);
        avgField.setVerticalAlignment(SwingConstants.CENTER);
        avgField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        getContentPane().add(avgField);

        zerosLabel = new JLabel("Anzahl Säulen die keine Personen erreichen:");
        zerosLabel.setBounds(30,290,400,30);
        zerosLabel.setFont(mainFont);
        getContentPane().add(zerosLabel);

        zerosField = new JLabel(String.valueOf((int)analysisValue[3]));
        zerosField.setBounds(400,290,90,30);
        zerosField.setFont(mainFont);
        zerosField.setHorizontalAlignment(SwingConstants.CENTER);
        zerosField.setVerticalAlignment(SwingConstants.CENTER);
        zerosField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        getContentPane().add(zerosField);

        bottom = new JPanel();
        bottom.setBackground(Color.BLACK);
        bottom.setBounds(20,329,480,2);
        getContentPane().add(bottom);


    }

    private void initTable(){
        String[] columnNames = {"ID","Longitude", "Latitude", "Schatten", "Erreicht"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        for(Pillar i : values){
            model.addRow(new Object[]{
                    String.valueOf(i.getPillarID()),
                    String.valueOf(i.getLongitude()),
                    String.valueOf(i.getLatitude()),
                    i.getShadow().toString().toLowerCase(),
                    i.getPeopleReached().cardinality()
            });
        }
        this.table = new JTable(model);
        table.setFocusable(false);
        table.setSelectionBackground(hoverColor);
        table.setGridColor(Color.BLACK);
        table.setRowHeight(30);
        table.setFont(mainFont);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        JTableHeader header = table.getTableHeader();
        header.setFont(mainFont);
        header.setPreferredSize(new Dimension(header.getWidth(), 30));
        TableCellRenderer headerRenderer = new DefaultTableCellRenderer() {
            {
                setHorizontalAlignment(SwingConstants.CENTER);
                setOpaque(true);
            }
            @Override
            public Component getTableCellRendererComponent(
                    JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel l = (JLabel) super.getTableCellRendererComponent(table, value, false, false, row, column);

                l.setBackground(table.getTableHeader().getBackground());
                l.setForeground(table.getTableHeader().getForeground());

                l.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
                return l;
            }
        };
        header.setDefaultRenderer(headerRenderer);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20,20,480,200);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        getContentPane().add(scrollPane);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ArrayList<Pillar> c = new ArrayList<>();
            c.add(new Pillar(0,11,12, Shadow.KEIN));
            c.add(new Pillar(0,20,20, Shadow.NIEDRIG));
            c.add(new Pillar(0,30,30, Shadow.MITTEL));
            c.add(new Pillar(0,40,40, Shadow.HOCH));
            c.add(new Pillar(0,50,50, Shadow.HOCH));
            c.add(new Pillar(0,60,60, Shadow.HOCH));
            c.add(new Pillar(0,70,70, Shadow.HOCH));
            c.add(new Pillar(0,80,80, Shadow.HOCH));

            c.get(0).getPeopleReached().set(2);
            c.get(4).getPeopleReached().set(2);
            c.get(4).getPeopleReached().set(3);

            double[] av = {1,2,3,4};

            UFAnalysisCoverageWindow viewer = new UFAnalysisCoverageWindow(c,av);
            viewer.setVisible(true);
        });
    }

}
