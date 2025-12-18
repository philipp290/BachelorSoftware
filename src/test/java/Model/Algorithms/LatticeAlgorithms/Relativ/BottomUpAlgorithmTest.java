package Model.Algorithms.LatticeAlgorithms.Relativ;

import Model.Components.Person;
import Model.Components.Pillar;
import Model.Services.CsvReaderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BottomUpAlgorithmTest {
    //Referenz Ergebnis wurde händisch berechnet
    //-> Im Detail zu sehen in: Data/Powerpoint-Visualisierungen/RelativerFall-Visualisierung.pptx
    @Test
    public void executeTest(){
        BottomUpAlgorithm bua = new BottomUpAlgorithm(8);
        CsvReaderService crs = new CsvReaderService();

        ArrayList<Pillar> pillars = crs.readPillarsFromFile("Data/TestingData/ReadTest/pillarReadTest2.csv");
        ArrayList<Person> people = crs.readPerson("Data/TestingData/AlgorithmTest/LogikAlgorithmTest/logikAlgorithmTest2.csv",pillars,20);

        ArrayList<Pillar> result = bua.execute(pillars,people,false,75);

        pillars.remove(7);
        pillars.remove(5);
        pillars.remove(4);
        pillars.remove(3);
        pillars.remove(2);
        pillars.remove(1);

        Assertions.assertEquals(pillars,result);
    }
}