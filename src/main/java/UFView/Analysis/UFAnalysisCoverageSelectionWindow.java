package UFView.Analysis;

import Model.Services.CsvAnalysisService;
import Model.Services.SolutionValidationService;
import Model.Session;

import javax.swing.*;
import java.awt.*;

public class UFAnalysisCoverageSelectionWindow extends JFrame{
    private JButton potential;
    private JButton isSet;


    private final Color defaultColor;
    private final Color hoverColor;
    private final Color clickColor;
    private final Font buttonFont;

    private ImageIcon pillarIcon;
    private ImageIcon coverIcon;


    public UFAnalysisCoverageSelectionWindow() {
        defaultColor = new Color(220, 220, 220);
        hoverColor = new Color(170, 170, 170);
        clickColor = new Color(130, 130, 130);
        buttonFont = new Font("Arial", Font.BOLD, 16);

        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/emergencityIcon.png"));
        setIconImage(icon);

        pillarIcon = new ImageIcon(getClass().getResource("/pillarIcon.png"));
        Image scaledImage = pillarIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        pillarIcon = new ImageIcon(scaledImage);

        coverIcon = new ImageIcon(getClass().getResource("/coverageIcon.png"));
        scaledImage = coverIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        coverIcon = new ImageIcon(scaledImage);


        setTitle("Analyse");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel content = new JPanel(null);
        content.setBackground(Color.WHITE);
        content.setPreferredSize(new Dimension(400, 140));
        setContentPane(content);

        initUI();
        pack();
        setLocationRelativeTo(null);
    }

    private void initUI(){
        potential = new JButton("Abdeckungs-Potential", pillarIcon);
        potential.setBounds(20, 20, 360, 40);
        formatButton(potential);
        getContentPane().add(potential);

        isSet = new JButton("Bereits Erreichte Abdeckung", coverIcon);
        isSet.setBounds(20, 80, 360, 40);
        formatButton(isSet);
        if(Session.getInstance().getSetIndexes().isEmpty()&&Session.getInstance().getLighthouses().isEmpty()){
            isSet.setEnabled(false);
        }
        getContentPane().add(isSet);


        //----------BUTTON-FUNCTION--------------------------------
        potential.addActionListener((ActionEvent)->{
            CsvAnalysisService cas = new CsvAnalysisService();
            SwingUtilities.invokeLater(() -> {
                double[] content = cas.coverageAnalysis(Session.getInstance().getPillars());
                UFAnalysisCoverageWindow viewer = new UFAnalysisCoverageWindow(Session.getInstance().getPillars(),content);
                viewer.setVisible(true);
            });
        });

        isSet.addActionListener((ActionEvent)->{
            SolutionValidationService svs = new SolutionValidationService();
            int type = 0;
            double[] content = new double[3];
            if(!Session.getInstance().getSetIndexes().isEmpty()&&Session.getInstance().getLighthouses().isEmpty()){
                type = 1;
                content = svs.setCoverage();
            }else if(Session.getInstance().getSetIndexes().isEmpty()&&!Session.getInstance().getLighthouses().isEmpty()){
                type = 2;
                content = svs.lighthouseCoverage();
            }else if(!Session.getInstance().getSetIndexes().isEmpty()&&!Session.getInstance().getLighthouses().isEmpty()){
                type = 3;
                content = svs.wholeSetCoverage();
            }
            final double[] fContent = content;
            final int fType = type;
            SwingUtilities.invokeLater(() -> {
                UFAnalysisSetCoverageWindow viewer = new UFAnalysisSetCoverageWindow(fContent,fType);
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
            UFAnalysisCoverageSelectionWindow viewer = new UFAnalysisCoverageSelectionWindow();
            viewer.setVisible(true);
        });
    }
}
