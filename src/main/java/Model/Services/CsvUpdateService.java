package Model.Services;

import Model.Components.Person;
import Model.Components.Pillar;

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
     * Method that delets every row which contains the keyword in the specified
     * Column
     * @param inputFile inputFile
     * @param outputFile outputFile
     * @param colIndex Index to watch
     * @param keyWord keyWord to Delete
     */
    public void deleteRows(String inputFile, String outputFile, int colIndex, String keyWord){
        try (
                BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))
        ) {
            String line = reader.readLine();
            writer.write(line);
            writer.newLine();
            while ((line = reader.readLine())!= null) {
                String[] tokens = line.split(",");
                if(!tokens[colIndex].equals(keyWord)) {
                    writer.write(line);
                    writer.newLine();
                }
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

    /**
     * Methode die Mapping erstellt für Unity Visualisierung
     * @param inputFile Verkehrs-Simulation
     * @param outputFile Output-File
     * @param pillars Menge gesetzter Säulen
     */
    public void solutionUnityExport(String inputFile, String outputFile, ArrayList<Pillar> pillars, int distance){

        ArrayList<Person> personModel = new ArrayList<>();
        int internalPersonId = 0;
        try (
                BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))
        ) {
            String line;

            writer.write(String.join(",","type","time","id","lat","lon","reached"));
            writer.newLine();

            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");
                Person lookingAt = findPerson(Integer.parseInt(tokens[1]),personModel);

                if(lookingAt == null){
                    personModel.add(new Person(Integer.parseInt(tokens[1]),internalPersonId));
                    lookingAt = findPerson(Integer.parseInt(tokens[1]),personModel);
                    internalPersonId++;
                }

                if(!lookingAt.isReached()){
                    Pillar isNear = findPassing(Integer.parseInt(tokens[2]),Integer.parseInt(tokens[3]),pillars,distance);
                    if(isNear != null){
                        lookingAt.setReached(true);
                    }
                }
                String reached = "";
                if(lookingAt.isReached()){
                    reached="1";
                }else{
                    reached="0";
                }
                String outputLine = String.join(",", "Point",tokens[0], tokens[1],tokens[2],tokens[3],reached);
                writer.write(outputLine);
                writer.newLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //Hilfsmethode1
    //Erkennt ob Person in Sichtbarkeits Radius von Säule war
    private static Pillar findPassing(double lon, double lat, ArrayList<Pillar> pillars, int dist){
        for(Pillar p: pillars){
            if(p.distanceTo(lon,lat) <= dist){
                return p;
            }
        }
        return null;
    }
    //Hilfsmethode1.2
    //Gibt entweder die Person wieder mit der Id
    //Oder: Gibt null wieder
    private static Person findPerson (int id, ArrayList<Person> pl){
        for(Person p:pl){
            if(id == p.getPersonID()){
                return p;
            }
        }
        return null;
    }
}
