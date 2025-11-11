package UFView.Analysis;

import Model.Components.Pillar;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.util.ArrayList;

public class UFAnalysisEncounterLengthDetailWindow extends JFrame {

    private JLabel header;
    private JTable table;
    private DefaultTableModel model;

    ArrayList<Integer> values;

    Font mainFont;
    private final Color defaultColor;
    private final Color hoverColor;
    private final Color clickColor;

    ImageIcon timeIcon;

    public UFAnalysisEncounterLengthDetailWindow(ArrayList<Integer> v) {
        this.values = v;
        mainFont = new Font("Arial", Font.BOLD, 16);
        defaultColor = new Color(220, 220, 220);
        hoverColor = new Color(170, 170, 170);
        clickColor = new Color(130, 130, 130);

        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/emergencityIcon.png"));
        setIconImage(icon);

        timeIcon = new ImageIcon(getClass().getResource("/timeIcon.png"));
        Image scaledImage = timeIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        timeIcon = new ImageIcon(scaledImage);

        setTitle("Analysis");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel content = new JPanel(null);
        content.setBackground(Color.WHITE);
        content.setPreferredSize(new Dimension(280, 280));
        setContentPane(content);

        initTable();
        initUI();
        pack();
        setLocationRelativeTo(null);


    }

    private void initUI(){
        header = new JLabel("Verweil-Dauer-Verteilung ",timeIcon,JLabel.LEFT);
        header.setHorizontalTextPosition(SwingConstants.LEFT);
        header.setVerticalTextPosition(SwingConstants.CENTER);
        header.setFont(mainFont);
        header.setBounds(20,10,240,40);
        getContentPane().add(header);
    }

    private void initTable(){
        String[] columnNames = {"Dauer","Aufkommen"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        int c = 1;
        for(Integer i : values){
            model.addRow(new Object[]{c,i});
            c++;
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
        scrollPane.setBounds(20,55,240,205);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        getContentPane().add(scrollPane);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ArrayList<Integer> t = new ArrayList<>();
            t.add(30);
            t.add(2222);
            t.add(45);
            t.add(509);
            t.add(777);
            t.add(4632);
            t.add(0);
            t.add(3);
            UFAnalysisEncounterLengthDetailWindow viewer = new UFAnalysisEncounterLengthDetailWindow(t);
            viewer.setVisible(true);
        });
    }
}
