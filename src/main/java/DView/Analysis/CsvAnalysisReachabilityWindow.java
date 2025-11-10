package DView.Analysis;

import javax.swing.*;
import java.awt.*;

public class CsvAnalysisReachabilityWindow extends JFrame {
    private int reachedAbs;
    private int wholeAbs;
    private double reachedRel;

    public CsvAnalysisReachabilityWindow(double[] input) {
        this.reachedAbs = (int) input[0];
        this.wholeAbs = (int) input[1];
        this.reachedRel = input[2];

        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/emergencityIcon.png"));
        setIconImage(icon);

        setTitle("Erreichbarkeits-Auswertung");
        setSize(340, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        initUI();
    }
    private void initUI() {
        getContentPane().setLayout(null);
        String padding = "";
        int padDistance=14;
        if(reachedAbs>99){
            padDistance=padDistance-2;
            if(reachedAbs>999){
                padDistance=padDistance-2;
                if(reachedAbs>9999){
                    padDistance=padDistance-2;
                }
            }
        }
        if(wholeAbs>99){
            padDistance=padDistance-2;
            if(wholeAbs>999){
                padDistance=padDistance-2;
                if(wholeAbs>9999){
                    padDistance=padDistance-2;
                }
            }
        }
        for(int i =0; i<padDistance;i++){
            padding =padding + " ";
        }
        JLabel absoluteLabel = new JLabel("Absolut: "+padding+reachedAbs+"/"+wholeAbs);
        absoluteLabel.setBounds(20,20,300,40);
        absoluteLabel.setFont(absoluteLabel.getFont().deriveFont(25f));
        getContentPane().add(absoluteLabel);

        JLabel relativeLabel = new JLabel("Prozentual:          "+(int)(reachedRel*100)+"%");
        relativeLabel.setBounds(20,90,300,40);
        relativeLabel.setFont(relativeLabel.getFont().deriveFont(25f));
        getContentPane().add(relativeLabel);

        JPanel thickLine = new JPanel();
        thickLine.setBackground(Color.BLACK);
        thickLine.setBounds(10, 75, 310, 2);
        getContentPane().add(thickLine);
    }

    public static void main(String[] args) {
        double[] test = {200,500,0.5};
        SwingUtilities.invokeLater(() -> {
            CsvAnalysisReachabilityWindow viewer = new CsvAnalysisReachabilityWindow(test);
            viewer.setVisible(true);
        });
    }
}
