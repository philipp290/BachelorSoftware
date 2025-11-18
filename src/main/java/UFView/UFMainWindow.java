package UFView;

import Model.Components.Person;
import Model.Session;
import UFView.Algorithm.UFAlgorithmInputSelectionWindow;
import UFView.Analysis.UFAnalysisWindow;
import UFView.Comparator.UFComparatorWindow;
import UFView.Export.UFExportSelectionWindow;
import UFView.Export.UFExportUnityWindow;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class UFMainWindow extends JFrame {
    private JButton analysisButton;
    private JButton algorithmButton;
    private JButton compareButton;
    private JButton unityButton;


    private final Color defaultColor;
    private final Color hoverColor;
    private final Color clickColor;
    private final Font buttonFont;

    private ImageIcon analysisIcon;
    private ImageIcon algorithmIcon;
    private ImageIcon compareIcon;
    private ImageIcon exportIcon;

    public UFMainWindow() {
        defaultColor = new Color(220, 220, 220);
        hoverColor = new Color(170, 170, 170);
        clickColor = new Color(130, 130, 130);
        buttonFont = new Font("Arial", Font.BOLD, 16);

        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/emergencityIcon.png"));
        setIconImage(icon);

        analysisIcon = new ImageIcon(getClass().getResource("/searchIcon.png"));
        Image scaledImage = analysisIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        analysisIcon = new ImageIcon(scaledImage);

        algorithmIcon = new ImageIcon(getClass().getResource("/algorithmIcon.png"));
        scaledImage = algorithmIcon.getImage().getScaledInstance(18, 18, Image.SCALE_SMOOTH);
        algorithmIcon = new ImageIcon(scaledImage);

        compareIcon = new ImageIcon(getClass().getResource("/compareIcon.png"));
        scaledImage = compareIcon.getImage().getScaledInstance(18, 18, Image.SCALE_SMOOTH);
        compareIcon = new ImageIcon(scaledImage);

        exportIcon = new ImageIcon(getClass().getResource("/exportIcon.png"));
        scaledImage = exportIcon.getImage().getScaledInstance(18, 18, Image.SCALE_SMOOTH);
        exportIcon = new ImageIcon(scaledImage);

        setTitle("Start");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel content = new JPanel(null);
        content.setBackground(Color.WHITE);
        content.setPreferredSize(new Dimension(400, 260));
        setContentPane(content);

        initUI();
        pack();
        setLocationRelativeTo(null);


    }

    public void initUI() {
        ArrayList<Person> test = Session.getInstance().getPeople();

        analysisButton = new JButton("CSVs analysieren ", analysisIcon);
        analysisButton.setBounds(20, 20, 360, 40);
        formatButton(analysisButton);
        getContentPane().add(analysisButton);

        algorithmButton = new JButton("Verteilungs-Algorithmus ", algorithmIcon);
        algorithmButton.setBounds(20, 80, 360, 40);
        formatButton(algorithmButton);
        getContentPane().add(algorithmButton);

        compareButton = new JButton("Lösungen vergleichen ", compareIcon);
        compareButton.setBounds(20, 140, 360, 40);
        formatButton(compareButton);
        getContentPane().add(compareButton);

        unityButton = new JButton("Export ", exportIcon);
        unityButton.setBounds(20, 200, 360, 40);
        formatButton(unityButton);
        getContentPane().add(unityButton);

        //-------------------BUTTON-FUNCTION-------------
        algorithmButton.addActionListener((ActionEvent)->{
            SwingUtilities.invokeLater(() -> {
                UFAlgorithmInputSelectionWindow viewer = new UFAlgorithmInputSelectionWindow();
                viewer.setVisible(true);
            });
        });
        compareButton.addActionListener((ActionEvent)->{
            SwingUtilities.invokeLater(() -> {
                UFComparatorWindow viewer = new UFComparatorWindow();
                viewer.setVisible(true);
            });
        });
        unityButton.addActionListener((ActionEvent) ->{
            SwingUtilities.invokeLater(() -> {
                UFExportSelectionWindow viewer = new UFExportSelectionWindow();
                viewer.setVisible(true);
            });
        });
        analysisButton.addActionListener((ActionEvent) ->{
            SwingUtilities.invokeLater(() -> {
                UFAnalysisWindow viewer = new UFAnalysisWindow();
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
            UFMainWindow viewer = new UFMainWindow();
            viewer.setVisible(true);
        });
    }


}
