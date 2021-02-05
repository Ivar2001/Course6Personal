package Afvinkopdracht6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Opdracht1 {
    public static ArrayList<String> seq_name = new ArrayList<>();
    public static ArrayList<String> seq_string = new ArrayList<>();
    public static void main(String[] args) {
        Openfile("/home/ivar/IdeaProjects/Course6Personal/src/Afvinkopdracht6/RosalindOpd1.txt");
        Assable();
    }
    public static void Openfile(String file){
        BufferedReader bestand;
        try {
            // reads the file
            bestand = new BufferedReader(new FileReader(file));
            String line;
            while (true) {
                try {
                    line = bestand.readLine();
                    if (line != null && line.startsWith(">")) {
                        seq_name.add(line);
                    }else if (line != null && !line.startsWith(">")) {
                        seq_string.add(line);
                    }else if (line == null) {
                        break;
                    }
                } catch (IOException e) {
                    // Handles IO Exceptions
                    e.printStackTrace();
                    break;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void Assable(){
        int n = 3;
        for (int i = 0; i < seq_string.size(); i++) {
            for (int j = 0; j < seq_string.size(); j++) {
                if (i != j) {
                    if (seq_string.get(i).substring(seq_string.get(i).length() - n)
                            .equals(seq_string.get(j).substring(0, n))){
                        System.out.println(seq_name.get(i)+", "+seq_name.get(j));
                    }
                }
            }
        }
    }
}
