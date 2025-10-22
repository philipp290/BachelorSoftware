package Model.Services;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class CsvUpdateService {

    /**
     * Methode um 2 Spalten in einer CSV zu tauschen
     * @param inputFile input File
     * @param outputFile output File
     * @param colIndex1 zu tauschender Index 1
     * @param colIndex2 zu tauschender Index 2
     */
    public void switchColumns(String inputFile, String outputFile, int colIndex1, int colIndex2){
        int biggerCol = Math.max(colIndex1,colIndex2);
        int smallerCol= Math.min(colIndex1,colIndex2);
        try (
                BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))
        ) {
            String line = reader.readLine();
            String[] tokens = line.split(",");
            ArrayList<Integer> newColIndexes = new ArrayList<>();
            for (int i = 0; i < tokens.length; i++) {
                if (i == smallerCol) {
                    newColIndexes.add(biggerCol);
                } else if (i == biggerCol) {
                    newColIndexes.add(smallerCol);
                } else {
                    newColIndexes.add(i);
                }
            }
            while (line != null) {
                tokens = line.split(",");
                String firstLineContent="";
                for(Integer i : newColIndexes){
                    firstLineContent=firstLineContent + tokens[i]+",";
                }
                String content = firstLineContent.substring(0,firstLineContent.length()-1);
                writer.write(content);
                writer.newLine();
                line = reader.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Methode um eine Spalte in einer CSV zu löschen
     * @param inputFile inputFile
     * @param outputFile outputFile
     * @param colIndex Spalten-Index
     */
    public void deleteColumn(String inputFile, String outputFile, int colIndex){
        try (
                BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))
        ) {
            String line = reader.readLine();
            String[] preTokens = line.split(",");
            ArrayList<Integer> colIndexesToKeep = new ArrayList<>();
            for(int i = 0; i < preTokens.length; i++){
                if(i != colIndex) {
                    colIndexesToKeep.add(i);
                }
            }
            while (line != null) {
                String[] tokens = line.split(",");
                String LineContent="";
                for(Integer i : colIndexesToKeep){
                    LineContent=LineContent + tokens[i]+",";
                }
                String content = LineContent.substring(0,LineContent.length()-1);
                writer.write(content);
                writer.newLine();
                line = reader.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Methode um CSV zu samplen auf jede n'te Zeile
     * @param inputFile zu sampelnde CSV
     * @param outputFile gesampelte CSV
     * @param sampleRate n
     */
    public void sampleCSV(String inputFile, String outputFile, int sampleRate){
        int counter = sampleRate;
        try (
                BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))
        ) {
            reader.readLine();
            String line = "";
            while ((line = reader.readLine()) != null) {
                if (counter == sampleRate) {
                    writer.write(line);
                    writer.newLine();
                    counter = 1;
                } else {
                    counter++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Methode die CSV vom InputFile zu dem Output File kopiert
     * @param inputFile inputFile
     * @param outputFile outputFile
     */
    public void copyCSV (String inputFile, String outputFile){
        try (
                BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))
        ) {
            String line ="";
            while ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.newLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
