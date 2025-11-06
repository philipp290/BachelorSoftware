package Model.Algorithms.LatticeAlgorithms.Absolut;

import Model.Algorithms.LatticeAlgorithms.General.BitSetBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.BitSet;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Die Referenz Daten sind aus dem Schritt für Schritt berechnetem Beispiel
 * Aus der Powerpoint "BachelorPräsentation15.10"
 */
class BitSetBuilderTest {
    @Test
    void startingInstanceTest() {
        BitSetBuilder bsb = new BitSetBuilder();

        BitSet reference = new BitSet(8);
        reference.set(0, 3);
        reference.set(8);

        BitSet testInstance = bsb.buildStartInstance(3, 8);
        assertEquals(testInstance, reference);
    }

    @Test
    void bitSwapTest() {
        BitSetBuilder bsb = new BitSetBuilder();

        BitSet reference = new BitSet(8);
        reference.set(1);
        reference.set(0);
        reference.set(5);

        BitSet testInstance = new BitSet(8);
        testInstance.set(0, 3);
        testInstance = bsb.bitSwap(testInstance, new TabuElement(2, 5, -1));

        assertEquals(testInstance, reference);
    }

    @Test
    void candidatesWithoutTabuListTest() {
        BitSetBuilder bsb = new BitSetBuilder();
        ArrayList<TabuElement> tabuListe = new ArrayList<>();

        ArrayList<Tabu_Plus_BitSet> referenceNeighbourhood = new ArrayList<>();
        BitSet ref1 = new BitSet(8);
        ref1.set(1);
        ref1.set(2);
        ref1.set(3);
        ref1.set(8);
        referenceNeighbourhood.add(new Tabu_Plus_BitSet(new TabuElement(0, 3, -1), ref1));

        BitSet ref2 = new BitSet(8);
        ref2.set(1);
        ref2.set(2);
        ref2.set(4);
        ref2.set(8);
        referenceNeighbourhood.add(new Tabu_Plus_BitSet(new TabuElement(0, 4, -1), ref2));

        BitSet ref3 = new BitSet(8);
        ref3.set(1);
        ref3.set(2);
        ref3.set(5);
        ref3.set(8);
        referenceNeighbourhood.add(new Tabu_Plus_BitSet(new TabuElement(0, 5, -1), ref3));

        BitSet ref4 = new BitSet(8);
        ref4.set(1);
        ref4.set(2);
        ref4.set(6);
        ref4.set(8);
        referenceNeighbourhood.add(new Tabu_Plus_BitSet(new TabuElement(0, 6, -1), ref4));

        BitSet ref5 = new BitSet(8);
        ref5.set(1);
        ref5.set(2);
        ref5.set(7);
        ref5.set(8);
        referenceNeighbourhood.add(new Tabu_Plus_BitSet(new TabuElement(0, 7, -1), ref5));

        BitSet ref6 = new BitSet(8);
        ref6.set(0);
        ref6.set(2);
        ref6.set(3);
        ref6.set(8);
        referenceNeighbourhood.add(new Tabu_Plus_BitSet(new TabuElement(1, 3, -1), ref6));

        BitSet ref7 = new BitSet(8);
        ref7.set(0);
        ref7.set(2);
        ref7.set(4);
        ref7.set(8);
        referenceNeighbourhood.add(new Tabu_Plus_BitSet(new TabuElement(1, 4, -1), ref7));

        BitSet ref8 = new BitSet(8);
        ref8.set(0);
        ref8.set(2);
        ref8.set(5);
        ref8.set(8);
        referenceNeighbourhood.add(new Tabu_Plus_BitSet(new TabuElement(1, 5, -1), ref8));

        BitSet ref9 = new BitSet(8);
        ref9.set(0);
        ref9.set(2);
        ref9.set(6);
        ref9.set(8);
        referenceNeighbourhood.add(new Tabu_Plus_BitSet(new TabuElement(1, 6, -1), ref9));

        BitSet ref10 = new BitSet(8);
        ref10.set(0);
        ref10.set(2);
        ref10.set(7);
        ref10.set(8);
        referenceNeighbourhood.add(new Tabu_Plus_BitSet(new TabuElement(1, 7, -1), ref10));

        BitSet ref11 = new BitSet(8);
        ref11.set(0);
        ref11.set(1);
        ref11.set(3);
        ref11.set(8);
        referenceNeighbourhood.add(new Tabu_Plus_BitSet(new TabuElement(2, 3, -1), ref11));

        BitSet ref12 = new BitSet(8);
        ref12.set(0);
        ref12.set(1);
        ref12.set(4);
        ref12.set(8);
        referenceNeighbourhood.add(new Tabu_Plus_BitSet(new TabuElement(2, 4, -1), ref12));

        BitSet ref13 = new BitSet(8);
        ref13.set(0);
        ref13.set(1);
        ref13.set(5);
        ref13.set(8);
        referenceNeighbourhood.add(new Tabu_Plus_BitSet(new TabuElement(2, 5, -1), ref13));

        BitSet ref14 = new BitSet(8);
        ref14.set(0);
        ref14.set(1);
        ref14.set(6);
        ref14.set(8);
        referenceNeighbourhood.add(new Tabu_Plus_BitSet(new TabuElement(2, 6, -1), ref14));

        BitSet ref15 = new BitSet(8);
        ref15.set(0);
        ref15.set(1);
        ref15.set(7);
        ref15.set(8);
        referenceNeighbourhood.add(new Tabu_Plus_BitSet(new TabuElement(2, 7, -1), ref15));

        BitSet testInstance = new BitSet(8);
        testInstance.set(0, 3);
        testInstance.set(8);
        ArrayList<Tabu_Plus_BitSet> testNeighbourhood = bsb.generateCandidatesPlus(testInstance, tabuListe, -1);
        Assertions.assertEquals(testNeighbourhood, referenceNeighbourhood);
    }

