package UFController.Algorithm;

import DView.Algorithm.AlgorithmExportWindow;
import DView.Algorithm.AlgorithmResultWindow;
import Model.Algorithms.Algorithm;
import Model.Algorithms.LogikAlgorithm;
import Model.Components.Person;
import Model.Components.Pillar;
import Model.Session;
import UFView.Algorithm.UFAlgorithmInputWindow;
import UFView.Algorithm.UFAlgorithmResultWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class UFAlgorithmInputController {
    UFAlgorithmInputWindow view;

    public UFAlgorithmInputController(UFAlgorithmInputWindow v){
        this.view = v;
        wire();
    }
    public void wire(){
        view.addExecuteFunction((ActionEvent) -> {
            if (view.getLogikSelect()) {
                LogikAlgorithm a = new LogikAlgorithm();
                executeAlgorithm(a);
            }
        });
    }
    public void executeAlgorithm (Algorithm algo){
        ArrayList<Pillar> pillars = Session.getInstance().getPillars();
        ArrayList<Person> people = Session.getInstance().getPeople();

        boolean abs_rel = true;
        int goal = 0;
        if (view.getRelSelect()) {
            abs_rel = false;
            goal = Integer.parseInt(view.getRelField());
        } else if (view.getAbsSelect()) {
            goal = Integer.parseInt(view.getAbsField());
        }

        ArrayList<Pillar> result = algo.execute(pillars, people, abs_rel, goal);

        if(algo.getClass().toString().equals("class Model.Algorithms.LinearOptimizationAlgorithm")){
            SwingUtilities.invokeLater(() -> {
                AlgorithmExportWindow viewer = new AlgorithmExportWindow("Data/Cache/LinearOptimizationCache/loExport.txt");
                viewer.setVisible(true);
            });
        }else {
            SwingUtilities.invokeLater(() -> {
                UFAlgorithmResultWindow viewer = new UFAlgorithmResultWindow(result);
                viewer.setVisible(true);
            });
        }
    }
}
