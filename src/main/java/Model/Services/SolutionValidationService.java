package Model.Services;

import Model.Components.Pillar;
import Model.Session;

import java.util.ArrayList;
import java.util.BitSet;

public class SolutionValidationService {
    /**
     * Methode, die die Anzahl abgedeckter Personen von den gesetzten Säulen
     * wieder gibt
     * @return Gesetzte Säulen Abdeckung
     */
    public double[] setCoverage(){
        ArrayList<Integer> indexes = Session.getInstance().getSetIndexes();
        ArrayList<Pillar> setPillars = new ArrayList<>();
        for(Integer i:indexes){
            setPillars.add(Session.getInstance().getPillars().get(i));
        }
        double[] result = {coverageValidation(setPillars)};
        return result;
    }
    /**
     * Methode, die die Anzahl abgedeckter Personen von den Katastrophenschutz
     * Leuchttürmen wieder gibt
     * @return Katastrophenschutz-Leuchtturm Abdeckung
     */
    public double[] lighthouseCoverage(){
        double[] result = {coverageValidation(Session.getInstance().getLighthouses())};
        return result;
    }
    /**
     * Methode, die die Anzahl abgedeckter Personen von den Katastrophenschutz
     * Leuchttürmen, den gesetzten Säulen und Gesammt wieder gibt
     * @return [0] Gesetzte Säulen Abdeckung
     *         [1] Katastrophenschutz-Leuchtturm Abdeckung
     *         [2] Gesamt Abdeckung
     */
    public double[] wholeSetCoverage(){
        ArrayList<Integer> indexes = Session.getInstance().getSetIndexes();
        ArrayList<Pillar> setPillars = new ArrayList<>();
        for(Integer i:indexes){
            setPillars.add(Session.getInstance().getPillars().get(i));
        }
        double[] result = new double[3];
        result[0] = coverageValidation(setPillars);
        ArrayList<Pillar> lighthouses = Session.getInstance().getLighthouses();
        result[1] = coverageValidation(lighthouses);
        ArrayList whole = new ArrayList<>();
        whole.addAll(setPillars);
        whole.addAll(lighthouses);
        result[2] = coverageValidation(whole);
        return result;
    }


    /** C O V E R A G E
     * Methode die Validiert, wieviele Personen von einer Belegung
     * abgedeckt werden
     * @param pillars Belegung
     * @return Anzahl erreichter Personen
     */
    public int coverageValidation(ArrayList<Pillar> pillars){
        BitSet completeCoverage = new BitSet();
        for(Pillar p : pillars){
            completeCoverage.or(p.getPeopleReached());
        }
        return completeCoverage.cardinality();
    }

    /** S C O R E
     * Methode die Validiert, wie gut die Beschattung einer
     * Belegung ist
     * @param pillars Belegung
     * @return Beschattungs-Score
     */
    public int scoreValidation(ArrayList<Pillar> pillars){
        int completeScore = 0;
        for(Pillar p : pillars){
            completeScore += p.getShadow().getValue();
        }
        return completeScore;
    }



}
