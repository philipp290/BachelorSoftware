package Model.Algorithms;

import Model.Components.Person;
import Model.Components.Pillar;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;

public class LinearOptimizationAlgorithm implements Algorithm {
    @Override
    public ArrayList<Pillar> execute(ArrayList<Pillar> pillars, ArrayList<Person> people, boolean ABS_REL, int goal) {
        String outputFile = "Data/Cache/LinearOptimizationCache/loExport.txt";

        if(ABS_REL){
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
                writer.write("/* Objective function: to maximize */");
                writer.newLine();

                double pillarWeight = (1.0/goal)/3.0;
                pillarWeight = Math.floor(pillarWeight*1000.0)/1000.0;

                StringBuilder objectiveLine = new StringBuilder("max: ");
                int i = 0;
                while(i < people.size()){
                    objectiveLine.append("y" + i + " + ");
                    i++;
                }
                objectiveLine.delete(objectiveLine.length() - 3, objectiveLine.length());
                objectiveLine.append(" - ");
                i = 0;
                while(i < pillars.size()){
                    objectiveLine.append((pillars.get(i).getShadow().getValue() * pillarWeight) + "x" + i + " - ");
                    i++;
                }
                objectiveLine.delete(objectiveLine.length() - 3, objectiveLine.length());
                writer.write(objectiveLine.toString());
                writer.newLine();
                writer.newLine();

                writer.write("/* Constraints of the problem */");
                writer.newLine();
                int yIndex = 0;
                int xIndex = 0;
                while(yIndex < people.size()){
                    StringBuilder constraint = new StringBuilder("y");
                    constraint.append(yIndex + " <= ");

                    xIndex = 0;
                    while (xIndex < pillars.size()) {
                        if (people.get(yIndex).getPillarsPassed().get(xIndex)) {
                            constraint.append("x" + xIndex + " + ");
                        }
                        xIndex++;
                    }
                    constraint.delete(constraint.length() - 3, constraint.length());
                    writer.write(constraint.toString());
                    writer.newLine();
                    constraint.setLength(0);

                    yIndex++;
                }
                writer.newLine();

                StringBuilder goalLine = new StringBuilder(goal+" >= ");
                i = 0;
                while(i < pillars.size()){
                    goalLine.append("x" + i + " + ");
                    i++;
                }
                goalLine.delete(goalLine.length() - 3, goalLine.length());
                writer.write(goalLine.toString());
                writer.newLine();
                writer.newLine();
                writer.write("/* Declaration of boolean variables */");
                writer.newLine();

                StringBuilder booleanLine = new StringBuilder("bin ");
                xIndex = 0;
                yIndex = 0;
                while(yIndex<people.size()){
                    booleanLine.append("y" + yIndex + ", ");
                    yIndex++;
                }
                while(xIndex<pillars.size()){
                    booleanLine.append("x" + xIndex + ", ");
                    xIndex++;
                }
                booleanLine.delete(booleanLine.length()-2, booleanLine.length());
                writer.write(booleanLine.toString());

            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
                writer.write("/* Objective function: to minimize */");
                writer.newLine();

                double pillarWeight = (1.0/ pillars.size())/3.0;
                pillarWeight = Math.floor(pillarWeight*1000.0)/1000.0;

                StringBuilder objectiveLine = new StringBuilder("min: ");

                int i = 0;
                while(i < pillars.size()){
                    objectiveLine.append(((pillars.get(i).getShadow().getValue()*pillarWeight)+1)+"x"+i+" + ");
                    i++;
                }
                objectiveLine.delete(objectiveLine.length() - 3, objectiveLine.length());
                writer.write(objectiveLine.toString());
                writer.newLine();
                writer.newLine();

                writer.write("/* Constraints of the problem */");
                writer.newLine();
                int yIndex = 0;
                int xIndex = 0;
                while(yIndex < people.size()){
                    StringBuilder constraintOne = new StringBuilder("y"+yIndex+" >= ");
                    StringBuilder constraintTwo = new StringBuilder("y"+yIndex+" <= ");

                    xIndex = 0;
                    while(xIndex < pillars.size()){
                        if(people.get(yIndex).getPillarsPassed().get(xIndex)){
                            String adder = "x"+xIndex;
                            constraintOne.append(adder);
                            writer.write(constraintOne.toString());
                            writer.newLine();
                            constraintOne.delete(constraintOne.length()-adder.length(),constraintOne.length());
                            constraintTwo.append("x"+xIndex+" + ");
                        }
                        xIndex++;
                    }
                    constraintTwo.delete(constraintTwo.length()-3,constraintTwo.length());
                    writer.write(constraintTwo.toString());
                    writer.newLine();
                    writer.newLine();

                    constraintOne.setLength(0);
                    constraintTwo.setLength(0);

                    yIndex++;
                }
                //double temp =
                goal = (int) Math.ceil((people.size()/100.0)*goal);
                StringBuilder goalLine = new StringBuilder(goal+" <= ");
                i = 0;
                while(i < people.size()){
                    goalLine.append("y"+i+" + ");
                    i++;
                }
                goalLine.delete(goalLine.length() - 3, goalLine.length());
                writer.write(goalLine.toString());
                writer.newLine();
                writer.newLine();
                writer.write("/* Declaration of boolean variables */");
                writer.newLine();

                StringBuilder booleanLine = new StringBuilder("bin ");
                xIndex = 0;
                yIndex = 0;
                while(yIndex<people.size()){
                    booleanLine.append("y"+yIndex+", ");
                    yIndex++;
                }
                while(xIndex<pillars.size()){
                    booleanLine.append("x"+xIndex+", ");
                    xIndex++;
                }
                booleanLine.delete(booleanLine.length()-2, booleanLine.length());
                writer.write(booleanLine.toString());

            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
