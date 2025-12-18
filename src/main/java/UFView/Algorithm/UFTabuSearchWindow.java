package UFView.Algorithm;

import Model.Algorithms.LatticeAlgorithms.LatticeAlgorithm;
import Model.Session;
import UFController.Algorithm.UFAlgorithmInput;
import UFController.Algorithm.UFAlgorithmInputController;
import UFView.Comparator.UFComparatorResultWindow;
import UFView.Start.UFErrorWindow;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.font.TextAttribute;
import java.util.Map;

public class UFTabuSearchWindow extends JFrame {
    private JLabel header;
    private JLabel noOptimumTimerLabel;
    private JTextField noOptimumTimerV;
    private JLabel tabuTimerLabel;
    private JTextField tabuTimerV;
    private JButton next;

    private Font mainFont;
    private Font bigFont;
    private Font underlinedFont;
    private final Color defaultColor;
    private final Color hoverColor;
    private final Color clickColor;

    private ImageIcon startIcon;

    private UFAlgorithmInput ac;
    public UFTabuSearchWindow(UFAlgorithmInput ac) {
        this.ac = ac;

        defaultColor = new Color(220, 220, 220);
        hoverColor = new Color(170, 170, 170);
        clickColor = new Color(130, 130, 130);
        mainFont = new Font("Arial", Font.BOLD, 16);
        bigFont = new Font("Arial", Font.BOLD, 20);
        Map<TextAttribute, Object> attributes = (Map<TextAttribute, Object>) bigFont.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        underlinedFont = new Font(attributes);

        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/emergencityIcon.png"));
        setIconImage(icon);

        startIcon = new ImageIcon(getClass().getResource("/startIcon.png"));
        Image scaledImage = startIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        startIcon = new ImageIcon(scaledImage);

        setTitle("Tabu-Search");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel content = new JPanel(null);
        content.setBackground(Color.WHITE);
        content.setPreferredSize(new Dimension(300, 220));
        setContentPane(content);

        initUI();
        pack();
        setLocationRelativeTo(null);
    }

    public void initUI(){
        header = new JLabel("Tabu-Search Parameter");
        header.setFont(underlinedFont);
        header.setBounds(20,20,240,40);
        getContentPane().add(header);

        noOptimumTimerLabel = new JLabel("No Optimum Timer");
        noOptimumTimerLabel.setFont(bigFont);
        noOptimumTimerLabel.setBounds(20,70,180,30);
        getContentPane().add(noOptimumTimerLabel);

        Border lineBorder = BorderFactory.createLineBorder(Color.BLACK, 2);
        Border emptyBorder = new EmptyBorder(5, 10, 5, 10);

        noOptimumTimerV = new JTextField();
        noOptimumTimerV.setFont(mainFont);
        noOptimumTimerV.setBounds(220,70,60,30);
        noOptimumTimerV.setBorder(BorderFactory.createCompoundBorder(lineBorder, emptyBorder));
        getContentPane().add(noOptimumTimerV);

        tabuTimerLabel = new JLabel("Tabu Timer");
        tabuTimerLabel.setFont(bigFont);
        tabuTimerLabel.setBounds(20,110,180,30);
        getContentPane().add(tabuTimerLabel);

        tabuTimerV = new JTextField();
        tabuTimerV.setFont(mainFont);
        tabuTimerV.setBounds(220,110,60,30);
        tabuTimerV.setBorder(BorderFactory.createCompoundBorder(lineBorder, emptyBorder));
        getContentPane().add(tabuTimerV);

        next = new JButton("Weiter", startIcon);
        formatButton(next);
        next.setBounds(160,160,120,40);
        getContentPane().add(next);

        //----------------------BUTTON-FUNCTION----------------------
        next.addActionListener((ActionEvent)->{
            if(!tabuTimerV.getText().isEmpty() && !noOptimumTimerV.getText().isEmpty()){
                Session.getInstance().getAlgorithmParameters().clear();
                Session.getInstance().getAlgorithmParameters().add(Integer.parseInt(tabuTimerV.getText()));
                Session.getInstance().getAlgorithmParameters().add(Integer.parseInt(noOptimumTimerV.getText()));
                ac.executeAlgorithm(new LatticeAlgorithm());
                dispose();
            }else{
                SwingUtilities.invokeLater(() -> {
                    UFErrorWindow viewer = new UFErrorWindow("Noch nicht fertig", "spezifiziert!");
                    viewer.setVisible(true);
                });
            }
        });
        tabuTimerV.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c)) {
                    e.consume();
                }
            }
        });
        noOptimumTimerV.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c)) {
                    e.consume();
                }
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
    //--------------------Debugging---------------------------
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UFTabuSearchWindow viewer = new UFTabuSearchWindow(new UFAlgorithmInputController(new UFAlgorithmInputWindow()));
            viewer.setVisible(true);
        });
    }
}
