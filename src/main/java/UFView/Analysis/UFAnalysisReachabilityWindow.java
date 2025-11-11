package UFView.Analysis;

import UFController.Algorithm.UFAlgorithmInputController;
import UFView.Algorithm.UFAlgorithmInputWindow;
import UFView.Comparator.UFComparatorWindow;
import UFView.ExportUnity.UFExportUnityWindow;
import UFView.UFMainWindow;

import javax.swing.*;
import java.awt.*;

public class UFAnalysisReachabilityWindow extends JFrame {
    private JLabel absolutLabel;
    private JLabel absV1;
    private JLabel slash;
    private JLabel absV2;
    private JLabel relativLabel;
    private JLabel relV1;
    private JLabel percent;


    private final Font mainFont;
    private final Font bigFont;

    private double[] values;

    public UFAnalysisReachabilityWindow(double[] v) {
        this.values = v;
        mainFont = new Font("Arial", Font.BOLD, 16);
        bigFont = new Font("Arial", Font.BOLD, 20);

        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/emergencityIcon.png"));
        setIconImage(icon);

        setTitle("Analysis");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel content = new JPanel(null);
        content.setBackground(Color.WHITE);
        content.setPreferredSize(new Dimension(280, 120));
        setContentPane(content);

        initUI();
        pack();
        setLocationRelativeTo(null);


    }

    public void initUI() {
        absolutLabel = new JLabel("Absolut:");
        absolutLabel.setFont(bigFont);
        absolutLabel.setBounds(20,20,100,30);
        getContentPane().add(absolutLabel);

        absV1 = new JLabel(String.valueOf((int)values[0]));
        absV1.setFont(mainFont);
        absV1.setBounds(120,20,60,30);
        absV1.setHorizontalAlignment(SwingConstants.CENTER);
        absV1.setVerticalAlignment(SwingConstants.CENTER);
        absV1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        getContentPane().add(absV1);

        slash = new JLabel("/");
        slash.setFont(bigFont);
        slash.setBounds(187,20,30,30);
        getContentPane().add(slash);

        absV2 = new JLabel(String.valueOf((int)values[1]));
        absV2.setFont(mainFont);
        absV2.setBounds(200,20,60,30);
        absV2.setHorizontalAlignment(SwingConstants.CENTER);
        absV2.setVerticalAlignment(SwingConstants.CENTER);
        absV2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        getContentPane().add(absV2);

        relativLabel = new JLabel("Relativ:");
        relativLabel.setFont(bigFont);
        relativLabel.setBounds(20,70,100,30);
        getContentPane().add(relativLabel);

        relV1 = new JLabel(String.valueOf((int)(values[2]*100)));
        relV1.setFont(mainFont);
        relV1.setBounds(120,70,60,30);
        relV1.setHorizontalAlignment(SwingConstants.CENTER);
        relV1.setVerticalAlignment(SwingConstants.CENTER);
        relV1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        getContentPane().add(relV1);

        percent = new JLabel("%");
        percent.setFont(bigFont);
        percent.setBounds(187,70,40,30);
        getContentPane().add(percent);
    }



    //--------------------------------------DEBUGGING--------------------------
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            double[] t = new double[3];
            t[0]=0;
            t[1]=1;
            t[2]=2;
            UFAnalysisReachabilityWindow viewer = new UFAnalysisReachabilityWindow(t);
            viewer.setVisible(true);
        });
    }




}
