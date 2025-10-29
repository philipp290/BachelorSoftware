package Model.Algorithms;

import Model.Components.Person;
import Model.Components.Pillar;
import Model.Components.Shadow;
import Model.Services.CsvReaderService;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class LinearOptimizationAlgorithmTest {
    @Test
    void executeAbsolutTest(){
        CsvReaderService crs = new CsvReaderService();
        LinearOptimizationAlgorithm loa = new LinearOptimizationAlgorithm();

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

        ArrayList<Pillar> testingInstance = loa.execute(pillars,people,true,3);


    }
}