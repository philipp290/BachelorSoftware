package View.Algorithm;

import Controller.Algorithm.AlgorithmInputController;
import Model.Algorithms.LatticeAlgorithms.LatticeAlgorithm;
import Model.Algorithms.LatticeAlgorithms.Relativ.BottomUpAlgorithm;
import Model.Algorithms.LatticeAlgorithms.Relativ.TopDownAlgorithm;
import Model.Session;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AlgorithmBottomUpTopDownWindow extends JFrame {
    private JLabel mainText = new JLabel();
    private JTextField inputField = new JTextField();
    private JPanel seperationLine = new JPanel();
    private JButton nextButton = new JButton("Weiter");
    private boolean windowType;

    private AlgorithmInputController aic;

    public AlgorithmBottomUpTopDownWindow(boolean type, AlgorithmInputController aic){
        this.aic = aic;

        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/emergencityIcon.png"));
        setIconImage(icon);

        this.windowType = type;
        if(windowType) {
            setTitle("Top-Down-Selection");
        }else{
            setTitle("Bottom-Up-Selection");
        }
        setSize(280, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);


        initUI();
    }
    private void initUI() {
        getContentPane().setLayout(null);

        Font fontNormal = new Font("Dialog", Font.PLAIN, 18);

        mainText.setBounds(20, 20, 130, 40);
        mainText.setFont(fontNormal);
        if (windowType) {
            mainText.setText("Untergrenze:");
        } else {
            mainText.setText("Obergrenze:");
        }
        getContentPane().add(mainText);

        inputField.setBounds(145, 20, 100, 40);
        getContentPane().add(inputField);

        seperationLine.setBounds(10, 80, 235, 1);
        seperationLine.setBackground(Color.BLACK);
        getContentPane().add(seperationLine);

        nextButton.setBounds(145, 100, 100, 40);
        getContentPane().add(nextButton);

        //-------------------BUTTON-FUNCTION------------------
        nextButton.addActionListener((ActionEvent) ->{
            int input = Integer.parseInt(this.inputField.getText());
            Session.getInstance().getAlgorithmParameters().add(input);
            aic.executeAlgorithm(new LatticeAlgorithm());
            dispose();
        });


    }
    //--------------------Debugging---------------------------
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AlgorithmBottomUpTopDownWindow viewer = new AlgorithmBottomUpTopDownWindow(false ,new AlgorithmInputController(new AlgorithmInputWindow()));
            viewer.setVisible(true);
        });
    }
}
