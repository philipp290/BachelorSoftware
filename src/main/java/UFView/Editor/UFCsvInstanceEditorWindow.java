package UFView.Editor;

import UFController.Editor.UFCsvInstanceEditorController;
import UFController.Editor.UFCsvSchemaEditorController;
import UFView.Start.UFErrorWindow;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionListener;

public class UFCsvInstanceEditorWindow extends JFrame {
    private final JTextField browseField = new JTextField();
    private JButton browseButton;
    private JButton loadButton;
    private JTable table;
    private DefaultTableModel tableModel;
    private final JTextField deleteCol = new JTextField();
    private final JButton deleteButton = new JButton("Löschen");
    private final JTextField deleteWord = new JTextField();

    private final JTextField saveFile = new JTextField();
    private JButton saveFileBrowseButton;
    private final JButton saveButton = new JButton("Speichern");

    //------------------------------------------------------Function-Variables-------------
    private int colCount = 0;
    private String displayedFile = "";
    private String cacheFile1 = "Data/Cache/EditorCache/temporary1.csv";
    private String cacheFile2 = "Data/Cache/EditorCache/temporary2.csv";

    private final Color defaultColor;
    private final Color hoverColor;
    private final Color clickColor;
    private final Font mainFont;
    private final Border lineBorder;
    private final Border emptyBorder;

    private ImageIcon searchIcon;
    private ImageIcon loadIcon;


    public UFCsvInstanceEditorWindow() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/emergencityIcon.png"));
        setIconImage(icon);
        searchIcon = new ImageIcon(getClass().getResource("/searchIcon.png"));
        Image scaledImage = searchIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        searchIcon = new ImageIcon(scaledImage);

        loadIcon = new ImageIcon(getClass().getResource("/loadIcon.png"));
        scaledImage = loadIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        loadIcon = new ImageIcon(scaledImage);

        defaultColor = new Color(220, 220, 220);
        hoverColor = new Color(170, 170, 170);
        clickColor = new Color(130, 130, 130);
        mainFont = new Font("Arial", Font.BOLD, 16);

        lineBorder = BorderFactory.createLineBorder(Color.BLACK, 2);
        emptyBorder = new EmptyBorder(5, 10, 5, 10);

        setTitle("Editor");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel content = new JPanel(null);
        content.setBackground(Color.WHITE);
        content.setPreferredSize(new Dimension(520, 370));
        setContentPane(content);

        initUI();
        pack();
        setLocationRelativeTo(null);

    }

    private void initUI() {
        getContentPane().setLayout(null);
        //----------------------------------TOP--------------------
        browseButton = new JButton("Suchen");
        browseButton.setBounds(10,10,80,30);
        formatButton(browseButton);
        getContentPane().add(browseButton);

        browseField.setBounds(100,10,320,30);
        browseField.setEditable(true);
        formatTextField(browseField);
        getContentPane().add(browseField);

        loadButton = new JButton("Laden",loadIcon);
        loadButton.setBounds(430,10,80,30);
        formatButton(loadButton);
        getContentPane().add(loadButton);

        //----------------------------------CENTER--------------------
        tableModel = new DefaultTableModel();
        table = new JTable(tableModel);
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
        JScrollPane tableScrollPane = new JScrollPane(table);
        tableScrollPane.setBounds(10,50,500,200);
        tableScrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        getContentPane().add(tableScrollPane);

        //-----------------------------BOTTOM-LEFT--------------------
        JLabel deleteLabel = new JLabel("Zeile: ");
        deleteLabel.setBounds(20,270,80,30);
        deleteLabel.setFont(mainFont);
        getContentPane().add(deleteLabel);

        deleteCol.setBounds(70,270,50,30);
        formatTextField(deleteCol);
        getContentPane().add(deleteCol);

        deleteButton.setBounds(20,310, 330,30);
        formatButton(deleteButton);
        getContentPane().add(deleteButton);

        //-----------------------------BOTTOM-CENTER--------------------
        JLabel switchLabel = new JLabel("Begriff:");
        switchLabel.setBounds(140,270,80,30);
        switchLabel.setFont(mainFont);
        getContentPane().add(switchLabel);

        deleteWord.setBounds(210,270,140,30);
        formatTextField(deleteWord);
        getContentPane().add(deleteWord);


        //-----------------------------BOTTOM-RIGHT--------------------
        saveFile.setBounds(370, 270, 70,30);
        formatTextField(saveFile);
        getContentPane().add(saveFile);

        saveFileBrowseButton = new JButton(searchIcon);
        saveFileBrowseButton.setBounds(450, 270, 50,30);
        formatButton(saveFileBrowseButton);
        getContentPane().add(saveFileBrowseButton);

        saveButton.setBounds(370,310, 130,30);
        formatButton(saveButton);
        getContentPane().add(saveButton);

        //-----------------------------BOTTOM-LINE-WORK--------------------
        JPanel lineOne = new JPanel();
        lineOne.setBackground(Color.BLACK);
        lineOne.setBounds(10,261,500,2);
        getContentPane().add(lineOne);

        JPanel lineTwo = new JPanel();
        lineTwo.setBackground(Color.BLACK);
        lineTwo.setBounds(10,351,500,2);
        getContentPane().add(lineTwo);

        JPanel lineThree = new JPanel();
        lineThree.setBackground(Color.BLACK);
        lineThree.setBounds(10,261,2,90);
        getContentPane().add(lineThree);

        JPanel lineFive = new JPanel();
        lineFive.setBackground(Color.BLACK);
        lineFive.setBounds(359,261,2,90);
        getContentPane().add(lineFive);

        JPanel lineSix = new JPanel();
        lineSix.setBackground(Color.BLACK);
        lineSix.setBounds(508,261,2,90);
        getContentPane().add(lineSix);
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

    private void formatTextField(JTextField txt){
        txt.setFont(mainFont);
        txt.setBorder(BorderFactory.createCompoundBorder(lineBorder, emptyBorder));
    }


    //------------------------CsvEditorController-Export---------------------------------------

    public void addBrowseListener(ActionListener l){ browseButton.addActionListener(l); }
    public void addLoadListener(ActionListener l){ loadButton.addActionListener(l); }
    public void addDeleteListener(ActionListener l){ deleteButton.addActionListener(l); }

    public void addSaveBrowseListener(ActionListener l){ saveFileBrowseButton.addActionListener(l); }
    public void addSaveListener(ActionListener l){ saveButton.addActionListener(l); }

    public String getPathText(){ return browseField.getText().trim(); }
    public void setPathText(String t){ browseField.setText(t); }

    public String getDeleteCol(){ return deleteCol.getText().trim(); }
    public void clearDeleteCol(){ deleteCol.setText(""); }

    public String getDeleteWord(){ return deleteWord.getText().trim(); }
    public void clearDeleteWord(){ deleteWord.setText(""); }

    public String getSaveFile(){ return saveFile.getText().trim(); }
    public void setSaveFile(String t){ saveFile.setText(t); }

    public DefaultTableModel getTableModel(){return tableModel;}

    public void showError(String msg1, String msg2){
        SwingUtilities.invokeLater(() -> {
            UFErrorWindow viewer = new UFErrorWindow(msg1, msg2);
            viewer.setVisible(true);
        });
    }



    //--------------------------------------------------------DEBUGGING---------------
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UFCsvInstanceEditorWindow viewer = new UFCsvInstanceEditorWindow();
            new UFCsvInstanceEditorController(viewer);
            viewer.setVisible(true);
        });
    }
}
