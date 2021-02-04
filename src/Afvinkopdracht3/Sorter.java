package Afvinkopdracht3;

import javax.swing.*;
import java.io.*;
import java.util.Scanner;

public class Sorter {
    public Scanner bestand;
    public Sorter(File file) {
        try{
//            bestand = new BufferedReader(new FileReader(file));
            bestand = new Scanner(file);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Bestand is niet gevonden", "Error", JOptionPane.ERROR_MESSAGE);
        }
        String line = null;
//        while (true) {
//            if ((line = bestand.nextLine()) == null) break;
//            String[] lijn = line.split(",");
//            System.out.println(lijn);
//        }
        while (bestand.hasNext()) {
            System.out.println("epicc");
        }
    }
}
