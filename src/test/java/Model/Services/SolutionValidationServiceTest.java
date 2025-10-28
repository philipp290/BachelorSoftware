package Model.Services;

import Model.Components.Pillar;
import Model.Components.Shadow;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.BitSet;

import static org.junit.jupiter.api.Assertions.*;

class SolutionValidationServiceTest {

    @Test
    void coverageValidationTest(){
        ArrayList<Pillar> testpillars = new ArrayList<Pillar>();
        testpillars.add(new Pillar(0,10,10, Shadow.KEIN));
        BitSet bit1 = new BitSet();
        bit1.set(0);
        bit1.set(2);
        testpillars.get(0).setPeopleReached(bit1);

        testpillars.add(new Pillar(0,10,10, Shadow.KEIN));
        BitSet bit2 = new BitSet();
        bit2.set(2);
        bit2.set(4);
        bit2.set(5);
        testpillars.get(1).setPeopleReached(bit2);

        testpillars.add(new Pillar(0,10,10, Shadow.KEIN));
        BitSet bit3 = new BitSet();
        bit3.set(0);
        bit3.set(5);
        bit3.set(20);
        testpillars.get(2).setPeopleReached(bit3);

        testpillars.add(new Pillar(0,10,10, Shadow.KEIN));
        BitSet bit4 = new BitSet();
        testpillars.get(3).setPeopleReached(bit4);

        SolutionValidationService svs = new SolutionValidationService();
        int testInstance = svs.coverageValidation(testpillars);

        Assertions.assertEquals(testInstance,5);
    }

    @Test
    void scoreValidationTest(){
        ArrayList<Pillar> testpillars = new ArrayList<Pillar>();
        testpillars.add(new Pillar(0,10,10, Shadow.HOCH));
        testpillars.add(new Pillar(0,10,10, Shadow.NIEDRIG));
        testpillars.add(new Pillar(0,10,10, Shadow.KEIN));
        testpillars.add(new Pillar(0,10,10, Shadow.MITTEL));

        SolutionValidationService svs = new SolutionValidationService();
        int testInstance = svs.scoreValidation(testpillars);

        Assertions.assertEquals(testInstance,6);
    }

}