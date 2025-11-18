package UFView.Export;

import Model.Components.Pillar;
import Model.Services.CsvReaderService;
import Model.Services.CsvWriterService;
import Model.Session;
import UFView.Start.UFCrossingWindow;
import UFView.Start.UFErrorWindow;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

public class UFExportProblemWindow extends JFrame {
    private JLabel pillarHeader;
    private JButton pillarSearch;
    private JTextField pillarPath;

    private JLabel peopleHeader;
    private JButton peopleSearch;
    private JTextField peoplePath;


    private JPanel seperationLine;
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



    public UFExportProblemWindow(){
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

        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/emergencityIcon.png"));
        setIconImage(icon);
        setTitle("Export");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel content = new JPanel(null);
        content.setBackground(Color.WHITE);

        content.setPreferredSize(new Dimension(400, 245));

        setContentPane(content);

        initUI();
        pack();
        setLocationRelativeTo(null);
    }

    public void initUI(){
        pillarHeader = new JLabel("Litfaßsäulen Ordner", pillarIcon, JLabel.LEFT);

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


        peopleHeader = new JLabel("Personen Ordner", personIcon, JLabel.LEFT);
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

        seperationLine = new JPanel();
        seperationLine.setBackground(Color.BLACK);
        seperationLine.setBounds(10,175,380,2);
        getContentPane().add(seperationLine);

        next = new JButton("Exportieren",startIcon);
        next.setBounds(10, 190, 380, 40);

        formatButton(next);
        getContentPane().add(next);

        //-------------BUTTON-FUNCTION----------------
        next.addActionListener((ActionEvent) -> {
            if (!peoplePath.getText().isEmpty() && !pillarPath.getText().isEmpty()) {
                CsvWriterService cws = new CsvWriterService();
                cws.exportPillarsToFile(pillarPath.getText()+"/pillarExport.csv");
                cws.exportPeopleToFile(peoplePath.getText()+"/personExport.csv");
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
            fileChooser.setDialogTitle("Wähle den Ziel-Ordner für die Säulen");
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                this.pillarPath.setText(selectedFile.getAbsolutePath());
            }
        });
        peopleSearch.addActionListener((ActionEvent) -> {
                File startDir = new File("Data");
                JFileChooser fileChooser = new JFileChooser(startDir);
                fileChooser.setDialogTitle("Wähle den Ziel-Ordner für die Personen");
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int result = fileChooser.showOpenDialog(this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    this.peoplePath.setText(selectedFile.getAbsolutePath());
                }
            });






        //DEBUGGING
        pillarPath.setText("Data/TestingData/ReadTest/pillarReadTest2.csv");
        peoplePath.setText("Data/TestingData/AlgorithmTest/LogikAlgorithmTest/logikAlgorithmTest2.csv");

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
        SwingUtilities.invokeLater(() -> {
            UFExportProblemWindow viewer = new UFExportProblemWindow();
            viewer.setVisible(true);
        });
    }
}
