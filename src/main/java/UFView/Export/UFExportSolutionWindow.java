package UFView.Export;

import Model.Components.Pillar;
import Model.Services.CsvUpdateService;
import Model.Session;
import UFView.Start.UFErrorWindow;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class UFExportSolutionWindow extends JFrame {
    private JLabel solutionLabel;
    private JComboBox solution;
    private JLabel goalHeader;
    private JButton goalSearch;
    private JTextField goalPath;
    private JPanel seperationLineOne;
    private JCheckBox cLighthouse;
    private JLabel lighthouseLabel;
    private JTextField lighthousePath;
    private JButton lighthouseSearchButton;
    private JPanel seperationLineTwo;


    private JButton start;

    private ImageIcon checkSelectedIcon;
    private ImageIcon checkUnselectedIcon;
    private ImageIcon startIcon;
    private ImageIcon searchIcon;

    private final Color defaultColor;
    private final Color hoverColor;
    private final Color clickColor;
    private final Font mainFont;
    private String[] solutionKeys;

    public UFExportSolutionWindow(){
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

        checkSelectedIcon = new ImageIcon(getClass().getResource("/checkboxSelectedIcon.png"));
        scaledImage = checkSelectedIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        checkSelectedIcon = new ImageIcon(scaledImage);

        checkUnselectedIcon = new ImageIcon(getClass().getResource("/checkboxUnselectedIcon.png"));
        scaledImage = checkUnselectedIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        checkUnselectedIcon = new ImageIcon(scaledImage);

        setTitle("Export");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel content = new JPanel(null);
        content.setBackground(Color.WHITE);
        content.setPreferredSize(new Dimension(400, 250));
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
        solution.setBounds(170,20,210,20);
        formatComboBox(solution);
        getContentPane().add(solution);

        goalHeader = new JLabel("Lösungs Ordner");
        goalHeader.setHorizontalTextPosition(SwingConstants.LEFT);
        goalHeader.setVerticalTextPosition(SwingConstants.CENTER);
        goalHeader.setBounds(20, 60, 280, 20);
        goalHeader.setFont(mainFont);
        getContentPane().add(goalHeader);

        goalSearch = new JButton(searchIcon);
        goalSearch.setBounds(330, 60, 50, 30);
        formatButton(goalSearch);
        getContentPane().add(goalSearch);

        Border lineBorder = BorderFactory.createLineBorder(Color.BLACK, 2);
        Border emptyBorder = new EmptyBorder(5, 10, 5, 10);

        goalPath = new JTextField();
        goalPath.setBounds(170, 60, 150, 30);
        goalPath.setFont(mainFont);
        goalPath.setBorder(BorderFactory.createCompoundBorder(lineBorder, emptyBorder));
        getContentPane().add(goalPath);

        seperationLineOne = new JPanel();
        seperationLineOne.setBackground(Color.BLACK);
        seperationLineOne.setBounds(10,104,380,2);
        getContentPane().add(seperationLineOne);

        cLighthouse = new JCheckBox("Katastrophenschutz-Leuchttürme");
        formatCheckBox(cLighthouse);
        cLighthouse.setBounds(15,115,320,20);
        getContentPane().add(cLighthouse);

        lighthouseLabel = new JLabel("Ordner");
        lighthouseLabel.setBounds(20, 145, 280, 20);
        lighthouseLabel.setFont(mainFont);
        getContentPane().add(lighthouseLabel);

        lighthousePath = new JTextField();
        lighthousePath.setBounds(170, 145, 150, 30);
        lighthousePath.setFont(mainFont);
        lighthousePath.setBackground(defaultColor);
        lighthousePath.setEnabled(false);
        lighthousePath.setBorder(BorderFactory.createCompoundBorder(lineBorder, emptyBorder));
        getContentPane().add(lighthousePath);

        lighthouseSearchButton = new JButton(searchIcon);
        lighthouseSearchButton.setBounds(330, 145, 50, 30);
        formatButton(lighthouseSearchButton);
        lighthouseSearchButton.setEnabled(false);
        getContentPane().add(lighthouseSearchButton);

        seperationLineTwo = new JPanel();
        seperationLineTwo.setBackground(Color.BLACK);
        seperationLineTwo.setBounds(10,187,380,2);
        getContentPane().add(seperationLineTwo);

        start = new JButton("Exportieren",startIcon);
        start.setBounds(10,200,380,40);
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
        lighthouseSearchButton.addActionListener((ActionEvent)->{
            File startDir = new File("Data");
            JFileChooser fileChooser = new JFileChooser(startDir);
            fileChooser.setDialogTitle("Wähle den Ziel Ordner aus");
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                this.lighthousePath.setText(selectedFile.getAbsolutePath());
            }
        });
        start.addActionListener((ActionEvent)->{

        });
        cLighthouse.addActionListener((ActionEvent)->{
            if(cLighthouse.isSelected()){
                lighthousePath.setBackground(Color.WHITE);
                lighthousePath.setEnabled(true);
                lighthouseSearchButton.setEnabled(true);
            }else{
                lighthousePath.setBackground(defaultColor);
                lighthousePath.setEnabled(false);
                lighthouseSearchButton.setEnabled(false);
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
            UFExportSolutionWindow viewer = new UFExportSolutionWindow();
            viewer.setVisible(true);
        });
    }









}
