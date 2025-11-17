package Model;

import Model.Components.Person;
import Model.Components.Pillar;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Singleton, dass die Details der Sitzung speichert
 */
public class Session {
    private static Session instance;

    private ArrayList<Pillar> pillars = new ArrayList<>();
    private ArrayList<Integer> setIndexes = new ArrayList<>();
    private ArrayList<Pillar> lighthouses = new ArrayList<>();
    private ArrayList<Person> people = new ArrayList<>();


    private String originalPeopleFile = "";

    private int defaultReachingDistance = 20;

    private int amountOfReachablePeople = -1;
    private ArrayList<Integer> algorithmParameters = new ArrayList<>();


    private HashMap<String, ArrayList<Pillar>> solutionCache = new HashMap<>();
    private ArrayList<String> solutionKeys = new ArrayList<>();


    private Session() {}

    public static Session getInstance() {
        if (instance == null) {
            instance = new Session();
        }
        return instance;
    }

    public ArrayList<Pillar> getPillars() {return pillars;}
    public void setPillars(ArrayList<Pillar> pillars) {this.pillars = pillars;}
    public ArrayList<Person> getPeople() {return people;}
    public void setPeople(ArrayList<Person> people) {this.people = people;}
    public int getReachingDistance() {return defaultReachingDistance;}
    public void setReachingDistance(int reachingDistance) {this.defaultReachingDistance = reachingDistance;}
    public int getAmountOfReachablePeople() {return amountOfReachablePeople;}
    public void setAmountOfReachablePeople(int amountOfReachablePeople) {this.amountOfReachablePeople = amountOfReachablePeople;}
    public ArrayList<Integer> getAlgorithmParameters() {return algorithmParameters;}
    public void setAlgorithmParameters(ArrayList<Integer> algorithmParameters) {this.algorithmParameters = algorithmParameters;}

    public HashMap<String, ArrayList<Pillar>> getSolutionCache() {return solutionCache;}
    public void setSolutionCache(HashMap<String, ArrayList<Pillar>> solutionCache) {this.solutionCache = solutionCache;}
    public ArrayList<String> getSolutionKeys() {return solutionKeys;}
    public void setSolutionKeys(ArrayList<String> solutionKeys) {this.solutionKeys = solutionKeys;}
    public String getOriginalPeopleFile() {return originalPeopleFile;}
    public void setOriginalPeopleFile(String originalPeopleFile) {this.originalPeopleFile = originalPeopleFile;}
    public ArrayList<Pillar> getLighthouses() {return lighthouses;}
    public void setLighthouses(ArrayList<Pillar> lighthouses) {this.lighthouses = lighthouses;}
    public ArrayList<Integer> getSetIndexes() {return setIndexes;}
    public void setSetIndexes(ArrayList<Integer> setIndexes) {this.setIndexes = setIndexes;}
}
