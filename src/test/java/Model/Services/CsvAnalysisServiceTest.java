package Model.Services;

import Model.Components.Person;
import Model.Components.Pillar;
import Model.Components.Shadow;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CsvAnalysisServiceTest {
    @Test
    void encounterLengthAnalysisTest(){
        double[] referenceInstance = new double[4];
        referenceInstance[0] = 2;
        referenceInstance[1] = 1.85;
        referenceInstance[2] = 3;
        referenceInstance[3] = 1;

        CsvReaderService crs = new CsvReaderService();
        ArrayList<Pillar> pillars = crs.readPillarsFromFile("Data/TestingData/ReadTest/pillarReadTest1.csv");

        CsvAnalysisService cas = new CsvAnalysisService();
        double[] testInstance = cas.encounterLengthAnalysis("Data/TestingData/AnalysisTest/reachabilityTestPeople.csv",pillars,20);

        Assertions.assertEquals(referenceInstance[0],testInstance[0]);
        Assertions.assertEquals(referenceInstance[1],testInstance[1],0.1);
        Assertions.assertEquals(referenceInstance[2],testInstance[2]);
        Assertions.assertEquals(referenceInstance[3],testInstance[3]);
    }
    @Test
    void reachabilityAnalysisTest(){
        double[] referenceInstance = new double[3];
        referenceInstance[0] = 6;
        referenceInstance[1] = 10;
        referenceInstance[2] = 0.6;

        CsvReaderService crs = new CsvReaderService();
        ArrayList<Pillar> pillars = crs.readPillarsFromFile("Data/TestingData/ReadTest/pillarReadTest1.csv");
        ArrayList<Person> people = crs.readPerson("Data/TestingData/AnalysisTest/reachabilityTestPeople.csv",pillars,20);

        CsvAnalysisService cas = new CsvAnalysisService();
        double[] testInstance = cas.reachabilityAnalysis(people);

        Assertions.assertEquals(referenceInstance[0],testInstance[0]);
        Assertions.assertEquals(referenceInstance[1],testInstance[1]);
        Assertions.assertEquals(referenceInstance[2],testInstance[2]);
    }

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