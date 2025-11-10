package DView.Editor;

import DController.Editor.CsvEditorController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class CsvEditorWindow extends JFrame {
   //--------------------------------------------UI-Elemente-------------------
    private final JTextField browseField = new JTextField();
    private final JButton browseButton = new JButton("Datei suchen");
    private final JButton loadButton = new JButton("CSV laden");
    private JTable table;
    private DefaultTableModel tableModel;
    private final JTextField deleteCol = new JTextField();
    private final JButton deleteButton = new JButton("Löschen");
    private final JTextField switchCol1 = new JTextField();
    private final JTextField switchCol2 = new JTextField();
    private final JButton switchButton = new JButton("Tauschen");
    private final JTextField saveFile = new JTextField();
    private final JButton saveFileBrowseButton = new JButton("Zielordner");
    private final JButton saveButton = new JButton("Speichern");

    //------------------------------------------------------Function-Variables-------------
    private int colCount = 0;
    private String displayedFile = "";
    private String cacheFile1 = "Data/Cache/EditorCache/temporary1.csv";
    private String cacheFile2 = "Data/Cache/EditorCache/temporary2.csv";




    public CsvEditorWindow() {
        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/emergencityIcon.png"));
        setIconImage(icon);

        setTitle("CSV-Editor");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        initUI();
    }

    private void initUI() {
        getContentPane().setLayout(null);
        //----------------------------------TOP--------------------
        browseField.setBounds(150,4,500,40);
        browseField.setEditable(true);
        getContentPane().add(browseField);

        browseButton.setBounds(0,3,150,40);
        getContentPane().add(browseButton);

        loadButton.setBounds(650,3,150,40);
        getContentPane().add(loadButton);

        //----------------------------------CENTER--------------------
        tableModel = new DefaultTableModel();
        table = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(table);
        tableScrollPane.setBounds(0,50,800,200);
        getContentPane().add(tableScrollPane);

        //-----------------------------BOTTOM-LEFT--------------------
        JLabel deleteLabel = new JLabel("Lösche folgende Splate: ");
        deleteLabel.setBounds(10,270,160,40);
        getContentPane().add(deleteLabel);

        deleteCol.setBounds(180,270,40,40);
        getContentPane().add(deleteCol);

        deleteButton.setBounds(0,320, 220,40);
        getContentPane().add(deleteButton);

        //-----------------------------BOTTOM-CENTER--------------------
        JLabel switchLabel = new JLabel("Tausch-Spalten: ");
        switchLabel.setBounds(300,270,110,40);
        getContentPane().add(switchLabel);

        switchCol1.setBounds(420,270,40,40);
        getContentPane().add(switchCol1);

        switchCol2.setBounds(470,270,40,40);
        getContentPane().add(switchCol2);

        switchButton.setBounds(290,320, 220,40);
        getContentPane().add(switchButton);

        //-----------------------------BOTTOM-RIGHT--------------------
        saveFile.setBounds(580, 270, 100,40);
        getContentPane().add(saveFile);

        saveFileBrowseButton.setBounds(690, 270, 110,40);
        getContentPane().add(saveFileBrowseButton);

        saveButton.setBounds(580,320, 220,40);
        getContentPane().add(saveButton);

        //-----------------------------BOTTOM-LINE-WORK--------------------
        JSeparator lineOne = new JSeparator(SwingConstants.VERTICAL);
        lineOne.setBounds(255,250,2,150);
        getContentPane().add(lineOne);

        JSeparator lineTwo = new JSeparator(SwingConstants.VERTICAL);
        lineTwo.setBounds(545,250,2,150);
        getContentPane().add(lineTwo);
    }



    //------------------------CsvEditorController-Export---------------------------------------

    public void addBrowseListener(ActionListener l){ browseButton.addActionListener(l); }
    public void addLoadListener(ActionListener l){ loadButton.addActionListener(l); }
    public void addDeleteListener(ActionListener l){ deleteButton.addActionListener(l); }
    public void addSwitchListener(ActionListener l){ switchButton.addActionListener(l); }
    public void addSaveBrowseListener(ActionListener l){ saveFileBrowseButton.addActionListener(l); }
    public void addSaveListener(ActionListener l){ saveButton.addActionListener(l); }

    public String getPathText(){ return browseField.getText().trim(); }
    public void setPathText(String t){ browseField.setText(t); }

    public String getDeleteCol(){ return deleteCol.getText().trim(); }
    public void clearDeleteCol(){ deleteCol.setText(""); }

    public String getSwapCol1(){ return switchCol1.getText().trim(); }
    public String getSwapCol2(){ return switchCol2.getText().trim(); }
    public void clearSwapCols(){ switchCol1.setText(""); switchCol2.setText(""); }

    public String getSaveFile(){ return saveFile.getText().trim(); }
    public void setSaveFile(String t){ saveFile.setText(t); }

    public DefaultTableModel getTableModel(){return tableModel;}

    public void showError(String msg){ JOptionPane.showMessageDialog(this, msg, "Fehler", JOptionPane.ERROR_MESSAGE); }



    //--------------------------------------------------------DEBUGGING---------------
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CsvEditorWindow viewer = new CsvEditorWindow();
            new CsvEditorController(viewer);
            viewer.setVisible(true);
        });
    }
}
