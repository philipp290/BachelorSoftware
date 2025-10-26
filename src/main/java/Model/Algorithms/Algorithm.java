package Model.Algorithms;

import Model.Components.Person;
import Model.Components.Pillar;

import java.util.ArrayList;

public interface Algorithm {
    ArrayList<Pillar> execute(ArrayList<Pillar> pillars, ArrayList<Person> people,boolean ABS_REL, int goal);
}
