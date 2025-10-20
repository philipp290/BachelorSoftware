package Model.Components;
import java.util.BitSet;

public class Person {
    //------------------------------------------------------------------------Attribute-------------
    //Schlüssel Attribut um Personen zu identifizieren
    private int personID;

    //Attribut, dass speichert an wievielter Stelle Person gelesen wurde
    private int internalID;
    //Menge aller passierten Säulen
    //"Welche Knoten decken die Person ab?"
    private BitSet pillarsPassed;
    //Boolean das beim UnityExport trackt, ob die Person in einem Zeitpunkt
    //bereits abgedeckt wurde oder nicht
    private boolean isReached = false;

    //-----------------------------------------------------------------------Constructor------------
    public Person(int id, int internalId){
        this.personID=id;
        this.internalID = internalId;
        pillarsPassed = new BitSet();
    }

    public Person(int id, int internalId, BitSet pillars ){
        this.personID = id;
        this.internalID = internalId;
        this.pillarsPassed = pillars;
    }
    //Copy Constructor
    public Person (Person p){
        this.personID=p.getPersonID();
        this.pillarsPassed = p.getPillarsPassed();
    }

    //-----------------------------------------------------------------------Function----------------
    public boolean isTheSame (Person p){
        if((this.personID==p.getPersonID()) && (this.pillarsPassed.equals(p.getPillarsPassed()))){
            return true;
        }
        return false;
    }
    //-----------------------------------------------------------------Getter and Setter Methods-----
    public int getPersonID() {
        return personID;
    }
    public void setPersonID(int personID) {
        this.personID = personID;
    }
    public BitSet getPillarsPassed() {
        return this.pillarsPassed;
    }
    public void setPillarsPassed(BitSet pillarsPassed) {
        this.pillarsPassed = pillarsPassed;
    }
    public boolean isReached() {
        return isReached;
    }
    public void setReached(boolean reached) {
        isReached = reached;
    }
    public int getInternalID() {return internalID;}
    public void setInternalID(int internalID) {this.internalID = internalID;}
}
