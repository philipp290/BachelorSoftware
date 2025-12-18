package Model.Components;

import Model.Services.CsvReaderService;
import Model.Session;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PillarTest {
    //Die Referenz-Werte wurden mit einem externen Programm berechnet
    @Test
    public void distanceAbove20Test(){
        Pillar p = new Pillar(1,-74.006000,40.712800,Shadow.HOCH);
        double result = p.distanceTo(-74.006000, 40.712989);
        System.out.print(result);
        assertEquals(result,21, 0.1);
    }
    @Test
    public void distanceAt20Test(){
        Pillar p = new Pillar(1,13.405000,52.520000,Shadow.HOCH);
        double result = p.distanceTo(13.405000, 52.5201799);
        System.out.print(result);
        assertEquals(result,20, 0.1);
    }
    @Test
    public void distanceUnder20Test(){
        Pillar p = new Pillar(1, 13.405000,52.520000,Shadow.HOCH);
        double result = p.distanceTo(13.405000,  52.5201709);
        System.out.print(result);
        assertEquals(result,19, 0.1);
    }
    //distanceBigTest schlägt fehl, da Euklid auf diese Distanz
    //nicht mehr das richtige Ergebnis liefert.
    @Test
    public void distanceBigTest(){
        Pillar p = new Pillar(1, 11.576124,48.137255,Shadow.HOCH);
        double result = p.distanceTo(11.567424,  48.227000);
        System.out.print(result);
        assertEquals(result,10000, 0.1);
    }

    /**
     * Test welche Säule die Säule am Riegerplatz ist
     */
    @Test
    public void riegerPlatzPillar() {
        CsvReaderService crs = new CsvReaderService();
        ArrayList<Pillar> test = crs.readPillarsFromFile("C:/Users/phili/OneDrive/Desktop/Bachelor Evaluation/Standorte_Litfaßsäule_Darmstadt.csv");
        Pillar RiegerPlatz = new Pillar(-9999999, 8.66093, 49.88071, Shadow.HOCH);
        double minDistance = 99999999999.0;
        int nearetPillar = -1;
        for (Pillar p : test) {
            double dis = p.distanceTo(RiegerPlatz.getLongitude(), RiegerPlatz.getLatitude());
            if (dis < minDistance) {
                minDistance = dis;
                nearetPillar = p.getPillarID();
            }
        }
        System.out.println("Säule " + nearetPillar + " : " + minDistance + " meter");
    }
    /**
     * Test wieviele Säulen durch die Katastrophenschutz-Leuchttürme
     * geprunt werden
     */
    @Test
    void pillarPrunePotential(){
        CsvReaderService crs = new CsvReaderService();
        ArrayList<Pillar> pil = crs.readPillarsFromFile("C:/Users/phili/OneDrive/Desktop/Bachelor Evaluation/Standorte_Litfaßsäule_Darmstadt.csv");
        crs.importLighthouses("C:/Users/phili/OneDrive/Desktop/Bachelor Evaluation/Katastrophenschutz Leuchtturm/KSL-complete.csv");
        ArrayList<Pillar> light = Session.getInstance().getLighthouses();
        int pruneCount = 0;
        for(Pillar p: pil){
            for(Pillar l : light){
                if(p.distanceTo(l.getLongitude(),l.getLatitude())<=20){
                    pruneCount++;
                    break;
                }
            }
        }
        System.out.println(pruneCount);
    }
}