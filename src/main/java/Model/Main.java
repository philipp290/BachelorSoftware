package Model;

import Model.Components.Pillar;
import Model.Components.Shadow;
import Model.Services.CsvReaderService;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        CsvReaderService crs = new CsvReaderService();
        ArrayList<Pillar> test = crs.readPillarsFromFile("C:/Users/phili/OneDrive/Desktop/Bachelor Evaluation/Standorte_Litfaßsäule_Darmstadt.csv");
        Pillar RiegerPlatz = new Pillar(-9999999, 8.66093 , 49.88071, Shadow.HOCH);
        double minDistance = 99999999999.0;
        int nearetPillar = -1;
        for(Pillar p : test){
            double dis = p.distanceTo(RiegerPlatz.getLongitude(),RiegerPlatz.getLatitude());
            if(dis<minDistance){
                minDistance = dis;
                nearetPillar = p.getPillarID();
            }
        }
        System.out.println("Säule "+nearetPillar+ " : "+ minDistance+ " meter");
    }
}