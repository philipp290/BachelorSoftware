package UFView.Editor;

import UFController.Algorithm.UFAlgorithmInputController;
import UFController.Algorithm.UFAlgorithmVisualisationController;
import UFController.Editor.UFCsvInstanceEditorController;
import UFController.Editor.UFCsvSchemaEditorController;
import UFView.Algorithm.UFAlgorithmInputSelectionWindow;
import UFView.Algorithm.UFAlgorithmInputWindow;
import UFView.Algorithm.UFAlgorithmVisualisationWindow;

import javax.swing.*;
import java.awt.*;

public class UFEditorSelectionWindow extends JFrame {
    private JButton schemata;
    private JButton instance;

    private final Color defaultColor;
    private final Color hoverColor;
    private final Color clickColor;
    private final Font buttonFont;

    private ImageIcon schemataIcon;
    private ImageIcon instanceIcon;

    public UFEditorSelectionWindow() {
        defaultColor = new Color(220, 220, 220);
        hoverColor = new Color(170, 170, 170);
        clickColor = new Color(130, 130, 130);
        buttonFont = new Font("Arial", Font.BOLD, 16);

        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/emergencityIcon.png"));
        setIconImage(icon);

        schemataIcon = new ImageIcon(getClass().getResource("/columnIcon.png"));
        Image scaledImage = schemataIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        schemataIcon = new ImageIcon(scaledImage);

        instanceIcon = new ImageIcon(getClass().getResource("/rowIcon.png"));
        scaledImage = instanceIcon.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
        instanceIcon = new ImageIcon(scaledImage);

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
        schemata = new JButton("Schemata Editor ", schemataIcon);
        schemata.setBounds(20, 20, 360, 40);
        formatButton(schemata);
        getContentPane().add(schemata);

        instance = new JButton("Instanz Editor     ", instanceIcon);
        instance.setBounds(20, 80, 360, 40);
        formatButton(instance);
        getContentPane().add(instance);

        //----------BUTTON-FUNCTION--------------------------------
        schemata.addActionListener((ActionEvent)->{
            SwingUtilities.invokeLater(() -> {
                UFCsvSchemaEditorWindow viewer = new UFCsvSchemaEditorWindow();
                new UFCsvSchemaEditorController(viewer);
                viewer.setVisible(true);
            });
            dispose();
        });
        instance.addActionListener((ActionEvent)->{
            SwingUtilities.invokeLater(() -> {
                UFCsvInstanceEditorWindow viewer = new UFCsvInstanceEditorWindow();
                new UFCsvInstanceEditorController(viewer);
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
            UFEditorSelectionWindow viewer = new UFEditorSelectionWindow();
            viewer.setVisible(true);
        });
    }



}
