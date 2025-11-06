package Model.Algorithms.LatticeAlgorithms;

import Model.Algorithms.Algorithm;
import Model.Algorithms.LatticeAlgorithms.Absolut.TabuSearch;
import Model.Algorithms.LatticeAlgorithms.Relativ.BottomUpAlgorithm;
import Model.Algorithms.LatticeAlgorithms.Relativ.CombinedAlgorithm;
import Model.Algorithms.LatticeAlgorithms.Relativ.RandomWalkAlgorithm;
import Model.Algorithms.LatticeAlgorithms.Relativ.TopDownAlgorithm;
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
        }else{
            int algoType = Session.getInstance().getAlgorithmParameters().get(0);
            if(algoType == 1){
                BottomUpAlgorithm bua = new BottomUpAlgorithm(Session.getInstance().getAlgorithmParameters().get(1));
                return bua.execute(pillars,people,ABS_REL,goal);
            }else if(algoType == 2){
                TopDownAlgorithm tda = new TopDownAlgorithm(Session.getInstance().getAlgorithmParameters().get(1));
                return tda.execute(pillars,people,ABS_REL,goal);
            }else if(algoType == 3){
                RandomWalkAlgorithm rwa = new RandomWalkAlgorithm(Session.getInstance().getAlgorithmParameters().get(1),Session.getInstance().getAlgorithmParameters().get(2),Session.getInstance().getAlgorithmParameters().get(3));
                return rwa.execute(pillars,people,ABS_REL,goal);
            }else if(algoType == 4){
                CombinedAlgorithm ca = new CombinedAlgorithm(Session.getInstance().getAlgorithmParameters().get(1),Session.getInstance().getAlgorithmParameters().get(2),Session.getInstance().getAlgorithmParameters().get(3));
                return ca.execute(pillars,people,ABS_REL,goal);
            }
        }



        return null;
    }
}
