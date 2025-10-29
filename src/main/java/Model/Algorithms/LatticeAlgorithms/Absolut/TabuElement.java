package Model.Algorithms.LatticeAlgorithms.Absolut;

public class TabuElement {
    public int indexOne;
    public int indexTwo;
    public int tabuTimer;

    public TabuElement(int i1, int i2, int tt){
        indexOne=i1;
        indexTwo=i2;
        tabuTimer=tt;
    }
    public boolean isTheSame(TabuElement tE){
        if((this.indexOne==tE.indexOne) && (this.indexTwo==tE.indexTwo)){
            return true;
        }
        return false;
    }
    public String toString(){
        return "("+(indexOne+1)+","+(indexTwo+1)+")";
    }

    // equals() überschreiben
    @Override
    public boolean equals(Object obj) {
        if (this == obj){ return true;}
        if (obj == null || getClass() != obj.getClass()){ return false;}
        TabuElement other = (TabuElement) obj;
        if((this.indexOne==other.indexOne) && (this.indexTwo==other.indexTwo) && (this.tabuTimer==other.tabuTimer)){
            return true;
        }
        return false;
    }
}
