package Model.Services;

import Model.Components.Person;
import Model.Components.Pillar;
import Model.Components.Shadow;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class CsvReaderServiceTest {

    @Test
    public void readPillarTest1(){
        CsvReaderService crs = new CsvReaderService();
        ArrayList<Pillar> testInstance = crs.readPillarsFromFile("Data/TestingData/ReadTest/pillarReadTest1.csv");

        Assertions.assertEquals(testInstance.get(0).getLatitude(),10);
        Assertions.assertEquals(testInstance.get(0).getLongitude(),10);
        Assertions.assertEquals(testInstance.get(0).getShadow(), Shadow.HOCH);

        Assertions.assertEquals(testInstance.get(1).getLatitude(),20);
        Assertions.assertEquals(testInstance.get(1).getLongitude(),20);
        Assertions.assertEquals(testInstance.get(1).getShadow(),Shadow.MITTEL);

        Assertions.assertEquals(testInstance.get(2).getLatitude(),30);
        Assertions.assertEquals(testInstance.get(2).getLongitude(),30);
        Assertions.assertEquals(testInstance.get(2).getShadow(),Shadow.NIEDRIG);

        Assertions.assertEquals(testInstance.get(3).getLatitude(),40);
        Assertions.assertEquals(testInstance.get(3).getLongitude(),40);
        Assertions.assertEquals(testInstance.get(3).getShadow(),Shadow.KEIN);
    }

    @Test
    public void readPillarFromCacheTest(){
        CsvReaderService crs = new CsvReaderService();
        ArrayList<Pillar> testingInstance = crs.readPillarsFromCache("pillarCacheWriterTest1");
        Pillar pillar1 = testingInstance.get(0);
        Pillar pillar2 = testingInstance.get(1);
        Pillar pillar3 = testingInstance.get(2);
        Pillar pillar4 = testingInstance.get(3);

        Assertions.assertEquals(pillar1.getPillarID(),1);
        Assertions.assertEquals(pillar1.getLatitude(),10);
        Assertions.assertEquals(pillar1.getLongitude(),10);
        Assertions.assertEquals(pillar1.getShadow(),Shadow.HOCH);

        Assertions.assertEquals(pillar2.getPillarID(),2);
        Assertions.assertEquals(pillar2.getLatitude(),20);
        Assertions.assertEquals(pillar2.getLongitude(),20);
        Assertions.assertEquals(pillar2.getShadow(),Shadow.MITTEL);

        Assertions.assertEquals(pillar3.getPillarID(),3);
        Assertions.assertEquals(pillar3.getLatitude(),30);
        Assertions.assertEquals(pillar3.getLongitude(),30);
        Assertions.assertEquals(pillar3.getShadow(),Shadow.NIEDRIG);

        Assertions.assertEquals(pillar4.getPillarID(),4);
        Assertions.assertEquals(pillar4.getLatitude(),40);
        Assertions.assertEquals(pillar4.getLongitude(),40);
        Assertions.assertEquals(pillar4.getShadow(),Shadow.KEIN);
    }

    @Test
    public void readPersonTest1(){
        CsvReaderService crs = new CsvReaderService();

        ArrayList<Pillar> testPillars = crs.readPillarsFromFile("Data/TestingData/ReadTest/pillarReadTest1.csv");
        ArrayList<Person> testPersons = crs.readPerson("Data/TestingData/ReadTest/personReadTest1.csv",testPillars,20);

        Person person100 = testPersons.get(0);
        Person person101 = testPersons.get(1);
        Person person102 = testPersons.get(2);
        Person person103 = testPersons.get(5);
        Person person104 = testPersons.get(3);
        Person person105 = testPersons.get(4);

        Assertions.assertEquals(person100.getPersonID(),100);
        Assertions.assertEquals(person101.getPersonID(),101);
        Assertions.assertEquals(person102.getPersonID(),102);
        Assertions.assertEquals(person103.getPersonID(),103);
        Assertions.assertEquals(person104.getPersonID(),104);
        Assertions.assertEquals(person105.getPersonID(),105);

        Assertions.assertEquals(person100.getInternalID(),0);
        Assertions.assertEquals(person101.getInternalID(),1);
        Assertions.assertEquals(person102.getInternalID(),2);
        Assertions.assertEquals(person103.getInternalID(),5);
        Assertions.assertEquals(person104.getInternalID(),3);
        Assertions.assertEquals(person105.getInternalID(),4);

        Assertions.assertEquals(person100.getPillarsPassed().cardinality(),1);
        Assertions.assertTrue(person100.getPillarsPassed().get(3));

        Assertions.assertEquals(person101.getPillarsPassed().cardinality(),0);

        Assertions.assertEquals(person102.getPillarsPassed().cardinality(),2);
        Assertions.assertTrue(person102.getPillarsPassed().get(0));
        Assertions.assertTrue(person102.getPillarsPassed().get(1));

        Assertions.assertEquals(person103.getPillarsPassed().cardinality(),2);
        Assertions.assertTrue(person103.getPillarsPassed().get(1));
        Assertions.assertTrue(person103.getPillarsPassed().get(3));

        Assertions.assertEquals(person104.getPillarsPassed().cardinality(),0);

        Assertions.assertEquals(person105.getPillarsPassed().cardinality(),2);
        Assertions.assertTrue(person105.getPillarsPassed().get(1));
        Assertions.assertTrue(person105.getPillarsPassed().get(2));

        Pillar pillar1 = testPillars.get(0);
        Pillar pillar2 = testPillars.get(1);
        Pillar pillar3 = testPillars.get(2);
        Pillar pillar4 = testPillars.get(3);

        Assertions.assertEquals(pillar1.getPeopleReached().cardinality(),1);
        Assertions.assertTrue(pillar1.getPeopleReached().get(2));

        Assertions.assertEquals(pillar2.getPeopleReached().cardinality(),3);
        Assertions.assertTrue(pillar2.getPeopleReached().get(2));
        Assertions.assertTrue(pillar2.getPeopleReached().get(5));
        Assertions.assertTrue(pillar2.getPeopleReached().get(4));

        Assertions.assertEquals(pillar3.getPeopleReached().cardinality(),1);
        Assertions.assertTrue(pillar3.getPeopleReached().get(4));

        Assertions.assertEquals(pillar4.getPeopleReached().cardinality(),2);
        Assertions.assertTrue(pillar4.getPeopleReached().get(0));
        Assertions.assertTrue(pillar4.getPeopleReached().get(5));
    }

    @Test
    void integerReadTest(){
        ArrayList<Integer> referenceInstancce = new ArrayList<>();
        referenceInstancce.add(1);
        referenceInstancce.add(50);
        referenceInstancce.add(23);
        referenceInstancce.add(-4);
        referenceInstancce.add(30);

        CsvReaderService crs = new CsvReaderService();
        ArrayList<Integer> testingInstance = crs.readIntegerList("Data/TestingData/ReadTest/integerReadTest.csv");
        Assertions.assertEquals(testingInstance, referenceInstancce);
    }
}