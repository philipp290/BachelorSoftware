package UFView.Comparator;

import DView.Comparator.ComparatorResultWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.font.TextAttribute;
import java.util.Map;

public class UFComparatorResultWindow extends JFrame {
    double rel1;
    double abs1;
    double shd1;
    double rel2;
    double abs2;
    double shd2;
    double c1;
    double c2;

    JLabel solutionOneLabel = new JLabel("Lösung 1");
    JLabel solutionTwoLabel = new JLabel("Lösung 2");
    JLabel relativeLabelOne = new JLabel("Prozentual: ");
    JLabel relativeValueLabelOne = new JLabel();
    JLabel relativeLabelTwo = new JLabel("Prozentual: ");
    JLabel relativeValueLabelTwo = new JLabel();
    JLabel absolutLabelOne = new JLabel("Absolut: ");
    JLabel absolutValueLabelOne = new JLabel();
    JLabel absolutLabelTwo = new JLabel("Absolut: ");
    JLabel absolutValueLabelTwo = new JLabel();
    JLabel countLabelOne = new JLabel("Säulenzahl: ");
    JLabel countValueLabelOne = new JLabel();
    JLabel countLabelTwo = new JLabel("Säulenzahl: ");
    JLabel countValueLabelTwo = new JLabel();
    JLabel shadowLabelOne = new JLabel("Ø Schatten: ");
    JLabel shadowValueLabelOne = new JLabel();
    JLabel shadowLabelTwo = new JLabel("Ø Schatten: ");
    JLabel shadowValueLabelTwo = new JLabel();
    JPanel seperationLine = new JPanel();

    private final Font mainFont;
    private final Font fontBig;


    public UFComparatorResultWindow(double[]input){
        rel1=input[0];
        abs1=input[1];
        c1  =input[2];
        shd1=input[3];
        rel2=input[4];
        abs2=input[5];
        c2  =input[6];
        shd2=input[7];

        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/emergencityIcon.png"));
        setIconImage(icon);

        mainFont = new Font("Arial", Font.BOLD, 16);

        Font font = new Font("Arial", Font.BOLD, 20);
        Map<TextAttribute, Object> attributes = (Map<TextAttribute, Object>) font.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        fontBig = new Font(attributes);

        setTitle("Vergleich");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel content = new JPanel(null);
        content.setBackground(Color.WHITE);
        content.setPreferredSize(new Dimension(400, 230));
        setContentPane(content);

        initUI();
        pack();
        setLocationRelativeTo(null);
    }

