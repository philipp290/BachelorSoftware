package View;

import javax.swing.*;
import java.awt.*;

public class ExportUnityWindow extends JFrame{
        private final JLabel solutionLabelOne = new JLabel("Lösungs-Säulen CSV");
        private final JButton browseButtonOne = new JButton("Suchen");
        private final JTextField solutionTextFieldOne = new JTextField();

        private final JLabel personLabel = new JLabel("Personen CSV");
        private final JButton browseButtonTwo = new JButton("Suchen");
        private final JTextField personTextField = new JTextField();

        private final JPanel seperationLineOne = new JPanel();

        private final JButton startButton = new JButton("Exportieren");


        public ExportUnityWindow() {
            setTitle("Unity-Export");
            setSize(400, 310);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setLocationRelativeTo(null);

            initUI();
        }

        public void initUI() {
            getContentPane().setLayout(null);

            solutionLabelOne.setBounds(20, 20, 200, 20);
            getContentPane().add(solutionLabelOne);
            browseButtonOne.setBounds(20, 40, 120, 40);
            getContentPane().add(browseButtonOne);
            solutionTextFieldOne.setBounds(160, 40, 200, 40);
            solutionTextFieldOne.setEditable(true);
            getContentPane().add(solutionTextFieldOne);

            personLabel.setBounds(20, 100, 200, 20);
            getContentPane().add(personLabel);
            browseButtonTwo.setBounds(20, 120, 120, 40);
            getContentPane().add(browseButtonTwo);
            personTextField.setBounds(160, 120, 200, 40);
            personTextField.setEditable(true);
            getContentPane().add(personTextField);

            seperationLineOne.setBounds(20, 180, 340, 1);
            seperationLineOne.setBackground(Color.BLACK);
            getContentPane().add(seperationLineOne);


            startButton.setBounds(60, 200, 260, 50);
            getContentPane().add(startButton);
        }

        //--------------------------------------------------------DEBUGGING---------------
        public static void main(String[] args) {
            SwingUtilities.invokeLater(() -> {
                ExportUnityWindow viewer = new ExportUnityWindow();
                viewer.setVisible(true);
            });
        }

}
