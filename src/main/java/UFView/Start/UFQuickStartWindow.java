package UFView.Start;

import Model.Services.CsvReaderService;
import Model.Services.CsvWriterService;
import Model.Session;
import UFView.Export.UFExportProblemWindow;
import UFView.UFMainWindow;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;

public class UFQuickStartWindow extends JFrame {
    private JLabel pillarHeader;
    private JButton pillarSearch;
    private JTextField pillarPath;

    private JLabel peopleHeader;
    private JButton peopleSearch;
    private JTextField peoplePath;


    private JPanel seperationLineOne;


    private JLabel peopleOGHeader;
    private JButton peopleOGSearch;
    private JTextField peopleOGPath;

    private JPanel seperationLineTwo;

    private JCheckBox cLighthouse;

    private JTextField lighthousePath;
    private JButton lighthouseSearchButton;

    private JPanel seperationLineThree;

    private JButton next;

    private final Color defaultColor;
    private final Color hoverColor;
    private final Color clickColor;
    private final Font mainFont;

    private ImageIcon pillarIcon;
    private ImageIcon personIcon;
    private ImageIcon searchIcon;
    private ImageIcon startIcon;
    private ImageIcon lighthouseIcon;
    private ImageIcon checkSelectedIcon;
    private ImageIcon checkUnselectedIcon;



    public UFQuickStartWindow(){
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }


        defaultColor = new Color(220, 220, 220);
        hoverColor = new Color(170, 170, 170);
        clickColor = new Color(130, 130, 130);

        mainFont = new Font("Arial", Font.BOLD, 16);

        pillarIcon = new ImageIcon(getClass().getResource("/pillarIcon.png"));
        Image scaledImage = pillarIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        pillarIcon = new ImageIcon(scaledImage);

        personIcon = new ImageIcon(getClass().getResource("/singleIcon.png"));
        scaledImage = personIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        personIcon = new ImageIcon(scaledImage);

        searchIcon = new ImageIcon(getClass().getResource("/searchIcon.png"));
        scaledImage = searchIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        searchIcon = new ImageIcon(scaledImage);

        startIcon = new ImageIcon(getClass().getResource("/startIcon.png"));
        scaledImage = startIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        startIcon = new ImageIcon(scaledImage);

        lighthouseIcon = new ImageIcon(getClass().getResource("/lighthouseIcon.png"));
        scaledImage = lighthouseIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        lighthouseIcon = new ImageIcon(scaledImage);

        checkSelectedIcon = new ImageIcon(getClass().getResource("/checkboxSelectedIcon.png"));
        scaledImage = checkSelectedIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        checkSelectedIcon = new ImageIcon(scaledImage);

        checkUnselectedIcon = new ImageIcon(getClass().getResource("/checkboxUnselectedIcon.png"));
        scaledImage = checkUnselectedIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        checkUnselectedIcon = new ImageIcon(scaledImage);

        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/emergencityIcon.png"));
        setIconImage(icon);
        setTitle("Schnell-Start");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel content = new JPanel(null);
        content.setBackground(Color.WHITE);

        content.setPreferredSize(new Dimension(400, 430));

        setContentPane(content);

