package UFView.Start;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class UFSpecificationWindow extends JFrame {
    private JLabel pillarHeader;
    private JButton pillarSearch;
    private JTextField pillarPath;

    private JLabel peopleHeader;
    private JButton peopleSearch;
    private JTextField peoplePath;

    private JButton next;

    private final Color defaultColor;
    private final Color hoverColor;
    private final Color clickColor;
    private final Font mainFont;

    private ImageIcon pillarIcon;
    private ImageIcon carIcon;
    private ImageIcon searchIcon;
    private ImageIcon startIcon;

    public UFSpecificationWindow(){
        defaultColor = new Color(220, 220, 220);
        hoverColor = new Color(170, 170, 170);
        clickColor = new Color(130, 130, 130);

        mainFont = new Font("Arial", Font.BOLD, 16);

        pillarIcon = new ImageIcon(getClass().getResource("/pillarIcon.png"));
        Image scaledImage = pillarIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        pillarIcon = new ImageIcon(scaledImage);

        carIcon = new ImageIcon(getClass().getResource("/carIcon.png"));
        scaledImage = carIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        carIcon = new ImageIcon(scaledImage);

        searchIcon = new ImageIcon(getClass().getResource("/searchIcon.png"));
        scaledImage = searchIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        searchIcon = new ImageIcon(scaledImage);

        startIcon = new ImageIcon(getClass().getResource("/startIcon.png"));
        scaledImage = startIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        startIcon = new ImageIcon(scaledImage);

        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/emergencityIcon.png"));
        setIconImage(icon);
        setTitle("Spezifikation");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel content = new JPanel(null);
        content.setBackground(Color.WHITE);
        content.setPreferredSize(new Dimension(400, 300));
        setContentPane(content);

        initUI();
        pack();
        setLocationRelativeTo(null);
    }

    public void initUI(){
        pillarHeader = new JLabel("Litfaßsäulen CSV",pillarIcon,JLabel.LEFT);
        pillarHeader.setHorizontalTextPosition(SwingConstants.LEFT);
        pillarHeader.setVerticalTextPosition(SwingConstants.CENTER);
        pillarHeader.setBounds(20,20,220,20);
        pillarHeader.setFont(mainFont);
        getContentPane().add(pillarHeader);

        pillarSearch = new JButton("Suche",searchIcon);
        pillarSearch.setBounds(20,60,80,40);
        formatButton(pillarSearch);
        getContentPane().add(pillarSearch);

        pillarPath = new JTextField();
        pillarPath.setBounds(120,60,260,40);
        pillarPath.setFont(mainFont);
        Border lineBorder = BorderFactory.createLineBorder(Color.BLACK, 2);
        Border emptyBorder = new EmptyBorder(5, 10, 5, 10);
        pillarPath.setBorder(BorderFactory.createCompoundBorder(lineBorder, emptyBorder));
        getContentPane().add(pillarPath);

        peopleHeader = new JLabel("Verkehrs-Simulations CSV",carIcon,JLabel.LEFT);
        peopleHeader.setHorizontalTextPosition(SwingConstants.LEFT);
        peopleHeader.setVerticalTextPosition(SwingConstants.CENTER);
        peopleHeader.setBounds(20,120,280,20);
        peopleHeader.setFont(mainFont);
        getContentPane().add(peopleHeader);

        peopleSearch = new JButton("Suche",searchIcon);
        peopleSearch.setBounds(20,160,80,40);
        formatButton(peopleSearch);
        getContentPane().add(peopleSearch);

        peoplePath = new JTextField();
        peoplePath.setBounds(120,160,260,40);
        peoplePath.setFont(mainFont);
        peoplePath.setBorder(BorderFactory.createCompoundBorder(lineBorder, emptyBorder));
        getContentPane().add(peoplePath);

        next = new JButton("Weiter",startIcon);
        next.setBounds(260,220,120,40);
        formatButton(next);
        getContentPane().add(next);
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
            UFSpecificationWindow viewer = new UFSpecificationWindow();
            viewer.setVisible(true);
        });
    }
}
