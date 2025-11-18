package Model.Services;

import Model.Components.Person;
import Model.Components.Pillar;
import Model.Session;

import javax.management.StringValueExp;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class CsvWriterService {
    /**
     * Methode die Pillar Objekte zurück in eine CSV Datei überträgt
     * @param pp Pillar Objekte
     * @param outputFile Filepath auf dem CSV landen soll
     * Sinn: EXPORT
     */
    public void writePillarsToFile(ArrayList<Pillar> pp, String outputFile){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            boolean firstline= true;
            for (Pillar p : pp) {
                if(firstline) {
                    writer.write(String.join(",", "longitude", "latitude", "schatten"));
                    writer.newLine();
                    firstline = false;
                }
                String outputLine = String.join(",", Double.toString(p.getLongitude()), Double.toString(p.getLatitude()), p.getShadow().toString().toLowerCase());
                writer.write(outputLine);
                writer.newLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Methode die Pillar Objekte als CSV Datei in den Cache überträgt
     * @param pp Pillar Objekte
     * @param cacheFileName name der Datei im Cache
     * Sinn: Speichern von mehreren Lösungen im Cache
     * Hier ist es sinnvoll die internen IDs sich zu merken
     */
    public void writePillarsToCache(ArrayList<Pillar> pp, String cacheFileName){
        Path cacheDir = Paths.get("Data","Cache", "solutionFiles");
        Path outputFile = cacheDir.resolve(cacheFileName+".csv");
        try (BufferedWriter writer = Files.newBufferedWriter(outputFile, StandardCharsets.UTF_8)) {
            writer.write(String.join(",", "ID","longitude", "latitude", "schatten"));
            writer.newLine();
            for (Pillar p : pp) {
                String outputLine = String.join(",", Integer.toString(p.getPillarID()),Double.toString(p.getLongitude()), Double.toString(p.getLatitude()), p.getShadow().toString().toLowerCase());
                writer.write(outputLine);
                writer.newLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Methode ide ArrayListe von Integern in den Cache schreiben kann
     * @param info input Liste
     * @param outputFile File auf den diese geschrieben werden soll
     */
    public void writeIntegerList(ArrayList<Integer> info, String outputFile){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            for (Integer i : info) {
                writer.write(Integer.toString(i));
                writer.newLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Methode die in Komprimierter Form die gelesenen Personen in eine CSV
     * schreibt -> Vorbereitung auf Quick-Start
     * @param outputFile Ziel File
     */
    public void exportPeopleToFile(String outputFile){
        ArrayList<Person> people = Session.getInstance().getPeople();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            writer.write(String.join(",", "PersonID","PillarID"));
            writer.newLine();
            boolean lighthousesSet = !Session.getInstance().getLighthouses().isEmpty();
            for (Person p : people) {
                for(int i = 0; i<Session.getInstance().getPillars().size(); i++) {
                    if(p.getPillarsPassed().get(i)) {
                        String outputLine = String.join(",", String.valueOf(p.getPersonID()),String.valueOf(i));
                        System.out.println(outputLine);
                        writer.write(outputLine);
                        writer.newLine();
                    }
                }
                if(lighthousesSet){
                    for(int i = 0; i<Session.getInstance().getLighthouses().size(); i++) {
                        if(p.getLighthousesPassed().get(i)) {
                            String outputLine = String.join(",", String.valueOf(p.getInternalID()),String.valueOf(i*(-1)));
                            writer.write(outputLine);
                            writer.newLine();
                        }
                    }
                }
                if(p.getLighthousesPassed().cardinality()==0 && p.getPillarsPassed().cardinality()==0){
                    String outputLine = String.join(",", String.valueOf(p.getPersonID()),"-99999");
                    System.out.println(outputLine);
                    writer.write(outputLine);
                    writer.newLine();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Methode zum exportieren von Pillar Objekten. Merkt sich, ob diese gesetzt waren und die interne ID
     * @param outputFile outputFile
     */
    public void exportPillarsToFile(String outputFile){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            writer.write(String.join(",", "ID","longitude","latitude","schatten","Gesetzt"));
            writer.newLine();
            ArrayList<Pillar> pp = Session.getInstance().getPillars();
            for (Pillar p : pp) {
                int gesetzt = 0;
                if(Session.getInstance().getSetIndexes().contains(p.getPillarID())){
                    gesetzt = 1;
                }
                String outputLine = String.join(",", String.valueOf(p.getPillarID()),Double.toString(p.getLongitude()), Double.toString(p.getLatitude()), p.getShadow().toString().toLowerCase(),String.valueOf(gesetzt));
                writer.write(outputLine);
                writer.newLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
