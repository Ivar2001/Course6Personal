package Afvinkopdracht3;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class StackerChecker {

    public BufferedReader bestand;
    public Stack<String> checkstack = new Stack<>();
    public List<String> items= Arrays.asList("{", "(", "}", ")", "[", "]");
    public List<String> openings = Arrays.asList("{", "(", "[");
    public String message = "";
    public int lijnnummer = -1;


    public StackerChecker(String filename) {
        try{
            bestand = new BufferedReader(new FileReader(filename));
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Bestand is niet gevonden", "Error", JOptionPane.ERROR_MESSAGE);
        }
        String line = null;
        while (true){
            try {
                if ((line = bestand.readLine()) == null) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            for (int i=0; i<line.length(); i++){
                lijnnummer+=1;
                String character = String.valueOf(line.charAt(i));
                if (items.contains(character)){
                    Check(character);
                }else{
                    continue;
                }
            }
        }
    }
    public String getMessage() {
        return message;
    }

    public void Check(String character) {
        if (checkstack.isEmpty()) {
            if (character.equals("{")){
                assert false;
                checkstack.push(character);
                System.out.println(checkstack.peek());
            }else{
                message = "Code word niet geopend met [] of () maar met {}";
            }
        } else {
            String item = checkstack.peek();
            if (openings.contains(character)){
                checkstack.push(character);
                System.out.println(checkstack.peek());
            } else if (item.equals("{")&character.equals("}")||
                    item.equals("(")&character.equals(")")||
                    item.equals("[")&character.equals("]")) {
                checkstack.pop();
                if (checkstack.isEmpty()){
                    System.out.println("Code klopt");
                }
            } else{
                message = "Het laaste "+item+" word niet gesloten, sluit dit item voor "+character+" op regel "+(lijnnummer+1)+".";
            }
        }
    }
}
