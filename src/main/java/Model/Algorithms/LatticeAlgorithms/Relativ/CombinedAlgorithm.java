package Model.Algorithms.LatticeAlgorithms.Relativ;

import Model.Algorithms.Algorithm;
import Model.Algorithms.LatticeAlgorithms.General.BitSetValidator;
import Model.Components.Person;
import Model.Components.Pillar;

import java.util.ArrayList;
import java.util.BitSet;

public class CombinedAlgorithm implements Algorithm {
    private BottomUpAlgorithm bua;
    private TopDownAlgorithm tda;
    private RandomWalkAlgorithm rwa;

    public CombinedAlgorithm(int minLevel, int maxLevel, int timer){
    bua = new BottomUpAlgorithm(minLevel);
    tda = new TopDownAlgorithm(maxLevel);
    rwa = new RandomWalkAlgorithm(minLevel,maxLevel,timer);
    }


    @Override
    public ArrayList<Pillar> execute(ArrayList<Pillar> pillars, ArrayList<Person> people, boolean ABS_REL, int goal) {
        BitSetValidator bsv = new BitSetValidator();

        bua.initAlgo(pillars, people, goal);
        tda.initAlgo(pillars, people, goal);
        rwa.initAlgo(pillars,people,goal);

        BitSet optimum = new BitSet();
        optimum.set(0, pillars.size()+1);
        while(true){
            if(!bua.algoEnded){
                bua.nextStep();
                if(bua.optimumNode!=null){
                    optimum = bua.optimumNode;
                    break;
                }
            }
            if(!tda.algoEnded){
                tda.nextStep();
                if(tda.optimumNode!=null) {
                    if (bsv.newOptimumRel(optimum, tda.optimumNode, tda.pillarCoverage, tda.pillarScore)) {
                        optimum = tda.optimumNode;
                    }
                }
            }
            if(tda.algoEnded&& bua.algoEnded){
                rwa.timerActivated = true;
            }
            if(!rwa.algoEnded){
                rwa.nextStep();
                if(rwa.optimumNode!=null) {
                    if (bsv.newOptimumRel(optimum, rwa.optimumNode, rwa.pillarCoverage, rwa.pillarScore)) {
                        optimum = rwa.optimumNode;
                    }
                }
                if(rwa.foundValidNode){
                    tda.algoEnded = true;
                }
            }else{
                break;
            }
            ArrayList<Pillar> result = new ArrayList<>();
            for(int i = 0; i<pillars.size();i++){
                if(optimum.get(i)){
                    result.add(pillars.get(i));
                }
            }
            return result;

        }


        return null;
    }
}
