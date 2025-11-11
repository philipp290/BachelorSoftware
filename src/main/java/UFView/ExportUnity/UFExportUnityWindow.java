package UFView.ExportUnity;

import Model.Components.Pillar;
import Model.Services.CsvUpdateService;
import Model.Session;
import UFView.Comparator.UFComparatorWindow;
import UFView.Start.UFErrorWindow;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;

public class UFExportUnityWindow extends JFrame {
    private JLabel solutionLabel;
    private JComboBox solution;
    private JLabel goalHeader;
    private JButton goalSearch;
    private JTextField goalPath;
    private JPanel seperationLineOne;
    private JButton start;


    private ImageIcon startIcon;
    private ImageIcon searchIcon;
    private ImageIcon goalIcon;

    private final Color defaultColor;
    private final Color hoverColor;
    private final Color clickColor;
    private final Font mainFont;
    private String[] solutionKeys;

    public UFExportUnityWindow(){
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        solutionKeys = Session.getInstance().getSolutionKeys().toArray(new String[0]);

        defaultColor = new Color(220, 220, 220);
        hoverColor = new Color(170, 170, 170);
        clickColor = new Color(130, 130, 130);
        mainFont = new Font("Arial", Font.BOLD, 16);

        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/emergencityIcon.png"));
        setIconImage(icon);

        startIcon = new ImageIcon(getClass().getResource("/startIcon.png"));
        Image scaledImage = startIcon.getImage().getScaledInstance(18, 18, Image.SCALE_SMOOTH);
        startIcon = new ImageIcon(scaledImage);

        searchIcon = new ImageIcon(getClass().getResource("/searchIcon.png"));
        scaledImage = searchIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        searchIcon = new ImageIcon(scaledImage);

        goalIcon = new ImageIcon(getClass().getResource("/goalIcon.png"));
        scaledImage = goalIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        goalIcon = new ImageIcon(scaledImage);

        setTitle("Export");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel content = new JPanel(null);
        content.setBackground(Color.WHITE);
        content.setPreferredSize(new Dimension(400, 220));
        setContentPane(content);

        initUI();
        pack();
        setLocationRelativeTo(null);
    }

    public void initUI(){
        solutionLabel = new JLabel("Export-Lösung");
        solutionLabel.setFont(mainFont);
        solutionLabel.setBounds(20,20,140,20);
        getContentPane().add(solutionLabel);

        solution = new JComboBox<>(solutionKeys);
        solution.setBounds(140,20,240,20);
        formatComboBox(solution);
        getContentPane().add(solution);

        goalHeader = new JLabel("Ziel Ordner", goalIcon, JLabel.LEFT);
        goalHeader.setHorizontalTextPosition(SwingConstants.LEFT);
        goalHeader.setVerticalTextPosition(SwingConstants.CENTER);
        goalHeader.setBounds(20, 60, 280, 20);
        goalHeader.setFont(mainFont);
        getContentPane().add(goalHeader);

        goalSearch = new JButton("Suche", searchIcon);
        goalSearch.setBounds(20, 90, 80, 30);
        formatButton(goalSearch);
        getContentPane().add(goalSearch);

        Border lineBorder = BorderFactory.createLineBorder(Color.BLACK, 2);
        Border emptyBorder = new EmptyBorder(5, 10, 5, 10);

        goalPath = new JTextField();
        goalPath.setBounds(120, 90, 260, 30);
        goalPath.setFont(mainFont);
        goalPath.setBorder(BorderFactory.createCompoundBorder(lineBorder, emptyBorder));
        getContentPane().add(goalPath);

        seperationLineOne = new JPanel();
        seperationLineOne.setBackground(Color.BLACK);
        seperationLineOne.setBounds(10,139,380,2);
        getContentPane().add(seperationLineOne);

        start = new JButton("Exportieren",startIcon);
        start.setBounds(10,160,380,40);
        formatButton(start);
        getContentPane().add(start);

        //---------------BUTTON-FUNCTION----------------------
        goalSearch.addActionListener((ActionEvent)->{
            File startDir = new File("Data");
            JFileChooser fileChooser = new JFileChooser(startDir);
            fileChooser.setDialogTitle("Wähle den Ziel Ordner aus");
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                this.goalPath.setText(selectedFile.getAbsolutePath());
            }
        });
        start.addActionListener((ActionEvent)->{
            if(!goalPath.getText().isEmpty() && !solution.getSelectedItem().toString().isEmpty()){
                CsvUpdateService cus = new CsvUpdateService();
                String saveFile = goalPath +"/"+ solution.getSelectedItem().toString()+"UnityExport.csv";
                ArrayList<Pillar> pillars = Session.getInstance().getSolutionCache().get(solution.getSelectedItem().toString());
                cus.solutionUnityExport(Session.getInstance().getOriginalPeopleFile(), saveFile, pillars, Session.getInstance().getReachingDistance());
                dispose();
            }else{
                SwingUtilities.invokeLater(() -> {
                    UFErrorWindow viewer = new UFErrorWindow("Noch nicht fertig", "spezifiziert!");
                    viewer.setVisible(true);
                });
            }





        });

    }



    public void formatComboBox(JComboBox box) {
        box.setFont(mainFont);
        box.setFocusable(false);
        box.setBorder(new LineBorder(Color.BLACK, 2));
        box.setSelectedIndex(-1);
        box.setUI(new javax.swing.plaf.basic.BasicComboBoxUI() {
            @Override
            protected JButton createArrowButton() {
                JButton button = new JButton() {
                    @Override
                    protected void paintComponent(Graphics g) {
                        g.setColor(getBackground());
                        g.fillRect(0, 0, getWidth(), getHeight());

                        g.setColor(Color.BLACK);
                        g.fillRect(0, 0, 2, getHeight());

                        g.setColor(Color.BLACK);
                        int w = getWidth();
                        int h = getHeight();
                        int offsetX = 2;

                        g.fillPolygon(
                                new int[]{w / 2 - 5 + offsetX, w / 2 + 5 + offsetX, w / 2 + offsetX},
                                new int[]{h / 2 - 2, h / 2 - 2, h / 2 + 4},
                                3
                        );
                    }
                };
                button.setBorderPainted(false);
                button.setFocusPainted(false);
                button.setContentAreaFilled(false);
                button.setOpaque(true);
                button.setBackground(Color.WHITE);
                return button;
            }
        });
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

    public static void main(String[] args) {
        Session.getInstance().getSolutionKeys().add("Test1");
        Session.getInstance().getSolutionKeys().add("Test2");
        Session.getInstance().getSolutionKeys().add("Test3");
        Session.getInstance().getSolutionKeys().add("Test4");
        SwingUtilities.invokeLater(() -> {
            UFExportUnityWindow viewer = new UFExportUnityWindow();
            viewer.setVisible(true);
        });
    }
}
