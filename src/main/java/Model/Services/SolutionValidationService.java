package Model.Services;

import Model.Components.Pillar;

import java.util.ArrayList;
import java.util.BitSet;

public class SolutionValidationService {

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
