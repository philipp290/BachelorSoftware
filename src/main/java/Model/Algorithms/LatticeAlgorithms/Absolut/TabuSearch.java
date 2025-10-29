package Model.Algorithms.LatticeAlgorithms.Absolut;

import Model.Components.Pillar;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.BitSet;

public class TabuSearch {
    /**
     * Execute Methode
     * @param resultCardinality Fixe Anzahl Säulen die gesetzt werden sollen
     * @param cancelTimer Nach so vielen Iterationen ohne neues Optimum terminiet der Algorithmus
     * @param tabuTimer Nach so vielen Iterationen wird ein Tabu-Element von der Tabu-Liste entfernt
     * @param pillars Alle setzbaren Säulen
     * @return Approximierter optimalste Belegung
     */
    //Auskommentiert sind hier Print-Befehle zum Debuggen
    public ArrayList<Pillar> execute(int resultCardinality, int cancelTimer, int tabuTimer, ArrayList<Pillar> pillars){
        BitSetBuilder bsb = new BitSetBuilder();
        BitSetValidator bsv = new BitSetValidator();

        ArrayList<BitSet> pillarCoverage = new ArrayList<>();
        ArrayList<Integer> pillarScore = new ArrayList<>();
        for(Pillar p : pillars){
            pillarCoverage.add(p.getPeopleReached());
            pillarScore.add(p.getShadow().getValue());
        }

        if(resultCardinality < 1 || resultCardinality > pillarCoverage.size()){
            return null;
        }
        if(pillarCoverage.size() != pillarScore.size()){
            return null;
        }
        BitSet start = bsb.buildStartInstance(resultCardinality,pillarScore.size());
        BitSet optimum = (BitSet) start.clone();

        ArrayList<TabuElement> tabuList = new ArrayList<>();
        int noOptimumTimer = 0;
        //int rundenTimer = 1;
        while (noOptimumTimer < cancelTimer){
            //System.out.println("-----------Runde-"+rundenTimer+"----------------");
            ArrayList<Tabu_Plus_BitSet> candidates = bsb.generateCandidatesPlus(start,tabuList,tabuTimer);
            //System.out.println("N(Theta_"+(rundenTimer-1)+"):");
            //for(Tabu_Plus_String tps: candidates ){
            //    System.out.println(tps.toString());
            //}
            Tabu_Plus_BitSet topCandidate = bsv.findTopCandidate(candidates,pillarCoverage,pillarScore);
            //System.out.println("Theta_C:");
            //System.out.println(topCandidate.toString());
            if(bsv.newOptimum(optimum,topCandidate,pillarCoverage,pillarScore)){
                optimum = topCandidate.bitSet;
                noOptimumTimer = 0;
            }else{
                noOptimumTimer++;
            }
            //System.out.println("Optimum:");
            //System.out.println(optimum.toString()+" | "+noOptimumTimer);
            tabuListUpdate(tabuList);
            tabuList.add(topCandidate.tabuElement);
            start = topCandidate.bitSet;
            //rundenTimer++;
        }
        ArrayList<Pillar> result = new ArrayList<>();
        for(int i = 0; i< pillars.size();i++){
            if(optimum.get(i)){
                result.add(pillars.get(i));
            }
        }
        return result;
    }


    public void tabuListUpdate(ArrayList<TabuElement> tabuList){
        for(TabuElement tE : tabuList){
            tE.tabuTimer--;
        }
        tabuList.removeIf(tabuElement -> tabuElement.tabuTimer==0);
    }
}
