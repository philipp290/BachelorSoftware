package Model.Algorithms.LatticeAlgorithms.Absolut;

import Model.Components.Pillar;
import Model.Components.Shadow;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.BitSet;

import static org.junit.jupiter.api.Assertions.*;

class TabuSearchTest {
    //Referenz Ergebnis wurde händisch berechnet
    //-> Im Detail zu sehen in: Data/Powerpoint-Visualisierungen/AbsoluterFall-Visualisierung.pptx
    @Test
    void executeTest(){
        ArrayList<Pillar> pillars = new ArrayList<>();
        pillars.add(new Pillar(-1,-1,-1, Shadow.KEIN));
        pillars.get(0).setPeopleReached(BitSet.valueOf(new byte[]{(byte) 0b10111001}));

        pillars.add(new Pillar(-1,-1,-1, Shadow.MITTEL));
        pillars.get(1).setPeopleReached(BitSet.valueOf(new byte[]{(byte) 0b01001010}));

        pillars.add(new Pillar(-1,-1,-1, Shadow.NIEDRIG));
        pillars.get(2).setPeopleReached(BitSet.valueOf(new byte[]{(byte) 0b11000001}));

        pillars.add(new Pillar(-1,-1,-1, Shadow.HOCH));
        pillars.get(3).setPeopleReached(BitSet.valueOf(new byte[]{(byte) 0b00010011}));

        pillars.add(new Pillar(-1,-1,-1, Shadow.HOCH));
        pillars.get(4).setPeopleReached(BitSet.valueOf(new byte[]{(byte) 0b10101100}));

        pillars.add(new Pillar(-1,-1,-1, Shadow.NIEDRIG));
        pillars.get(5).setPeopleReached(BitSet.valueOf(new byte[]{(byte) 0b01010100}));

        pillars.add(new Pillar(-1,-1,-1, Shadow.KEIN));
        pillars.get(6).setPeopleReached(BitSet.valueOf(new byte[]{(byte) 0b00101011}));

        pillars.add(new Pillar(-1,-1,-1, Shadow.NIEDRIG));
        pillars.get(7).setPeopleReached(BitSet.valueOf(new byte[]{(byte) 0b00001110}));


        BitSet reference = new BitSet(8);
        reference.set(0);
        reference.set(5);
        reference.set(6);
        reference.set(8);

        TabuSearch bsTs = new TabuSearch();
        ArrayList<Pillar> testInstance = bsTs.execute(3,2,3, pillars);

        pillars.remove(7);
        pillars.remove(4);
        pillars.remove(3);
        pillars.remove(2);
        pillars.remove(1);

        Assertions.assertEquals(testInstance,pillars);
    }



    @Test
    void tabuListUpdateTest(){
        ArrayList<TabuElement> tabuListe = new ArrayList<>();
        tabuListe.add(new TabuElement(1,1,5));
        tabuListe.add(new TabuElement(2,2,1));

        TabuSearch bstS = new TabuSearch();
        bstS.tabuListUpdate(tabuListe);

        ArrayList<TabuElement> refTabuListe = new ArrayList<>();
        refTabuListe.add(new TabuElement(1,1,4));

        Assertions.assertEquals(tabuListe,refTabuListe);
    }


}