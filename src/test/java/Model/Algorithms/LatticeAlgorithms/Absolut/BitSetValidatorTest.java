package Model.Algorithms.LatticeAlgorithms.Absolut;

import Model.Algorithms.LatticeAlgorithms.General.BitSetValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;

class BitSetValidatorTest {
    @Test
    void validateScoreTest(){
        //Welche Säule hat welches Gewicht?
        ArrayList<Integer> pillarScore = new ArrayList<>();
        pillarScore.add(2);     //Säule1
        pillarScore.add(0);     //Säule2
        pillarScore.add(1);     //...
        pillarScore.add(-1);
        pillarScore.add(-1);
        pillarScore.add(1);
        pillarScore.add(2);
        pillarScore.add(1);

        BitSetValidator bsV = new BitSetValidator();
        BitSet testInstance = new BitSet(8);
        testInstance.set(0);
        testInstance.set(1);
        testInstance.set(7);
        testInstance.set(8);
        int score = bsV.validateScore(testInstance,pillarScore);
        Assertions.assertEquals(score,3);
    }

    @Test
    void validateAbsTest(){
        //Welche Säule deckt welche Person ab?
        ArrayList<BitSet> pillarCoverage = new ArrayList<>();
        pillarCoverage.add(BitSet.valueOf(new byte[]{(byte) 0b10111001}));  //Säule 1
        pillarCoverage.add(BitSet.valueOf(new byte[]{(byte) 0b01001010}));  //Säule 2
        pillarCoverage.add(BitSet.valueOf(new byte[]{(byte) 0b11000001}));  // ...
        pillarCoverage.add(BitSet.valueOf(new byte[]{(byte) 0b00010011}));
        pillarCoverage.add(BitSet.valueOf(new byte[]{(byte) 0b10101100}));
        pillarCoverage.add(BitSet.valueOf(new byte[]{(byte) 0b01010100}));
        pillarCoverage.add(BitSet.valueOf(new byte[]{(byte) 0b00101011}));
        pillarCoverage.add(BitSet.valueOf(new byte[]{(byte) 0b00001110}));

        BitSetValidator bsV = new BitSetValidator();
        BitSet testInstance = new BitSet(8);
        testInstance.set(1);
        testInstance.set(5);
        testInstance.set(7);
        testInstance.set(8);
        int score = bsV.validateAbs(testInstance,pillarCoverage);
        Assertions.assertEquals(score,5);
    }

    @Test
    void newOptimumTestFalse(){
        //Welche Säule deckt welche Person ab?
        ArrayList<BitSet> pillarCoverage = new ArrayList<>();
        pillarCoverage.add(BitSet.valueOf(new byte[]{(byte) 0b10111001}));  //Säule 1
        pillarCoverage.add(BitSet.valueOf(new byte[]{(byte) 0b01001010}));  //Säule 2
        pillarCoverage.add(BitSet.valueOf(new byte[]{(byte) 0b11000001}));  // ...
        pillarCoverage.add(BitSet.valueOf(new byte[]{(byte) 0b00010011}));
        pillarCoverage.add(BitSet.valueOf(new byte[]{(byte) 0b10101100}));
        pillarCoverage.add(BitSet.valueOf(new byte[]{(byte) 0b01010100}));
        pillarCoverage.add(BitSet.valueOf(new byte[]{(byte) 0b00101011}));
        pillarCoverage.add(BitSet.valueOf(new byte[]{(byte) 0b00001110}));

        //Welche Säule hat welches Gewicht?
        ArrayList<Integer> pillarScore = new ArrayList<>();
        pillarScore.add(2);     //Säule1
        pillarScore.add(0);     //Säule2
        pillarScore.add(1);     //...
        pillarScore.add(-1);
        pillarScore.add(-1);
        pillarScore.add(1);
        pillarScore.add(2);
        pillarScore.add(1);

        BitSet candidateBitSet = new BitSet(8);
        candidateBitSet.set(0);
        candidateBitSet.set(5);
        candidateBitSet.set(7);
        candidateBitSet.set(8);
        Tabu_Plus_BitSet candidate = new Tabu_Plus_BitSet(new TabuElement(-1,-1,-1), candidateBitSet);

        BitSet currentOptimum = new BitSet(8);
        currentOptimum.set(0);
        currentOptimum.set(2);
        currentOptimum.set(7);
        currentOptimum.set(8);

        BitSetValidator bsV = new BitSetValidator();
        boolean newOptimum = !bsV.newOptimum(currentOptimum, candidate, pillarCoverage,pillarScore);
        Assertions.assertTrue(newOptimum);
    }

