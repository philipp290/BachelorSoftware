package UFView.Analysis;

import DView.Analysis.CsvAnalysisEncounterLengthWindow;
import DView.Analysis.CsvAnalysisReachabilityWindow;
import Model.Services.CsvAnalysisService;
import Model.Session;
import UFView.UFMainWindow;

import javax.swing.*;
import java.awt.*;

public class UFAnalysisWindow extends JFrame {
    private JButton reachability;
    private JButton coverage;
    private JButton encounterLength;

    private final Color defaultColor;
    private final Color hoverColor;
    private final Color clickColor;
    private final Font buttonFont;

    private ImageIcon reachIcon;
    private ImageIcon coverIcon;
    private ImageIcon timeIcon;

    public UFAnalysisWindow() {
        defaultColor = new Color(220, 220, 220);
        hoverColor = new Color(170, 170, 170);
        clickColor = new Color(130, 130, 130);
        buttonFont = new Font("Arial", Font.BOLD, 16);

        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/emergencityIcon.png"));
        setIconImage(icon);

        reachIcon = new ImageIcon(getClass().getResource("/reachabilityIcon.png"));
        Image scaledImage = reachIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        reachIcon = new ImageIcon(scaledImage);

        coverIcon = new ImageIcon(getClass().getResource("/coverageIcon.png"));
        scaledImage = coverIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        coverIcon = new ImageIcon(scaledImage);

        timeIcon = new ImageIcon(getClass().getResource("/timeIcon.png"));
        scaledImage = timeIcon.getImage().getScaledInstance(18, 18, Image.SCALE_SMOOTH);
        timeIcon = new ImageIcon(scaledImage);

        setTitle("Analyse");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel content = new JPanel(null);
        content.setBackground(Color.WHITE);
        content.setPreferredSize(new Dimension(400, 200));
        setContentPane(content);

        initUI();
        pack();
        setLocationRelativeTo(null);
    }

    private void initUI(){
        reachability = new JButton("Erreichbarkeits-Analyse ", reachIcon);
        reachability.setBounds(20, 20, 360, 40);
        formatButton(reachability);
        getContentPane().add(reachability);

        coverage = new JButton("Abdeckungs-Analyse ", coverIcon);
        coverage.setBounds(20, 80, 360, 40);
        formatButton(coverage);
        getContentPane().add(coverage);

        encounterLength = new JButton("Verweildauer-Analyse ", timeIcon);
        encounterLength.setBounds(20, 140, 360, 40);
        formatButton(encounterLength);
        getContentPane().add(encounterLength);

        //----------BUTTON-FUNCTION--------------------------------
        reachability.addActionListener((ActionEvent)->{
            CsvAnalysisService cas = new CsvAnalysisService();
            double[] reachability = cas.reachabilityAnalysis(Session.getInstance().getPeople());
            SwingUtilities.invokeLater(() -> {
                UFAnalysisReachabilityWindow viewer = new UFAnalysisReachabilityWindow(reachability);
                viewer.setVisible(true);
            });
        });

        coverage.addActionListener((ActionEvent)->{
            CsvAnalysisService cas = new CsvAnalysisService();
            SwingUtilities.invokeLater(() -> {
                double[] content = cas.coverageAnalysis(Session.getInstance().getPillars());
                UFAnalysisCoverageWindow viewer = new UFAnalysisCoverageWindow(Session.getInstance().getPillars(),content);
                viewer.setVisible(true);
            });
        });

        encounterLength.addActionListener((ActionEvent)->{
            CsvAnalysisService cas = new CsvAnalysisService();
            double[] encounterdata = cas.encounterLengthAnalysis(Session.getInstance().getOriginalPeopleFile(), Session.getInstance().getPillars(),Session.getInstance().getReachingDistance());
            SwingUtilities.invokeLater(() -> {
                UFAnalysisEncounterLengthWindow viewer = new UFAnalysisEncounterLengthWindow(encounterdata);
                viewer.setVisible(true);
            });
        });
    }

    private void formatButton(JButton button){
        button.setHorizontalTextPosition(SwingConstants.LEFT);
        button.setVerticalTextPosition(SwingConstants.CENTER);
        button.setBackground(defaultColor);
        button.setForeground(Color.BLACK);
        button.setFont(buttonFont);
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
    //--------------------------------------DEBUGGING--------------------------
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UFAnalysisWindow viewer = new UFAnalysisWindow();
            viewer.setVisible(true);
        });
    }

}
