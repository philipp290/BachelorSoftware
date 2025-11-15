package UFController.Editor;

import Model.Services.CsvUpdateService;
import UFView.Editor.UFCsvSchemaEditorWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class UFCsvSchemaEditorController {
    private final UFCsvSchemaEditorWindow view;
    private CsvUpdateService cus = new CsvUpdateService();
    private int colCount = 0;
    private String displayedFile = "";
    private String cacheFile1 = "Data/Cache/EditorCache/temporary1.csv";
    private String cacheFile2 = "Data/Cache/EditorCache/temporary2.csv";

    public UFCsvSchemaEditorController(UFCsvSchemaEditorWindow view) {
        this.view = view;
        wire();
    }

    private void wire() {
        view.addBrowseListener((ActionEvent e) -> {
            File startDir = new File("Data");
            JFileChooser fileChooser = new JFileChooser(startDir);
            int result = fileChooser.showOpenDialog(view);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                view.setPathText(selectedFile.getAbsolutePath());
            }
        });

        view.addLoadListener((ActionEvent e) -> {
            String filePath = view.getPathText();
            if (!filePath.isEmpty()) {
                loadCSVToTable(filePath);
                displayedFile=filePath;
            } else {
                view.showError("Es wurde noch", "keine Datei ausgewählt");
            }
        });

        view.addDeleteListener((ActionEvent e) -> {
            if(!displayedFile.isEmpty()) {
                if (!view.getDeleteCol().isEmpty() && (Integer.parseInt(view.getDeleteCol()) > 0) && (Integer.parseInt(view.getDeleteCol()) < colCount)) {
                    String cacheFile = "";
                    if (!displayedFile.equals(cacheFile1)) {
                        cacheFile = cacheFile1;
                    } else {
                        cacheFile = cacheFile2;
                    }
                    cus.deleteColumn(displayedFile, cacheFile, Integer.parseInt(view.getDeleteCol()));
                    loadCSVToTable(cacheFile);
                    view.clearDeleteCol();
                    displayedFile = cacheFile;
                } else {
                    view.showError("Es wurde invalide","Lösch-Spalte gewählt");
                }
            }else{
                view.showError("Es wurde noch", "keine CSV geladen");
            }
        });

        view.addSwitchListener((ActionEvent e) -> {
            if(!displayedFile.isEmpty()){
                if (!(view.getSwapCol1().equals(view.getSwapCol2())) && !view.getSwapCol1().isEmpty() && (Integer.parseInt(view.getSwapCol1()) >= 0) && (Integer.parseInt(view.getSwapCol1()) < colCount) && !view.getSwapCol2().isEmpty() && (Integer.parseInt(view.getSwapCol2()) >= 0) && (Integer.parseInt(view.getSwapCol2()) < colCount)) {
                    String cacheFile = "";
                    if(!displayedFile.equals(cacheFile1)){
                        cacheFile=cacheFile1;
                    }else{
                        cacheFile=cacheFile2;
                    }
                    cus.switchColumns(displayedFile,cacheFile,Integer.parseInt(view.getSwapCol1()),Integer.parseInt(view.getSwapCol2()));
                    loadCSVToTable(cacheFile);
                    view.clearSwapCols();
                    displayedFile=cacheFile;
                } else {
                    view.showError("Es wurde invalide","Tausch-Spalte gewählt");
                }
            }else{
                view.showError("Es wurde noch", "keine CSV geladen");
            }
        });

        view.addSaveBrowseListener((ActionEvent e) -> {
            File startDir = new File("data");
            JFileChooser fileChooser = new JFileChooser(startDir);
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int result = fileChooser.showOpenDialog(view);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                view.setSaveFile(selectedFile.getAbsolutePath());
            }
        });

        view.addSaveListener((ActionEvent e) -> {
            if(!view.getSaveFile().isEmpty()) {
                CsvUpdateService cus = new CsvUpdateService();
                cus.copyCSV(displayedFile, view.getSaveFile() + "/editedCsvFile.csv");
                view.setSaveFile("");
            }else{
                view.showError("Es wurde noch keine", "Datei ausgewählt");
            }
        });
    }

    private void loadCSVToTable(String filePath) {
        view.getTableModel().setRowCount(0);
        view.getTableModel().setColumnCount(0);

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int rowCount = 0;
            while ((line = reader.readLine()) != null && rowCount < 100) {
                String[] values = line.split(",");
                if (rowCount == 0) {
                    for (String col : values) {
                        view.getTableModel().addColumn(col.trim());
                    }
                } else {
                    view.getTableModel().addRow(values);
                }
                rowCount++;
                this.colCount= line.split(",").length;
            }
        } catch (IOException ex) {
            view.showError("Fehler beim Lesen","der Datei");
        }
    }
}
