package Model.Algorithms.LatticeAlgorithms.Relativ;

import Model.Algorithms.Algorithm;
import Model.Components.Person;
import Model.Components.Pillar;

import java.util.ArrayList;

public class CombinedAlgorithm implements Algorithm {
    private BottomUpAlgorithm bua;
    private TopDownAlgorithm tda;
    private RandomWalkAlgorithm rwa;

    //TODO I M P L E M E N T A T I O N
    public CombinedAlgorithm(int minLevel, int maxLevel, int timer){

    }


    @Override
    public ArrayList<Pillar> execute(ArrayList<Pillar> pillars, ArrayList<Person> people, boolean ABS_REL, int goal) {
        return null;
    }
}
