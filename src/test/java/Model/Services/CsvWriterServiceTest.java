package Model.Services;

import Model.Components.Pillar;
import Model.Components.Shadow;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class CsvWriterServiceTest {

    @Test
    void writePillars(){
        Pillar pillar1 = new Pillar(0,10,10, Shadow.HOCH);
        Pillar pillar2 = new Pillar(1,20,20, Shadow.MITTEL);
        Pillar pillar3 = new Pillar(2,30,30, Shadow.NIEDRIG);
        Pillar pillar4 = new Pillar(3,40,40, Shadow.KEIN);
        ArrayList<Pillar> pillars = new ArrayList<>();
        pillars.add(pillar1);
        pillars.add(pillar2);
        pillars.add(pillar3);
        pillars.add(pillar4);

        CsvWriterService cws = new CsvWriterService();
        cws.writePillarsToFile(pillars,"Data/TestingData/WriteTest/pillarWriterTest1.csv");

        CsvReaderService crs = new CsvReaderService();
        ArrayList<Pillar> testingPillars = crs.readPillarsFromFile("Data/TestingData/WriteTest/pillarWriterTest1.csv");

        Assertions.assertEquals(pillars,testingPillars);
    }

    @Test
    void writePillarsToCache(){
        Pillar pillar1 = new Pillar(1,10,10, Shadow.HOCH);
        Pillar pillar2 = new Pillar(2,20,20, Shadow.MITTEL);
        Pillar pillar3 = new Pillar(3,30,30, Shadow.NIEDRIG);
        Pillar pillar4 = new Pillar(4,40,40, Shadow.KEIN);
        ArrayList<Pillar> pillars = new ArrayList<>();
        pillars.add(pillar1);
        pillars.add(pillar2);
        pillars.add(pillar3);
        pillars.add(pillar4);

        CsvWriterService cws = new CsvWriterService();
        cws.writePillarsToCache(pillars,"pillarCacheWriterTest1");

        CsvReaderService crs = new CsvReaderService();
        ArrayList<Pillar> testingPillars = crs.readPillarsFromCache("pillarCacheWriterTest1");

        Assertions.assertEquals(pillars,testingPillars);
    }

    @Test
    void writeIntegerTest(){
        ArrayList<Integer> referenceInstance = new ArrayList<>();
        referenceInstance.add(23);
        referenceInstance.add(-223);
        referenceInstance.add(0);
        referenceInstance.add(7);
        referenceInstance.add(46);
        referenceInstance.add(-4);

        CsvWriterService cws = new CsvWriterService();
        cws.writeIntegerList(referenceInstance, "Data/TestingData/WriteTest/integerWriteTest1.csv");

        CsvReaderService crs = new CsvReaderService();
        ArrayList<Integer> testingInstance = crs.readIntegerList("Data/TestingData/WriteTest/integerWriteTest1.csv");

        Assertions.assertEquals(referenceInstance,testingInstance);
    }
}