package Model.Components;

public class Encounter {
    //------------------------------------------------------------------------Attributes------------
    //Schlüssel Attribut zum Ausseinander Halten der Begegnungen
    private int encounterID;
    //Schlüssel Attribut der involvierten Person
    private int personID;
    //Schlüssel Attribut der involvierten Säule
    private int pillarID;
    //Attribut zum zählen der Begegnungs-Dauer
    private int encounterLength = 0;
    //Boolean um zu checken, ob eine Begegnung geendet hat
    private boolean encounterEnded = false;
    //-----------------------------------------------------------------------Constructor------------
    Encounter(int entcoID, int persID, int pillID){
        this.encounterID=entcoID;
        this.personID=persID;
        this.pillarID=pillID;
    }
    //-----------------------------------------------------------------------Function----------------
    public void lengthIncrease(){
        this.encounterLength++;
    }
    //-----------------------------------------------------------------Getter and Setter Methods-----
    public int getEncounterID() {return encounterID;}
    public void setEncounterID(int encounterID) {this.encounterID = encounterID;}

    public int getPersonID() {return personID;}
    public void setPersonID(int personID) {this.personID = personID;}

    public int getPillarID() {return pillarID;}
    public void setPillarID(int pillarID) {this.pillarID = pillarID;}

    public int getEncounterLength() {return encounterLength;}
    public void setEncounterLength(int encounterLength) {this.encounterLength = encounterLength;}

    public boolean isEncounterEnded() {return encounterEnded;}
    public void setEncounterEnded(boolean encounterEnded) {this.encounterEnded = encounterEnded;}
}
