package Model.Algorithms.LatticeAlgorithms;

import Model.Algorithms.Algorithm;
import Model.Algorithms.LatticeAlgorithms.Absolut.TabuSearch;
import Model.Components.Person;
import Model.Components.Pillar;
import Model.Session;

import java.io.PipedInputStream;
import java.util.ArrayList;
import java.util.BitSet;

public class LatticeAlgorithm implements Algorithm {
    @Override
    public ArrayList<Pillar> execute(ArrayList<Pillar> pillars, ArrayList<Person> people, boolean ABS_REL, int goal) {
        if(ABS_REL){
            TabuSearch ts = new TabuSearch();
            return ts.execute(goal, Session.getInstance().getAlgorithmParameters().get(0),Session.getInstance().getAlgorithmParameters().get(0),pillars);
        }



        return null;
    }
}
