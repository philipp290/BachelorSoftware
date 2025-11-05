package Model.Algorithms.LatticeAlgorithms.Relativ;

import Model.Components.Person;
import Model.Components.Pillar;
import Model.Services.CsvReaderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TopDownAlgorithmTest {
    //Referenz Werte wurden händisch berechnet
    //Im Detail zu finden in der Powerpoint Präsentation "BachelorPräsentation5.11.pptx"
    @Test
    public void executeTest(){
        TopDownAlgorithm tda = new TopDownAlgorithm(0);
        CsvReaderService crs = new CsvReaderService();

        ArrayList<Pillar> pillars = crs.readPillarsFromFile("Data/TestingData/ReadTest/pillarReadTest2.csv");
        ArrayList<Person> people = crs.readPerson("Data/TestingData/AlgorithmTest/LogikAlgorithmTest/logikAlgorithmTest2.csv",pillars,20);

        ArrayList<Pillar> result = tda.execute(pillars,people,false,75);

        pillars.remove(7);
        pillars.remove(5);
        pillars.remove(4);
        pillars.remove(3);
        pillars.remove(2);
        pillars.remove(1);

        Assertions.assertEquals(pillars,result);
    }
}