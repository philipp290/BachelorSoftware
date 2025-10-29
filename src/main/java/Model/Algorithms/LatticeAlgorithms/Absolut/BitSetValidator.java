package Model.Algorithms.LatticeAlgorithms.Absolut;

import java.util.ArrayList;
import java.util.BitSet;

public class BitSetValidator {
    /**
     * Methode die aus N(Theta_k) das beste BitSet Theta_C findet
     * @param candidates N(Theta_k)
     * @param pillarCov Liste welche Säule welche Personen abdeckt
     * @param pillarSc Liste welche Säule welchen Gewichtugns-Faktor haben
     * @return Theta_C
     */
    public Tabu_Plus_BitSet findTopCandidate(ArrayList<Tabu_Plus_BitSet> candidates, ArrayList<BitSet> pillarCov, ArrayList<Integer> pillarSc){
        Tabu_Plus_BitSet top = candidates.get(0);
        for(int i = 1; i < candidates.size(); i++){
            if(validateAbs(candidates.get(i).bitSet,pillarCov) > validateAbs(top.bitSet,pillarCov)){
                top = candidates.get(i);
            }else if(validateAbs(candidates.get(i).bitSet,pillarCov) == validateAbs(top.bitSet,pillarCov)){
                if(validateScore(candidates.get(i).bitSet,pillarSc) > validateScore(top.bitSet,pillarSc)){
                    top = candidates.get(i);
                }
            }
        }
        return top;
    }

    /**Methode die erkennt, ob ein neues Optimum gefunden wurde
     * @param optimum vorheriges Optimum
     * @param candidate neuer Anwärter für das Optimum
     * @param pillarCov Liste welche Säule welche Personen abdeckt
     * @param pillarSc Liste welche Säule welche Gewichte hat
     * @return true = neues optimum gefunden
     *         false = kein neues Optimum gefunden
     */
    public boolean newOptimum(BitSet optimum, Tabu_Plus_BitSet candidate, ArrayList<BitSet> pillarCov, ArrayList<Integer> pillarSc){
        if(validateAbs(optimum,pillarCov) < validateAbs(candidate.bitSet,pillarCov)){
            return true;
        }else if(validateAbs(optimum,pillarCov) == validateAbs(candidate.bitSet,pillarCov)){
            if(validateScore(optimum,pillarSc) < validateScore(candidate.bitSet,pillarSc)){
                return true;
            }
        }
        return false;
    }

    /**
     * Methode um den Gewichtungs-Score eines
     * Knotens zu bestimmen
     * @param bS BitSet deas Knoten repräsentiert
     * @param pillarSc Assozierter Score zu den repräsentierten Säulen
     * @return Gewichtungs-Score des Knotens
     */
    public int validateScore(BitSet bS, ArrayList<Integer> pillarSc){
        int result = 0;
        for(int i = 0; i<bS.length()-1; i++){
            if(bS.get(i)){
                result += pillarSc.get(i);
            }
        }
        return result;
    }
    /**
     * Methode die zurück gibt wieviele Personen von einem Knoten abgedeckt werden
     * @param bS Bit-Set das Knoten repräsentiert
     * @param pillarCov Liste welche Litfaßsäulen, welche Personen erreichen
     * @return Anzahl der von der Säule abgedeckten Personen
     */
    public int validateAbs(BitSet bS, ArrayList<BitSet> pillarCov){
        BitSet result = new BitSet(bS.length());
        for(int i = 0; i<bS.length()-1; i++){
            if(bS.get(i)){
                result.or(pillarCov.get(i));
            }
        }
        int fin = 0;
        for (int i = 0; i<result.length(); i++) {
            if (result.get(i)) {
                fin++;
            }
        }
        return fin;
    }
}