    @Test
    void newOptimumTestTrue(){
        //Welche Säule deckt welche Person ab?
        ArrayList<BitSet> pillarCoverage = new ArrayList<>();
        pillarCoverage.add(BitSet.valueOf(new byte[]{(byte) 0b10111001}));  //Säule 1
        pillarCoverage.add(BitSet.valueOf(new byte[]{(byte) 0b01001010}));  //Säule 2
        pillarCoverage.add(BitSet.valueOf(new byte[]{(byte) 0b11000001}));  // ...
        pillarCoverage.add(BitSet.valueOf(new byte[]{(byte) 0b00010011}));
        pillarCoverage.add(BitSet.valueOf(new byte[]{(byte) 0b10101100}));
        pillarCoverage.add(BitSet.valueOf(new byte[]{(byte) 0b01010100}));
        pillarCoverage.add(BitSet.valueOf(new byte[]{(byte) 0b00101011}));
        pillarCoverage.add(BitSet.valueOf(new byte[]{(byte) 0b00001110}));

        //Welche Säule hat welches Gewicht?
        ArrayList<Integer> pillarScore = new ArrayList<>();
        pillarScore.add(2);     //Säule1
        pillarScore.add(0);     //Säule2
        pillarScore.add(1);     //...
        pillarScore.add(-1);
        pillarScore.add(-1);
        pillarScore.add(1);
        pillarScore.add(2);
        pillarScore.add(1);

        BitSet candidateBitSet = new BitSet(8);
        candidateBitSet.set(0);
        candidateBitSet.set(5);
        candidateBitSet.set(6);
        candidateBitSet.set(8);
        Tabu_Plus_BitSet candidate = new Tabu_Plus_BitSet(new TabuElement(-1,-1,-1), candidateBitSet);

        BitSet currentOptimum = new BitSet(8);
        currentOptimum.set(0);
        currentOptimum.set(2);
        currentOptimum.set(7);
        currentOptimum.set(8);

        BitSetValidator bsV = new BitSetValidator();
        boolean newOptimum = bsV.newOptimum(currentOptimum, candidate, pillarCoverage,pillarScore);
        Assertions.assertTrue(newOptimum);
    }

