package UFView.Start;

import Model.Session;
import UFView.UFMainWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UFCrossingWindow extends JFrame {
    private JLabel header;
    private JButton yesButton;
    private JButton noButton;

    private final Color defaultColor;
    private final Color hoverColor;
    private final Color clickColor;
    private final Font mainFont;

    private ImageIcon yesIcon;
    private ImageIcon noIcon;

    private final boolean type;
    public UFCrossingWindow(boolean type){
        this.type = type;
        defaultColor = new Color(220, 220, 220);
        hoverColor = new Color(170, 170, 170);
        clickColor = new Color(130, 130, 130);

        mainFont = new Font("Arial", Font.BOLD, 16);

        yesIcon = new ImageIcon(getClass().getResource("/yesIcon.png"));
        Image scaledImage = yesIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        yesIcon = new ImageIcon(scaledImage);

        noIcon = new ImageIcon(getClass().getResource("/noIcon.png"));
        scaledImage = noIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        noIcon = new ImageIcon(scaledImage);

        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/emergencityIcon.png"));
        setIconImage(icon);
        setTitle("Auswahl");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel content = new JPanel(null);
        content.setBackground(Color.WHITE);
        content.setPreferredSize(new Dimension(400, 120));
        setContentPane(content);

        initUI();
        pack();
        setLocationRelativeTo(null);
    }

    private void initUI(){
        if(type){
            header = new JLabel("Wurden bereits Litfaßsäulen gesetzt?", JLabel.CENTER);
        }else{
            header = new JLabel("Existieren Katastrophenschutz-Leuchttürme?", JLabel.CENTER);
        }
        header.setFont(mainFont);
        int width = getContentPane().getPreferredSize().width;
        header.setBounds(20,20,355,20);
        getContentPane().add(header);

        yesButton = new JButton("Ja",yesIcon);
        yesButton.setBounds(40,60,120,40);
        formatButton(yesButton);
        getContentPane().add(yesButton);

        noButton = new JButton("Nein",noIcon);
        noButton.setBounds(240,60,120,40);
        formatButton(noButton);
        getContentPane().add(noButton);

        yesButton.addActionListener((ActionEvent)->{
            if(type) {
                SwingUtilities.invokeLater(() -> {
                    UFPillarChoosingWindow viewer = new UFPillarChoosingWindow(Session.getInstance().getPillars());
                    viewer.setVisible(true);
                });
            }else{
                SwingUtilities.invokeLater(() -> {
                    UFSpecificationWindow viewer = new UFSpecificationWindow(false);
                    viewer.setVisible(true);
                });
            }
            dispose();
        });
        noButton.addActionListener((ActionEvent)->{
            if(type) {
                SwingUtilities.invokeLater(() -> {
                    UFCrossingWindow viewer = new UFCrossingWindow(false);
                    viewer.setVisible(true);
                });
            }else{
                SwingUtilities.invokeLater(UFLoadingScreen::showLoadingScreen);
            }
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
            UFCrossingWindow viewer = new UFCrossingWindow(false);
            viewer.setVisible(true);
        });
    }
}
