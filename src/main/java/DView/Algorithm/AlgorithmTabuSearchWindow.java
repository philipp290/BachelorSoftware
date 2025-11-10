package DView.Algorithm;

import DController.Algorithm.AlgorithmInputController;
import Model.Algorithms.LatticeAlgorithms.LatticeAlgorithm;
import Model.Session;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.font.TextAttribute;
import java.util.Map;

public class AlgorithmTabuSearchWindow extends JFrame {
    private AlgorithmInputController aic;

    JLabel header = new JLabel("TabuSearch-Parameter");
    JLabel textOne = new JLabel("NoOptimumTimer:");
    JLabel textTwo = new JLabel("TabuTimer:");
    JTextField noOptimumTimer = new JTextField();
    JTextField tabuTimer = new JTextField();
    JButton nextButton = new JButton("Weiter");

    public AlgorithmTabuSearchWindow(AlgorithmInputController ai){

        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/emergencityIcon.png"));
        setIconImage(icon);

        setTitle("Solution-Comparator-Result");
        setSize(315, 280);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        this.aic = ai;

        initUI();
    }
    void initUI(){
        getContentPane().setLayout(null);

        Font font = new Font("Dialog", Font.BOLD, 20);
        Map<TextAttribute, Object> attributes = (Map<TextAttribute, Object>) font.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        Font fontBig = new Font(attributes);

        Font fontNormal = new Font("Dialog", Font.PLAIN, 18);

        header.setBounds(30,20,300,40);
        header.setFont(fontBig);
        getContentPane().add(header);

        textOne.setBounds(30,70,160,40);
        textOne.setFont(fontNormal);
        getContentPane().add(textOne);

        noOptimumTimer.setBounds(190,70,80,40);
        noOptimumTimer.setText("2");
        getContentPane().add(noOptimumTimer);

        textTwo.setBounds(30,120,160,40);
        textTwo.setFont(fontNormal);
        getContentPane().add(textTwo);

        tabuTimer.setBounds(190,120,80,40);
        tabuTimer.setText("3");
        getContentPane().add(tabuTimer);

        nextButton.setBounds(150,170,120,40);
        getContentPane().add(nextButton);

        //----------------BUTTON-FUNCTION-----------------------
        nextButton.addActionListener((ActionEvent e) -> {
            Session.getInstance().getAlgorithmParameters().clear();
            Session.getInstance().getAlgorithmParameters().add(Integer.parseInt(noOptimumTimer.getText()));
            Session.getInstance().getAlgorithmParameters().add(Integer.parseInt(tabuTimer.getText()));
            this.dispose();
            aic.executeAlgorithm(new LatticeAlgorithm());
        });
    }

    //--------------------Debugging---------------------------
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AlgorithmTabuSearchWindow viewer = new AlgorithmTabuSearchWindow(new AlgorithmInputController(new AlgorithmInputWindow()));
            viewer.setVisible(true);
        });
    }
}
