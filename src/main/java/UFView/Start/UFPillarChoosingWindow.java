package UFView.Start;

import Model.Components.Pillar;
import Model.Components.Shadow;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.util.ArrayList;

public class UFPillarChoosingWindow extends JFrame {
    private JLabel header;
    private JTable table;
    private DefaultTableModel model;
    private JButton next;

    private ImageIcon startIcon;
    private ImageIcon yesIcon;
    private ImageIcon noIcon;
    private final Color defaultColor;
    private final Color hoverColor;
    private final Color clickColor;
    private final Font mainFont;

    private ArrayList<Pillar> values;
    public UFPillarChoosingWindow(ArrayList<Pillar> con){
        this.values = con;

        defaultColor = new Color(220, 220, 220);
        hoverColor = new Color(170, 170, 170);
        clickColor = new Color(130, 130, 130);

        mainFont = new Font("Arial", Font.BOLD, 16);

        startIcon = new ImageIcon(getClass().getResource("/startIcon.png"));
        Image scaledImage = startIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        startIcon = new ImageIcon(scaledImage);

        yesIcon = new ImageIcon(getClass().getResource("/yesIcon.png"));
        scaledImage = yesIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        yesIcon = new ImageIcon(scaledImage);

        noIcon = new ImageIcon(getClass().getResource("/noIcon.png"));
        scaledImage = noIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        noIcon = new ImageIcon(scaledImage);

        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/emergencityIcon.png"));
        setIconImage(icon);
        setTitle("Auswahl");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel content = new JPanel(null);
        content.setBackground(Color.WHITE);
        content.setPreferredSize(new Dimension(520, 300));
        setContentPane(content);

        initUI();
        pack();
        setLocationRelativeTo(null);

        initTable();
        initUI();
    }

    public void initUI(){
        header = new JLabel("Kreuzen Sie an, welche Litfaßsäulen bereits gesetzt wurden:");
        header.setBounds(20,20,500,20);
        header.setFont(mainFont);
        getContentPane().add(header);

        next = new JButton("Weiter",startIcon);
        next.setBounds(380,240,120,40);
        next.setHorizontalTextPosition(SwingConstants.LEFT);
        next.setVerticalTextPosition(SwingConstants.CENTER);
        next.setBackground(defaultColor);
        next.setForeground(Color.BLACK);
        next.setFont(mainFont);
        next.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        next.setFocusPainted(false);
        next.setContentAreaFilled(false);
        next.setOpaque(true);
        //TODO Hovern harkt hier
        next.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {next.setBackground(hoverColor);}
            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {next.setBackground(defaultColor);}
            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {next.setBackground(clickColor);}
            @Override
            public void mouseReleased(java.awt.event.MouseEvent e) {
                if (next.contains(e.getPoint())) {
                    next.setBackground(hoverColor);
                } else {
                    next.setBackground(defaultColor);
                }
            }
        });
        getContentPane().add(next);
    }

    private void initTable(){
        String[] columnNames = {"Longitude", "Latitude", "Schatten", "Gesetzt"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 3) return Boolean.class;
                return String.class;
            }
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 3;
            }
        };

        for(Pillar i : values){
            model.addRow(new String[]{String.valueOf(i.getLongitude()), String.valueOf(i.getLatitude()),i.getShadow().toString().toLowerCase(), "false"});
        }
        this.table = new JTable(model);
        table.setFocusable(false);
        table.setSelectionBackground(hoverColor);
        table.setGridColor(Color.BLACK);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        TableCellRenderer iconRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus,
                                                           int row, int column) {
                JLabel label = new JLabel();
                label.setHorizontalAlignment(SwingConstants.CENTER);

                if (Boolean.TRUE.equals(value)) {
                    label.setIcon(yesIcon);
                } else {
                    label.setIcon(noIcon);
                }

                if (isSelected) {
                    label.setOpaque(true);
                    label.setBackground(table.getSelectionBackground());
                }
                return label;
            }
        };
        table.getColumnModel().getColumn(3).setCellRenderer(iconRenderer);

        table.setRowHeight(30);
        table.setFont(mainFont);

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
        scrollPane.setBounds(20,60,480,160);
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

            UFPillarChoosingWindow viewer = new UFPillarChoosingWindow(c);
            viewer.setVisible(true);
        });
    }
}
