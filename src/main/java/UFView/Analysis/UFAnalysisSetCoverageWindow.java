package UFView.Analysis;

import Model.Components.Person;
import Model.Session;

import javax.swing.*;
import java.awt.*;
import java.awt.font.TextAttribute;
import java.util.BitSet;
import java.util.Map;

public class UFAnalysisSetCoverageWindow extends JFrame {
    private JLabel header1;
    private JLabel absolutLabel1;
    private JLabel absV1_1;
    private JLabel slash1;
    private JLabel absV1_2;
    private JLabel relativLabel1;
    private JLabel relV1;
    private JLabel percent1;

    private JPanel seperationLineOne;

    private JLabel header2;
    private JLabel absolutLabel2;
    private JLabel absV2_1;
    private JLabel slash2;
    private JLabel absV2_2;
    private JLabel relativLabel2;
    private JLabel relV2;
    private JLabel percent2;

    private JPanel seperationLineTwo;

    private JLabel header3;
    private JLabel absolutLabel3;
    private JLabel absV3_1;
    private JLabel slash3;
    private JLabel absV3_2;
    private JLabel relativLabel3;
    private JLabel relV3;
    private JLabel percent3;

    private final Font mainFont;
    private final Font bigFont;
    private final Font underline;

    private double[] values;

    private int type;

    public UFAnalysisSetCoverageWindow(double[] v, int t) {
        this.values = v;
        this.type = t;
        mainFont = new Font("Arial", Font.BOLD, 16);
        bigFont = new Font("Arial", Font.BOLD, 20);
        Map<TextAttribute, Object> attributes = (Map<TextAttribute, Object>) bigFont.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        underline = new Font(attributes);

        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/emergencityIcon.png"));
        setIconImage(icon);

        setTitle("Analyse");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel content = new JPanel(null);
        content.setBackground(Color.WHITE);
        if(type == 1 || type == 2) {
            content.setPreferredSize(new Dimension(280, 150));
        }else if(type == 3){
            content.setPreferredSize(new Dimension(280, 420));
        }
        setContentPane(content);

        initUI();
        pack();
        setLocationRelativeTo(null);


    }

