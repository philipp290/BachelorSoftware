package View.Comparator;

import javax.swing.*;
import java.awt.*;
import java.awt.font.TextAttribute;
import java.util.Map;

public class ComparatorResultWindow extends JFrame {
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
    JLabel countLabelOne = new JLabel("Säulenanzahl: ");
    JLabel countValueLabelOne = new JLabel();
    JLabel countLabelTwo = new JLabel("Säulenanzahl: ");
    JLabel countValueLabelTwo = new JLabel();
    JLabel shadowLabelOne = new JLabel("Ø Schatten: ");
    JLabel shadowValueLabelOne = new JLabel();
    JLabel shadowLabelTwo = new JLabel("Ø Schatten: ");
    JLabel shadowValueLabelTwo = new JLabel();
    JPanel seperationLine = new JPanel();

    public ComparatorResultWindow(double[]input){
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

        setTitle("Solution-Comparator-Result");
        setSize(420, 360);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);


        initUI();
    }

    public void initUI(){
        getContentPane().setLayout(null);

        Font font = new Font("Dialog", Font.BOLD, 20);
        Map<TextAttribute, Object> attributes = (Map<TextAttribute, Object>) font.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        Font fontBig = new Font(attributes);

        Font fontNormal = new Font("Dialog", Font.PLAIN, 16);


        solutionOneLabel.setBounds(30,20,120,40);
        solutionOneLabel.setFont(fontBig);
        getContentPane().add(solutionOneLabel);

        relativeLabelOne.setBounds(30,70,120,40);
        relativeLabelOne.setFont(fontNormal);
        getContentPane().add(relativeLabelOne);
        relativeValueLabelOne.setText((int) rel1+"%");
        relativeValueLabelOne.setBounds(140,70,60,40);
        relativeValueLabelOne.setFont(fontNormal);
        getContentPane().add(relativeValueLabelOne);

        absolutLabelOne.setBounds(30,130,120,40);
        absolutLabelOne.setFont(fontNormal);
        getContentPane().add(absolutLabelOne);
        absolutValueLabelOne.setText(String.valueOf((int) abs1));
        absolutValueLabelOne.setBounds(140,130,40,40);
        absolutValueLabelOne.setFont(fontNormal);
        getContentPane().add(absolutValueLabelOne);

        countLabelOne.setBounds(30,190,120,40);
        countLabelOne.setFont(fontNormal);
        getContentPane().add(countLabelOne);
        countValueLabelOne.setText(String.valueOf((int)c1));
        countValueLabelOne.setBounds(140,190,40,40);
        countValueLabelOne.setFont(fontNormal);
        getContentPane().add(countValueLabelOne);

        shadowLabelOne.setBounds(30,250,120,40);
        shadowLabelOne.setFont(fontNormal);
        getContentPane().add(shadowLabelOne);
        shadowValueLabelOne.setText(String.valueOf(shd1));
        shadowValueLabelOne.setBounds(140,250,40,40);
        shadowValueLabelOne.setFont(fontNormal);
        getContentPane().add(shadowValueLabelOne);

        seperationLine.setBounds(200,20,2,280);
        seperationLine.setBackground(Color.BLACK);
        getContentPane().add(seperationLine);

        solutionTwoLabel.setBounds(230,20,120,40);
        solutionTwoLabel.setFont(fontBig);
        getContentPane().add(solutionTwoLabel);

        relativeLabelTwo.setBounds(230,70,120,40);
        relativeLabelTwo.setFont(fontNormal);
        getContentPane().add(relativeLabelTwo);
        relativeValueLabelTwo.setText((int) rel2+"%");
        relativeValueLabelTwo.setBounds(340,70,60,40);
        relativeValueLabelTwo.setFont(fontNormal);
        getContentPane().add(relativeValueLabelTwo);

        absolutLabelTwo.setBounds(230,130,120,40);
        absolutLabelTwo.setFont(fontNormal);
        getContentPane().add(absolutLabelTwo);
        absolutValueLabelTwo.setText(String.valueOf((int) abs2));
        absolutValueLabelTwo.setBounds(340,130,40,40);
        absolutValueLabelTwo.setFont(fontNormal);
        getContentPane().add(absolutValueLabelTwo);

        countLabelTwo.setBounds(230,190,120,40);
        countLabelTwo.setFont(fontNormal);
        getContentPane().add(countLabelTwo);
        countValueLabelTwo.setText(String.valueOf((int)c2));
        countValueLabelTwo.setBounds(340,190,40,40);
        countValueLabelTwo.setFont(fontNormal);
        getContentPane().add(countValueLabelTwo);

        shadowLabelTwo.setBounds(230,250,120,40);
        shadowLabelTwo.setFont(fontNormal);
        getContentPane().add(shadowLabelTwo);
        shadowValueLabelTwo.setText(String.valueOf(shd2));
        shadowValueLabelTwo.setBounds(340,250,40,40);
        shadowValueLabelTwo.setFont(fontNormal);
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
            ComparatorResultWindow viewer = new ComparatorResultWindow(test);
            viewer.setVisible(true);
        });
    }
}
