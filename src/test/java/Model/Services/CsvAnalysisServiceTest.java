package Model.Services;

import Model.Components.Pillar;
import Model.Components.Shadow;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CsvAnalysisServiceTest {
    //Anmerkung: 111320.0 ist der Faktor von Breitengrad zu Metern
    @Test
    void minimumDistanceBetweenPillarsTest(){
        ArrayList<Pillar> testingList = new ArrayList<>();
        testingList.add(new Pillar(1,10,10, Shadow.HOCH));
        testingList.add(new Pillar(1,15,10, Shadow.HOCH));
        testingList.add(new Pillar(1,24,5, Shadow.HOCH));
        testingList.add(new Pillar(1,20,15, Shadow.HOCH));

        CsvAnalysisService cas = new CsvAnalysisService();
        double testInstance = cas.minimumDistanceBetweenPillars(testingList);

        Assertions.assertEquals(testInstance, (5.0*111320.0));
    }



}