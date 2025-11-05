package Model.Algorithms.LatticeAlgorithms.Relativ;

import Model.Algorithms.LatticeAlgorithms.Absolut.Tabu_Plus_BitSet;

import java.util.BitSet;

public class BitSet_Plus {
    public BitSet bitSet;
    public boolean valid;
    public int score;

    BitSet_Plus(BitSet bs, boolean v, int s){
        this.bitSet = bs;
        this.valid = v;
        this.score = s;
    }

    // equals() überschreiben
    @Override
    public boolean equals(Object obj) {
        if (this == obj){ return true;}
        if (obj == null || getClass() != obj.getClass()){ return false;}
        BitSet_Plus other = (BitSet_Plus) obj;
        if((other.valid != this.valid) || !(other.bitSet.equals(this.bitSet)) || (other.score!=this.score) ){
            return false;
        }
        return true;
    }
}
