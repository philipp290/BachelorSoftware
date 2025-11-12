package Model.Services;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;

public class TxtWriterService {

    public void writeRExport(ArrayList<Double>[] values, int from, int to){
        String outputFile = "Data/Cache/RExportCache/rExport.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            writer.write("library(ggplot2)");
            writer.newLine();
            writer.write("library(tidyr)");
            writer.newLine();
            writer.write("library(dplyr)");
            writer.newLine();
            writer.newLine();
            writer.write("df <- data.frame(");
            writer.newLine();
            writer.write("x = ("+from+":"+to+"),");
            writer.newLine();
            if(values[0]!=null){
                StringBuilder toBeWriten = new StringBuilder(listToVektor("abs",values[0]));
                if(values[1]==null && values[2]==null && values[3]==null){
                    toBeWriten.delete(toBeWriten.length()-1,toBeWriten.length());
                }
                writer.write(toBeWriten.toString());
                writer.newLine();
            }
            if(values[1]!=null){
                StringBuilder toBeWriten = new StringBuilder(listToVektor("perc",values[1]));
                if(values[2]==null && values[3]==null){
                    toBeWriten.delete(toBeWriten.length()-1,toBeWriten.length());
                }
                writer.write(toBeWriten.toString());
                writer.newLine();
            }
            if(values[2]!=null){
                StringBuilder toBeWriten = new StringBuilder(listToVektor("pil",values[2]));
                if(values[3]==null){
                    toBeWriten.delete(toBeWriten.length()-1,toBeWriten.length());
                }
                writer.write(toBeWriten.toString());
                writer.newLine();
            }
            if(values[3]!=null){
                StringBuilder toBeWriten = new StringBuilder(listToVektor("shad",values[3]));
                toBeWriten.delete(toBeWriten.length()-1,toBeWriten.length());

                writer.write(toBeWriten.toString());
                writer.newLine();
            }
            writer.write(")");
            writer.newLine();
            writer.newLine();
            writer.write("df_long <- df %>%");
            writer.newLine();
            writer.write("pivot_longer(-x, names_to = \"Variable\", values_to = \"Wert\")");
            writer.newLine();
            writer.newLine();
            writer.write("ggplot(df_long, aes(x=x, y=Wert, color=Variable)) +");
            writer.newLine();
            writer.write("geom_line(size=1) +");
            writer.newLine();
            writer.write("facet_wrap(~Variable, scales=\"free_y\", ncol=1) +");
            writer.newLine();
            writer.write("theme_minimal()");

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String listToVektor(String name ,ArrayList<Double> input){
        StringBuilder result = new StringBuilder(name);
        result.append(" = c(");
        for(Double d : input){
            result.append(d+",");
        }
        result.delete(result.length()-1,result.length());
        result.append("),");

        return result.toString();
    }


    public static void main(String[] args) {
        ArrayList<Double> test = new ArrayList<>();
        test.add(2.0);
        test.add(2.456);
        test.add(3.0);
        TxtWriterService tws = new TxtWriterService();
        System.out.println(tws.listToVektor("test",test));
    }
}
