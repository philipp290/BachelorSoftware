package View.Algorithm;

import Controller.Algorithm.AlgorithmInputController;
import Model.Algorithms.LatticeAlgorithms.LatticeAlgorithm;
import Model.Algorithms.LatticeAlgorithms.Relativ.BottomUpAlgorithm;
import Model.Algorithms.LatticeAlgorithms.Relativ.CombinedAlgorithm;
import Model.Algorithms.LatticeAlgorithms.Relativ.RandomWalkAlgorithm;
import Model.Algorithms.LatticeAlgorithms.Relativ.TopDownAlgorithm;
import Model.Session;

import javax.swing.*;
import java.awt.*;

public class AlgorithmRandomWalkWindow extends JFrame {
    private JLabel ceilText = new JLabel("Obergrenze:");
    private JTextField ceilField = new JTextField();
    private JLabel botText = new JLabel("Untergrenze:");
    private JTextField botField = new JTextField();
    private JLabel timerText = new JLabel("Timer:");
    private JTextField timerField = new JTextField();
    private JPanel seperationLine = new JPanel();
    private JButton nextButton = new JButton("Weiter");

    private boolean windowType;

    private AlgorithmInputController aic;

    public AlgorithmRandomWalkWindow(boolean type, AlgorithmInputController aic){
        this.aic = aic;
        this.windowType = type;

        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/emergencityIcon.png"));
        setIconImage(icon);
        if(type) {
            setTitle("Random-Walk-Selection");
        }else{
            setTitle("Combined-Selection");
        }

        setSize(280, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);


        initUI();
    }
    private void initUI() {
        getContentPane().setLayout(null);

        Font fontNormal = new Font("Dialog", Font.PLAIN, 18);

        ceilText.setBounds(20, 20, 130, 40);
        ceilText.setFont(fontNormal);
        getContentPane().add(ceilText);

        ceilField.setBounds(145, 20, 100, 40);
        getContentPane().add(ceilField);

        botText.setBounds(20, 70, 130, 40);
        botText.setFont(fontNormal);
        getContentPane().add(botText);

        botField.setBounds(145, 70, 100, 40);
        getContentPane().add(botField);

        timerText.setBounds(20, 120, 130, 40);
        timerText.setFont(fontNormal);
        getContentPane().add(timerText);

        timerField.setBounds(145, 120, 100, 40);
        getContentPane().add(timerField);

        seperationLine.setBounds(10, 180, 235, 1);
        seperationLine.setBackground(Color.BLACK);
        getContentPane().add(seperationLine);

        nextButton.setBounds(145, 200, 100, 40);
        getContentPane().add(nextButton);

        //-----------------BUTTON-FUNCTION------------------------
        nextButton.addActionListener((ActionEvent) -> {
            int ceil = Integer.parseInt(this.ceilField.getText());
            int bot = Integer.parseInt(this.botField.getText());
            int timer = Integer.parseInt(this.timerField.getText());
            Session.getInstance().getAlgorithmParameters().add(bot);
            Session.getInstance().getAlgorithmParameters().add(ceil);
            Session.getInstance().getAlgorithmParameters().add(timer);
            aic.executeAlgorithm(new LatticeAlgorithm());
            dispose();
        });

    }
    //--------------------Debugging---------------------------
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AlgorithmRandomWalkWindow viewer = new AlgorithmRandomWalkWindow(true, new AlgorithmInputController(new AlgorithmInputWindow()));
            viewer.setVisible(true);
        });
    }
}
