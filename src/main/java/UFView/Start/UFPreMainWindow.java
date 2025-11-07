package UFView.Start;
//Credits for the Icons used in this GUI:
//All the icons come from flaticon.com
//In DetaiL:
//Pillar:
//<a href="https://www.flaticon.com/free-icons/pillar" title="pillar icons">Pillar icons created by Good Ware - Flaticon</a>
//Car:
//<a href="https://www.flaticon.com/free-icons/car" title="car icons">Car icons created by Kiranshastry - Flaticon</a>
//Pencil:
//<a href="https://www.flaticon.com/free-icons/pencil" title="pencil icons">Pencil icons created by Pixel perfect - Flaticon</a>
//Start:
//<a href="https://www.flaticon.com/free-icons/play-button" title="play-button icons">Play-button icons created by Freepik - Flaticon</a>
//Search:
//<a href="https://www.flaticon.com/free-icons/search" title="search icons">Search icons created by Chanut - Flaticon</a>
//Yes:
//<a href="https://www.flaticon.com/free-icons/yes" title="yes icons">Yes icons created by heisenberg_jr - Flaticon</a>
//No:
//<a href="https://www.flaticon.com/free-icons/close" title="close icons">Close icons created by Pixel perfect - Flaticon</a>
import javax.swing.*;
import java.awt.*;


public class UFPreMainWindow extends JFrame {


    private JButton editingButton;
    private JButton startingButton;

    private final Color defaultColor;
    private final Color hoverColor;
    private final Color clickColor;
    private final Font buttonFont;

    private ImageIcon editIcon;
    private ImageIcon startIcon;

    public UFPreMainWindow() {

        defaultColor = new Color(220, 220, 220);
        hoverColor = new Color(170, 170, 170);
        clickColor = new Color(130, 130, 130);
        buttonFont = new Font("Arial", Font.BOLD, 16);

        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/emergencityIcon.png"));
        setIconImage(icon);

        editIcon = new ImageIcon(getClass().getResource("/pencilIcon.png"));
        Image scaledImage = editIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        editIcon = new ImageIcon(scaledImage);

        startIcon = new ImageIcon(getClass().getResource("/startIcon.png"));
        scaledImage = startIcon.getImage().getScaledInstance(18, 18, Image.SCALE_SMOOTH);
        startIcon = new ImageIcon(scaledImage);

        setTitle("Start");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel content = new JPanel(null);
        content.setBackground(Color.WHITE);
        content.setPreferredSize(new Dimension(400, 140));
        setContentPane(content);

        initUI();
        pack();
        setLocationRelativeTo(null);


    }

    public void initUI() {
        editingButton = new JButton("CSVs editieren ", editIcon);
        editingButton.setBounds(20, 20, 360, 40);
        formatButton(editingButton);
        getContentPane().add(editingButton);

        startingButton = new JButton("Start ", startIcon);
        startingButton.setBounds(20, 80, 360, 40);
        formatButton(startingButton);
        getContentPane().add(startingButton);
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
            UFPreMainWindow viewer = new UFPreMainWindow();
            viewer.setVisible(true);
        });
    }
}
