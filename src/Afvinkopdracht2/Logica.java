package Afvinkopdracht2;

import java.util.Arrays;
import java.util.Collections;

public class Logica extends Thread{
    public Boolean[] omgeving = new Boolean[8]; //maakt boolean array
    public int levend; // maakt int
    public int dood; // maakt int
    public Organisme[][] populatie; // maakt button array
    private int xas = 0; // maakt int
    private int yas = 0; // maakt int
    private boolean permission;

    public Logica(Afvinkopdracht2.Organisme[][] populatie, int xas, int yas, boolean permission) {
        this.permission=permission;
        this.xas = xas;
        this.yas = yas;
        this.populatie = populatie;
    }

    public void run(){
        /***
         * Tweede thread om te runnen, dit is de daatwerkelijke logica achter het spel
         */
        while (permission) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            CeckOmgeving();
        }
    }
    public void CeckOmgeving() {
        /**
         * Deze functie kijk of de omgeving van het organisme (button) voldoed aan de regels
         * van de game of life en beslist of het desbetreffende organisme (button) dood gaat (false)
         * of levend blijft/word (true)
         */
        for (int y = 0; y < populatie.length; y++) {
            for (int x = 0; x < populatie[y].length; x++) {
                getseroundings(y, x);
                if (levend < 2 && populatie[y][x].getState()) {
                    populatie[y][x].setState(false);
                } else if (levend > 3 && populatie[y][x].getState()) {
                    populatie[y][x].setState(false);
                } else if (levend == 3 && !populatie[y][x].getState()) {
                    populatie[y][x].setState(true);
                } else if (populatie[y][x].getState() && levend == 2 | levend == 3) {
                    continue;
                }
            }
        }
    }
    public void getseroundings(int y, int x){
        /**
         * Deze functie heeft in if statement die de status ophaald van omligende
         * organisme deze worden toegevoegd aan een array en na de if statement word
         * gekeken hoeveel organisme eromheen levend of dood zijn
         * @input: int y, int x
         */
        if(y==0&&x==0){
            //Hoek links boven
            omgeving[3]= populatie[y][x+1].getState();
            omgeving[4]= populatie[y+1][x+1].getState();
            omgeving[5]= populatie[y+1][x].getState();
        }else if(y==0&&x== xas -1){
            //Hoek rechts boven
            omgeving[5]= populatie[y+1][x].getState();
            omgeving[6]= populatie[y+1][x-1].getState();
            omgeving[7]= populatie[y][x-1].getState();
        }else if(x== xas -1&&y== yas -1){
            //Hoek rechts onder
            omgeving[0]= populatie[y-1][x-1].getState();
            omgeving[1]= populatie[y-1][x].getState();
            omgeving[7]= populatie[y][x-1].getState();
        }else if(x==0&&y== yas -1) {
            //Hoek links onder
            omgeving[1]= populatie[y-1][x].getState();
            omgeving[2]= populatie[y-1][x+1].getState();
            omgeving[3]= populatie[y][x+1].getState();
        }else if(y==0){
            // Bovenzijde
            omgeving[3]= populatie[y][x+1].getState();
            omgeving[4]= populatie[y+1][x+1].getState();
            omgeving[5]= populatie[y+1][x].getState();
            omgeving[6]= populatie[y+1][x-1].getState();
            omgeving[7]= populatie[y][x-1].getState();
        }else if(x==xas-1){
            // Rechterzijde
            omgeving[0]= populatie[y-1][x-1].getState();
            omgeving[1]= populatie[y-1][x].getState();
            omgeving[5]= populatie[y+1][x].getState();
            omgeving[6]= populatie[y+1][x-1].getState();
            omgeving[7]= populatie[y][x-1].getState();
        }else if(y==yas-1){
            // Onderzijde
            omgeving[0]= populatie[y-1][x-1].getState();
            omgeving[1]= populatie[y-1][x].getState();
            omgeving[2]= populatie[y-1][x+1].getState();
            omgeving[3]= populatie[y][x+1].getState();
            omgeving[7]= populatie[y][x-1].getState();
        }else if(x==0){
            // Linkerzijde
            omgeving[1]= populatie[y-1][x].getState();
            omgeving[2]= populatie[y-1][x+1].getState();
            omgeving[3]= populatie[y][x+1].getState();
            omgeving[4]= populatie[y+1][x+1].getState();
            omgeving[5]= populatie[y+1][x].getState();
        }else{
            // Midden
            omgeving[0]= populatie[y-1][x-1].getState();
            omgeving[1]= populatie[y-1][x].getState();
            omgeving[2]= populatie[y-1][x+1].getState();
            omgeving[3]= populatie[y][x+1].getState();
            omgeving[4]= populatie[y+1][x+1].getState();
            omgeving[5]= populatie[y+1][x].getState();
            omgeving[6]= populatie[y+1][x-1].getState();
            omgeving[7]= populatie[y][x-1].getState();
        }
        // Tel aantal levend en dode organisme
        levend = Collections.frequency(Arrays.asList(omgeving), true);
        dood = Collections.frequency(Arrays.asList(omgeving), false);
    }

    public void setPermission(boolean permission) {
        this.permission = permission;
    }
}
