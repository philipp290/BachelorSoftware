package UFView.Algorithm;

import UFView.UFMainWindow;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class UFAlgorithmInputWindow extends JFrame {
    JLabel iconLabel;

    JRadioButton cLogik;
    JRadioButton cLattice;
    JRadioButton cLinear;

    JPanel seperationLineOne;

    JRadioButton cAbsolut;
    JTextField absolutField;
    JLabel absolutLabel;

    JPanel seperationLineTwo;


    JRadioButton cRelativ;
    JTextField relativField;
    JLabel relativLabel;

    JPanel seperationLineThree;

    JCheckBox cSampling;
    JTextField samplingField;


    private JPanel seperationLineFour;
    private JCheckBox cTimeWindow;
    private JLabel lengthLabel;
    private JTextField lengthField;
    private JLabel offsetLabel;
    private JTextField offsetField;

    private JPanel seperationLineFive;

    private JButton startButton;

    private ImageIcon algorithmIcon;
    private ImageIcon startIcon;
    private ImageIcon infoIcon;
    private ImageIcon radioSelectedIcon;
    private ImageIcon radioUnselectedIcon;

    private ImageIcon checkSelectedIcon;
    private ImageIcon checkUnselectedIcon;

    private final Color defaultColor;
    private final Color hoverColor;
    private final Color clickColor;
    private final Font mainFont;

    public UFAlgorithmInputWindow(){
        defaultColor = new Color(220, 220, 220);
        hoverColor = new Color(170, 170, 170);
        clickColor = new Color(130, 130, 130);
        mainFont = new Font("Arial", Font.BOLD, 16);

        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/emergencityIcon.png"));
        setIconImage(icon);

        algorithmIcon = new ImageIcon(getClass().getResource("/algorithmIcon.png"));
        Image scaledImage = algorithmIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        algorithmIcon = new ImageIcon(scaledImage);

        startIcon = new ImageIcon(getClass().getResource("/startIcon.png"));
        scaledImage = startIcon.getImage().getScaledInstance(18, 18, Image.SCALE_SMOOTH);
        startIcon = new ImageIcon(scaledImage);

        infoIcon = new ImageIcon(getClass().getResource("/infoIcon.png"));
        scaledImage = infoIcon.getImage().getScaledInstance(18, 18, Image.SCALE_SMOOTH);
        infoIcon = new ImageIcon(scaledImage);

        radioSelectedIcon = new ImageIcon(getClass().getResource("/radioSelectedIcon.png"));
        scaledImage = radioSelectedIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        radioSelectedIcon = new ImageIcon(scaledImage);

        radioUnselectedIcon = new ImageIcon(getClass().getResource("/radioUnselectedIcon.png"));
        scaledImage = radioUnselectedIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        radioUnselectedIcon = new ImageIcon(scaledImage);

        checkSelectedIcon = new ImageIcon(getClass().getResource("/checkboxSelectedIcon.png"));
        scaledImage = checkSelectedIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        checkSelectedIcon = new ImageIcon(scaledImage);

        checkUnselectedIcon = new ImageIcon(getClass().getResource("/checkboxUnselectedIcon.png"));
        scaledImage = checkUnselectedIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        checkUnselectedIcon = new ImageIcon(scaledImage);

        setTitle("Algorithmus");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel content = new JPanel(null);
        content.setBackground(Color.WHITE);
        content.setPreferredSize(new Dimension(400, 460));
        setContentPane(content);

        initUI();
        pack();
        setLocationRelativeTo(null);
    }
    public void initUI(){
        iconLabel = new JLabel(algorithmIcon);
        iconLabel.setBounds(280,20,80,80);
        getContentPane().add(iconLabel);

        cLogik = new JRadioButton("Logik");
        cLogik.setBounds(20,20,360,20);
        formatRadioButton(cLogik);
        cLogik.setSelected(true);
        getContentPane().add(cLogik);

        cLattice = new JRadioButton("Lattice Search");
        formatRadioButton(cLattice);
        cLattice.setBounds(20,50,360,20);
        getContentPane().add(cLattice);

        cLinear = new JRadioButton("Lineare Optimierung");
        formatRadioButton(cLinear);
        cLinear.setBounds(20,80,360,20);
        getContentPane().add(cLinear);

        ButtonGroup algoGroup = new ButtonGroup();
        algoGroup.add(cLogik);
        algoGroup.add(cLattice);
        algoGroup.add(cLinear);

        seperationLineOne=new JPanel();
        seperationLineOne.setBackground(Color.BLACK);
        seperationLineOne.setBounds(10,121,380,2);
        getContentPane().add(seperationLineOne);

        seperationLineTwo=new JPanel();
        seperationLineTwo.setBackground(Color.BLACK);
        seperationLineTwo.setBounds(199,121,2,100);
        getContentPane().add(seperationLineTwo);

        Border lineBorder = BorderFactory.createLineBorder(Color.BLACK, 2);
        Border emptyBorder = new EmptyBorder(5, 10, 5, 10);

        cAbsolut = new JRadioButton("Absolut");
        formatRadioButton(cAbsolut);
        cAbsolut.setBounds(20,140,160,20);
        cAbsolut.setSelected(true);
        getContentPane().add(cAbsolut);

        absolutField = new JTextField();
        absolutField.setBounds(25,170,120,30);
        absolutField.setFont(mainFont);
        absolutField.setBorder(BorderFactory.createCompoundBorder(lineBorder, emptyBorder));
        absolutField.setOpaque(true);
        absolutField.setDisabledTextColor(Color.BLACK);
        getContentPane().add(absolutField);

        absolutLabel = new JLabel("Stk.");
        absolutLabel.setFont(mainFont);
        absolutLabel.setBounds(155,165,50,40);
        getContentPane().add(absolutLabel);

        cRelativ = new JRadioButton("Relativ");
        formatRadioButton(cRelativ);
        cRelativ.setBounds(220,140,160,20);
        getContentPane().add(cRelativ);

        relativField = new JTextField();
        relativField.setBounds(225,170,120,30);
        relativField.setFont(mainFont);
        relativField.setBorder(BorderFactory.createCompoundBorder(lineBorder, emptyBorder));
        relativField.setOpaque(true);
        relativField.setBackground(defaultColor);
        relativField.setEnabled(false);
        relativField.setDisabledTextColor(Color.BLACK);
        getContentPane().add(relativField);

        relativLabel = new JLabel("%");
        relativLabel.setFont(mainFont);
        relativLabel.setBounds(355,165,50,40);
        getContentPane().add(relativLabel);

        ButtonGroup type = new ButtonGroup();
        type.add(cRelativ);
        type.add(cAbsolut);

        seperationLineThree=new JPanel();
        seperationLineThree.setBackground(Color.BLACK);
        seperationLineThree.setBounds(10,221,380,2);
        getContentPane().add(seperationLineThree);

        cSampling = new JCheckBox("Sampling Rate");
        cSampling.setBounds(20,245,180,20);
        formatCheckBox(cSampling);
        getContentPane().add(cSampling);

        samplingField = new JTextField();
        samplingField.setBounds(180,240,120,30);
        samplingField.setFont(mainFont);
        samplingField.setBorder(BorderFactory.createCompoundBorder(lineBorder, emptyBorder));
        samplingField.setBackground(defaultColor);
        samplingField.setEnabled(false);
        samplingField.setDisabledTextColor(Color.BLACK);
        getContentPane().add(samplingField);

        seperationLineFour = new JPanel();
        seperationLineFour.setBackground(Color.BLACK);
        seperationLineFour.setBounds(10,286,380,2);
        getContentPane().add(seperationLineFour);

        cTimeWindow = new JCheckBox("Ziel-Zeitfenster");
        cTimeWindow.setBounds(20,300,180,20);
        formatCheckBox(cTimeWindow);
        getContentPane().add(cTimeWindow);

        lengthLabel = new JLabel("Länge");
        lengthLabel.setFont(mainFont);
        lengthLabel.setBounds(25,330,180,30);
        getContentPane().add(lengthLabel);

        lengthField = new JTextField();
        lengthField.setBounds(80,330,100,30);
        lengthField.setFont(mainFont);
        lengthField.setBorder(BorderFactory.createCompoundBorder(lineBorder, emptyBorder));
        lengthField.setBackground(defaultColor);
        lengthField.setEnabled(false);
        lengthField.setDisabledTextColor(Color.BLACK);
        getContentPane().add(lengthField);

        offsetLabel = new JLabel("Offset");
        offsetLabel.setFont(mainFont);
        offsetLabel.setBounds(205,330,180,30);
        getContentPane().add(offsetLabel);

        offsetField = new JTextField();
        offsetField.setBounds(260,330,100,30);
        offsetField.setFont(mainFont);
        offsetField.setBorder(BorderFactory.createCompoundBorder(lineBorder, emptyBorder));
        offsetField.setBackground(defaultColor);
        offsetField.setEnabled(false);
        offsetField.setDisabledTextColor(Color.BLACK);
        getContentPane().add(offsetField);

        seperationLineFive = new JPanel();
        seperationLineFive.setBackground(Color.BLACK);
        seperationLineFive.setBounds(10,381,380,2);
        getContentPane().add(seperationLineFive);

        startButton = new JButton("Run ",startIcon);
        startButton.setBounds(10,400,380,40);
        formatButton(startButton);
        getContentPane().add(startButton);

        //--------------BUTTON-FUNCTION----------------
        cAbsolut.addActionListener(e->{
            if(cAbsolut.isSelected()){
                absolutField.setEnabled(true);
                absolutField.setBackground(Color.WHITE);
                relativField.setEnabled(false);
                relativField.setBackground(defaultColor);
            }
        });
        cRelativ.addActionListener(e->{
            if(cRelativ.isSelected()){
                relativField.setEnabled(true);
                relativField.setBackground(Color.WHITE);
                absolutField.setEnabled(false);
                absolutField.setBackground(defaultColor);
            }
        });
        cSampling.addActionListener(e->{
            if(cSampling.isSelected()){
                samplingField.setEnabled(true);
                samplingField.setBackground(Color.WHITE);
            }else{
                samplingField.setEnabled(false);
                samplingField.setBackground(defaultColor);
            }
        });
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

    private void formatRadioButton(JRadioButton radio){
        radio.setFont(mainFont);
        radio.setBackground(Color.WHITE);
        radio.setIcon(radioUnselectedIcon);
        radio.setSelectedIcon(radioSelectedIcon);
        radio.setFocusPainted(false);
        radio.setOpaque(false);
    }

    //-------------------Export-für-Controller-----------
    public void addExecuteFunction(ActionListener e){
        startButton.addActionListener(e);
    }
    public boolean getLogikSelect(){return cLogik.isSelected();}
    public boolean getLatticeSelect(){return cLattice.isSelected();}
    public boolean getLinearSelect(){return cLinear.isSelected();}

    public boolean getAbsSelect(){return cAbsolut.isSelected();}
    public String getAbsField(){return absolutField.getText();}

    public boolean getRelSelect(){return cRelativ.isSelected();}
    public String getRelField(){return relativField.getText();}

    //--------------------------------------DEBUGGING--------------------------
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UFAlgorithmInputWindow viewer = new UFAlgorithmInputWindow();
            viewer.setVisible(true);
        });
    }
}
