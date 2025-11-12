package UFView.Algorithm;

import DView.Algorithm.AlgorithmBottomUpTopDownWindow;
import DView.Algorithm.AlgorithmRandomWalkWindow;
import Model.Session;
import UFController.Algorithm.UFAlgorithmInput;
import UFController.Algorithm.UFAlgorithmInputController;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.font.TextAttribute;
import java.util.Map;

public class UFLatticeSearchSelectionWindow extends JFrame {
    private JRadioButton cBottomUp;
    private JRadioButton cTopDown;
    private JRadioButton cRandomWalk;
    private JPanel seperationLineOne;
    private JRadioButton cCombined;
    private JPanel seperationLineTwo;
    private JButton next;


    private Font mainFont;
    private Font bigFont;
    private final Color defaultColor;
    private final Color hoverColor;
    private final Color clickColor;

    private ImageIcon startIcon;
    private ImageIcon unselectedIcon;
    private ImageIcon selectedIcon;

    private UFAlgorithmInput ac;
    public UFLatticeSearchSelectionWindow(UFAlgorithmInput ac){
        this.ac = ac;

        defaultColor = new Color(220, 220, 220);
        hoverColor = new Color(170, 170, 170);
        clickColor = new Color(130, 130, 130);
        mainFont = new Font("Arial", Font.BOLD, 16);
        bigFont = new Font("Arial", Font.BOLD, 20);

        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/emergencityIcon.png"));
        setIconImage(icon);

        startIcon = new ImageIcon(getClass().getResource("/startIcon.png"));
        Image scaledImage = startIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        startIcon = new ImageIcon(scaledImage);

        selectedIcon = new ImageIcon(getClass().getResource("/radioSelectedIcon.png"));
        scaledImage = selectedIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        selectedIcon = new ImageIcon(scaledImage);

        unselectedIcon = new ImageIcon(getClass().getResource("/radioUnselectedIcon.png"));
        scaledImage = unselectedIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        unselectedIcon = new ImageIcon(scaledImage);


        setTitle("Auswahl");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel content = new JPanel(null);
        content.setBackground(Color.WHITE);
        content.setPreferredSize(new Dimension(240, 270));
        setContentPane(content);

        initUI();
        pack();
        setLocationRelativeTo(null);
    }

    public void initUI(){

        cBottomUp = new JRadioButton("Bottom Up");
        cBottomUp.setBounds(20,20,180,30);
        formatRadioButton(cBottomUp);
        getContentPane().add(cBottomUp);

        cTopDown = new JRadioButton("Top Down");
        cTopDown.setBounds(20,60,180,30);
        formatRadioButton(cTopDown);
        getContentPane().add(cTopDown);

        cRandomWalk = new JRadioButton("Random Walk");
        cRandomWalk.setBounds(20,100,180,30);
        formatRadioButton(cRandomWalk);
        getContentPane().add(cRandomWalk);

        seperationLineOne=new JPanel();
        seperationLineOne.setBackground(Color.BLACK);
        seperationLineOne.setBounds(10,141,220,2);
        getContentPane().add(seperationLineOne);

        cCombined = new JRadioButton("Combined");
        cCombined.setBounds(20,150,180,30);
        formatRadioButton(cCombined);
        cCombined.setSelected(true);
        getContentPane().add(cCombined);

        ButtonGroup select = new ButtonGroup();
        select.add(cBottomUp);
        select.add(cTopDown);
        select.add(cRandomWalk);
        select.add(cCombined);

        seperationLineTwo=new JPanel();
        seperationLineTwo.setBackground(Color.BLACK);
        seperationLineTwo.setBounds(10,191,220,2);
        getContentPane().add(seperationLineTwo);

        next = new JButton("Weiter", startIcon);
        formatButton(next);
        next.setBounds(100,210,120,40);
        getContentPane().add(next);

        next.addActionListener((ActionEvent)->{
            Session.getInstance().getAlgorithmParameters().clear();
            if (cBottomUp.isSelected()) {
                SwingUtilities.invokeLater(() -> {
                    Session.getInstance().getAlgorithmParameters().add(1);
                    UFAlgorithmLatticeSpecificationWindow viewer = new UFAlgorithmLatticeSpecificationWindow(1, this.ac);
                    viewer.setVisible(true);
                });
            } else if (cTopDown.isSelected()) {
                SwingUtilities.invokeLater(() -> {
                    Session.getInstance().getAlgorithmParameters().add(2);
                    UFAlgorithmLatticeSpecificationWindow viewer = new UFAlgorithmLatticeSpecificationWindow(2, this.ac);;
                    viewer.setVisible(true);
                });
            } else if (cRandomWalk.isSelected()) {
                SwingUtilities.invokeLater(() -> {
                    Session.getInstance().getAlgorithmParameters().add(3);
                    UFAlgorithmLatticeSpecificationWindow viewer = new UFAlgorithmLatticeSpecificationWindow(3, this.ac);;
                    viewer.setVisible(true);
                });
            } else if (cCombined.isSelected()) {
                SwingUtilities.invokeLater(() -> {
                    Session.getInstance().getAlgorithmParameters().add(4);
                    UFAlgorithmLatticeSpecificationWindow viewer = new UFAlgorithmLatticeSpecificationWindow(3, this.ac);;
                    viewer.setVisible(true);
                });
            }
            this.dispose();
        });
    }

    private void formatRadioButton(JRadioButton radio){
        radio.setFont(bigFont);
        radio.setBackground(Color.WHITE);
        radio.setIcon(unselectedIcon);
        radio.setSelectedIcon(selectedIcon);
        radio.setFocusPainted(false);
        radio.setOpaque(false);
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
            UFLatticeSearchSelectionWindow viewer = new UFLatticeSearchSelectionWindow(new UFAlgorithmInputController(new UFAlgorithmInputWindow()));
            viewer.setVisible(true);
        });
    }
}
