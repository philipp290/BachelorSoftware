package UFController.Algorithm;

import Model.Algorithms.Algorithm;
import Model.Algorithms.LinearOptimizationAlgorithm;
import Model.Algorithms.LogikAlgorithm;
import Model.Components.Person;
import Model.Components.Pillar;
import Model.Services.SolutionValidationService;
import Model.Services.TxtWriterService;
import Model.Session;
import UFView.Algorithm.*;
import UFView.Start.UFErrorWindow;
import UFView.Start.UFSpecificationWindow;

import javax.swing.*;
import java.util.ArrayList;

public class UFAlgorithmVisualisationController implements UFAlgorithmInput{
    UFAlgorithmVisualisationWindow view;
    public UFAlgorithmVisualisationController(UFAlgorithmVisualisationWindow v){
        this.view = v;
        wire();
    }
    public void wire(){
        view.addExecuteFunction((ActionEvent) -> {
            if(view.getPerc()||view.getPeop()||view.getShad()||view.getPil()) {
                if(!view.getAbsField().isEmpty()&&!view.getRelField().isEmpty()) {
                    if (view.getLogikSelect()) {
                        LogikAlgorithm a = new LogikAlgorithm();
                        executeAlgorithm(a);
                    }
                    if (view.getLatticeSelect()) {
                        if (view.getAbsSelect()) {
                            SwingUtilities.invokeLater(() -> {
                                UFTabuSearchWindow viewer = new UFTabuSearchWindow(this);
                                viewer.setVisible(true);
                            });
                        } else {
                            SwingUtilities.invokeLater(() -> {
                                UFLatticeSearchSelectionWindow viewer = new UFLatticeSearchSelectionWindow(this);
                                viewer.setVisible(true);
                            });
                        }
                    }
                }else{
                    SwingUtilities.invokeLater(() -> {
                        UFErrorWindow viewer = new UFErrorWindow("Von / Bis nicht fertig", "sepzifiziert!");
                        viewer.setVisible(true);
                    });
                }
            }else{
                SwingUtilities.invokeLater(() -> {
                    UFErrorWindow viewer = new UFErrorWindow("Kreuz ein Visualisier-", "ungs Attribut an!");
                    viewer.setVisible(true);
                });
            }
        });
    }
    public void executeAlgorithm (Algorithm algo){
        SolutionValidationService svs = new SolutionValidationService();

        ArrayList<Pillar> pillars = Session.getInstance().getPillars();
        ArrayList<Person> people = Session.getInstance().getPeople();
        boolean abs_rel = view.getAbsSelect();

        ArrayList<Double> perc = new ArrayList<>();
        ArrayList<Double> shad = new ArrayList<>();
        ArrayList<Double> peop = new ArrayList<>();
        ArrayList<Double> pil = new ArrayList<>();

        for(int i = Integer.parseInt(view.getAbsField()); i <= Integer.parseInt(view.getRelField()); i++) {
            ArrayList<Pillar> resultPillars = algo.execute(pillars, people, abs_rel, i);

            ArrayList<Person> temp = Session.getInstance().getPeople();
            int peopleAmount = temp.size();
            int coverage = svs.coverageValidation(resultPillars);
            int score = svs.scoreValidation(resultPillars);

            if(view.getPerc()){
                perc.add((double)Math.round((double) coverage /peopleAmount * 100.0));
            }
            if(view.getPeop()){
                peop.add((double)coverage);
            }
            if(view.getPil()){
                pil.add((double)resultPillars.size());
            }
            if(view.getShad()){
                shad.add(Math.round((double) score /resultPillars.size() * 100.0) / 100.0);
            }
            System.out.println(i+". Rechnung fertig");
        }
        TxtWriterService tws = new TxtWriterService();
        ArrayList<Double>[] exportContainer = new ArrayList[4];
        if(!perc.isEmpty()){
            exportContainer[0]=perc;
        }
        if(!peop.isEmpty()){
            exportContainer[1]=peop;
        }
        if(!pil.isEmpty()){
            exportContainer[2]=pil;
        }
        if(!shad.isEmpty()){
            exportContainer[3]=shad;
        }
        tws.writeRExport(exportContainer,Integer.parseInt(view.getAbsField()),Integer.parseInt(view.getRelField()));
        SwingUtilities.invokeLater(() -> {
            UFAlgorithmExportWindow viewer = new UFAlgorithmExportWindow("Data/Cache/RExportCache/rExport.txt");
            viewer.setVisible(true);
        });
    }
}
