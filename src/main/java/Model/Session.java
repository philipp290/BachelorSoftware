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
}
