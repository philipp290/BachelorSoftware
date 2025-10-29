package Model.Services;

import Model.Components.Encounter;
import Model.Components.Person;
import Model.Components.Pillar;
import Model.Session;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class CsvAnalysisService {

    /**
     * Methode zur Analyse der Begegnungs-Dauern zwischen Säulen und Personen
     * @param inputFile File mit Verkehrs-Simulation
     * @param pillars Menge von Säulen Objekten
     * @param dist Distanz inerhalb von der Personen von Säule abgedeckt werden
     * @return [0] Median Begegnungs-Dauer
     *         [1] Durschnitts Begegnungs-Dauer
     *         [2] Maximale Begegnungs-Dauer
     *         [3] Minimale Begegnungs-Dauer
     */
    public double[] encounterLengthAnalysis(String inputFile, ArrayList<Pillar> pillars, int dist){
        ArrayList<Encounter> currentEncounters = new ArrayList<>();
        ArrayList<Integer> encounterLenghts = new ArrayList<>();
        int encounterCount =0;
        Long currentTime=0L;
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            reader.readLine();
            boolean secondLine = true;
            while ((line = reader.readLine()) != null) {
                String[] components = line.split(",");
                if(secondLine){
                    currentTime = Long.parseLong(components[0]);
                    secondLine = false;
                }

                if(Long.parseLong(components[0]) != currentTime){
                    for (int i = currentEncounters.size() - 1; i >= 0; i--) {
                        if (currentEncounters.get(i).isEncounterEnded()) {
                            encounterLenghts.add(currentEncounters.get(i).getEncounterLength());
                            currentEncounters.remove(i);
                        }else{
                            currentEncounters.get(i).setEncounterEnded(true);
                        }
                    }
                    currentTime=Long.parseLong(components[0]);
                }

                ArrayList<Pillar> isNearOf = findPassing(Double.parseDouble(components[2]),Double.parseDouble(components[3]),pillars, dist);
                if(isNearOf != null){
                    for(Pillar p : isNearOf) {
                        Encounter lookingAt = findEncounter(Integer.parseInt(components[1]),p.getPillarID(),currentEncounters);
                        if(lookingAt==null){
                            lookingAt = new Encounter(encounterCount,Integer.parseInt(components[1]),p.getPillarID());
                            currentEncounters.add(lookingAt);
                            encounterCount++;
                        }
                        lookingAt.setEncounterEnded(false);
                        lookingAt.lengthIncrease();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        for(Encounter e : currentEncounters){
            encounterLenghts.add(e.getEncounterLength());
        }

        if(encounterLenghts.isEmpty()){
            return null;
        }

        int minimum = Integer.MAX_VALUE;
        int maximum = 0;
        int encounterLengthSum = 0;
        for(Integer i : encounterLenghts){
            if(i < minimum){
                minimum = i;
            }
            if(i > maximum){
                maximum = i;
            }
            encounterLengthSum = encounterLengthSum + i;
        }

        ArrayList<Integer> occurrences = new ArrayList<>();
        System.out.println("Häufigkeiten");
        for(int n = minimum; n<=maximum; n++){
            int countApperance = 0;
            for(Integer i : encounterLenghts){
                if(i == n){
                    countApperance++;
                }
            }
            occurrences.add(countApperance);
            System.out.println(n+": "+ countApperance+" Vorkommen");
        }

        CsvWriterService cws = new CsvWriterService();
        cws.writeIntegerList(occurrences,"Data/Cache/AnalysisCache/encounterLengthTemporary.csv");

        double median;
        boolean evenCase = false;
        if((encounterLengthSum%2)==0){
            evenCase = true;
        }
        int solutionIndex=-1;
        int countingUp = 0;
        int targetIndex;
        if(evenCase){
            targetIndex = encounterLengthSum/2;
        }else{
            targetIndex = (encounterLengthSum+1)/2;
        }

        while(countingUp < targetIndex){
            solutionIndex++;
            countingUp = countingUp + occurrences.get(solutionIndex);
        }
        if(countingUp==targetIndex&&evenCase){
            median= solutionIndex+0.5;
        }else{
            median = solutionIndex;
        }

        double[] result = new double[4];
        result[0] = median;
        result[1] = (double) encounterLengthSum /encounterLenghts.size();
        result[2] = maximum;
        result[3] = minimum;
        return result;
    }
    //Hilfsmethode: findet Passieren von "Person trifft Säule Event"
    private static ArrayList<Pillar> findPassing(double lon, double lat, ArrayList<Pillar> pillars, int dist){
        ArrayList<Pillar> result = new ArrayList<>();
        for(Pillar p: pillars){
            if(p.distanceTo(lon,lat) <= dist){
                result.add(p);
            }
        }
        return result;
    }
    //Hilfsmethode: findet Encounter in Encounter Liste
    private static Encounter findEncounter(int persID, int pillID, ArrayList<Encounter> encounters){
        for(Encounter e : encounters){
            if((e.getPersonID() == persID) && (e.getPillarID() == pillID)){
                return e;
            }
        }
        return null;
    }

    /**
     * Methode um zu analysieren wie erreichbar, die Leute aus peopleFile durch die
     * Säulen in pillarFile sind
     * @param people File mit der Verkehrs-Simulation
     * @return [0] absolute Anzahl erreichter Personen
     *         [1] anzahl Personen insgesamt in der Verkehrs-Simulation
     *         [2] relative Erreichbarkeitsquote der Personen durch die Säulen
     */
    public double[] reachabilityAnalysis(ArrayList<Person> people){
        double whole = people.size();
        double reached = 0;
        for(Person p : people){
            if(!p.getPillarsPassed().isEmpty()){
                reached++;
            }
        }

        //Vorarbeit für benutzerfreundliche UI
        Session.getInstance().setAmountOfReachablePeople((int) reached);

        double[] result = new double[3];
        result[0] = reached;
        result[1] = whole;
        result[2] = reached/whole;
        return result;
    }


    /**
     * Methode die den Minimalen Abstand zwischen Säulen findet
     * @param pillars Säulen die analysiert werden sollen
     * @return minimaler Abstand zwischen den Säulen
     */
    public double minimumDistanceBetweenPillars(ArrayList<Pillar> pillars){
        double minimumDis = Double.MAX_VALUE;
        for(int i=0; i<pillars.size(); i++){
            for(int j=i+1; j<pillars.size();j++){
                double dis = pillars.get(i).distanceTo(pillars.get(j).getLongitude(),pillars.get(j).getLatitude());
                if(dis < minimumDis){
                    minimumDis = dis;
                }
            }
        }
        return minimumDis;
    }
}
