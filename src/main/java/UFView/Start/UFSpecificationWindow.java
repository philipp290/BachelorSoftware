package UFView.Start;

import Model.Components.Person;
import Model.Components.Pillar;
import Model.Services.CsvReaderService;
import Model.Session;
import UFView.UFMainWindow;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;

public class UFSpecificationWindow extends JFrame {
    private JLabel pillarHeader;
    private JButton pillarSearch;
    private JTextField pillarPath;

    private JLabel peopleHeader;
    private JButton peopleSearch;
    private JTextField peoplePath;

    private JButton next;

    private final Color defaultColor;
    private final Color hoverColor;
    private final Color clickColor;
    private final Font mainFont;

    private ImageIcon pillarIcon;
    private ImageIcon carIcon;
    private ImageIcon searchIcon;
    private ImageIcon startIcon;
    private ImageIcon lighthouseIcon;

    private boolean type;

    public UFSpecificationWindow(boolean t){
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.type = t;
        defaultColor = new Color(220, 220, 220);
        hoverColor = new Color(170, 170, 170);
        clickColor = new Color(130, 130, 130);

        mainFont = new Font("Arial", Font.BOLD, 16);

        pillarIcon = new ImageIcon(getClass().getResource("/pillarIcon.png"));
        Image scaledImage = pillarIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        pillarIcon = new ImageIcon(scaledImage);

        carIcon = new ImageIcon(getClass().getResource("/carIcon.png"));
        scaledImage = carIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        carIcon = new ImageIcon(scaledImage);

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
        setTitle("Spezifikation");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel content = new JPanel(null);
        content.setBackground(Color.WHITE);
        if(type) {
            content.setPreferredSize(new Dimension(400, 300));
        }else{
            content.setPreferredSize(new Dimension(400, 200));
        }
        setContentPane(content);

        initUI();
        pack();
        setLocationRelativeTo(null);
    }

    public void initUI(){
        if(type) {
            pillarHeader = new JLabel("Litfaßsäulen CSV", pillarIcon, JLabel.LEFT);
        }else{
            pillarHeader = new JLabel("Katastrophenschutz-Leuchtturm CSV", lighthouseIcon, JLabel.LEFT);
        }
        pillarHeader.setHorizontalTextPosition(SwingConstants.LEFT);
        pillarHeader.setVerticalTextPosition(SwingConstants.CENTER);
        pillarHeader.setBounds(20,20,320,20);
        pillarHeader.setFont(mainFont);
        getContentPane().add(pillarHeader);

        pillarSearch = new JButton("Suche",searchIcon);
        pillarSearch.setBounds(20,60,80,40);
        formatButton(pillarSearch);
        getContentPane().add(pillarSearch);

        pillarPath = new JTextField();
        pillarPath.setBounds(120,60,260,40);
        pillarPath.setFont(mainFont);
        Border lineBorder = BorderFactory.createLineBorder(Color.BLACK, 2);
        Border emptyBorder = new EmptyBorder(5, 10, 5, 10);
        pillarPath.setBorder(BorderFactory.createCompoundBorder(lineBorder, emptyBorder));
        getContentPane().add(pillarPath);

        if(type) {
            peopleHeader = new JLabel("Verkehrs-Simulations CSV", carIcon, JLabel.LEFT);
            peopleHeader.setHorizontalTextPosition(SwingConstants.LEFT);
            peopleHeader.setVerticalTextPosition(SwingConstants.CENTER);
            peopleHeader.setBounds(20, 120, 280, 20);
            peopleHeader.setFont(mainFont);
            getContentPane().add(peopleHeader);

            peopleSearch = new JButton("Suche", searchIcon);
            peopleSearch.setBounds(20, 160, 80, 40);
            formatButton(peopleSearch);
            getContentPane().add(peopleSearch);

            peoplePath = new JTextField();
            peoplePath.setBounds(120, 160, 260, 40);
            peoplePath.setFont(mainFont);
            peoplePath.setBorder(BorderFactory.createCompoundBorder(lineBorder, emptyBorder));
            getContentPane().add(peoplePath);
        }

        next = new JButton("Weiter",startIcon);
        if(type) {
            next.setBounds(260, 220, 120, 40);
        }else{
            next.setBounds(260, 120, 120, 40);
        }
        formatButton(next);
        getContentPane().add(next);

        //-------------BUTTON-FUNCTION----------------
        next.addActionListener((ActionEvent) -> {
            if(type) {
                if (!peoplePath.getText().isEmpty() && !pillarPath.getText().isEmpty()) {
                    Session.getInstance().setOriginalPeopleFile(peoplePath.getText());
                    CsvReaderService crs = new CsvReaderService();
                    ArrayList<Pillar> pil = crs.readPillarsFromFile(pillarPath.getText());
                    Session.getInstance().setPillars(pil);
                    SwingUtilities.invokeLater(() -> {
                        UFCrossingWindow viewer = new UFCrossingWindow(true);
                        viewer.setVisible(true);
                    });

                    dispose();
                } else {
                    SwingUtilities.invokeLater(() -> {
                        UFErrorWindow viewer = new UFErrorWindow("Noch nicht fertig", "spezifiziert!");
                        viewer.setVisible(true);
                    });
                }
            }else{
                if (!pillarPath.getText().isEmpty()) {
                    CsvReaderService crs = new CsvReaderService();
                    ArrayList<Pillar> pil = crs.readPillarsFromFile(pillarPath.getText());
                    Session.getInstance().getLighthouses().addAll(pil);
                    SwingUtilities.invokeLater(UFLoadingScreen::showLoadingScreen);


                    dispose();
                }else {
                    SwingUtilities.invokeLater(() -> {
                        UFErrorWindow viewer = new UFErrorWindow("Noch nicht fertig", "spezifiziert!");
                        viewer.setVisible(true);
                    });
                }
            }
        });

        pillarSearch.addActionListener((ActionEvent)->{
            File startDir = new File("Data");
            JFileChooser fileChooser = new JFileChooser(startDir);
            fileChooser.setDialogTitle("Wähle die Säulen CSV aus");
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                this.pillarPath.setText(selectedFile.getAbsolutePath());
            }
        });
        if(type) {
            peopleSearch.addActionListener((ActionEvent) -> {
                File startDir = new File("Data");
                JFileChooser fileChooser = new JFileChooser(startDir);
                fileChooser.setDialogTitle("Wähle die Verkehrs-Simulation aus");
                int result = fileChooser.showOpenDialog(this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    this.peoplePath.setText(selectedFile.getAbsolutePath());
                }
            });
        }






        //DEBUGGING
        pillarPath.setText("Data/TestingData/ReadTest/pillarReadTest2.csv");
        if(type) {
            peoplePath.setText("Data/TestingData/AlgorithmTest/LogikAlgorithmTest/logikAlgorithmTest2.csv");
        }
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
            UFSpecificationWindow viewer = new UFSpecificationWindow(false);
            viewer.setVisible(true);
        });
    }
}
