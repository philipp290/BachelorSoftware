package UFView.Export;

import UFView.Algorithm.UFAlgorithmInputSelectionWindow;
import UFView.Analysis.UFAnalysisWindow;
import UFView.Comparator.UFComparatorWindow;
import UFView.UFMainWindow;

import javax.swing.*;
import java.awt.*;

public class UFExportSelectionWindow extends JFrame {
    private JButton solutionExportButton;
    private JButton problemExportButton;
    private JButton unityExportButton;



    private final Color defaultColor;
    private final Color hoverColor;
    private final Color clickColor;
    private final Font buttonFont;

    private ImageIcon solutionIcon;
    private ImageIcon problemIcon;
    private ImageIcon unityIcon;


    public UFExportSelectionWindow() {
        defaultColor = new Color(220, 220, 220);
        hoverColor = new Color(170, 170, 170);
        clickColor = new Color(130, 130, 130);
        buttonFont = new Font("Arial", Font.BOLD, 16);

        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/emergencityIcon.png"));
        setIconImage(icon);

        solutionIcon = new ImageIcon(getClass().getResource("/solutionIcon.png"));
        Image scaledImage = solutionIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        solutionIcon = new ImageIcon(scaledImage);

        problemIcon = new ImageIcon(getClass().getResource("/problemIcon.png"));
        scaledImage = problemIcon.getImage().getScaledInstance(18, 18, Image.SCALE_SMOOTH);
        problemIcon = new ImageIcon(scaledImage);

        unityIcon = new ImageIcon(getClass().getResource("/unityIcon.png"));
        scaledImage = unityIcon.getImage().getScaledInstance(18, 18, Image.SCALE_SMOOTH);
        unityIcon = new ImageIcon(scaledImage);

        setTitle("Export");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel content = new JPanel(null);
        content.setBackground(Color.WHITE);
        content.setPreferredSize(new Dimension(400, 200));
        setContentPane(content);

        initUI();
        pack();
        setLocationRelativeTo(null);
    }

    public void initUI() {
        solutionExportButton = new JButton("Lösungs Export ", solutionIcon);
        solutionExportButton.setBounds(20, 20, 360, 40);
        formatButton(solutionExportButton);
        getContentPane().add(solutionExportButton);

        problemExportButton = new JButton("Problem Export ", problemIcon);
        problemExportButton.setBounds(20, 80, 360, 40);
        formatButton(problemExportButton);
        getContentPane().add(problemExportButton);

        unityExportButton = new JButton("Unity Export ", unityIcon);
        unityExportButton.setBounds(20, 140, 360, 40);
        formatButton(unityExportButton);
        getContentPane().add(unityExportButton);


        //-------------------BUTTON-FUNCTION-------------
        solutionExportButton.addActionListener((ActionEvent) ->{
            SwingUtilities.invokeLater(() -> {
                UFExportSolutionWindow viewer = new UFExportSolutionWindow();
                viewer.setVisible(true);
            });
        });
        problemExportButton.addActionListener((ActionEvent)->{
            SwingUtilities.invokeLater(() -> {
                UFExportProblemWindow viewer = new UFExportProblemWindow();
                viewer.setVisible(true);
            });
        });
        unityExportButton.addActionListener((ActionEvent)->{
            SwingUtilities.invokeLater(() -> {
                UFExportUnityWindow viewer = new UFExportUnityWindow();
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
            UFExportSelectionWindow viewer = new UFExportSelectionWindow();
            viewer.setVisible(true);
        });
    }
}