    public void initUI(){
        getContentPane().setLayout(null);

        solutionOneLabel.setBounds(20,20,120,40);
        solutionOneLabel.setFont(fontBig);
        getContentPane().add(solutionOneLabel);

        relativeLabelOne.setBounds(20,60,120,30);
        relativeLabelOne.setFont(mainFont);
        getContentPane().add(relativeLabelOne);
        relativeValueLabelOne.setText((int) rel1+"%");
        relativeValueLabelOne.setHorizontalAlignment(SwingConstants.CENTER);
        relativeValueLabelOne.setVerticalAlignment(SwingConstants.CENTER);
        relativeValueLabelOne.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        relativeValueLabelOne.setBounds(130,60,50,30);
        relativeValueLabelOne.setFont(mainFont);
        getContentPane().add(relativeValueLabelOne);

        absolutLabelOne.setBounds(20,100,120,30);
        absolutLabelOne.setFont(mainFont);
        getContentPane().add(absolutLabelOne);
        absolutValueLabelOne.setText(String.valueOf((int) abs1));
        absolutValueLabelOne.setBounds(130,100,50,30);
        absolutValueLabelOne.setFont(mainFont);
        absolutValueLabelOne.setHorizontalAlignment(SwingConstants.CENTER);
        absolutValueLabelOne.setVerticalAlignment(SwingConstants.CENTER);
        absolutValueLabelOne.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        getContentPane().add(absolutValueLabelOne);

        countLabelOne.setBounds(20,140,120,30);
        countLabelOne.setFont(mainFont);
        getContentPane().add(countLabelOne);
        countValueLabelOne.setText(String.valueOf((int)c1));
        countValueLabelOne.setBounds(130,140,50,30);
        countValueLabelOne.setFont(mainFont);
        countValueLabelOne.setHorizontalAlignment(SwingConstants.CENTER);
        countValueLabelOne.setVerticalAlignment(SwingConstants.CENTER);
        countValueLabelOne.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        getContentPane().add(countValueLabelOne);

        shadowLabelOne.setBounds(20,180,120,30);
        shadowLabelOne.setFont(mainFont);
        getContentPane().add(shadowLabelOne);
        shadowValueLabelOne.setText(String.valueOf(shd1));
        shadowValueLabelOne.setBounds(130,180,50,30);
        shadowValueLabelOne.setFont(mainFont);
        shadowValueLabelOne.setHorizontalAlignment(SwingConstants.CENTER);
        shadowValueLabelOne.setVerticalAlignment(SwingConstants.CENTER);
        shadowValueLabelOne.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        getContentPane().add(shadowValueLabelOne);

        seperationLine.setBounds(199,10,2,210);
        seperationLine.setBackground(Color.BLACK);
        getContentPane().add(seperationLine);

        solutionTwoLabel.setBounds(220,20,120,30);
        solutionTwoLabel.setFont(fontBig);
        getContentPane().add(solutionTwoLabel);

        relativeLabelTwo.setBounds(220,60,120,30);
        relativeLabelTwo.setFont(mainFont);
        getContentPane().add(relativeLabelTwo);
        relativeValueLabelTwo.setText((int) rel2+"%");
        relativeValueLabelTwo.setBounds(330,60,50,30);
        relativeValueLabelTwo.setFont(mainFont);
        relativeValueLabelTwo.setHorizontalAlignment(SwingConstants.CENTER);
        relativeValueLabelTwo.setVerticalAlignment(SwingConstants.CENTER);
        relativeValueLabelTwo.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        getContentPane().add(relativeValueLabelTwo);

        absolutLabelTwo.setBounds(220,100,120,30);
        absolutLabelTwo.setFont(mainFont);
        getContentPane().add(absolutLabelTwo);
        absolutValueLabelTwo.setText(String.valueOf((int) abs2));
        absolutValueLabelTwo.setBounds(330,100,50,30);
        absolutValueLabelTwo.setFont(mainFont);
        absolutValueLabelTwo.setHorizontalAlignment(SwingConstants.CENTER);
        absolutValueLabelTwo.setVerticalAlignment(SwingConstants.CENTER);
        absolutValueLabelTwo.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        getContentPane().add(absolutValueLabelTwo);

        countLabelTwo.setBounds(220,140,120,30);
        countLabelTwo.setFont(mainFont);
        getContentPane().add(countLabelTwo);
        countValueLabelTwo.setText(String.valueOf((int)c2));
        countValueLabelTwo.setBounds(330,140,50,30);
        countValueLabelTwo.setFont(mainFont);
        countValueLabelTwo.setHorizontalAlignment(SwingConstants.CENTER);
        countValueLabelTwo.setVerticalAlignment(SwingConstants.CENTER);
        countValueLabelTwo.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        getContentPane().add(countValueLabelTwo);

        shadowLabelTwo.setBounds(220,180,120,30);
        shadowLabelTwo.setFont(mainFont);
        getContentPane().add(shadowLabelTwo);
        shadowValueLabelTwo.setText(String.valueOf(shd2));
        shadowValueLabelTwo.setBounds(330,180,50,30);
        shadowValueLabelTwo.setFont(mainFont);
        shadowValueLabelTwo.setHorizontalAlignment(SwingConstants.CENTER);
        shadowValueLabelTwo.setVerticalAlignment(SwingConstants.CENTER);
        shadowValueLabelTwo.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        getContentPane().add(shadowValueLabelTwo);
    }

    //--------------------Debugging---------------------------
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            double[] test = new double[8];
            test[0] = 10;
            test[1] = 20;
            test[2] = 30;
            test[3] = 40;
            test[4] = 50;
            test[5] = 60;
            test[6] = 70;
            test[7] = 80;
            UFComparatorResultWindow viewer = new UFComparatorResultWindow(test);
            viewer.setVisible(true);
        });
    }
}
