package Model.Algorithms.LatticeAlgorithms.Absolut;

import java.util.ArrayList;
import java.util.BitSet;

public class BitSetBuilder {
    /**
     * Methode die das selbe tut wie generateCandidates nur das Sie sich merkt,
     * welche Bit-Swaps zu welchem Element der Nachbarschaft geführt hat
     * @param startBits Theta_k
     * @param tabuListe Tabu-Liste
     * @return N(Theta_k)
     */

    public ArrayList<Tabu_Plus_BitSet> generateCandidatesPlus (BitSet startBits, ArrayList<TabuElement> tabuListe, int tabuTimer ){
        ArrayList<Tabu_Plus_BitSet> result = new ArrayList<>();
        ArrayList<Integer> zeroIndex = new ArrayList<>();
        ArrayList<Integer> oneIndex = new ArrayList<>();
        for(int i = 0; i< startBits.length()-1; i++){
            if(startBits.get(i)){
                oneIndex.add(i);
            }else{
                zeroIndex.add(i);
            }
        }
        for(Integer i: oneIndex){
            for(Integer j : zeroIndex){
                TabuElement tE;
                if(i<j){
                    tE = new TabuElement(i,j,tabuTimer);
                }else{
                    tE = new TabuElement(j,i,tabuTimer);
                }
                boolean tabu = false;
                for(TabuElement t : tabuListe){
                    if(tE.isTheSame(t)){
                        tabu = true;
                    }
                }
                if(!tabu){
                    result.add(new Tabu_Plus_BitSet(tE, bitSwap(startBits,tE)));
                }
            }
        }
        return result;
    }


    /**
     * Hilfsmethode für die Methode generateCandidates
     * @param bS Bit-Set, in dem der Bit-Swap durchgeführt werden soll
     * @param t Tabu-Element, was angibt, welche Bits getauscht werden sollen
     * @return bS mit dem Durchgeführten Bit-Swap
     */
    BitSet bitSwap(BitSet bS, TabuElement t){
        BitSet bitSet = (BitSet) bS.clone();
        if(bitSet.get(t.indexOne)){
            bitSet.clear(t.indexOne);
            bitSet.set(t.indexTwo);
        }else{
            bitSet.set(t.indexOne);
            bitSet.clear(t.indexTwo);
        }
        return bitSet;
    }

    /**
     * Methiode um eine Start-Instanz für Tabu Search zu generieren
     * @param fixedPillarAmount Anzahl zu setztender Säulen
     * @param pillarAmount Anzahl Säulen insgesamt
     * @return Theta_0
     */
    //Besonderheit: wir fügen immer 1 Extra Bit hinzu, da wir sonst nicht die Ursprungslänge bekommen
    public BitSet buildStartInstance(int fixedPillarAmount, int pillarAmount) {
        if (fixedPillarAmount > pillarAmount || pillarAmount < 0) {
            throw new IllegalArgumentException("Ungültiger Input");
        }
        BitSet bitSet = new BitSet(pillarAmount+1);
        for (int i = 0; i < fixedPillarAmount; i++) {
            bitSet.set(i);
        }
        bitSet.set(pillarAmount);
        return bitSet;
    }

}
