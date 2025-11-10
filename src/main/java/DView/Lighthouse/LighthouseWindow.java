package DView.Lighthouse;

import javax.swing.*;
import java.awt.*;

public class LighthouseWindow extends JFrame {
    private final JLabel lighthouseLabel = new JLabel("Katastrophenschutz-Leuchtturm CSV");
    private final JButton browseButton = new JButton("Suchen");
    private final JTextField lighthouseTextField = new JTextField();
    private final JButton startButton = new JButton("Speichern");

    public LighthouseWindow(){
        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/emergencityIcon.png"));
        setIconImage(icon);

        setTitle("Disaster Protection Lighthouse");
        setSize(590, 140);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        initUI();
    }
    public void initUI(){
        getContentPane().setLayout(null);

        lighthouseLabel.setBounds(20, 10, 250, 20);
        lighthouseLabel.setFont(new Font("Dialog",Font.PLAIN,15));
        getContentPane().add(lighthouseLabel);

        browseButton.setBounds(20, 40, 120, 40);
        getContentPane().add(browseButton);

        lighthouseTextField.setBounds(160, 40, 250, 40);
        lighthouseTextField.setEditable(true);
        getContentPane().add(lighthouseTextField);

        startButton.setBounds(430, 40, 120, 40);
        getContentPane().add(startButton);
    }

    //--------------------------------------------------------DEBUGGING---------------
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LighthouseWindow viewer = new LighthouseWindow();
            viewer.setVisible(true);
        });
    }
}
