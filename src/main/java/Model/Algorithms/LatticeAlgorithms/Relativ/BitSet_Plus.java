package Model.Algorithms.LatticeAlgorithms.Relativ;

import Model.Algorithms.LatticeAlgorithms.Absolut.Tabu_Plus_BitSet;

import java.util.BitSet;

public class BitSet_Plus {
    public BitSet bitSet;
    public int timer;

    BitSet_Plus(BitSet bs, int t){
        this.bitSet = bs;
        this.timer = t;
    }

    // equals() überschreiben
    @Override
    public boolean equals(Object obj) {
        if (this == obj){ return true;}
        if (obj == null || getClass() != obj.getClass()){ return false;}
        BitSet_Plus other = (BitSet_Plus) obj;
        if(!(other.bitSet.equals(this.bitSet)) || (other.timer!=this.timer) ){
            return false;
        }
        return true;
    }
}
