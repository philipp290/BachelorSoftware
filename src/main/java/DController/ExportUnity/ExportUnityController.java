package DController.ExportUnity;

import Model.Components.Pillar;
import Model.Services.CsvReaderService;
import Model.Services.CsvUpdateService;
import DView.ExportUnity.ExportUnityWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;

public class ExportUnityController {
    private ExportUnityWindow view;
    private CsvUpdateService cus;
    private CsvReaderService crs;

    public ExportUnityController(ExportUnityWindow v){
        cus = new CsvUpdateService();
        crs = new CsvReaderService();

        this.view = v;
        wire();
    }
    private void wire(){
        view.addBrowserOne((ActionEvent e) -> {
            File startDir = new File("Data");
            JFileChooser fileChooser = new JFileChooser(startDir);
            int result = fileChooser.showOpenDialog(view);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                view.setSolution(selectedFile.getAbsolutePath());
            }
        });
        view.addBrowserTwo((ActionEvent e) -> {
            File startDir = new File("Data");
            JFileChooser fileChooser = new JFileChooser(startDir);
            int result = fileChooser.showOpenDialog(view);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                view.setPeople(selectedFile.getAbsolutePath());
            }
        });
        view.addStartFunction((ActionEvent e)->{
            if(view.getPeople().isEmpty()||view.getSolution().isEmpty()){
                view.showError("Kein File angegeben");
            }else {
                File startDir = new File("data");
                JFileChooser fileChooser = new JFileChooser(startDir);
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int result = fileChooser.showOpenDialog(view);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    String saveFile = selectedFile.getAbsolutePath() + "/unityExportTest2.csv";
                    ArrayList<Pillar> pillars = crs.readPillarsFromFile(view.getSolution());
                    //Die 20 hier sollte eig auch variabel sein (evtl TODO)
                    cus.solutionUnityExport(view.getPeople(), saveFile, pillars, 20);
                    view.dispose();
                }
            }
        });
    }
}
