package Model.Algorithms.LatticeAlgorithms.Relativ;

import Model.Algorithms.Algorithm;
import Model.Algorithms.LatticeAlgorithms.General.BitSetBuilder;
import Model.Algorithms.LatticeAlgorithms.General.BitSetValidator;
import Model.Components.Person;
import Model.Components.Pillar;

import java.util.ArrayList;
import java.util.BitSet;

public class BottomUpAlgorithm implements Algorithm {
    private final BitSetBuilder bsb;
    private final BitSetValidator bsv;
    private int currentLevelIndex;
    private final int maxLevel;
    private int pillarCount;
    private int minCoverage;
    private ArrayList<BitSet> pillarCoverage;
    private ArrayList<Integer> pillarScore;
    public BitSet optimumNode = null;

    public boolean algoEnded = false;

    public BottomUpAlgorithm(int maxLevel){
        this.maxLevel = maxLevel;
        currentLevelIndex=0;
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

    public void initAlgo(ArrayList<Pillar> pillars, ArrayList<Person> people, int goal){
        this.pillarCount = pillars.size();
        this.minCoverage = ((int)Math.ceil((people.size()/100.0)*goal));
        this.pillarCoverage = new ArrayList<>();
        this.pillarScore = new ArrayList<>();
        for(Pillar p : pillars){
            this.pillarScore.add(p.getShadow().getValue());
            this.pillarCoverage.add(p.getPeopleReached());
        }

    }


    public void nextStep(){
        currentLevelIndex++;
        if(currentLevelIndex > this.maxLevel){
            this.algoEnded=true;
            return;
        }
        ArrayList<BitSet> currentLevel = bsb.buildLevelK(currentLevelIndex,pillarCount);
        ArrayList<BitSet> validNodes = new ArrayList<>();
        for(BitSet bs : currentLevel){
            if(bsv.validateAbs(bs, this.pillarCoverage) >= this.minCoverage){
                validNodes.add(bs);
            }
        }
        if(validNodes.size()>0){
            this.algoEnded=true;
            int highScore = -999999;
            BitSet optimum = new BitSet();
            for(BitSet bs : validNodes){
                int score = bsv.validateScore(bs,pillarScore);
                if(score > highScore){
                    optimum = bs;
                    highScore = score;
                }
            }
            this.optimumNode = optimum;
        }
    }
}
