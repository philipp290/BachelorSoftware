package UFView.Algorithm;

import DView.Algorithm.AlgorithmExportWindow;
import Model.Services.CsvUpdateService;
import UFView.Start.UFErrorWindow;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class UFAlgorithmExportWindow extends JFrame {
    private String filePath ="";
    private JTextArea textArea = new JTextArea();
    private  JTextField filepathField;
    private JButton browsingButton;
    private  JButton savingButton;

    ImageIcon startIcon;
    ImageIcon searchIcon;

    private final Color defaultColor;
    private final Color hoverColor;
    private final Color clickColor;
    Font mainFont;

    public UFAlgorithmExportWindow(String filePath) {
        this.filePath = filePath;

        defaultColor = new Color(220, 220, 220);
        hoverColor = new Color(170, 170, 170);
        clickColor = new Color(130, 130, 130);

        mainFont = new Font("Arial", Font.BOLD, 16);

        startIcon = new ImageIcon(getClass().getResource("/startIcon.png"));
        Image scaledImage = startIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        startIcon = new ImageIcon(scaledImage);

        searchIcon = new ImageIcon(getClass().getResource("/searchIcon.png"));
        scaledImage = searchIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        searchIcon = new ImageIcon(scaledImage);


        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/emergencityIcon.png"));
        setIconImage(icon);
        setTitle("Ergebnis");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel content = new JPanel(null);
        content.setBackground(Color.WHITE);
        content.setPreferredSize(new Dimension(520, 300));
        setContentPane(content);

        initScrollPane();
        initUI();
        pack();
        setLocationRelativeTo(null);
    }
    private void initScrollPane(){

        textArea.setEditable(false);
        textArea.setFont(new Font("Monospaced", Font.BOLD, 14));

        JScrollPane scrollPane = new JScrollPane(
                textArea,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS
        );

        try (BufferedReader reader = new BufferedReader(new FileReader(this.filePath))) {
            textArea.read(reader, null);
        } catch (IOException e) {
            textArea.setText("Fehler beim Laden der Datei");
        }
        scrollPane.setBounds(20,20,480,210);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        scrollPane.setViewportBorder(BorderFactory.createMatteBorder(
                0, 0, 2, 2, Color.BLACK  // top, left, bottom, right
        ));
        getContentPane().add(scrollPane);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void initUI(){
        filepathField = new JTextField();
        filepathField.setBounds(150,250,220,30);
        Border lineBorder = BorderFactory.createLineBorder(Color.BLACK, 2);
        Border emptyBorder = new EmptyBorder(5, 10, 5, 10);
        filepathField.setBorder(BorderFactory.createCompoundBorder(lineBorder, emptyBorder));
        filepathField.setFont(mainFont);
        getContentPane().add(filepathField);

        browsingButton = new JButton("Suchen",searchIcon);
        browsingButton.setBounds(20,250,120,30);
        formatButton(browsingButton);
        getContentPane().add(browsingButton);

        savingButton = new JButton("Speichern",startIcon);
        savingButton.setBounds(380,250,120,30);
        formatButton(savingButton);
        getContentPane().add(savingButton);

        browsingButton.addActionListener((ActionEvent e) -> {
            String oldLookAndFeel = UIManager.getLookAndFeel().getClass().getName();
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            File startDir = new File("data");
            JFileChooser fileChooser = new JFileChooser(startDir);
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            fileChooser.setDialogTitle("Wähle den Ziel Ordner aus");
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                filepathField.setText(selectedFile.getAbsolutePath());
            }
            try {
                UIManager.setLookAndFeel(oldLookAndFeel);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        savingButton.addActionListener((ActionEvent e) -> {
            if(!filepathField.getText().isEmpty()) {
                String outputFile = filepathField.getText() + "/AlgorithmCalculationExport.txt";
                CsvUpdateService cus = new CsvUpdateService();
                cus.copyCSV(filePath,outputFile);
                filepathField.setText("");
            }else{
                SwingUtilities.invokeLater(() -> {
                    UFErrorWindow viewer = new UFErrorWindow("Wähle einen Ziel-", "Ordner!");
                    viewer.setVisible(true);
                });
            }
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
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UFAlgorithmExportWindow viewer = new UFAlgorithmExportWindow("Data/Cache/LinearOptimizationCache/loExport.txt");
            viewer.setVisible(true);
        });
    }
}
