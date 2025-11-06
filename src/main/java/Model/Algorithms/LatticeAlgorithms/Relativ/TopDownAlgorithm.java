package Model.Algorithms.LatticeAlgorithms.Relativ;

import Model.Algorithms.Algorithm;
import Model.Algorithms.LatticeAlgorithms.General.BitSetBuilder;
import Model.Algorithms.LatticeAlgorithms.General.BitSetValidator;
import Model.Components.Person;
import Model.Components.Pillar;

import java.util.ArrayList;
import java.util.BitSet;

public class TopDownAlgorithm implements Algorithm {
    private final BitSetBuilder bsb;
    private final BitSetValidator bsv;
    private int currentLevelIndex;
    private ArrayList<BitSet> invalidPreviousLevel;
    private ArrayList<BitSet> validPreviousLevel;
    private final int minLevel;
    private int pillarCount;
    private int minCoverage;
    private ArrayList<BitSet> pillarCoverage;
    private ArrayList<Integer> pillarScore;
    private BitSet optimumNode = null;

    private boolean algoEnded = false;

    public TopDownAlgorithm(int minLevel){
        this.minLevel = minLevel;
        this.invalidPreviousLevel = new ArrayList<>();
        this.validPreviousLevel = new ArrayList<>();
        bsb = new BitSetBuilder();
        bsv = new BitSetValidator();
    }

    /**
     * Method to initialize the Algorithm
     * @param pillars Input Pillars
     * @param people Input People
     * @param goal goal coverage
     */
    public void initAlgo(ArrayList<Pillar> pillars, ArrayList<Person> people, int goal){
        this.pillarCount = pillars.size();
        currentLevelIndex=pillarCount+1;
        this.minCoverage = ((int)Math.ceil((people.size()/100.0)*goal));
        this.pillarCoverage = new ArrayList<>();
        this.pillarScore = new ArrayList<>();
        for(Pillar p : pillars){
            this.pillarScore.add(p.getShadow().getValue());
            this.pillarCoverage.add(p.getPeopleReached());
        }
    }

    @Override
    public ArrayList<Pillar> execute(ArrayList<Pillar> pillars, ArrayList<Person> people, boolean ABS_REL, int goal) {
        initAlgo(pillars,people,goal);
        while(!this.algoEnded){
            nextStep();
            int debug = 0;
        }
        ArrayList<Pillar> result = new ArrayList<>();
        if(this.optimumNode!=null){
            for(int i = 0; i<pillars.size();i++){
                if(this.optimumNode.get(i)){
                    result.add(pillars.get(i));
                }
            }
        }
        return result;
    }

    public void nextStep(){
        currentLevelIndex--;
        if(currentLevelIndex < this.minLevel){
            this.algoEnded = true;
            return;
        }
        ArrayList<BitSet> currentLevel = bsb.buildLevelK(currentLevelIndex,pillarCount);
        ArrayList<BitSet> invalidCurrentLevel = new ArrayList<>();
        ArrayList<BitSet> validCurrentLevel = new ArrayList<>();
        // TOP DOWN PRUNING
        if(!invalidPreviousLevel.isEmpty()){
            ArrayList<BitSet> temp = bsv.topDownPruning(currentLevel, this.invalidPreviousLevel);
            invalidCurrentLevel.addAll(temp);
            currentLevel.removeAll(invalidCurrentLevel);
        }
        for(BitSet bs: currentLevel){
            if(bsv.validateAbs(bs,pillarCoverage) >= minCoverage){
                validCurrentLevel.add(bs);
            }else{
                invalidCurrentLevel.add(bs);
            }
        }
        if(validCurrentLevel.isEmpty()){
            this.algoEnded=true;
            int highScore = -999999;
            BitSet optimum = new BitSet();
            for(BitSet bs : validPreviousLevel){
                int score = bsv.validateScore(bs,pillarScore);
                if(score > highScore){
                    optimum = bs;
                    highScore = score;
                }
            }
            this.optimumNode = optimum;
        }else{
            this.validPreviousLevel.clear();
            this.validPreviousLevel.addAll(validCurrentLevel);

            this.invalidPreviousLevel.clear();
            this.invalidPreviousLevel.addAll(invalidCurrentLevel);
        }
    }
}
