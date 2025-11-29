package Model.Algorithms.LatticeAlgorithms.Relativ;

import Model.Algorithms.Algorithm;
import Model.Algorithms.LatticeAlgorithms.General.BitSetBuilder;
import Model.Algorithms.LatticeAlgorithms.General.BitSetValidator;
import Model.Components.Person;
import Model.Components.Pillar;

import java.util.ArrayList;
import java.util.BitSet;

public class RandomWalkAlgorithm implements Algorithm {
    private final BitSetBuilder bsb;
    private final BitSetValidator bsv;
    private int currentLevelIndex;
    private BitSet currentCenterNode;
    private ArrayList<BitSet_Plus> validNodesCache;
    private ArrayList<BitSet_Plus> invalidNodesCache;
    private final int minLevel;
    private final int maxLevel;
    private int pillarCount;
    private int minCoverage;
    public ArrayList<BitSet> pillarCoverage;
    public ArrayList<Integer> pillarScore;

    private int timer = 0;
    private int noOptimumTimer;
    public boolean timerActivated = false;

    public int maxTimer = 0;
    public int maxTimerMAX = 500;


    public BitSet optimumNode = null;
    public boolean algoEnded = false;
    public boolean foundValidNode = false;

    public RandomWalkAlgorithm(int minLevel, int maxLevel,int noOptimumTimer){
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
        timerActivated=true;
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
        System.out.println("Itteration:" +maxTimer);
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
        currentLevelIndex = (int) Math.floor(minLevel+(0.5*(maxLevel-minLevel)));
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

    public void nextStep() {
        maxTimer++;
        if (maxTimer > maxTimerMAX) {
            this.algoEnded = true;
        }

        updateList(invalidNodesCache);
        updateList(validNodesCache);

        boolean up_down = true;
        //Valide?
        int currentScore = bsv.validateAbs(currentCenterNode, pillarCoverage);
        if (currentScore >= this.minCoverage) {
            up_down = false;
        }
        if (currentLevelIndex >= maxLevel) {
            up_down = false;
        }
        if (currentLevelIndex <= minLevel) {
            up_down = true;
        }
        if (up_down) {
            currentLevelIndex++;
        } else{
            currentLevelIndex--;
        }
        ArrayList<BitSet> currentLevel = new ArrayList<>();
        ArrayList<BitSet> currentValid = new ArrayList<>();
        ArrayList<BitSet> currentInvalid = new ArrayList<>();
        //TOP DOWN PRUNING
        if(!up_down){
            currentLevel = bsb.buildGeneralisations(currentCenterNode);
            //invalide Generalisierungen Prunen
            ArrayList<BitSet> pruneDown = bsv.topDownPruning(currentLevel,convert(invalidNodesCache));
            currentInvalid.addAll(pruneDown);
            currentLevel.removeAll(pruneDown);
            //valide Wiederholungen prunen
            ArrayList<BitSet> eqPrune = bsv.equalityPruning(currentLevel,convert(validNodesCache));
            currentValid.addAll(eqPrune);
            currentLevel.removeAll(eqPrune);
        }else{
            //BOTTOM UP
            currentLevel = bsb.buildSpecialisations(currentCenterNode);
            //valide Spezialisierungen prunen
            ArrayList<BitSet> pruneUp = bsv.bottomUpPruning(currentLevel,convert(validNodesCache));
            currentValid.addAll(pruneUp);
            currentLevel.removeAll(pruneUp);
            //invalide Wiederholungen prunen
            ArrayList<BitSet> eqPrune = bsv.equalityPruning(currentLevel,convert(invalidNodesCache));
            currentInvalid.addAll(eqPrune);
            currentLevel.removeAll(eqPrune);
        }

        //alle currentLevel "weggeprunt"
        if(currentLevel.isEmpty()){
            //currentCenterNode = bsb.findRandomBitSet(currentLevelIndex,pillarCount);
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
        addConvert(currentInvalid,invalidNodesCache);
        addConvert(currentValid,validNodesCache);
        //this.invalidNodesCache.addAll(currentInvalid);
        //this.validNodesCache.addAll(currentValid);

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
            boolean newOptimum = bsv.newOptimumRel(this.optimumNode,currentOptimum,pillarCoverage,pillarScore);
            if(newOptimum){
                this.optimumNode = currentOptimum;
                this.foundValidNode = true;
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

    public ArrayList<BitSet> convert(ArrayList<BitSet_Plus> input){
        ArrayList<BitSet> output = new ArrayList<>();
        for(BitSet_Plus bp : input){
            output.add(bp.bitSet);
        }
        return output;
    }
    public void addConvert(ArrayList<BitSet> traveler, ArrayList<BitSet_Plus> goal){
        for(BitSet b: traveler){
            goal.add(new BitSet_Plus(b,5));
        }
    }
    public void updateList(ArrayList<BitSet_Plus> input){
        for(BitSet_Plus bs : input){
            bs.timer--;
        }
        input.removeIf(bitSetPlus -> bitSetPlus.timer == 0);
    }
}
