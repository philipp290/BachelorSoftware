package Model.Services;

import Model.Components.Pillar;

import java.util.ArrayList;

public class CsvAnalysisService {
    /**
     * Methode die den Minimalen Abstand zwischen Säulen findet
     * @param pillars Säulen die analysiert werden sollen
     * @return minimaler Abstand zwischen den Säulen
     */
    public double minimumDistanceBetweenPillars(ArrayList<Pillar> pillars){
        double minimumDis = Double.MAX_VALUE;
        for(int i=0; i<pillars.size(); i++){
            for(int j=i+1; j<pillars.size();j++){
                double dis = pillars.get(i).distanceTo(pillars.get(j).getLongitude(),pillars.get(j).getLatitude());
                if(dis < minimumDis){
                    minimumDis = dis;
                }
            }
        }
        return minimumDis;
    }
}