    public void initUI() {

        if(type == 1 || type == 3) {
            header1 = new JLabel("Gesetzte Säulen:");
        }else if(type == 2){
            header1 = new JLabel("K.-Leuchttürme:");
        }
        header1.setFont(underline);
        header1.setBounds(20,20,200,30);
        getContentPane().add(header1);

        absolutLabel1 = new JLabel("Absolut:");
        absolutLabel1.setFont(bigFont);
        absolutLabel1.setBounds(20,60,100,30);
        getContentPane().add(absolutLabel1);

        absV1_1 = new JLabel(String.valueOf((int)values[0]));
        absV1_1.setFont(mainFont);
        absV1_1.setBounds(120,60,60,30);
        absV1_1.setHorizontalAlignment(SwingConstants.CENTER);
        absV1_1.setVerticalAlignment(SwingConstants.CENTER);
        absV1_1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        getContentPane().add(absV1_1);

        slash1 = new JLabel("/");
        slash1.setFont(bigFont);
        slash1.setBounds(187,60,30,30);
        getContentPane().add(slash1);

        absV1_2 = new JLabel(String.valueOf(Session.getInstance().getPeople().size()));
        absV1_2.setFont(mainFont);
        absV1_2.setBounds(200,60,60,30);
        absV1_2.setHorizontalAlignment(SwingConstants.CENTER);
        absV1_2.setVerticalAlignment(SwingConstants.CENTER);
        absV1_2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        getContentPane().add(absV1_2);

        relativLabel1 = new JLabel("Relativ:");
        relativLabel1.setFont(bigFont);
        relativLabel1.setBounds(20,100,100,30);
        getContentPane().add(relativLabel1);

        relV1 = new JLabel(String.valueOf((int)((values[0]/Session.getInstance().getPeople().size())*100.0)));
        relV1.setFont(mainFont);
        relV1.setBounds(120,100,60,30);
        relV1.setHorizontalAlignment(SwingConstants.CENTER);
        relV1.setVerticalAlignment(SwingConstants.CENTER);
        relV1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        getContentPane().add(relV1);

        percent1 = new JLabel("%");
        percent1.setFont(bigFont);
        percent1.setBounds(187,100,40,30);
        getContentPane().add(percent1);

        if(type == 3){
            seperationLineOne = new JPanel();
            seperationLineOne.setBackground(Color.BLACK);
            seperationLineOne.setBounds(10,140,260,2);
            getContentPane().add(seperationLineOne);

            header2 = new JLabel("K.-Leuchttürme:");
            header2.setFont(underline);
            header2.setBounds(20,150,200,30);
            getContentPane().add(header2);

            absolutLabel2 = new JLabel("Absolut:");
            absolutLabel2.setFont(bigFont);
            absolutLabel2.setBounds(20,190,100,30);
            getContentPane().add(absolutLabel2);

            absV2_1 = new JLabel(String.valueOf((int)values[1]));
            absV2_1.setFont(mainFont);
            absV2_1.setBounds(120,190,60,30);
            absV2_1.setHorizontalAlignment(SwingConstants.CENTER);
            absV2_1.setVerticalAlignment(SwingConstants.CENTER);
            absV2_1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
            getContentPane().add(absV2_1);

            slash2 = new JLabel("/");
            slash2.setFont(bigFont);
            slash2.setBounds(187,190,30,30);
            getContentPane().add(slash2);

            absV2_2 = new JLabel(String.valueOf(Session.getInstance().getPeople().size()));
            absV2_2.setFont(mainFont);
            absV2_2.setBounds(200,190,60,30);
            absV2_2.setHorizontalAlignment(SwingConstants.CENTER);
            absV2_2.setVerticalAlignment(SwingConstants.CENTER);
            absV2_2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
            getContentPane().add(absV2_2);

            relativLabel2 = new JLabel("Relativ:");
            relativLabel2.setFont(bigFont);
            relativLabel2.setBounds(20,230,100,30);
            getContentPane().add(relativLabel2);

            relV2 = new JLabel(String.valueOf((int)((values[1]/Session.getInstance().getPeople().size())*100.0)));
            relV2.setFont(mainFont);
            relV2.setBounds(120,230,60,30);
            relV2.setHorizontalAlignment(SwingConstants.CENTER);
            relV2.setVerticalAlignment(SwingConstants.CENTER);
            relV2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
            getContentPane().add(relV2);

            percent2 = new JLabel("%");
            percent2.setFont(bigFont);
            percent2.setBounds(187,230,40,30);
            getContentPane().add(percent2);

            seperationLineTwo = new JPanel();
            seperationLineTwo.setBackground(Color.BLACK);
            seperationLineTwo.setBounds(10,280,260,2);
            getContentPane().add(seperationLineTwo);

            header3 = new JLabel("Gesamt:");
            header3.setFont(underline);
            header3.setBounds(20,290,200,30);
            getContentPane().add(header3);

            absolutLabel3 = new JLabel("Absolut:");
            absolutLabel3.setFont(bigFont);
            absolutLabel3.setBounds(20,330,100,30);
            getContentPane().add(absolutLabel3);

            absV3_1 = new JLabel(String.valueOf((int)values[2]));
            absV3_1.setFont(mainFont);
            absV3_1.setBounds(120,330,60,30);
            absV3_1.setHorizontalAlignment(SwingConstants.CENTER);
            absV3_1.setVerticalAlignment(SwingConstants.CENTER);
            absV3_1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
            getContentPane().add(absV3_1);

            slash3 = new JLabel("/");
            slash3.setFont(bigFont);
            slash3.setBounds(187,330,30,30);
            getContentPane().add(slash3);

            absV3_2 = new JLabel(String.valueOf(Session.getInstance().getPeople().size()));
            absV3_2.setFont(mainFont);
            absV3_2.setBounds(200,330,60,30);
            absV3_2.setHorizontalAlignment(SwingConstants.CENTER);
            absV3_2.setVerticalAlignment(SwingConstants.CENTER);
            absV3_2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
            getContentPane().add(absV3_2);

            relativLabel3 = new JLabel("Relativ:");
            relativLabel3.setFont(bigFont);
            relativLabel3.setBounds(20,370,100,30);
            getContentPane().add(relativLabel3);

            relV3 = new JLabel(String.valueOf((int)((values[2]/Session.getInstance().getPeople().size())*100.0)));
            relV3.setFont(mainFont);
            relV3.setBounds(120,370,60,30);
            relV3.setHorizontalAlignment(SwingConstants.CENTER);
            relV3.setVerticalAlignment(SwingConstants.CENTER);
            relV3.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
            getContentPane().add(relV3);

            percent3 = new JLabel("%");
            percent3.setFont(bigFont);
            percent3.setBounds(187,370,40,30);
            getContentPane().add(percent2);
        }
    }



    //--------------------------------------DEBUGGING--------------------------
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Session.getInstance().getPeople().add(new Person(1, 1, new BitSet()));
            Session.getInstance().getPeople().add(new Person(2, 2, new BitSet()));
            double[] t = new double[3];
            t[0] = 1;
            t[1] = 1;
            t[2] = 2;
            UFAnalysisSetCoverageWindow viewer = new UFAnalysisSetCoverageWindow(t, 3);
            viewer.setVisible(true);
        });
    }
}