    @Test
    void candidatesWithTabuListTest() {
        BitSetBuilder bsb = new BitSetBuilder();
        ArrayList<TabuElement> tabuListe = new ArrayList<>();
        tabuListe.add(new TabuElement(1, 7, -1));
        tabuListe.add(new TabuElement(2, 5, -1));

        ArrayList<Tabu_Plus_BitSet> referenceNeighbourhood = new ArrayList<>();
        BitSet ref1 = new BitSet(8);
        ref1.set(1);
        ref1.set(5);
        ref1.set(7);
        ref1.set(8);
        referenceNeighbourhood.add(new Tabu_Plus_BitSet(new TabuElement(0, 1, -1), ref1));

        BitSet ref2 = new BitSet(8);
        ref2.set(2);
        ref2.set(5);
        ref2.set(7);
        ref2.set(8);
        referenceNeighbourhood.add(new Tabu_Plus_BitSet(new TabuElement(0, 2, -1), ref2));

        BitSet ref3 = new BitSet(8);
        ref3.set(3);
        ref3.set(5);
        ref3.set(7);
        ref3.set(8);
        referenceNeighbourhood.add(new Tabu_Plus_BitSet(new TabuElement(0, 3, -1), ref3));

        BitSet ref4 = new BitSet(8);
        ref4.set(4);
        ref4.set(5);
        ref4.set(7);
        ref4.set(8);
        referenceNeighbourhood.add(new Tabu_Plus_BitSet(new TabuElement(0, 4, -1), ref4));

        BitSet ref5 = new BitSet(8);
        ref5.set(6);
        ref5.set(5);
        ref5.set(7);
        ref5.set(8);
        referenceNeighbourhood.add(new Tabu_Plus_BitSet(new TabuElement(0, 6, -1), ref5));

        BitSet ref6 = new BitSet(8);
        ref6.set(0);
        ref6.set(1);
        ref6.set(7);
        ref6.set(8);
        referenceNeighbourhood.add(new Tabu_Plus_BitSet(new TabuElement(1, 5, -1), ref6));

        BitSet ref7 = new BitSet(8);
        ref7.set(0);
        ref7.set(3);
        ref7.set(7);
        ref7.set(8);
        referenceNeighbourhood.add(new Tabu_Plus_BitSet(new TabuElement(3, 5, -1), ref7));

        BitSet ref8 = new BitSet(8);
        ref8.set(0);
        ref8.set(4);
        ref8.set(7);
        ref8.set(8);
        referenceNeighbourhood.add(new Tabu_Plus_BitSet(new TabuElement(4, 5, -1), ref8));

        BitSet ref9 = new BitSet(8);
        ref9.set(0);
        ref9.set(6);
        ref9.set(7);
        ref9.set(8);
        referenceNeighbourhood.add(new Tabu_Plus_BitSet(new TabuElement(5, 6, -1), ref9));

        BitSet ref10 = new BitSet(8);
        ref10.set(0);
        ref10.set(2);
        ref10.set(5);
        ref10.set(8);
        referenceNeighbourhood.add(new Tabu_Plus_BitSet(new TabuElement(2, 7, -1), ref10));

        BitSet ref11 = new BitSet(8);
        ref11.set(0);
        ref11.set(3);
        ref11.set(5);
        ref11.set(8);
        referenceNeighbourhood.add(new Tabu_Plus_BitSet(new TabuElement(3, 7, -1), ref11));

        BitSet ref12 = new BitSet(8);
        ref12.set(0);
        ref12.set(4);
        ref12.set(5);
        ref12.set(8);
        referenceNeighbourhood.add(new Tabu_Plus_BitSet(new TabuElement(4, 7, -1), ref12));

        BitSet ref13 = new BitSet(8);
        ref13.set(0);
        ref13.set(5);
        ref13.set(6);
        ref13.set(8);
        referenceNeighbourhood.add(new Tabu_Plus_BitSet(new TabuElement(6, 7, -1), ref13));

        BitSet testInstance = new BitSet(8);
        testInstance.set(0);
        testInstance.set(5);
        testInstance.set(7);
        testInstance.set(8);
        ArrayList<Tabu_Plus_BitSet> testNeighbourhood = bsb.generateCandidatesPlus(testInstance, tabuListe, -1);
        Assertions.assertEquals(testNeighbourhood, referenceNeighbourhood);
    }

