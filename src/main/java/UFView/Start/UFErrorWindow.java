package UFView.Start;

import javax.swing.*;
import java.awt.*;

public class UFErrorWindow extends JFrame {
    JLabel errorLabel;
    JLabel errorMessageOne;
    JLabel errorMessageTwo;
    JButton next;

    private final Color defaultColor;
    private final Color hoverColor;
    private final Color clickColor;
    private final Font mainFont;

    private ImageIcon errorIcon;

    private String strOne;
    private String strTwo;

    public UFErrorWindow(String str1, String str2){
        strOne=str1;
        strTwo=str2;

        defaultColor = new Color(220, 220, 220);
        hoverColor = new Color(170, 170, 170);
        clickColor = new Color(130, 130, 130);

        mainFont = new Font("Arial", Font.BOLD, 16);

        errorIcon = new ImageIcon(getClass().getResource("/errorIcon.png"));
        Image scaledImage = errorIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        errorIcon = new ImageIcon(scaledImage);

        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/emergencityIcon.png"));
        setIconImage(icon);
        setTitle("Fehler");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel content = new JPanel(null);
        content.setBackground(Color.WHITE);
        content.setPreferredSize(new Dimension(320, 180));


        setContentPane(content);

        initUI();
        pack();
        setLocationRelativeTo(null);
    }

    public void initUI(){
        errorLabel = new JLabel(errorIcon);
        errorLabel.setBounds(20,20,80,80);
        getContentPane().add(errorLabel);

        errorMessageOne = new JLabel(strOne);
        errorMessageOne.setFont(mainFont);
        errorMessageOne.setBounds(120,30,180,20);
        getContentPane().add(errorMessageOne);

        errorMessageTwo = new JLabel(strTwo);
        errorMessageTwo.setFont(mainFont);
        errorMessageTwo.setBounds(120,60,180,20);
        getContentPane().add(errorMessageTwo);

        next = new JButton("Okay");
        next.setBounds(100,120,120,40);
        formatButton(next);
        getContentPane().add(next);


        next.addActionListener((ActionEvent)->{
            dispose();
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UFErrorWindow viewer = new UFErrorWindow("Das hier ist eine","Test Fehlermeldung");
            viewer.setVisible(true);
        });
    }
}
