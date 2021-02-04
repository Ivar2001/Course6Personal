package Afvinkopdracht3;

import javax.swing.*;
import java.nio.file.Path;
import java.util.Scanner;
import java.io.File;

public class Opdracht2 extends JFrame{
    public static Sorter analysis;
    public static String filename;
    public static String choise;
    public static String filepath;


    public static void main(String[] args) {
        File file = getsettings();
        settings(file);
        //filename = System.getProperty("user.dir")+"/"+args[1];
        //choise = args[2];
    }

    private static void settings(File file) {
        analysis = new Sorter(file);

    }
    public static File getsettings(){
        /***
         *
         */
        Scanner myObj = new Scanner(System.in);
        System.out.println("Enter filename:");
        // filename word opgehaad
        filename = myObj.nextLine();
        File file = new File(filename);
        System.out.println(filepath);
        System.out.println("Enter list name (AL for ArrayList or LL for LinkedList):");
        // lijst keuze
        choise = myObj.nextLine();

        return file;
    }

}