    @Test
    void findTopCandidateTest(){
        //Welche Säule deckt welche Person ab?
        ArrayList<BitSet> pillarCoverage = new ArrayList<>();
        pillarCoverage.add(BitSet.valueOf(new byte[]{(byte) 0b10111001}));  //Säule 1
        pillarCoverage.add(BitSet.valueOf(new byte[]{(byte) 0b01001010}));  //Säule 2
        pillarCoverage.add(BitSet.valueOf(new byte[]{(byte) 0b11000001}));  // ...
        pillarCoverage.add(BitSet.valueOf(new byte[]{(byte) 0b00010011}));
        pillarCoverage.add(BitSet.valueOf(new byte[]{(byte) 0b10101100}));
        pillarCoverage.add(BitSet.valueOf(new byte[]{(byte) 0b01010100}));
        pillarCoverage.add(BitSet.valueOf(new byte[]{(byte) 0b00101011}));
        pillarCoverage.add(BitSet.valueOf(new byte[]{(byte) 0b00001110}));

        //Welche Säule hat welches Gewicht?
        ArrayList<Integer> pillarScore = new ArrayList<>();
        pillarScore.add(2);     //Säule1
        pillarScore.add(0);     //Säule2
        pillarScore.add(1);     //...
        pillarScore.add(-1);
        pillarScore.add(-1);
        pillarScore.add(1);
        pillarScore.add(2);
        pillarScore.add(1);

        ArrayList<Tabu_Plus_BitSet> testNeighbourhood = new ArrayList<>();
        BitSet ref1 = new BitSet(8);
        ref1.set(1);
        ref1.set(5);
        ref1.set(7);
        ref1.set(8);
        testNeighbourhood.add(new Tabu_Plus_BitSet(new TabuElement(0,1,-1),ref1));

        BitSet ref2 = new BitSet(8);
        ref2.set(2);
        ref2.set(5);
        ref2.set(7);
        ref2.set(8);
        testNeighbourhood.add(new Tabu_Plus_BitSet(new TabuElement(0,2,-1),ref2));

        BitSet ref3 = new BitSet(8);
        ref3.set(3);
        ref3.set(5);
        ref3.set(7);
        ref3.set(8);
        testNeighbourhood.add(new Tabu_Plus_BitSet(new TabuElement(0,3,-1),ref3));

        BitSet ref4 = new BitSet(8);
        ref4.set(4);
        ref4.set(5);
        ref4.set(7);
        ref4.set(8);
        testNeighbourhood.add(new Tabu_Plus_BitSet(new TabuElement(0,4,-1),ref4));

        BitSet ref5 = new BitSet(8);
        ref5.set(5);
        ref5.set(6);
        ref5.set(7);
        ref5.set(8);
        testNeighbourhood.add(new Tabu_Plus_BitSet(new TabuElement(0,6,-1),ref5));

        BitSet ref6 = new BitSet(8);
        ref6.set(0);
        ref6.set(1);
        ref6.set(7);
        ref6.set(8);
        testNeighbourhood.add(new Tabu_Plus_BitSet(new TabuElement(1,5,-1),ref6));

        BitSet ref7 = new BitSet(8);
        ref7.set(0);
        ref7.set(3);
        ref7.set(7);
        ref7.set(8);
        testNeighbourhood.add(new Tabu_Plus_BitSet(new TabuElement(3,5,-1),ref7));

        BitSet ref8 = new BitSet(8);
        ref8.set(0);
        ref8.set(4);
        ref8.set(7);
        ref8.set(8);
        testNeighbourhood.add(new Tabu_Plus_BitSet(new TabuElement(4,5,-1),ref8));

        BitSet ref9 = new BitSet(8);
        ref9.set(0);
        ref9.set(6);
        ref9.set(7);
        ref9.set(8);
        testNeighbourhood.add(new Tabu_Plus_BitSet(new TabuElement(5,6,-1),ref9));

        BitSet ref10 = new BitSet(8);
        ref10.set(0);
        ref10.set(2);
        ref10.set(5);
        ref10.set(8);
        testNeighbourhood.add(new Tabu_Plus_BitSet(new TabuElement(2,7,-1),ref10));

        BitSet ref11 = new BitSet(8);
        ref11.set(0);
        ref11.set(3);
        ref11.set(5);
        ref11.set(8);
        testNeighbourhood.add(new Tabu_Plus_BitSet(new TabuElement(3,7,-1),ref11));

        BitSet ref12 = new BitSet(8);
        ref12.set(0);
        ref12.set(4);
        ref12.set(5);
        ref12.set(8);
        testNeighbourhood.add(new Tabu_Plus_BitSet(new TabuElement(4,7,-1),ref12));

        BitSet ref13 = new BitSet(8);
        ref13.set(0);
        ref13.set(5);
        ref13.set(6);
        ref13.set(8);
        testNeighbourhood.add(new Tabu_Plus_BitSet(new TabuElement(6,7,-1),ref13));


        BitSetValidator bsV = new BitSetValidator();
        BitSet referenceBitSet = new BitSet(8);
        referenceBitSet.set(0);
        referenceBitSet.set(5);
        referenceBitSet.set(6);
        referenceBitSet.set(8);
        Tabu_Plus_BitSet reference = new Tabu_Plus_BitSet(new TabuElement(6,7,-1),referenceBitSet);
        Tabu_Plus_BitSet result = bsV.findTopCandidate(testNeighbourhood,pillarCoverage,pillarScore);
        Assertions.assertEquals(result,reference);
    }

