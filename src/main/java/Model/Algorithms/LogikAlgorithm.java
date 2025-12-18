package Model.Algorithms;

import Model.Components.Person;
import Model.Components.Pillar;
import Model.Services.CsvAnalysisService;

import java.util.ArrayList;
import java.util.BitSet;

/**
 * Klasse die den Logik Algorithmus umsetzt
 */
public class LogikAlgorithm implements Algorithm{
    private BitSet markedColumns = new BitSet();
    private BitSet markedRows = new BitSet();
    private ArrayList<Integer> rarityTable = new ArrayList<>();

    @Override
    public ArrayList<Pillar> execute(ArrayList<Pillar> pillars, ArrayList<Person> people, boolean ABS_REL, int goal) {
        if(ABS_REL && goal > pillars.size()){
            System.out.println("invalid input");
            return null;
        }

        ArrayList<BitSet> playingField = new ArrayList<>();
        for(Pillar p: pillars){
            playingField.add((BitSet) p.getPeopleReached().clone());
        }

        markedColumns = new BitSet(people.size());
        markedRows = new BitSet(pillars.size());

        for(int n = 0; n < people.size(); n++){
            int rarityCount = 0;
            for(int m = 0; m < playingField.size(); m++){
                if(playingField.get(m).get(n)){
                    rarityCount++;
                }
            }
            rarityTable.add(rarityCount);
        }

        if(!ABS_REL){
            CsvAnalysisService cas = new CsvAnalysisService();
            double[] temp = cas.reachabilityAnalysis(people);
            goal = (int) Math.ceil((temp[0]/100)*goal);
        }

        while((ABS_REL && markedColumns.cardinality() < people.size() && markedRows.cardinality() < goal) || (!ABS_REL && markedColumns.cardinality() < goal)){
            //1.KRITERIUM
            ArrayList<Integer> candidateIndex = new ArrayList<>();
            int firstValidRow = 0;
            while(firstValidRow<pillars.size()){
                if(!markedRows.get(firstValidRow)){
                    break;
                }
                firstValidRow++;
            }
            if(firstValidRow<pillars.size()) {
                candidateIndex.add(firstValidRow);
                int j = firstValidRow + 1;
                while (j < playingField.size()) {
                    if (!markedRows.get(j)) {
                        if (remainingCardinality(playingField.get(j), people.size()) > remainingCardinality(playingField.get(candidateIndex.get(0)), people.size())) {
                            candidateIndex.clear();
                            candidateIndex.add(j);
                        } else if (remainingCardinality(playingField.get(j), people.size()) == remainingCardinality(playingField.get(candidateIndex.get(0)), people.size())) {
                            candidateIndex.add(j);
                        }
                    }
                    j++;
                }
                //2.KRITERIUM
                if (candidateIndex.size() > 1) {
                    ArrayList<Integer> candidateIndexNext = new ArrayList<>();
                    candidateIndexNext.add(candidateIndex.get(0));
                    int k = 1;
                    while (k < candidateIndex.size()) {
                        if (pillars.get(candidateIndex.get(k)).getShadow().getValue() > pillars.get(candidateIndexNext.get(0)).getShadow().getValue()) {
                            candidateIndexNext.clear();
                            candidateIndexNext.add(candidateIndex.get(k));
                        } else if (pillars.get(candidateIndex.get(k)).getShadow().getValue() == pillars.get(candidateIndexNext.get(0)).getShadow().getValue()) {
                            candidateIndexNext.add(candidateIndex.get(k));
                        }
                        k++;
                    }
                    candidateIndex.clear();
                    candidateIndex.addAll(candidateIndexNext);
                    candidateIndexNext.clear();
                    //3.KRITERIUM
                    if (candidateIndex.size() > 1) {
                        //System.out.println("Kriterium 3 ist in Kraft getretten");
                        candidateIndexNext.add(candidateIndex.get(0));
                        k = 1;
                        while (k < candidateIndex.size()) {
                            if (rarityScore(pillars.get(candidateIndex.get(k)).getPeopleReached(), people.size()) < rarityScore(pillars.get(candidateIndexNext.get(0)).getPeopleReached(), people.size())) {
                                candidateIndexNext.clear();
                                candidateIndexNext.add(candidateIndex.get(k));
                            } else if (rarityScore(pillars.get(candidateIndex.get(k)).getPeopleReached(), people.size()) == rarityScore(pillars.get(candidateIndexNext.get(0)).getPeopleReached(), people.size())) {
                                candidateIndexNext.add(candidateIndex.get(k));
                            }
                            k++;
                        }
                        candidateIndex.clear();
                        candidateIndex.addAll(candidateIndexNext);
                        candidateIndexNext.clear();
                    }
                }
                int candidate = candidateIndex.get(0);

                BitSet testUpdate = (BitSet) markedColumns.clone();
                testUpdate.or(playingField.get(candidate));
                if(testUpdate.equals(markedColumns)){
                    break;
                }
                markedRows.set(candidate);
                markedColumns.or(playingField.get(candidate));
            }else{
                break;
            }
        }
        ArrayList<Pillar> algorithmResult = new ArrayList<>();
        for(int i = 0; i < pillars.size(); i++){
            if(markedRows.get(i)){
                algorithmResult.add(pillars.get(i));
            }
        }
        return algorithmResult;
    }

    /**
     * Hilfsmethode die übrige abgedeckte Personen bestimmt
     * @param bitSet zu prüfendes Bitset
     * @param peopleSize anzahl Personen
     * @return Anzahl übriger Personen
     */
    private int remainingCardinality(BitSet bitSet, int peopleSize){
        BitSet inputBitSet = (BitSet) bitSet.clone();
        BitSet coveredPeople = (BitSet) markedColumns.clone();

        coveredPeople.flip(0,peopleSize);
        inputBitSet.and(coveredPeople);

        return inputBitSet.cardinality();
    }

    /**
     * Hilfsmethode um die Seltenheit der erreichten Personen zu bestimmen
     * @param bitSet Personen die die zu prüfende Säule erreicht
     * @param peopleSize Personen insgesamt
     * @return RarityScore der Säule;
     */
    private int rarityScore(BitSet bitSet, int peopleSize){
        int score = 0;
        for(int i = 0; i<peopleSize; i++){
            if(!markedRows.get(i) && bitSet.get(i)){
                score+= rarityTable.get(i);
            }
        }
        return score;
    }

    public static void main(String[] args) {
        LogikAlgorithm la = new LogikAlgorithm();

        BitSet pillar1 = new BitSet();
        pillar1.set(4);
        pillar1.set(5);
        System.out.println(la.remainingCardinality(pillar1,6));


        BitSet pillar2 = new BitSet();
        pillar2.set(0);
        pillar2.set(1);
        pillar2.set(2);
        pillar2.set(3);
        System.out.println(la.remainingCardinality(pillar2,6));

        BitSet pillar3 = new BitSet();
        pillar3.set(4);
        pillar3.set(5);
        System.out.println(la.remainingCardinality(pillar3,6));

        BitSet pillar4 = new BitSet();
        pillar4.set(1);
        pillar4.set(2);
        pillar4.set(3);

        System.out.println(la.remainingCardinality(pillar4,6));

    }
}
