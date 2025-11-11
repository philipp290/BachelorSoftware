package UFView.Comparator;

import DView.Comparator.ComparatorResultWindow;
import Model.Components.Person;
import Model.Components.Pillar;
import Model.Services.CsvReaderService;
import Model.Services.SolutionValidationService;
import Model.Session;
import UFView.Algorithm.UFAlgorithmInputWindow;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class UFComparatorWindow extends JFrame {
    private JLabel solutionOneLabel;
    private JComboBox<String> solutionOne;
    private JLabel solutionTwoLabel;
    private JComboBox<String> solutionTwo;

    private JPanel seperationLineOne;

    private JCheckBox cTimeWindow;
    private JLabel lengthLabel;
    private JTextField lengthField;
    private JLabel offsetLabel;
    private JTextField offsetField;

    private JPanel seperationLineTwo;

    private JButton startButton;

    private final Color defaultColor;
    private final Color hoverColor;
    private final Color clickColor;
    private final Font mainFont;

    private String[] solutionKeys;

    private ImageIcon startIcon;
    private ImageIcon checkSelectedIcon;
    private ImageIcon checkUnselectedIcon;

    private boolean updating = false;



    public UFComparatorWindow(){
        solutionKeys = Session.getInstance().getSolutionKeys().toArray(new String[0]);

        defaultColor = new Color(220, 220, 220);
        hoverColor = new Color(170, 170, 170);
        clickColor = new Color(130, 130, 130);
        mainFont = new Font("Arial", Font.BOLD, 16);

        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/emergencityIcon.png"));
        setIconImage(icon);

        startIcon = new ImageIcon(getClass().getResource("/startIcon.png"));
        Image scaledImage = startIcon.getImage().getScaledInstance(18, 18, Image.SCALE_SMOOTH);
        startIcon = new ImageIcon(scaledImage);

        checkSelectedIcon = new ImageIcon(getClass().getResource("/checkboxSelectedIcon.png"));
        scaledImage = checkSelectedIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        checkSelectedIcon = new ImageIcon(scaledImage);

        checkUnselectedIcon = new ImageIcon(getClass().getResource("/checkboxUnselectedIcon.png"));
        scaledImage = checkUnselectedIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        checkUnselectedIcon = new ImageIcon(scaledImage);

        setTitle("Vergleich");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel content = new JPanel(null);
        content.setBackground(Color.WHITE);
        content.setPreferredSize(new Dimension(400, 280));
        setContentPane(content);

        initUI();
        pack();
        setLocationRelativeTo(null);
    }

    private void initUI(){
        solutionOneLabel = new JLabel("Lösung 1");
        solutionOneLabel.setFont(mainFont);
        solutionOneLabel.setBounds(20,20,80,20);
        getContentPane().add(solutionOneLabel);

        solutionOne = new JComboBox<>(solutionKeys);
        solutionOne.setBounds(140,20,240,20);
        formatComboBox(solutionOne);
        getContentPane().add(solutionOne);

        solutionTwoLabel = new JLabel("Lösung 2");
        solutionTwoLabel.setFont(mainFont);
        solutionTwoLabel.setBounds(20,60,80,20);
        getContentPane().add(solutionTwoLabel);

        solutionTwo = new JComboBox<>(solutionKeys);
        solutionTwo.setBounds(140,60,240,20);
        formatComboBox(solutionTwo);
        getContentPane().add(solutionTwo);

        seperationLineOne = new JPanel();
        seperationLineOne.setBackground(Color.BLACK);
        seperationLineOne.setBounds(10,99,380,2);
        getContentPane().add(seperationLineOne);

        Border lineBorder = BorderFactory.createLineBorder(Color.BLACK, 2);
        Border emptyBorder = new EmptyBorder(5, 10, 5, 10);

        cTimeWindow = new JCheckBox("Ziel-Zeitfenster");
        cTimeWindow.setBounds(20,120,180,20);
        formatCheckBox(cTimeWindow);
        getContentPane().add(cTimeWindow);

        lengthLabel = new JLabel("Länge");
        lengthLabel.setFont(mainFont);
        lengthLabel.setBounds(25,150,180,30);
        getContentPane().add(lengthLabel);

        lengthField = new JTextField();
        lengthField.setBounds(80,150,100,30);
        lengthField.setFont(mainFont);
        lengthField.setBorder(BorderFactory.createCompoundBorder(lineBorder, emptyBorder));
        lengthField.setBackground(defaultColor);
        lengthField.setEnabled(false);
        lengthField.setDisabledTextColor(Color.BLACK);
        getContentPane().add(lengthField);

        offsetLabel = new JLabel("Offset");
        offsetLabel.setFont(mainFont);
        offsetLabel.setBounds(220,150,180,30);
        getContentPane().add(offsetLabel);

        offsetField = new JTextField();
        offsetField.setBounds(280,150,100,30);
        offsetField.setFont(mainFont);
        offsetField.setBorder(BorderFactory.createCompoundBorder(lineBorder, emptyBorder));
        offsetField.setBackground(defaultColor);
        offsetField.setEnabled(false);
        offsetField.setDisabledTextColor(Color.BLACK);
        getContentPane().add(offsetField);

        seperationLineTwo = new JPanel();
        seperationLineTwo.setBackground(Color.BLACK);
        seperationLineTwo.setBounds(10,199,380,2);
        getContentPane().add(seperationLineTwo);

        startButton = new JButton("Auswertung",startIcon);
        startButton.setBounds(10,220,380,40);
        formatButton(startButton);
        getContentPane().add(startButton);



        cTimeWindow.addActionListener(e->{
            if(cTimeWindow.isSelected()){
                lengthField.setEnabled(true);
                lengthField.setBackground(Color.WHITE);
                offsetField.setEnabled(true);
                offsetField.setBackground(Color.WHITE);
            }else{
                lengthField.setEnabled(false);
                lengthField.setBackground(defaultColor);
                offsetField.setEnabled(false);
                offsetField.setBackground(defaultColor);
            }
        });

        solutionOne.addActionListener((ActionEvent)->{
            if(updating){return;}
            updating = true;

            String chosenOne = (String) solutionOne.getSelectedItem();
            String chosenTwo = (String) solutionTwo.getSelectedItem();

            solutionTwo.removeAllItems();
            for (String s : solutionKeys) {
                if (!s.equals(chosenOne)) {
                    solutionTwo.addItem(s);
                }
            }
            solutionTwo.setSelectedItem(chosenTwo);

            updating = false;
        });
        solutionTwo.addActionListener((ActionEvent)->{
            if(updating){return;}
            updating = true;

            String chosenTwo = (String) solutionTwo.getSelectedItem();
            String chosenOne = (String) solutionOne.getSelectedItem();

            solutionOne.removeAllItems();
            for (String s : solutionKeys) {
                if (!s.equals(chosenTwo)) {
                    solutionOne.addItem(s);
                }
            }
            solutionOne.setSelectedItem(chosenOne);


            updating = false;
        });

        //----------------------BUTTON-FUNCTION------------------
        startButton.addActionListener((ActionEvent)->{
            SolutionValidationService svs = new SolutionValidationService();
            ArrayList<Pillar> pillarsOne = Session.getInstance().getSolutionCache().get(solutionOne.getSelectedItem().toString());

            ArrayList<Person> temp = Session.getInstance().getPeople();
            int peopleAmount = temp.size();

            ArrayList<Pillar> pillarsTwo = Session.getInstance().getSolutionCache().get(solutionTwo.getSelectedItem().toString());

            int coverage1 = svs.coverageValidation(pillarsOne);
            int score1 = svs.scoreValidation(pillarsOne);
            int coverage2 = svs.coverageValidation(pillarsTwo);
            int score2 = svs.scoreValidation(pillarsTwo);

            double[] result = new double[8];
            result[0] = Math.round((double) coverage1 /peopleAmount * 100.0);
            result[1] = coverage1;
            result[2] = pillarsOne.size();
            result[3] = Math.round((double) score1 /pillarsOne.size() * 100.0) / 100.0;
            result[4] = Math.round((double) coverage2 /peopleAmount * 100.0) ;
            result[5] = coverage2;
            result[6] = pillarsTwo.size();
            result[7] = Math.round((double) score2 /pillarsTwo.size() * 100.0) / 100.0;

            SwingUtilities.invokeLater(() -> {
                UFComparatorResultWindow viewer = new UFComparatorResultWindow(result);
                viewer.setVisible(true);
            });
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

    private void formatCheckBox(JCheckBox box){
        box.setFont(mainFont);
        box.setBackground(Color.WHITE);
        box.setIcon(checkUnselectedIcon);
        box.setSelectedIcon(checkSelectedIcon);
        box.setFocusPainted(false);
        box.setOpaque(false);
    }

    public void formatComboBox(JComboBox box){
        box.setFont(mainFont);
        box.setFocusable(false);
        box.setBorder(new LineBorder(Color.BLACK, 2));
        box.setSelectedIndex(-1);
        box.setUI(new javax.swing.plaf.basic.BasicComboBoxUI() {
            @Override
            protected JButton createArrowButton() {
                JButton button = new JButton() {
                    @Override
                    protected void paintComponent(Graphics g) {
                        g.setColor(getBackground());
                        g.fillRect(0, 0, getWidth(), getHeight());

                        g.setColor(Color.BLACK);
                        g.fillRect(0, 0, 2, getHeight());

                        g.setColor(Color.BLACK);
                        int w = getWidth();
                        int h = getHeight();
                        int offsetX = 2;

                        g.fillPolygon(
                                new int[]{w / 2 - 5 + offsetX, w / 2 + 5 + offsetX, w / 2 + offsetX},
                                new int[]{h / 2 - 2, h / 2 - 2, h / 2 + 4},
                                3
                        );
                    }
                };
                button.setBorderPainted(false);
                button.setFocusPainted(false);
                button.setContentAreaFilled(false);
                button.setOpaque(true);
                button.setBackground(Color.WHITE);
                return button;
            }
        });
    }

    //--------------------------------------DEBUGGING--------------------------
    public static void main(String[] args) {
        Session.getInstance().getSolutionKeys().add("Test1");
        Session.getInstance().getSolutionKeys().add("Test2");
        Session.getInstance().getSolutionKeys().add("Test3");
        Session.getInstance().getSolutionKeys().add("Test4");
        SwingUtilities.invokeLater(() -> {
            UFComparatorWindow viewer = new UFComparatorWindow();
            viewer.setVisible(true);
        });
    }

}
