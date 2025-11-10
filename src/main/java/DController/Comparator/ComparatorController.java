package DController.Comparator;

import Model.Components.Person;
import Model.Components.Pillar;
import Model.Services.CsvReaderService;
import Model.Services.SolutionValidationService;
import DView.Comparator.ComparatorResultWindow;
import DView.Comparator.ComparatorWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;

public class ComparatorController {
    private final ComparatorWindow view;
    private final CsvReaderService crs;
    private final SolutionValidationService svs;

    public ComparatorController(ComparatorWindow v){
        this.view = v;
        this.crs = new CsvReaderService();
        this.svs = new SolutionValidationService();

        wire();
    }
    public void wire(){
        view.addBrowserOneFunction((ActionEvent e) -> {
            File startDir = new File("Data");
            JFileChooser fileChooser = new JFileChooser(startDir);
            int result = fileChooser.showOpenDialog(view);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                view.setSolutionOne(selectedFile.getAbsolutePath());
            }
        });
        view.addBrowserTwoFunction((ActionEvent e) -> {
            File startDir = new File("Data");
            JFileChooser fileChooser = new JFileChooser(startDir);
            int result = fileChooser.showOpenDialog(view);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                view.setSolutionTwo(selectedFile.getAbsolutePath());
            }
        });
        view.addBrowserThreeFunction((ActionEvent e) -> {
            File startDir = new File("Data");
            JFileChooser fileChooser = new JFileChooser(startDir);
            int result = fileChooser.showOpenDialog(view);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                view.setPeople(selectedFile.getAbsolutePath());
            }
        });
        view.addStartFunction((ActionEvent e) -> {
            ArrayList<Pillar> solutionOne = crs.readPillarsFromFile(view.getSolutionOne());
            ArrayList<Person> temp = crs.readPerson(view.getPeople(),solutionOne,Integer.parseInt(view.getDistance()));
            int peopleAmount = temp.size();

            ArrayList<Pillar> solutionTwo = crs.readPillarsFromFile(view.getSolutionTwo());
            crs.readPerson(view.getPeople(),solutionTwo,Integer.parseInt(view.getDistance()));
            int coverage1 = svs.coverageValidation(solutionOne);
            int score1 = svs.scoreValidation(solutionOne);
            int coverage2 = svs.coverageValidation(solutionTwo);
            int score2 = svs.scoreValidation(solutionTwo);

            double[] result = new double[8];
            result[0] = Math.round((double) coverage1 /peopleAmount * 100.0);
            result[1] = coverage1;
            result[2] = solutionOne.size();
            result[3] = Math.round((double) score1 /solutionOne.size() * 100.0) / 100.0;
            result[4] = Math.round((double) coverage2 /peopleAmount * 100.0) ;
            result[5] = coverage2;
            result[6] = solutionTwo.size();
            result[7] = Math.round((double) score2 /solutionTwo.size() * 100.0) / 100.0;

            SwingUtilities.invokeLater(() -> {
                ComparatorResultWindow viewer = new ComparatorResultWindow(result);
                viewer.setVisible(true);
            });
        });
    }
}