        initUI();
        pack();
        setLocationRelativeTo(null);
    }

    public void initUI(){
        pillarHeader = new JLabel("Litfaßsäulen Export CSV", pillarIcon, JLabel.LEFT);

        pillarHeader.setHorizontalTextPosition(SwingConstants.LEFT);
        pillarHeader.setVerticalTextPosition(SwingConstants.CENTER);
        pillarHeader.setBounds(20,20,320,20);
        pillarHeader.setFont(mainFont);
        getContentPane().add(pillarHeader);

        pillarSearch = new JButton("Suche",searchIcon);
        pillarSearch.setBounds(20,50,80,30);
        formatButton(pillarSearch);
        getContentPane().add(pillarSearch);

        pillarPath = new JTextField();
        pillarPath.setBounds(120,50,260,30);
        pillarPath.setFont(mainFont);
        Border lineBorder = BorderFactory.createLineBorder(Color.BLACK, 2);
        Border emptyBorder = new EmptyBorder(5, 10, 5, 10);
        pillarPath.setBorder(BorderFactory.createCompoundBorder(lineBorder, emptyBorder));
        getContentPane().add(pillarPath);


        peopleHeader = new JLabel("Personen Export CSV", personIcon, JLabel.LEFT);
        peopleHeader.setHorizontalTextPosition(SwingConstants.LEFT);
        peopleHeader.setVerticalTextPosition(SwingConstants.CENTER);
        peopleHeader.setBounds(20, 100, 280, 20);
        peopleHeader.setFont(mainFont);
        getContentPane().add(peopleHeader);

        peopleSearch = new JButton("Suche", searchIcon);
        peopleSearch.setBounds(20, 130, 80, 30);
        formatButton(peopleSearch);
        getContentPane().add(peopleSearch);

        peoplePath = new JTextField();
        peoplePath.setBounds(120, 130, 260, 30);
        peoplePath.setFont(mainFont);
        peoplePath.setBorder(BorderFactory.createCompoundBorder(lineBorder, emptyBorder));
        getContentPane().add(peoplePath);

        seperationLineOne = new JPanel();
        seperationLineOne.setBackground(Color.BLACK);
        seperationLineOne.setBounds(10,175,380,2);
        getContentPane().add(seperationLineOne);

        peopleOGHeader = new JLabel("Personen Original CSV", personIcon, JLabel.LEFT);
        peopleOGHeader.setHorizontalTextPosition(SwingConstants.LEFT);
        peopleOGHeader.setVerticalTextPosition(SwingConstants.CENTER);
        peopleOGHeader.setBounds(20, 190, 280, 20);
        peopleOGHeader.setFont(mainFont);
        getContentPane().add(peopleOGHeader);

        peopleOGSearch = new JButton("Suche", searchIcon);
        peopleOGSearch.setBounds(20, 220, 80, 30);
        formatButton(peopleOGSearch);
        getContentPane().add(peopleOGSearch);

        peopleOGPath = new JTextField();
        peopleOGPath.setBounds(120, 220, 260, 30);
        peopleOGPath.setFont(mainFont);
        peopleOGPath.setBorder(BorderFactory.createCompoundBorder(lineBorder, emptyBorder));
        getContentPane().add(peopleOGPath);

        seperationLineTwo = new JPanel();
        seperationLineTwo.setBackground(Color.BLACK);
        seperationLineTwo.setBounds(10,265,380,2);
        getContentPane().add(seperationLineTwo);

        cLighthouse = new JCheckBox("Katastrophenschutz-Leuchttürme");
        formatCheckBox(cLighthouse);
        cLighthouse.setBounds(15,280,320,20);
        getContentPane().add(cLighthouse);

        lighthousePath = new JTextField();
        lighthousePath.setBounds(120, 310, 260, 30);
        lighthousePath.setFont(mainFont);
        lighthousePath.setBackground(defaultColor);
        lighthousePath.setEnabled(false);
        lighthousePath.setBorder(BorderFactory.createCompoundBorder(lineBorder, emptyBorder));
        getContentPane().add(lighthousePath);

        lighthouseSearchButton = new JButton("Suche",searchIcon);
        lighthouseSearchButton.setBounds(20, 310, 80, 30);
        formatButton(lighthouseSearchButton);
        lighthouseSearchButton.setEnabled(false);
        getContentPane().add(lighthouseSearchButton);

        seperationLineThree = new JPanel();
        seperationLineThree.setBackground(Color.BLACK);
        seperationLineThree.setBounds(10,355,380,2);
        getContentPane().add(seperationLineThree);

        next = new JButton("Starten",startIcon);
        next.setBounds(10, 370, 380, 40);
        formatButton(next);
        getContentPane().add(next);

        //-------------BUTTON-FUNCTION----------------
        next.addActionListener((ActionEvent) -> {
            if (!peoplePath.getText().isEmpty() && !pillarPath.getText().isEmpty() && !peopleOGPath.getText().isEmpty() && (!cLighthouse.isSelected()||!lighthousePath.getText().isEmpty())) {
                CsvReaderService crs = new CsvReaderService();
                Session.getInstance().setOriginalPeopleFile(peopleOGPath.getText());
                crs.importPillars(pillarPath.getText());
                if(cLighthouse.isSelected()){
                    crs.importLighthouses(lighthousePath.getText());
                }
                crs.importPeople(peoplePath.getText());
                SwingUtilities.invokeLater(() -> {
                    UFMainWindow viewer = new UFMainWindow();
                    viewer.setVisible(true);
                });
                dispose();
            } else {
                SwingUtilities.invokeLater(() -> {
                    UFErrorWindow viewer = new UFErrorWindow("Noch nicht fertig", "spezifiziert!");
                    viewer.setVisible(true);
                });
            }

        });

        pillarSearch.addActionListener((ActionEvent)->{
            File startDir = new File("Data");
            JFileChooser fileChooser = new JFileChooser(startDir);
            fileChooser.setDialogTitle("Wähle deine Säulen Export CSV aus");
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                this.pillarPath.setText(selectedFile.getAbsolutePath());
            }
        });
        peopleSearch.addActionListener((ActionEvent) -> {
            File startDir = new File("Data");
            JFileChooser fileChooser = new JFileChooser(startDir);
            fileChooser.setDialogTitle("Wähle deine Personen Export CSV aus");
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                this.peoplePath.setText(selectedFile.getAbsolutePath());
            }
        });
        peopleOGSearch.addActionListener((ActionEvent) -> {
            File startDir = new File("Data");
            JFileChooser fileChooser = new JFileChooser(startDir);
            fileChooser.setDialogTitle("Wähle die original Verkehrssimulation aus");
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                this.peopleOGPath.setText(selectedFile.getAbsolutePath());
            }
        });
        lighthouseSearchButton.addActionListener((ActionEvent) -> {
            File startDir = new File("Data");
            JFileChooser fileChooser = new JFileChooser(startDir);
            fileChooser.setDialogTitle("Wähle deine Katastrophenschutz-Leuchttürme aus");
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                this.lighthousePath.setText(selectedFile.getAbsolutePath());
            }
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

        //DEBUGGING
        pillarPath.setText("C:/Users/phili/OneDrive/Desktop/Bachelor Evaluation/QuickStart-OhneAutos/pillarExport.csv");
        peoplePath.setText("C:/Users/phili/OneDrive/Desktop/Bachelor Evaluation/QuickStart-OhneAutos/personExport.csv");
        peopleOGPath.setText("C:/Users/phili/OneDrive/Desktop/Bachelor Evaluation/Verkehrs-Simulation-Ohne-Autos.csv");
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
            UFQuickStartWindow viewer = new UFQuickStartWindow();
            viewer.setVisible(true);
        });
    }


}
