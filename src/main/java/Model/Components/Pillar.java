package Model.Components;

import java.util.BitSet;

public class Pillar {
    //---------------------------------------------------------Attribute--------------------------
    //Schlüssel Attribut zum identifizieren
    private int pillarID;
    //GPS Position der Säule
    private double longitude;
    private double latitude;
    //Enum für das Schatten-Gewicht der Säule
    private Shadow shadow;
    //Menge von Personen die von der Säule abgedeckt werden
    private BitSet peopleReached = new BitSet();
    //Score für den Zeitfenster Algorithmus
    private int score = 0;

    //Boolean die trackt, ob eine Säule bereits gesetzt wurde
    private boolean isSet = false;

    //Radius der Erde für Distanz Berechnungen
    private static final double EARTH_RADIUS = 6371000;

    //-----------------------------------------------------------Constructor----------------------
    public Pillar(int pillarID, double longi, double lati, Shadow sh){
        this.pillarID=pillarID;
        this.latitude=lati;
        this.longitude=longi;
        this.shadow=sh;
    }
    //Copy-Constructor
    public Pillar(Pillar p){
        this.pillarID=p.getPillarID();
        this.latitude=p.getLatitude();
        this.longitude=p.getLongitude();
        this.shadow=p.getShadow();
    }

    //-------------------------------------------------------------Function-----------------------
    public boolean isTheSame(Pillar p){
        if(this.pillarID==p.getPillarID()){
            if(this.longitude==p.getLongitude()){
                if(this.latitude==p.getLatitude()){
                    if(this.shadow.equals(p.getShadow())){
                        if(this.peopleReached.equals(p.peopleReached)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    //Methode die die Distanz unserer Säule zu einer GPS Position berechnet
    public double distanceToHaversine(double lon2, double lat2){
        double lat1Rad = Math.toRadians(this.latitude);
        double lon1Rad = Math.toRadians(this.longitude);
        double lat2Rad = Math.toRadians(lat2);
        double lon2Rad = Math.toRadians(lon2);

        double deltaLat = lat2Rad - lat1Rad;
        double deltaLon = lon2Rad - lon1Rad;

        //Haversine-Formel
        double a = Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2)
                    + Math.cos(lat1Rad) * Math.cos(lat2Rad)
                    * Math.sin(deltaLon / 2) * Math.sin(deltaLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return EARTH_RADIUS * c;
    }

    //Euklid
    public double distanceTo(double lon2, double lat2) {
        final double gradZuMeter = 111320.0;
        double lat1 = this.latitude;
        double lon1 = this.longitude;

        double deltaLat = lat2 - lat1;
        double deltaLon = lon2 - lon1;

        double distGrad = Math.sqrt(deltaLat * deltaLat + deltaLon * deltaLon);
        return distGrad * gradZuMeter;
    }

    //------------------------------------------------------Getter Setter Methods------------------
    public int getPillarID() {return pillarID;}
    public void setPillarID(int pillarID) {this.pillarID = pillarID;}

    public double getLongitude() {return longitude;}
    public void setLongitude(double longitude) {this.longitude = longitude;}

    public double getLatitude() {return latitude;}
    public void setLatitude(double latitude) {this.latitude = latitude;}

    public Shadow getShadow() {return shadow;}
    public void setShadow(Shadow shadow) {this.shadow = shadow;}

    public BitSet getPeopleReached() {return peopleReached;}
    public void setPeopleReached(BitSet peopleReached) {this.peopleReached = peopleReached;}

    public int getScore() {return score;}
    public void setScore(int score) {this.score = score;}
    public void scorePlusOne(){this.score++;}

    public boolean isSet() {return isSet;}
    public void setSet(boolean set) {isSet = set;}
}