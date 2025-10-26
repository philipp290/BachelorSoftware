package Model.Services;

import Model.Components.Person;
import Model.Components.Pillar;
import Model.Components.Shadow;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.BitSet;


public class CsvReaderService {

    /**
     * Methode um Säulen Objekte aus CSV zu lesen
     * @param inputFile input File
     * @return Array Liste aller gelesener Säulen
     */
    public ArrayList<Pillar> readPillarsFromFile(String inputFile){
        ArrayList<Pillar> resultPillars = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            reader.readLine();
            String line;
            int id = 0;
            while ((line = reader.readLine()) != null) {
                String[] components = line.split(",");
                components[0]=components[0].replace("\"", "");
                double longitude = Double.parseDouble(components[0]);
                double latitude = Double.parseDouble(components[1]);
                Shadow shadow;
                switch (components[2]) {
                    case "hoch":
                        shadow = Shadow.HOCH;
                        break;
                    case "mittel":
                        shadow = Shadow.MITTEL;
                        break;
                    case "niedrig":
                        shadow = Shadow.NIEDRIG;
                        break;
                    default:
                        shadow = Shadow.KEIN;
                        break;
                }
                resultPillars.add(new Pillar(id, longitude, latitude, shadow));
                id++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultPillars;
    }

    /**
     * Methode um Säulen Objekte aus CSV zu lesen
     * @param cacheFileName input File
     * @return Array Liste aller gelesener Säulen
     */
    public ArrayList<Pillar> readPillarsFromCache(String cacheFileName){
        ArrayList<Pillar> resultPillars = new ArrayList<>();
        Path cacheFile = Paths.get("Data","Cache","SolutionFiles", cacheFileName+".csv");
        try (BufferedReader reader = Files.newBufferedReader(cacheFile, StandardCharsets.UTF_8)) {
            reader.readLine();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] components = line.split(",");
                components[1]=components[1].replace("\"", "");
                int id = Integer.parseInt(components[0]);
                double longitude = Double.parseDouble(components[1]);
                double latitude = Double.parseDouble(components[2]);
                Shadow shadow;
                switch (components[3]) {
                    case "hoch":
                        shadow = Shadow.HOCH;
                        break;
                    case "mittel":
                        shadow = Shadow.MITTEL;
                        break;
                    case "niedrig":
                        shadow = Shadow.NIEDRIG;
                        break;
                    default:
                        shadow = Shadow.KEIN;
                        break;
                }
                resultPillars.add(new Pillar(id, longitude, latitude, shadow));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultPillars;
    }

    /**
     * Methode die Personen Objekte aus CSV Datei liest
     * @param inputFile input File
     * @param pillars Säulen auf die gemappt werden soll
     * @param dist inerhalb dieser Distanz gilt Person als abgedeckt
     * @return Personen Objekte und Mappings in den Bitmaps
     */
    public ArrayList<Person> readPerson(String inputFile, ArrayList<Pillar> pillars, int dist){
        ArrayList<Person> resultPersons = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            reader.readLine();
            String line;
            int id = 0;
            while ((line = reader.readLine()) != null) {
                String[] components = line.split(",");
                Person lookingAt = findPerson(Integer.parseInt(components[1]),resultPersons);
                if(lookingAt == null){
                    lookingAt = new Person(Integer.parseInt(components[1]),id,new BitSet());
                    resultPersons.add(lookingAt);
                    id++;
                }
                ArrayList<Pillar> isNearOf = findPassing(Double.parseDouble(components[2]),Double.parseDouble(components[3]),pillars, dist);
                if(isNearOf != null){
                    for(Pillar p : isNearOf) {
                        p.getPeopleReached().set(lookingAt.getInternalID());
                        lookingAt.getPillarsPassed().set(p.getPillarID());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultPersons;
    }

    //Hilfsmethode1.2 Personen-Finder
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

    //Hilfsmethode1.1 Passing-Finder
    //Gibt Säulen wieder die im Unmkreis von 20 Metern stehen
    //Kann auch leere Liste zurück geben
    private static ArrayList<Pillar> findPassing(double lon, double lat, ArrayList<Pillar> pillars, int dist){
        ArrayList<Pillar> result = new ArrayList<>();
        for(Pillar p: pillars){
            if(p.distanceTo(lon,lat) <= dist){
                result.add(p);
            }
        }
        if(result.isEmpty()){
            return null;
        }
        return result;
    }
    //Hilfsmethode1.3 Säulen-Finder
    //Analog zu 1.2 mit PillarPosition
    private static Pillar findPillar(int id, ArrayList<Pillar> pp){
        for(Pillar p:pp){
            if(id == p.getPillarID()){
                return p;
            }
        }
        return null;
    }

    /**
     * Methode um Integer Liste aus CSV zu lesen
     * @param inputFile Input File
     * @return ArrayListe mit abgebildeten Integern
     */
    public ArrayList<Integer> readIntegerList (String inputFile){
        ArrayList<Integer> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                result.add(Integer.parseInt(line));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
