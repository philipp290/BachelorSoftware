package UFView.Start;

import Model.Components.Person;
import Model.Services.CsvReaderService;
import Model.Session;
import UFView.UFMainWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class UFLoadingScreen extends JFrame {
    private static long startTime;
    private static final Image emergencityIcon = Toolkit.getDefaultToolkit().getImage(UFLoadingScreen.class.getResource("/emergencityIcon.png"));
    private static final Image emergencityRoundIcon = Toolkit.getDefaultToolkit().getImage(UFLoadingScreen.class.getResource("/emergencityIconRound.png"));

    public static void main(String[] args) {
        SwingUtilities.invokeLater(UFLoadingScreen::showLoadingScreen);
    }

    public static void showLoadingScreen() {
        JFrame frame = new JFrame("Initialisierung");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(300, 120);
        frame.getContentPane().setBackground(new Color(42,133,78));
        frame.setLocationRelativeTo(null);
        frame.setIconImage(emergencityIcon);


        Image scaledImage = emergencityRoundIcon.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(scaledImage);

        JLabel label = new JLabel("0:00", icon, JLabel.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 32));
        label.setForeground(Color.WHITE);
        label.setIconTextGap(15);
        frame.add(label);

        frame.setVisible(true);

        startTime = System.currentTimeMillis();
        Timer timer = new Timer(1000, e -> {
            long elapsed = System.currentTimeMillis() - startTime;

            long minutes = TimeUnit.MILLISECONDS.toMinutes(elapsed);
            long seconds = TimeUnit.MILLISECONDS.toSeconds(elapsed) % 60;

            label.setText(String.format("%d:%02d", minutes, seconds));
        });
        timer.start();

        SwingWorker<Void, Void> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() {

                longRunningMethod();
                return null;
            }

            @Override
            protected void done() {
                timer.stop();
                frame.dispose();
                SwingUtilities.invokeLater(() -> {
                    UFMainWindow viewer = new UFMainWindow();
                    viewer.setVisible(true);
                });
            }
        };

        worker.execute();


    }

    private static void longRunningMethod() {
        CsvReaderService crs = new CsvReaderService();
        ArrayList<Person> per = crs.readPerson(Session.getInstance().getOriginalPeopleFile(), Session.getInstance().getPillars(), Session.getInstance().getReachingDistance());
        Session.getInstance().setPeople(per);
    }
}

