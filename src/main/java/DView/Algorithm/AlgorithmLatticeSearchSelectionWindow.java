package DView.Algorithm;

import DController.Algorithm.AlgorithmInputController;
import Model.Session;

import javax.swing.*;
import java.awt.*;


public class AlgorithmLatticeSearchSelectionWindow extends JFrame {
    private JLabel bottomUpText = new JLabel("Bottom Up");
    private JCheckBox cBottomUp = new JCheckBox();
    private JLabel topDownText = new JLabel("Top Down");
    private JCheckBox cTopDown = new JCheckBox();
    private JLabel randomWalkText = new JLabel("Random Walk");
    private JCheckBox cRandomWalk = new JCheckBox();
    private JPanel seperationLineOne = new JPanel();
    private JLabel combinedText = new JLabel("Combined");
    private JCheckBox cCombined = new JCheckBox();
    private JPanel seperationLineTwo = new JPanel();
    private JButton nextButton = new JButton("Weiter");

    private AlgorithmInputController aic;

    public AlgorithmLatticeSearchSelectionWindow(AlgorithmInputController aic){
        this.aic = aic;

        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/emergencityIcon.png"));
        setIconImage(icon);

        setTitle("Lattice-Search-Selection");
        setSize(280, 340);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);


        initUI();
    }
    private void initUI(){
        getContentPane().setLayout(null);

        Font fontNormal = new Font("Dialog", Font.PLAIN, 20);

        bottomUpText.setBounds(30,20,160,40);
        bottomUpText.setFont(fontNormal);
        getContentPane().add(bottomUpText);

        cBottomUp.setBounds(210,20,40,40);
        getContentPane().add(cBottomUp);

        topDownText.setBounds(30,65,160,40);
        topDownText.setFont(fontNormal);
        getContentPane().add(topDownText);

        cTopDown.setBounds(210,65,40,40);
        getContentPane().add(cTopDown);

        randomWalkText.setBounds(30,110,160,40);
        randomWalkText.setFont(fontNormal);
        getContentPane().add(randomWalkText);

        cRandomWalk.setBounds(210,110,40,40);
        getContentPane().add(cRandomWalk);

        seperationLineOne.setBounds(10,160,235,1);
        seperationLineOne.setBackground(Color.BLACK);
        getContentPane().add(seperationLineOne);

        combinedText.setBounds(30,170,160,40);
        combinedText.setFont(fontNormal);
        getContentPane().add(combinedText);

        cCombined.setBounds(210,170,40,40);
        getContentPane().add(cCombined);

        seperationLineTwo.setBounds(10,220,235,1);
        seperationLineTwo.setBackground(Color.BLACK);
        getContentPane().add(seperationLineTwo);

        nextButton.setBounds(145,240,100,40);
        getContentPane().add(nextButton);

        //--------------------BUTTON-FUNCTION-----------------
        cBottomUp.addActionListener((ActionEvent) ->{
            if(cBottomUp.isSelected()) {
                cTopDown.setSelected(false);
                cRandomWalk.setSelected(false);
                cCombined.setSelected(false);
            }
        });
        cTopDown.addActionListener((ActionEvent) ->{
            if(cTopDown.isSelected()) {
                cRandomWalk.setSelected(false);
                cBottomUp.setSelected(false);
                cCombined.setSelected(false);
            }
        });
        cRandomWalk.addActionListener((ActionEvent) ->{
            if(cRandomWalk.isSelected()) {
                cTopDown.setSelected(false);
                cBottomUp.setSelected(false);
                cCombined.setSelected(false);
            }
        });
        cCombined.addActionListener((ActionEvent) ->{
            if(cCombined.isSelected()) {
                cTopDown.setSelected(false);
                cBottomUp.setSelected(false);
                cRandomWalk.setSelected(false);
            }
        });
        nextButton.addActionListener((ActionEvent) -> {
            if(cCombined.isSelected()||cRandomWalk.isSelected()||cTopDown.isSelected()||cBottomUp.isSelected()) {
                Session.getInstance().getAlgorithmParameters().clear();
                if (cBottomUp.isSelected()) {
                    SwingUtilities.invokeLater(() -> {
                        Session.getInstance().getAlgorithmParameters().add(1);
                        AlgorithmBottomUpTopDownWindow viewer = new AlgorithmBottomUpTopDownWindow(false, this.aic);
                        viewer.setVisible(true);
                    });
                } else if (cTopDown.isSelected()) {
                    SwingUtilities.invokeLater(() -> {
                        Session.getInstance().getAlgorithmParameters().add(2);
                        AlgorithmBottomUpTopDownWindow viewer = new AlgorithmBottomUpTopDownWindow(true, this.aic);
                        viewer.setVisible(true);
                    });
                } else if (cRandomWalk.isSelected()) {
                    SwingUtilities.invokeLater(() -> {
                        Session.getInstance().getAlgorithmParameters().add(3);
                        AlgorithmRandomWalkWindow viewer = new AlgorithmRandomWalkWindow(true, this.aic);
                        viewer.setVisible(true);
                    });
                } else if (cCombined.isSelected()) {
                    SwingUtilities.invokeLater(() -> {
                        Session.getInstance().getAlgorithmParameters().add(4);
                        AlgorithmRandomWalkWindow viewer = new AlgorithmRandomWalkWindow(false, this.aic);
                        viewer.setVisible(true);
                    });
                }
                this.dispose();
            }else{
                JOptionPane.showMessageDialog(this, "Wähle einen Algorithmus", "Fehler", JOptionPane.ERROR_MESSAGE);
            }
        });



    }


    //--------------------Debugging---------------------------
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AlgorithmLatticeSearchSelectionWindow viewer = new AlgorithmLatticeSearchSelectionWindow(new AlgorithmInputController(new AlgorithmInputWindow()));
            viewer.setVisible(true);
        });
    }
}
