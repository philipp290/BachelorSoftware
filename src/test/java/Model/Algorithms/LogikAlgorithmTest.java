package Model.Algorithms;

import Model.Components.Person;
import Model.Components.Pillar;
import Model.Components.Shadow;
import Model.Services.CsvReaderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class LogikAlgorithmTest {
    @Test
    void executeTestWithoutCriteria3(){
        CsvReaderService crs = new CsvReaderService();
        LogikAlgorithm la = new LogikAlgorithm();

        ArrayList<Pillar> pillars = new ArrayList<>();
        pillars.add(new Pillar(1,10,10, Shadow.KEIN));
        pillars.add(new Pillar(2,20,20, Shadow.HOCH));
        pillars.add(new Pillar(3,30,30, Shadow.MITTEL));
        pillars.add(new Pillar(4,40,40, Shadow.HOCH));


        ArrayList<Person> people = crs.readPerson("Data/TestingData/AlgorithmTest/LogikAlgorithmTest/logikAlgorithmTest1.csv",pillars,20);


        ArrayList<Pillar> testingInstance = la.execute(pillars,people,true,2);

        pillars.remove(3);
        pillars.remove(2);

        Assertions.assertEquals(pillars,testingInstance);
    }
    @Test
    void executeTestWithCriteria3(){
        CsvReaderService crs = new CsvReaderService();
        LogikAlgorithm la = new LogikAlgorithm();

        ArrayList<Pillar> pillars = new ArrayList<>();
        pillars.add(new Pillar(0,10,10, Shadow.KEIN));
        pillars.add(new Pillar(1,20,20, Shadow.MITTEL));
        pillars.add(new Pillar(2,30,30, Shadow.NIEDRIG));
        pillars.add(new Pillar(3,40,40, Shadow.HOCH));
        pillars.add(new Pillar(4,50,50, Shadow.HOCH));
        pillars.add(new Pillar(5,60,60, Shadow.NIEDRIG));
        pillars.add(new Pillar(6,70,70, Shadow.KEIN));
        pillars.add(new Pillar(7,80,80, Shadow.NIEDRIG));

        ArrayList<Person> people = crs.readPerson("Data/TestingData/AlgorithmTest/LogikAlgorithmTest/logikAlgorithmTest2.csv",pillars,20);

        ArrayList<Pillar> testingInstance = la.execute(pillars,people,true,3);

        pillars.remove(7);
        pillars.remove(4);
        pillars.remove(3);
        pillars.remove(2);
        pillars.remove(1);

        Assertions.assertEquals(pillars,testingInstance);
    }
}