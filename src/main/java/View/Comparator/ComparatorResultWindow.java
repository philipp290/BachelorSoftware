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

    JLabel solutionOneLabel = new JLabel("Lösung 1");
    JLabel solutionTwoLabel = new JLabel("Lösung 2");
    JLabel relativeLabelOne = new JLabel("Ø Prozentual: ");
    JLabel relativeValueLabelOne = new JLabel();
    JLabel relativeLabelTwo = new JLabel("Ø Prozentual: ");
    JLabel relativeValueLabelTwo = new JLabel();
    JLabel absolutLabelOne = new JLabel("Ø Absolut: ");
    JLabel absolutValueLabelOne = new JLabel();
    JLabel absolutLabelTwo = new JLabel("Ø Absolut: ");
    JLabel absolutValueLabelTwo = new JLabel();
    JLabel shadowLabelOne = new JLabel("Ø Schatten: ");
    JLabel shadowValueLabelOne = new JLabel();
    JLabel shadowLabelTwo = new JLabel("Ø Schatten: ");
    JLabel shadowValueLabelTwo = new JLabel();
    JPanel seperationLine = new JPanel();

    ComparatorResultWindow(double[]input){
        rel1=input[0];
        abs1=input[1];
        shd1=input[2];
        rel2=input[3];
        abs2=input[4];
        shd2=input[5];

        setTitle("Solution-Comparator-Result");
        setSize(405, 300);
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


        solutionOneLabel.setBounds(20,20,120,40);
        solutionOneLabel.setFont(fontBig);
        getContentPane().add(solutionOneLabel);
        relativeLabelOne.setBounds(20,80,120,40);
        relativeLabelOne.setFont(fontNormal);
        getContentPane().add(relativeLabelOne);
        relativeValueLabelOne.setText(String.valueOf(rel1));
        relativeValueLabelOne.setBounds(130,80,40,40);
        relativeValueLabelOne.setFont(fontNormal);
        getContentPane().add(relativeValueLabelOne);
        absolutLabelOne.setBounds(20,140,120,40);
        absolutLabelOne.setFont(fontNormal);
        getContentPane().add(absolutLabelOne);
        absolutValueLabelOne.setText(String.valueOf(abs1));
        absolutValueLabelOne.setBounds(130,140,40,40);
        absolutValueLabelOne.setFont(fontNormal);
        getContentPane().add(absolutValueLabelOne);
        shadowLabelOne.setBounds(20,200,120,40);
        shadowLabelOne.setFont(fontNormal);
        getContentPane().add(shadowLabelOne);
        shadowValueLabelOne.setText(String.valueOf(shd1));
        shadowValueLabelOne.setBounds(130,200,40,40);
        shadowValueLabelOne.setFont(fontNormal);
        getContentPane().add(shadowValueLabelOne);

        seperationLine.setBounds(190,10,2,240);
        seperationLine.setBackground(Color.BLACK);
        getContentPane().add(seperationLine);

        solutionTwoLabel.setBounds(220,20,120,40);
        solutionTwoLabel.setFont(fontBig);
        getContentPane().add(solutionTwoLabel);
        relativeLabelTwo.setBounds(220,80,120,40);
        relativeLabelTwo.setFont(fontNormal);
        getContentPane().add(relativeLabelTwo);
        relativeValueLabelTwo.setText(String.valueOf(rel2));
        relativeValueLabelTwo.setBounds(330,80,40,40);
        relativeValueLabelTwo.setFont(fontNormal);
        getContentPane().add(relativeValueLabelTwo);
        absolutLabelTwo.setBounds(220,140,120,40);
        absolutLabelTwo.setFont(fontNormal);
        getContentPane().add(absolutLabelTwo);
        absolutValueLabelTwo.setText(String.valueOf(abs2));
        absolutValueLabelTwo.setBounds(330,140,40,40);
        absolutValueLabelTwo.setFont(fontNormal);
        getContentPane().add(absolutValueLabelTwo);
        shadowLabelTwo.setBounds(220,200,120,40);
        shadowLabelTwo.setFont(fontNormal);
        getContentPane().add(shadowLabelTwo);
        shadowValueLabelTwo.setText(String.valueOf(shd2));
        shadowValueLabelTwo.setBounds(330,200,40,40);
        shadowValueLabelTwo.setFont(fontNormal);
        getContentPane().add(shadowValueLabelTwo);
    }

    //--------------------Debugging---------------------------
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            double[] test = new double[6];
            test[0] = 10;
            test[1] = 20;
            test[2] = 30;
            test[3] = 40;
            test[4] = 50;
            test[5] = 60;
            ComparatorResultWindow viewer = new ComparatorResultWindow(test);
            viewer.setVisible(true);
        });
    }
}
