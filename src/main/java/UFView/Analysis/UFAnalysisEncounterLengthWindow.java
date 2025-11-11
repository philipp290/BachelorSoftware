package UFView.Analysis;

import DView.Analysis.CsvAnalysisEncounterLengthDetailWindow;
import Model.Services.CsvReaderService;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class UFAnalysisEncounterLengthWindow extends JFrame {
    private JLabel medianLabel;
    private JLabel medianV;
    private JLabel averageLabel;
    private JLabel averageV;
    private JLabel maxLabel;
    private JLabel maxV;
    private JLabel minLabel;
    private JLabel minV;

    private JButton detail;

    private ImageIcon startIcon;

    private final Color defaultColor;
    private final Color hoverColor;
    private final Color clickColor;
    private final Font mainFont;
    private final Font bigFont;

    private double[] values;

    public UFAnalysisEncounterLengthWindow(double[] v) {
        this.values = v;
        defaultColor = new Color(220, 220, 220);
        hoverColor = new Color(170, 170, 170);
        clickColor = new Color(130, 130, 130);
        mainFont = new Font("Arial", Font.BOLD, 16);
        bigFont = new Font("Arial", Font.BOLD, 20);

        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/emergencityIcon.png"));
        setIconImage(icon);

        startIcon = new ImageIcon(getClass().getResource("/startIcon.png"));
        Image scaledImage = startIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        startIcon = new ImageIcon(scaledImage);

        setTitle("Analysis");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel content = new JPanel(null);
        content.setBackground(Color.WHITE);
        content.setPreferredSize(new Dimension(240, 280));
        setContentPane(content);

        initUI();
        pack();
        setLocationRelativeTo(null);


    }

    public void initUI() {
        medianLabel = new JLabel("Median: ");
        medianLabel.setFont(bigFont);
        medianLabel.setBounds(20,20,140,30);
        getContentPane().add(medianLabel );

        medianV = new JLabel(String.valueOf((int)values[0]));
        medianV.setFont(mainFont);
        medianV.setBounds(160,20,60,30);
        medianV.setHorizontalAlignment(SwingConstants.CENTER);
        medianV.setVerticalAlignment(SwingConstants.CENTER);
        medianV.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        getContentPane().add(medianV);

        averageLabel = new JLabel("Durchschnitt: ");
        averageLabel.setFont(bigFont);
        averageLabel.setBounds(20,70,140,30);
        getContentPane().add(averageLabel);

        averageV = new JLabel(String.valueOf((Math.round(values[1]*100.0))/100.0));
        averageV.setFont(mainFont);
        averageV.setBounds(160,70,60,30);
        averageV.setHorizontalAlignment(SwingConstants.CENTER);
        averageV.setVerticalAlignment(SwingConstants.CENTER);
        averageV.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        getContentPane().add(averageV);

        maxLabel = new JLabel("Maximum: ");
        maxLabel.setFont(bigFont);
        maxLabel.setBounds(20,120,140,30);
        getContentPane().add(maxLabel);

        maxV = new JLabel(String.valueOf((int)values[2]));
        maxV.setFont(mainFont);
        maxV.setBounds(160,120,60,30);
        maxV.setHorizontalAlignment(SwingConstants.CENTER);
        maxV.setVerticalAlignment(SwingConstants.CENTER);
        maxV.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        getContentPane().add(maxV);

        minLabel = new JLabel("Minimum: ");
        minLabel.setFont(bigFont);
        minLabel.setBounds(20,170,140,30);
        getContentPane().add(minLabel);

        minV = new JLabel(String.valueOf((int)values[3]));
        minV.setFont(mainFont);
        minV.setBounds(160,170,60,30);
        minV.setHorizontalAlignment(SwingConstants.CENTER);
        minV.setVerticalAlignment(SwingConstants.CENTER);
        minV.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        getContentPane().add(minV);

        detail = new JButton("Detail-Ansicht",startIcon);
        formatButton(detail);
        detail.setBounds(20,220,200,40);
        getContentPane().add(detail);

        //-------------BUTTON-FUNCTION--------------------
        detail.addActionListener((ActionEvent)->{
            CsvReaderService crs = new CsvReaderService();
            ArrayList<Integer> encounterLengthDetails = crs.readIntegerList("Data/Cache/AnalysisCache/encounterLengthTemporary.csv");
            SwingUtilities.invokeLater(() -> {
                UFAnalysisEncounterLengthDetailWindow viewer = new UFAnalysisEncounterLengthDetailWindow(encounterLengthDetails);
                viewer.setVisible(true);
            });
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

    //--------------------------------------DEBUGGING--------------------------
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            double[] t = new double[4];
            t[0]=0;
            t[1]=1;
            t[2]=2;
            t[3]=3;
            UFAnalysisEncounterLengthWindow viewer = new UFAnalysisEncounterLengthWindow(t);
            viewer.setVisible(true);
        });
    }
}
