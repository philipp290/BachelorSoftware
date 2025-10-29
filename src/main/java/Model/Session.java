package Model;

import Model.Components.Person;
import Model.Components.Pillar;

import java.util.ArrayList;

/**
 * Singleton, dass die Details der Sitzung speichert
 */
public class Session {
    private static Session instance;
    private ArrayList<Pillar> pillars;
    private ArrayList<Person> people;

    private int reachingDistance = 20;


    private int amountOfReachablePeople = -1;

    private ArrayList<Integer> algorithmParameters = new ArrayList<>();


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
    public int getReachingDistance() {return reachingDistance;}
    public void setReachingDistance(int reachingDistance) {this.reachingDistance = reachingDistance;}
    public int getAmountOfReachablePeople() {return amountOfReachablePeople;}
    public void setAmountOfReachablePeople(int amountOfReachablePeople) {this.amountOfReachablePeople = amountOfReachablePeople;}
    public ArrayList<Integer> getAlgorithmParameters() {return algorithmParameters;}
    public void setAlgorithmParameters(ArrayList<Integer> algorithmParameters) {this.algorithmParameters = algorithmParameters;}

}
