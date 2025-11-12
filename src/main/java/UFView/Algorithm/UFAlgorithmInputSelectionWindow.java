package UFView.Algorithm;

import Model.Services.CsvAnalysisService;
import Model.Session;
import UFController.Algorithm.UFAlgorithmInput;
import UFController.Algorithm.UFAlgorithmInputController;
import UFController.Algorithm.UFAlgorithmVisualisationController;
import UFView.Analysis.UFAnalysisEncounterLengthWindow;
import UFView.Analysis.UFAnalysisReachabilityWindow;
import UFView.Analysis.UFAnalysisWindow;

import javax.swing.*;
import java.awt.*;

public class UFAlgorithmInputSelectionWindow extends JFrame {
    private JButton individual;
    private JButton bulk;

    private final Color defaultColor;
    private final Color hoverColor;
    private final Color clickColor;
    private final Font buttonFont;

    private ImageIcon singleIcon;
    private ImageIcon bulkIcon;

    public UFAlgorithmInputSelectionWindow() {
        defaultColor = new Color(220, 220, 220);
        hoverColor = new Color(170, 170, 170);
        clickColor = new Color(130, 130, 130);
        buttonFont = new Font("Arial", Font.BOLD, 16);

        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/emergencityIcon.png"));
        setIconImage(icon);

        singleIcon = new ImageIcon(getClass().getResource("/singleIcon.png"));
        Image scaledImage = singleIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        singleIcon = new ImageIcon(scaledImage);

        bulkIcon = new ImageIcon(getClass().getResource("/bulkIcon.png"));
        scaledImage = bulkIcon.getImage().getScaledInstance(18, 18, Image.SCALE_SMOOTH);
        bulkIcon = new ImageIcon(scaledImage);

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
        individual = new JButton("Einzel Ausführung ", singleIcon);
        individual.setBounds(20, 20, 360, 40);
        formatButton(individual);
        getContentPane().add(individual);

        bulk = new JButton("Bulk Ausführung   ", bulkIcon);
        bulk.setBounds(20, 80, 360, 40);
        formatButton(bulk);
        getContentPane().add(bulk);

        //----------BUTTON-FUNCTION--------------------------------
        individual.addActionListener((ActionEvent)->{
            SwingUtilities.invokeLater(() -> {
                UFAlgorithmInputWindow viewer = new UFAlgorithmInputWindow();
                new UFAlgorithmInputController(viewer);
                viewer.setVisible(true);
            });
            dispose();
        });
        bulk.addActionListener((ActionEvent)->{
            SwingUtilities.invokeLater(() -> {
                UFAlgorithmVisualisationWindow viewer = new UFAlgorithmVisualisationWindow();
                new UFAlgorithmVisualisationController(viewer);
                viewer.setVisible(true);
            });
            dispose();
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
            UFAlgorithmInputSelectionWindow viewer = new UFAlgorithmInputSelectionWindow();
            viewer.setVisible(true);
        });
    }
}
