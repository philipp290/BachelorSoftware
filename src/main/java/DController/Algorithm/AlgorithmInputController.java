package DController.Algorithm;

import Model.Algorithms.Algorithm;
import Model.Algorithms.LinearOptimizationAlgorithm;
import Model.Algorithms.LogikAlgorithm;
import Model.Components.Person;
import Model.Components.Pillar;
import Model.Services.CsvReaderService;
import DView.Algorithm.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;

public class AlgorithmInputController {
    private final AlgorithmInputWindow view;
    private final CsvReaderService crs;

    public AlgorithmInputController(AlgorithmInputWindow view) {
        this.view = view;
        this.crs = new CsvReaderService();
        wire();
    }

    private void wire(){
        view.addBrowseOneListener((ActionEvent e) -> {
            File startDir = new File("Data");
            JFileChooser fileChooser = new JFileChooser(startDir);
            int result = fileChooser.showOpenDialog(view);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                view.setPathFieldOne(selectedFile.getAbsolutePath());
            }
        });

        view.addBrowseTwoListener((ActionEvent e) -> {
            File startDir = new File("Data");
            JFileChooser fileChooser = new JFileChooser(startDir);
            int result = fileChooser.showOpenDialog(view);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                view.setPathFieldTwo(selectedFile.getAbsolutePath());
            }
        });

        view.addStartListener((ActionEvent e) -> {
            if(view.getPathFieldOne().isEmpty()){view.showError("Personen nicht spezifiziert");}
            else if(view.getPathFieldTwo().isEmpty()){view.showError("Säulen nicht spezifiziert");}
            else if(view.getDistance().isEmpty()){view.showError("Distanz nicht spezifiziert");}
            else if(!view.getCRelativ()&&!view.getCAbsolut()){view.showError("Absolut/Relativ nicht spezifiziert");}
            else if(view.getCAbsolut()&&view.getPillarCount().isEmpty()){view.showError("Säulenanzahl nicht spezifiziert");}
            else if(view.getCRelativ()&&view.getCoverage().isEmpty()){view.showError("Abdeckungs-Quote nicht spezifiziert");}
            else if(!view.getCLogik()&&!view.getCLatticeSearch()&&!view.getCLineareOptimierung()){view.showError("Algorithmus nicht spezifiziert");}
            else {

                if (view.getCLogik()) {
                    LogikAlgorithm a = new LogikAlgorithm();
                    executeAlgorithm(a);
                }
                if(view.getCLatticeSearch()){
                    if(view.getCAbsolut()){
                        SwingUtilities.invokeLater(() -> {
                            AlgorithmTabuSearchWindow viewer = new AlgorithmTabuSearchWindow(this);
                            viewer.setVisible(true);
                        });
                    }else{
                        SwingUtilities.invokeLater(() -> {
                            AlgorithmLatticeSearchSelectionWindow viewer = new AlgorithmLatticeSearchSelectionWindow(this);
                            viewer.setVisible(true);
                        });
                    }
                }
                if(view.getCLineareOptimierung()){
                    LinearOptimizationAlgorithm a = new LinearOptimizationAlgorithm();
                    executeAlgorithm(a);
                }
            }
        });

    }
    public void executeAlgorithm (Algorithm algo){
        ArrayList<Pillar> pillars = crs.readPillarsFromFile(view.getPathFieldTwo());
        ArrayList<Person> people = crs.readPerson(view.getPathFieldOne(), pillars, Integer.parseInt(view.getDistance()));

        boolean abs_rel = true;
        int goal = 0;
        if (view.getCRelativ()) {
            abs_rel = false;
            goal = Integer.parseInt(view.getCoverage());
        } else if (view.getCAbsolut()) {
            goal = Integer.parseInt(view.getPillarCount());
        }

        ArrayList<Pillar> result = algo.execute(pillars, people, abs_rel, goal);

        String test = algo.getClass().toString();

        if(algo.getClass().toString().equals("class Model.Algorithms.LinearOptimizationAlgorithm")){
            SwingUtilities.invokeLater(() -> {
                AlgorithmExportWindow viewer = new AlgorithmExportWindow("Data/Cache/LinearOptimizationCache/loExport.txt");
                viewer.setVisible(true);
            });
        }else {
            SwingUtilities.invokeLater(() -> {
                AlgorithmResultWindow viewer = new AlgorithmResultWindow(result);
                viewer.setVisible(true);
            });
        }
    }
}
