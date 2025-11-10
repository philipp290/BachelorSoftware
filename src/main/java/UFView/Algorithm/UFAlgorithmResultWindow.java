package UFView.Algorithm;

import Model.Components.Pillar;
import Model.Components.Shadow;
import Model.Session;
import UFView.Start.UFErrorWindow;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.*;
import java.awt.*;
import java.util.ArrayList;

public class UFAlgorithmResultWindow extends JFrame {
    private JTable table;
    private DefaultTableModel model;

    private JCheckBox cSave;
    private JLabel bottomText;
    private JTextField savename;

    private JButton next;

    private final Color defaultColor;
    private final Color hoverColor;
    private final Color clickColor;
    private final Font mainFont;

    private ImageIcon startIcon;
    private ImageIcon checkSelectedIcon;
    private ImageIcon checkUnselectedIcon;

    private ArrayList<Pillar> values;
    public UFAlgorithmResultWindow(ArrayList<Pillar> con){
        this.values = con;

        defaultColor = new Color(220, 220, 220);
        hoverColor = new Color(170, 170, 170);
        clickColor = new Color(130, 130, 130);

        mainFont = new Font("Arial", Font.BOLD, 16);

        startIcon = new ImageIcon(getClass().getResource("/startIcon.png"));
        Image scaledImage = startIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        startIcon = new ImageIcon(scaledImage);

        checkSelectedIcon = new ImageIcon(getClass().getResource("/checkboxSelectedIcon.png"));
        scaledImage = checkSelectedIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        checkSelectedIcon = new ImageIcon(scaledImage);

        checkUnselectedIcon = new ImageIcon(getClass().getResource("/checkboxUnselectedIcon.png"));
        scaledImage = checkUnselectedIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        checkUnselectedIcon = new ImageIcon(scaledImage);

        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/emergencityIcon.png"));
        setIconImage(icon);
        setTitle("Ergebnis");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel content = new JPanel(null);
        content.setBackground(Color.WHITE);
        content.setPreferredSize(new Dimension(520, 300));
        setContentPane(content);

        initTable();
        initUI();
        pack();
        setLocationRelativeTo(null);

    }

    public void initUI(){
        cSave = new JCheckBox();
        formatCheckBox(cSave);
        cSave.setBounds(20,200,40,40);
        getContentPane().add(cSave);

        bottomText = new JLabel("Lösung speichern unter folgendem Namen:");
        bottomText.setBounds(50,200,340,40);
        bottomText.setFont(mainFont);
        getContentPane().add(bottomText);

        Border lineBorder = BorderFactory.createLineBorder(Color.BLACK, 2);
        Border emptyBorder = new EmptyBorder(5, 10, 5, 10);

        savename = new JTextField();
        savename.setBounds(400,205,100,30);
        savename.setFont(mainFont);
        savename.setBorder(BorderFactory.createCompoundBorder(lineBorder, emptyBorder));
        savename.setBackground(defaultColor);
        savename.setOpaque(true);
        savename.setEnabled(false);
        savename.setDisabledTextColor(Color.BLACK);
        getContentPane().add(savename);

        next = new JButton("Weiter",startIcon);
        next.setBounds(380,255,120,30);
        formatButton(next);
        getContentPane().add(next);

        //--------BUTTON-FUNCTION---------------------------
        cSave.addActionListener(e -> {
            if (cSave.isSelected()) {
                savename.setBackground(Color.WHITE);
                savename.setEnabled(true);
            } else {
                savename.setBackground(defaultColor);
                savename.setEnabled(false);
            }
        });
        next.addActionListener(e -> {
            if(cSave.isSelected()){
                if(!savename.getText().isEmpty()){
                    Session.getInstance().getSolutionKeys().add(savename.getText());
                    Session.getInstance().getSolutionCache().put(savename.getText(),values);
                    dispose();
                }else{
                    SwingUtilities.invokeLater(() -> {
                        UFErrorWindow viewer = new UFErrorWindow("Gib deiner Lösung", "einen Namen!");
                        viewer.setVisible(true);
                    });
                }
            }else {
                dispose();
            }
        });
    }

    private void initTable(){
        String[] columnNames = {"ID","Longitude", "Latitude", "Schatten"};
        this.model = new DefaultTableModel(columnNames, 0);

        for(Pillar i : values){
            model.addRow(new String[]{
                    String.valueOf(i.getPillarID()),
                    String.valueOf(i.getLongitude()),
                    String.valueOf(i.getLatitude()),
                    i.getShadow().toString().toLowerCase(),
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
        for (int i = 0; i < table.getColumnCount(); i++) {table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);}

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
        scrollPane.setBounds(20,20,480,160);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        getContentPane().add(scrollPane);
    }

    private void formatButton(JButton button){
        button.setHorizontalTextPosition(SwingConstants.LEFT);
        button.setVerticalTextPosition(SwingConstants.CENTER);
        button.setBackground(defaultColor);
        button.setForeground(Color.BLACK);
        button.setFont(mainFont);
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(true);
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {button.setBackground(hoverColor);}
            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {button.setBackground(defaultColor);}
            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {button.setBackground(clickColor);}
            @Override
            public void mouseReleased(java.awt.event.MouseEvent e) {
                if (button.contains(e.getPoint())) {
                    button.setBackground(hoverColor);
                } else {
                    button.setBackground(defaultColor);
                }
            }
        });
    }

    private void formatCheckBox(JCheckBox box){
        box.setFont(mainFont);
        box.setBackground(Color.WHITE);
        box.setIcon(checkUnselectedIcon);
        box.setSelectedIcon(checkSelectedIcon);
        box.setFocusPainted(false);
        box.setOpaque(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ArrayList<Pillar> c = new ArrayList<>();
            c.add(new Pillar(0,11,12, Shadow.KEIN));
            c.add(new Pillar(1,20,20, Shadow.NIEDRIG));
            c.add(new Pillar(2,30,30, Shadow.MITTEL));
            c.add(new Pillar(3,40,40, Shadow.HOCH));
            c.add(new Pillar(4,50,50, Shadow.HOCH));
            c.add(new Pillar(5,60,60, Shadow.HOCH));
            c.add(new Pillar(6,70,70, Shadow.HOCH));
            c.add(new Pillar(7,80,80, Shadow.HOCH));

            UFAlgorithmResultWindow viewer = new UFAlgorithmResultWindow(c);
            viewer.setVisible(true);
        });
    }
}