    @Test
    public void isGeneralizationTest(){
        BitSet one = new BitSet(17);
        one.set(16);
        one.set(0,10);

        BitSet two = new BitSet(17);
        two.set(16);
        two.set(2,8);

        BitSetValidator bsv = new BitSetValidator();

        Assertions.assertTrue(!bsv.isGeneralisationOf(two,one));
        Assertions.assertTrue(bsv.isGeneralisationOf(one,two));
    }
    @Test
    public void isNoGeneralizationTest(){
        BitSet one = new BitSet(9);
        one.set(9);
        one.set(0,4);

        BitSet two = new BitSet(9);
        two.set(9);
        two.set(2,7);

        BitSetValidator bsv = new BitSetValidator();

        Assertions.assertTrue(!bsv.isGeneralisationOf(two,one));
        Assertions.assertTrue(!bsv.isGeneralisationOf(one,two));
    }

    @Test
    public void topDownPruningTest1(){
        //TODO FIX THIS TEST
        ArrayList<BitSet> originalLevel = new ArrayList<>();
        BitSet og1 = new BitSet();
        og1.set(0);
        og1.set(1);
        og1.set(2);
        og1.set(8);
        originalLevel.add(og1);

        ArrayList<BitSet> nextLevel = new ArrayList<>();
        BitSet bs1 = new BitSet();
        bs1.set(0);
        bs1.set(1);
        bs1.set(8);
        nextLevel.add(bs1);
        BitSet bs2 = new BitSet();
        bs2.set(0);
        bs2.set(2);
        bs2.set(8);
        nextLevel.add(bs2);
        BitSet bs3 = new BitSet();
        bs3.set(1);
        bs3.set(2);
        bs3.set(8);
        nextLevel.add(bs3);
        BitSet bs4 = new BitSet();
        bs4.set(0);
        bs4.set(6);
        bs4.set(8);
        nextLevel.add(bs4);

        BitSetValidator bsv = new BitSetValidator();

        ArrayList<BitSet> testingInstance = bsv.topDownPruning(nextLevel,originalLevel);


        nextLevel.remove(3);

        Assertions.assertEquals(testingInstance,nextLevel);
    }


    @Test
    public void topDownPruningTest2(){
        ArrayList<BitSet> originalLevel = new ArrayList<>();
        BitSet testingBits = new BitSet();
        testingBits.set(1);
        testingBits.set(5);
        testingBits.set(6);
        testingBits.set(7);

        originalLevel.add(testingBits);

        ArrayList<BitSet> nextLevel = new ArrayList<>();

        BitSet bs1 = new BitSet();
        bs1.set(0);
        bs1.set(1);
        bs1.set(7);
        nextLevel.add(bs1);  //1
        BitSet bs2 = new BitSet();
        bs2.set(0);
        bs2.set(2);
        bs2.set(7);
        nextLevel.add(bs2);  //2
        BitSet bs3 = new BitSet();
        bs3.set(0);
        bs3.set(3);
        bs3.set(7);
        nextLevel.add(bs3);  //3
        BitSet bs4 = new BitSet();
        bs4.set(0);
        bs4.set(4);
        bs4.set(7);
        nextLevel.add(bs4);  //4
        BitSet bs5 = new BitSet();
        bs5.set(0);
        bs5.set(5);
        bs5.set(7);
        nextLevel.add(bs5);  //5
        BitSet bs6 = new BitSet();
        bs6.set(0);
        bs6.set(6);
        bs6.set(7);
        nextLevel.add(bs6);  //6
        BitSet bs7 = new BitSet();
        bs7.set(1);
        bs7.set(2);
        bs7.set(7);
        nextLevel.add(bs7);  //7
        BitSet bs8 = new BitSet();
        bs8.set(1);
        bs8.set(3);
        bs8.set(7);
        nextLevel.add(bs8);  //8
        BitSet bs9 = new BitSet();
        bs9.set(1);
        bs9.set(4);
        bs9.set(7);
        nextLevel.add(bs9);  //9
        BitSet bs10 = new BitSet();
        bs10.set(1);
        bs10.set(5);
        bs10.set(7);
        nextLevel.add(bs10);  //10
        BitSet bs11 = new BitSet();
        bs11.set(1);
        bs11.set(6);
        bs11.set(7);
        nextLevel.add(bs11);  //11
        BitSet bs12 = new BitSet();
        bs12.set(2);
        bs12.set(3);
        bs12.set(7);
        nextLevel.add(bs12);  //12
        BitSet bs13 = new BitSet();
        bs13.set(2);
        bs13.set(4);
        bs13.set(7);
        nextLevel.add(bs13);  //13
        BitSet bs14 = new BitSet();
        bs14.set(2);
        bs14.set(5);
        bs14.set(7);
        nextLevel.add(bs14);  //14
        BitSet bs15 = new BitSet();
        bs15.set(2);
        bs15.set(6);
        bs15.set(7);
        nextLevel.add(bs15);  //15
        BitSet bs16 = new BitSet();
        bs16.set(3);
        bs16.set(4);
        bs16.set(7);
        nextLevel.add(bs16);  //16
        BitSet bs17 = new BitSet();
        bs17.set(3);
        bs17.set(5);
        bs17.set(7);
        nextLevel.add(bs17);  //17
        BitSet bs18 = new BitSet();
        bs18.set(3);
        bs18.set(6);
        bs18.set(7);
        nextLevel.add(bs18);  //18
        BitSet bs19 = new BitSet();
        bs19.set(4);
        bs19.set(5);
        bs19.set(7);
        nextLevel.add(bs19);  //19
        BitSet bs20 = new BitSet();
        bs20.set(4);
        bs20.set(6);
        bs20.set(7);
        nextLevel.add(bs20);  //20
        BitSet bs21 = new BitSet();
        bs21.set(5);
        bs21.set(6);
        bs21.set(7);
        nextLevel.add(bs21);  //21


        BitSetValidator bsv = new BitSetValidator();

        ArrayList<BitSet> testingInstance = bsv.topDownPruning(nextLevel,originalLevel);

        Assertions.assertEquals(testingInstance,new ArrayList<>(Arrays.asList(nextLevel.get(9), nextLevel.get(10), nextLevel.get(20))));
    }

