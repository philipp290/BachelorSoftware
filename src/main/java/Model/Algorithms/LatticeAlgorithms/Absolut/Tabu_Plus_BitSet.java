package Model.Algorithms.LatticeAlgorithms.Absolut;

import java.util.BitSet;

public class Tabu_Plus_BitSet {
    public TabuElement tabuElement;
    public BitSet bitSet;

    public Tabu_Plus_BitSet(TabuElement te, BitSet bS){
        this.tabuElement = te;
        this.bitSet = bS;
    }
    public String toString(){
        String bitString ="";
        for(int i = 0; i< bitSet.length();i++){
            if(bitSet.get(i)){
                bitString=bitString+"1";
            }else{
                bitString=bitString+"0";
            }
        }
        return "("+(tabuElement.indexOne+1)+","+(tabuElement.indexTwo+1)+") "+bitString;
    }
    // equals() überschreiben
    @Override
    public boolean equals(Object obj) {
        if (this == obj){ return true;}
        if (obj == null || getClass() != obj.getClass()){ return false;}
        Tabu_Plus_BitSet other = (Tabu_Plus_BitSet) obj;
        if(!other.tabuElement.isTheSame(this.tabuElement)||!other.bitSet.equals(this.bitSet)){
            return false;
        }
        return true;
    }
}
