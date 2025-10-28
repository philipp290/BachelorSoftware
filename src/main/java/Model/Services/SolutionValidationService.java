package Model.Services;

import Model.Components.Pillar;

import java.util.ArrayList;
import java.util.BitSet;

public class SolutionValidationService {

    public int coverageValidation(ArrayList<Pillar> pillars){
        BitSet completeCoverage = new BitSet();
        for(Pillar p : pillars){
            completeCoverage.or(p.getPeopleReached());
        }
        return completeCoverage.cardinality();
    }
    public int scoreValidation(ArrayList<Pillar> pillars){
        int completeScore = 0;
        for(Pillar p : pillars){
            completeScore += p.getShadow().getValue();
        }
        return completeScore;
    }



}
