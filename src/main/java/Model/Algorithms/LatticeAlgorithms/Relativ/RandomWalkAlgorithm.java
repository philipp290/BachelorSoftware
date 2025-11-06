package Model.Algorithms.LatticeAlgorithms.Relativ;

import Model.Algorithms.Algorithm;
import Model.Algorithms.LatticeAlgorithms.Absolut.BitSetBuilder;
import Model.Algorithms.LatticeAlgorithms.Absolut.BitSetValidator;
import Model.Components.Person;
import Model.Components.Pillar;

import java.util.ArrayList;
import java.util.BitSet;

public class RandomWalkAlgorithm implements Algorithm {
    private final BitSetBuilder bsb;
    private final BitSetValidator bsv;
    private int currentLevelIndex;
    private BitSet currentCenterNode;
    private ArrayList<BitSet> validNodesCache;
    private ArrayList<BitSet> invalidNodesCache;
    private final int minLevel;
    private final int maxLevel;
    private int pillarCount;
    private int minCoverage;
    private ArrayList<BitSet> pillarCoverage;
    private ArrayList<Integer> pillarScore;

    private int timer = 0;
    private int noOptimumTimer;
    private boolean timerActivated = false;

    private BitSet optimumNode = null;
    private boolean algoEnded = false;

    RandomWalkAlgorithm(int minLevel, int maxLevel,int noOptimumTimer){
        this.noOptimumTimer = noOptimumTimer;
        this.minLevel = minLevel;
        this.maxLevel = maxLevel;
        this.invalidNodesCache = new ArrayList<>();
        this.validNodesCache = new ArrayList<>();
        bsb = new BitSetBuilder();
        bsv = new BitSetValidator();
    }
    @Override
    public ArrayList<Pillar> execute(ArrayList<Pillar> pillars, ArrayList<Person> people, boolean ABS_REL, int goal) {
        initAlgo(pillars,people,goal);
        while(!this.algoEnded){
            nextStep();
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

    /**
     * Method to initialize the Algorithm
     * @param pillars Input Pillars
     * @param people Input People
     * @param goal goal coverage
     */
    public void initAlgo(ArrayList<Pillar> pillars, ArrayList<Person> people, int goal){
        this.pillarCount = pillars.size();
        currentLevelIndex = pillarCount/2-1;
        currentCenterNode = bsb.buildStartInstance(currentLevelIndex,pillarCount);
        optimumNode = currentCenterNode;
        this.minCoverage = ((int)Math.ceil((people.size()/100.0)*goal));
        this.pillarCoverage = new ArrayList<>();
        this.pillarScore = new ArrayList<>();
        for(Pillar p : pillars){
            this.pillarScore.add(p.getShadow().getValue());
            this.pillarCoverage.add(p.getPeopleReached());
        }
    }

    public void nextStep(){
        boolean up_down = true;
        if(bsv.validateAbs(currentCenterNode,pillarCoverage) >= this.minCoverage){
            up_down = false;
        }
        if(currentLevelIndex > maxLevel && up_down){
            up_down = false;
        }
        if(currentLevelIndex < minLevel && !up_down){
            up_down = true;
        }
        ArrayList<BitSet> currentLevel = new ArrayList<>();
        ArrayList<BitSet> currentValid = new ArrayList<>();
        ArrayList<BitSet> currentInvalid = new ArrayList<>();
        //TOP DOWN PRUNING
        if(!up_down){
            currentLevel = bsb.buildGeneralisations(currentCenterNode);
            ArrayList<BitSet> pruneDown = bsv.topDownPruning(currentLevel,invalidNodesCache);
            currentInvalid.addAll(pruneDown);
            currentLevel.removeAll(pruneDown);

            ArrayList<BitSet> eqPrune = bsv.equalityPruning(currentLevel,validNodesCache);
            currentValid.addAll(eqPrune);
            currentLevel.removeAll(eqPrune);
        }else{
            //BOTTOM UP
            currentLevel = bsb.buildSpecialisations(currentCenterNode);
            ArrayList<BitSet> pruneUp = bsv.bottomUpPruning(currentLevel,validNodesCache);
            currentValid.addAll(pruneUp);
            currentLevel.removeAll(pruneUp);

            ArrayList<BitSet> eqPrune = bsv.equalityPruning(currentLevel,invalidNodesCache);
            currentInvalid.addAll(eqPrune);
            currentLevel.removeAll(eqPrune);
        }

        if(currentLevel.isEmpty()){
            this.algoEnded = true;
            return;
        }

        //VALIDATION
        for(BitSet bs: currentLevel){
            if(bsv.validateAbs(bs,pillarCoverage) >= minCoverage){
                currentValid.add(bs);
            }else{
                currentInvalid.add(bs);
            }
        }
        this.invalidNodesCache.addAll(currentInvalid);
        this.validNodesCache.addAll(currentValid);

        BitSet currentOptimum = new BitSet();
        int highScore = -999999;
        if(!currentValid.isEmpty()){
            for(BitSet bs : currentValid){
                int score = bsv.validateScore(bs,pillarScore);
                if(score > highScore){
                    currentOptimum = bs;
                    highScore = score;
                }
            }
            if(bsv.newOptimumRel(this.optimumNode,currentOptimum,pillarCoverage,pillarScore)){
                this.optimumNode = currentOptimum;
                if(timerActivated){
                    this.timer = 0;
                }
            }else if(timerActivated){
                this.timer++;
                if(this.timer > noOptimumTimer){
                    this.algoEnded = true;
                    return;
                }
            }
        }else{
            for(BitSet bs : currentInvalid){
                int score = bsv.validateScore(bs,pillarScore);
                if(score > highScore){
                    currentOptimum = bs;
                    highScore = score;
                }
            }
        }
        currentCenterNode = currentOptimum;
    }


}