    @Test
    void bottomUpPruningTest1(){
        BitSetValidator bsv = new BitSetValidator();

        BitSet validPrev = new BitSet();
        validPrev.set(0);
        validPrev.set(1);
        validPrev.set(7);
        ArrayList<BitSet> prev = new ArrayList<>();
        prev.add(validPrev);

        ArrayList<BitSet> reference = new ArrayList<>();

        BitSet bs1 = new BitSet();
        bs1.set(0);
        bs1.set(1);
        bs1.set(2);
        bs1.set(7);
        reference.add(bs1);

        BitSet bs2 = new BitSet();
        bs2.set(0);
        bs2.set(1);
        bs2.set(3);
        bs2.set(7);
        reference.add(bs2);

        BitSet bs3 = new BitSet();
        bs3.set(0);
        bs3.set(1);
        bs3.set(4);
        bs3.set(7);
        reference.add(bs3);

        BitSet bs4 = new BitSet();
        bs4.set(0);
        bs4.set(1);
        bs4.set(5);
        bs4.set(7);
        reference.add(bs4);

        BitSet bs5 = new BitSet();
        bs5.set(0);
        bs5.set(1);
        bs5.set(6);
        bs5.set(7);
        reference.add(bs5);

        BitSet bs6 = new BitSet();
        bs6.set(4);
        bs6.set(5);
        bs6.set(6);
        bs6.set(7);
        reference.add(bs6);

        BitSet bs7 = new BitSet();
        bs7.set(2);
        bs7.set(5);
        bs7.set(6);
        bs7.set(7);
        reference.add(bs7);

        ArrayList<BitSet> testingInstance = bsv.bottomUpPruning(reference,prev);

        reference.remove(6);
        reference.remove(5);

        Assertions.assertEquals(reference,testingInstance);
    }

