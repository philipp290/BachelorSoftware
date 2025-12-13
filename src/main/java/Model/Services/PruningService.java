package Model.Services;

import Model.Components.Person;
import Model.Components.Pillar;
import Model.Session;

import java.util.ArrayList;

public class PruningService {

    /**
     * Methode um Litfaßsäulen zu prunen die keine Person
     * errecihen
     * @param input
     * @return
     */
    public ArrayList<Pillar> prunePillars(ArrayList<Pillar> input){
        ArrayList<Pillar> result = new ArrayList<>();
        for(Pillar p: input){
            if(p.getPeopleReached().cardinality()!=0){
                result.add(p);
            }
        }
        return result;
    }
    /**
     * Methode um Personen zu prunen die von keiner
     * Säule erreicht werden
     * @param input
     * @return
     */
    public ArrayList<Person> prunePeople(ArrayList<Person> input){
        boolean lightHousesSet = !Session.getInstance().getLighthouses().isEmpty();

        ArrayList<Person> result = new ArrayList<>();
        for(Person p: input){
            if(p.getPillarsPassed().cardinality()!=0){
                result.add(p);
            }
        }
        return result;
    }


}
