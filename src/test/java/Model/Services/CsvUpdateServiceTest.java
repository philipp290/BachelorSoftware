package Model.Services;

import Model.Components.Pillar;
import Model.Components.Shadow;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CsvUpdateServiceTest {
    //Visuelle Tests
    @Test
    void switchColumnsTest(){
        CsvUpdateService cus = new CsvUpdateService();
        cus.switchColumns("Data/TestingData/UpdateTest/deleteColumnTest1.csv","Data/TestingData/UpdateTest/switchColumnTest1.csv",0,3);
    }

    @Test
    void deleteColumnTest(){
        CsvUpdateService cus = new CsvUpdateService();
        cus.deleteColumn("Data/TestingData/UpdateTest/deleteColumnTest1.csv","Data/TestingData/UpdateTest/deleteColumnTest2.csv",1);
    }

    @Test
    void sampleTest(){
        ArrayList<Integer> testInstance1 = new ArrayList<>();
        testInstance1.add(0);
        testInstance1.add(1);
        testInstance1.add(2);
        testInstance1.add(3);
        testInstance1.add(4);
        testInstance1.add(5);
        testInstance1.add(6);
        testInstance1.add(7);
        testInstance1.add(8);
        testInstance1.add(9);
        testInstance1.add(10);

        ArrayList<Integer> referenceInstance = new ArrayList<>();
        referenceInstance.add(1);
        referenceInstance.add(4);
        referenceInstance.add(7);
        referenceInstance.add(10);

        CsvWriterService cws = new CsvWriterService();
        cws.writeIntegerList(testInstance1,"Data/TestingData/UpdateTest/sampleTest1.csv");

        CsvUpdateService cus = new CsvUpdateService();
        cus.sampleCSV("Data/TestingData/UpdateTest/sampleTest1.csv","Data/TestingData/UpdateTest/sampleTest2.csv",3);

        CsvReaderService crs = new CsvReaderService();
        ArrayList<Integer> testInstance2 = crs.readIntegerList("Data/TestingData/UpdateTest/sampleTest2.csv");

        Assertions.assertEquals(testInstance2,referenceInstance);
    }

    @Test
    void csvCopyTest(){
        ArrayList<Integer> referenceInstance = new ArrayList<>();
        referenceInstance.add(23);
        referenceInstance.add(-12);
        referenceInstance.add(0);
        referenceInstance.add(90);

        CsvWriterService cws = new CsvWriterService();
        cws.writeIntegerList(referenceInstance,"Data/TestingData/UpdateTest/copyTest1.csv");

        CsvUpdateService cus = new CsvUpdateService();
        cus.copyCSV("Data/TestingData/UpdateTest/copyTest1.csv","Data/TestingData/UpdateTest/copyTest2.csv");

        CsvReaderService crs = new CsvReaderService();
        ArrayList<Integer> testingInstance = crs.readIntegerList("Data/TestingData/UpdateTest/copyTest2.csv");

        Assertions.assertEquals(referenceInstance,testingInstance);
    }

    @Test
    void solutionUnityExportTest(){
        ArrayList<Pillar> pillars = new ArrayList<>();
        pillars.add(new Pillar(2,20,20, Shadow.KEIN));

        CsvUpdateService cus = new CsvUpdateService();
        cus.solutionUnityExport("Data/TestingData/AlgorithmTest/LogikAlgorithmTest/logikAlgorithmTest2.csv","Data/TestingData/UpdateTest/unityExportTest1.csv", pillars, 20);

    }
}