    @Test
    void buildGeneralizationsTest(){
        BitSetBuilder bsb = new BitSetBuilder();
        BitSet original = new BitSet();
        original.set(0);
        original.set(4);
        original.set(5);
        original.set(7);

        ArrayList<BitSet> reference = new ArrayList<>();

        BitSet bs1 = new BitSet();
        bs1.set(4);
        bs1.set(5);
        bs1.set(7);
        reference.add(bs1);

        BitSet bs2 = new BitSet();
        bs2.set(0);
        bs2.set(5);
        bs2.set(7);
        reference.add(bs2);

        BitSet bs3 = new BitSet();
        bs3.set(0);
        bs3.set(4);
        bs3.set(7);
        reference.add(bs3);

        ArrayList<BitSet> testingInstance = bsb.buildGeneralisations(original);

        Assertions.assertEquals(reference,testingInstance);

    }

    @Test
    void buildSpecializatiobsTest(){
        BitSetBuilder bsb = new BitSetBuilder();
        BitSet original = new BitSet();
        original.set(0);
        original.set(4);
        original.set(5);
        original.set(7);

        ArrayList<BitSet> reference = new ArrayList<>();

        BitSet bs1 = new BitSet();
        bs1.set(0);
        bs1.set(1);
        bs1.set(4);
        bs1.set(5);
        bs1.set(7);
        reference.add(bs1);

        BitSet bs2 = new BitSet();
        bs2.set(0);
        bs2.set(2);
        bs2.set(4);
        bs2.set(5);
        bs2.set(7);
        reference.add(bs2);

        BitSet bs3 = new BitSet();
        bs3.set(0);
        bs3.set(3);
        bs3.set(4);
        bs3.set(5);
        bs3.set(7);
        reference.add(bs3);

        BitSet bs4 = new BitSet();
        bs4.set(0);
        bs4.set(4);
        bs4.set(5);
        bs4.set(6);
        bs4.set(7);
        reference.add(bs4);

        ArrayList<BitSet> testingInstance = bsb.buildSpecialisations(original);

        Assertions.assertEquals(reference,testingInstance);

    }

}