    /**
     * Hier wollen wir fest stellen, ob die Spezialisierungs-
     * Erkennung auch über mehrere Ebenen funktioniert
     */
    @Test
    void bottomUpPruningTest2(){
        BitSetValidator bsv = new BitSetValidator();

        BitSet validPrev = new BitSet();
        validPrev.set(0);
        validPrev.set(7);
        ArrayList<BitSet> prev = new ArrayList<>();
        prev.add(validPrev);

        ArrayList<BitSet> reference = new ArrayList<>();

        BitSet bs1 = new BitSet();
        bs1.set(0);
        bs1.set(4);
        bs1.set(5);
        bs1.set(7);
        reference.add(bs1);

        BitSet bs2 = new BitSet();
        bs2.set(0);
        bs2.set(3);
        bs2.set(7);
        reference.add(bs2);

        BitSet bs3 = new BitSet();
        bs3.set(0);
        bs3.set(1);
        bs3.set(2);
        bs3.set(4);
        bs3.set(7);
        reference.add(bs3);

        BitSet bs4 = new BitSet();
        bs4.set(0);
        bs4.set(1);
        bs4.set(5);
        bs4.set(7);
        reference.add(bs4);

        BitSet bs5 = new BitSet();
        bs5.set(0);
        bs5.set(1);
        bs5.set(2);
        bs5.set(3);
        bs5.set(6);
        bs5.set(7);
        reference.add(bs5);

        BitSet bs6 = new BitSet();
        bs6.set(4);
        bs6.set(5);
        bs6.set(6);
        bs6.set(7);
        reference.add(bs6);

        BitSet bs7 = new BitSet();
        bs7.set(2);
        bs7.set(5);
        bs7.set(7);
        reference.add(bs7);

        ArrayList<BitSet> testingInstance = bsv.bottomUpPruning(reference,prev);

        reference.remove(6);
        reference.remove(5);

        Assertions.assertEquals(reference,testingInstance);
    }

    @Test
    void equalityPruningTest(){
        BitSetValidator bsv = new BitSetValidator();
        ArrayList<BitSet> original = new ArrayList<>();
        BitSet obs1 = new BitSet();
        obs1.set(0);
        obs1.set(1);
        obs1.set(7);
        original.add(obs1);

        ArrayList<BitSet> toBePruned = new ArrayList<>();
        BitSet bs1 = new BitSet();
        bs1.set(0);
        bs1.set(1);
        bs1.set(2);
        bs1.set(7);
        toBePruned.add(bs1);
        BitSet bs2 = new BitSet();
        bs2.set(0);
        bs2.set(7);
        toBePruned.add(bs2);
        BitSet bs3 = new BitSet();
        bs3.set(0);
        bs3.set(1);
        bs3.set(7);
        toBePruned.add(bs3);
        BitSet bs4 = new BitSet();
        bs4.set(3);
        bs4.set(4);
        bs4.set(5);
        bs4.set(7);
        toBePruned.add(bs4);

        ArrayList<BitSet> testingInstance = bsv.equalityPruning(toBePruned,original);
        toBePruned.remove(3);
        toBePruned.remove(1);
        toBePruned.remove(0);

        Assertions.assertEquals(toBePruned,original);

    }

    @Test
    void newOptimumRelTest(){
        BitSetValidator bsv = new BitSetValidator();

        ArrayList<BitSet> pillarCoverage = new ArrayList<>();
        ArrayList<Integer> pillarScore = new ArrayList<>();

        BitSet pc1 = new BitSet();
        pc1.set(0);
        pc1.set(1);
        pc1.set(2);
        pc1.set(3);
        pc1.set(7);
        pillarCoverage.add(pc1);
        pillarScore.add(0);

        BitSet pc2 = new BitSet();
        pc2.set(0);
        pc2.set(4);
        pc2.set(5);
        pc2.set(6);
        pc2.set(7);
        pillarCoverage.add(pc2);
        pillarScore.add(0);

        BitSet pc3 = new BitSet();
        pc3.set(0);
        pc3.set(7);
        pillarCoverage.add(pc3);
        pillarScore.add(3);

        BitSet candidat = new BitSet();
        candidat.set(0);
        candidat.set(1);
        candidat.set(7);

        BitSet optimum = new BitSet();
        optimum.set(2);
        optimum.set(7);

        Assertions.assertTrue(!bsv.newOptimumRel(optimum,candidat,pillarCoverage,pillarScore));
        Assertions.assertTrue(bsv.newOptimumRel(candidat,optimum,pillarCoverage,pillarScore));
    }

}