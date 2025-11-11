package UFView.Algorithm;

import Model.Algorithms.LatticeAlgorithms.LatticeAlgorithm;
import Model.Session;
import UFController.Algorithm.UFAlgorithmInputController;
import UFView.Start.UFErrorWindow;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.font.TextAttribute;
import java.util.Map;

public class UFAlgorithmLatticeSpecificationWindow extends JFrame {
    private JLabel field1Label;
    private JTextField field1V;
    private JLabel field2Label;
    private JTextField field2V;
    private JLabel field3Label;
    private JTextField field3V;
    private JPanel seperationLine;
    private JButton next;

    private Font mainFont;
    private Font bigFont;

    private final Color defaultColor;
    private final Color hoverColor;
    private final Color clickColor;

    private ImageIcon startIcon;

    private int type;
    private UFAlgorithmInputController ac;

    public UFAlgorithmLatticeSpecificationWindow(int t, UFAlgorithmInputController ac) {
        this.ac = ac;
        this.type = t;

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

        setTitle("Spezifikation");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel content = new JPanel(null);
        content.setBackground(Color.WHITE);
        if(type == 3) {
            content.setPreferredSize(new Dimension(240, 220));
        }else if(type == 1 || type == 2){
            content.setPreferredSize(new Dimension(240, 130));
        }
        setContentPane(content);

        initUI();
        pack();
        setLocationRelativeTo(null);
    }

    public void initUI(){

        if(type==1 || type == 3 ) {
            field1Label = new JLabel("Obergrenze");
        }else if(type == 2){
            field1Label = new JLabel("Untergrenze");
        }
        field1Label.setFont(bigFont);
        field1Label.setBounds(20,20,180,30);
        getContentPane().add(field1Label);

        Border lineBorder = BorderFactory.createLineBorder(Color.BLACK, 2);
        Border emptyBorder = new EmptyBorder(5, 10, 5, 10);

        field1V = new JTextField();
        field1V.setFont(mainFont);
        field1V.setBounds(160,20,60,30);
        field1V.setBorder(BorderFactory.createCompoundBorder(lineBorder, emptyBorder));
        getContentPane().add(field1V);

        if(type == 3) {
            field2Label = new JLabel("Untergrenze");
            field2Label.setFont(bigFont);
            field2Label.setBounds(20, 60, 180, 30);
            getContentPane().add(field2Label);

            field2V = new JTextField();
            field2V.setFont(mainFont);
            field2V.setBounds(160, 60, 60, 30);
            field2V.setBorder(BorderFactory.createCompoundBorder(lineBorder, emptyBorder));
            getContentPane().add(field2V);

            field3Label = new JLabel("Timer");
            field3Label.setFont(bigFont);
            field3Label.setBounds(20, 100, 180, 30);
            getContentPane().add(field3Label);

            field3V = new JTextField();
            field3V.setFont(mainFont);
            field3V.setBounds(160, 100, 60, 30);
            field3V.setBorder(BorderFactory.createCompoundBorder(lineBorder, emptyBorder));
            getContentPane().add(field3V);
        }
        seperationLine=new JPanel();
        seperationLine.setBackground(Color.BLACK);
        if(type == 3) {
            seperationLine.setBounds(10, 146, 220, 2);
        }else if(type == 1 || type == 2){
            seperationLine.setBounds(10, 59, 220, 2);
        }
        getContentPane().add(seperationLine);

        next = new JButton("Weiter", startIcon);
        formatButton(next);
        if(type == 3) {
            next.setBounds(100,160,120,40);
        }else if(type == 1 || type == 2){
            next.setBounds(100,70,120,40);
        }
        getContentPane().add(next);


        //-----------------BUTTON-FUNCTION-----------------
        next.addActionListener((ActionEvent)->{
            boolean valid = true;
            if(field1V.getText().isEmpty()){
                valid=false;
            }
            if((type==3) &&(field2V.getText().isEmpty()||field3V.getText().isEmpty())){
                valid = false;
            }
            if(valid) {
                Session.getInstance().getAlgorithmParameters().add(Integer.parseInt(field1V.getText()));
                if (type == 3) {
                    Session.getInstance().getAlgorithmParameters().add(Integer.parseInt(field2V.getText()));
                    Session.getInstance().getAlgorithmParameters().add(Integer.parseInt(field3V.getText()));
                }
                ac.executeAlgorithm(new LatticeAlgorithm());
                dispose();
            }else{
                SwingUtilities.invokeLater(() -> {
                    UFErrorWindow viewer = new UFErrorWindow("Noch nicht fertig", "spezifiziert!");
                    viewer.setVisible(true);
                });
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
            UFAlgorithmLatticeSpecificationWindow viewer = new UFAlgorithmLatticeSpecificationWindow(1, new UFAlgorithmInputController(new UFAlgorithmInputWindow()));
            viewer.setVisible(true);
        });
    }



}
