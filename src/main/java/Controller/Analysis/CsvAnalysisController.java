package Controller.Analysis;

import Model.Components.Person;
import Model.Components.Pillar;
import Model.Services.CsvAnalysisService;
import Model.Services.CsvReaderService;
import View.Analysis.CsvAnalysisEncounterLengthWindow;
import View.Analysis.CsvAnalysisReachabilityWindow;
import View.Analysis.CsvAnalysisWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;

public class CsvAnalysisController {
    private final CsvAnalysisWindow view;
    private final CsvAnalysisService cas = new CsvAnalysisService();
    private final CsvReaderService crs = new CsvReaderService();

    public CsvAnalysisController(CsvAnalysisWindow view) {
        this.view = view;
        wire();
    }

    private void wire() {
        view.addBrowseOneListener((ActionEvent e) -> {
            File startDir = new File("data");
            JFileChooser fileChooser = new JFileChooser(startDir);
            int result = fileChooser.showOpenDialog(view);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                view.setPathFieldOne(selectedFile.getAbsolutePath());
            }

        });
        view.addBrowseTwoListener((ActionEvent e) -> {
            File startDir = new File("data");
            JFileChooser fileChooser = new JFileChooser(startDir);
            int result = fileChooser.showOpenDialog(view);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                view.setPathFieldTwo(selectedFile.getAbsolutePath());
            }
        });
        view.addReachabilityListener((ActionEvent e) -> {
            if(!view.getDistance().isEmpty() && !view.getPathFieldOne().isEmpty() && !view.getPathFieldTwo().isEmpty()) {
                ArrayList<Pillar> pillars = crs.readPillarsFromFile(view.getPathFieldTwo());
                ArrayList<Person> people = crs.readPerson(view.getPathFieldOne(),pillars,Integer.parseInt(view.getDistance()));
                double[] reachability = cas.reachabilityAnalysis(people);
                SwingUtilities.invokeLater(() -> {
                    CsvAnalysisReachabilityWindow viewer = new CsvAnalysisReachabilityWindow(reachability);
                    viewer.setVisible(true);
                });
            }else{
                view.showError("Es wurden noch nicht alle notwendigen Felder ausgefüllt");
            }
        });
        view.addEncounterLengthListener((ActionEvent e) -> {
            if(!view.getDistance().isEmpty() && !view.getPathFieldOne().isEmpty() && !view.getPathFieldTwo().isEmpty()) {
                ArrayList<Pillar> pillars = crs.readPillarsFromFile(view.getPathFieldTwo());
                CsvAnalysisService cas = new CsvAnalysisService();
                double[] encounterdata = cas.encounterLengthAnalysis(view.getPathFieldOne(),pillars, Integer.parseInt(view.getDistance()));
                SwingUtilities.invokeLater(() -> {
                    CsvAnalysisEncounterLengthWindow viewer = new CsvAnalysisEncounterLengthWindow(encounterdata);
                    viewer.setVisible(true);
                });
            }else{
                view.showError("Es wurden noch nicht alle notwendigen Felder ausgefüllt");
            }
        });

    }
}
