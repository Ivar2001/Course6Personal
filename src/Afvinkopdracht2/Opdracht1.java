package Afvinkopdracht2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Opdracht1 extends JFrame {
    public static int breete = 0; // maakt int
    public static int lengte = 0; // maakt int
    public Organisme[][] populatie; // maakt button array
    public JButton startbutton; // maakt button
    public JButton resetbutton; // maakt button
    public JButton randombutton; // maakt button
    public Boolean[] omgeving = new Boolean[8]; //maakt boolean array
    public int levend; // maakt int
    public int dood; // maakt int
    public Random rd = new Random(); // maakt Random object
    public GridBagConstraints c;
    public Boolean run;


    public static void main(String[] args) {
        getsizes();
        Opdracht1 frame = new Opdracht1();
        frame.Applicatie();
        frame.setTitle("TicTacToe");
        frame.setVisible(true);
        frame.pack();
    }
    public static void getsizes(){
        Scanner myObj = new Scanner(System.in);
        System.out.println("Enter the size of the button array (example x y: 10 10): ");

        // Interger input voor breete van veld word opgehaald
        breete = myObj.nextInt();

        // Interger input voor lengte van veld word opgehaald
        lengte = myObj.nextInt();
    }
    private void Applicatie() {
        this.setDefaultCloseOperation(3);
        Container window = this.getContentPane();
        window.setLayout(new GridBagLayout());
        c = new GridBagConstraints();
        window.setBackground(Color.WHITE);
        populatie = new Organisme[breete][lengte];
        for (int i = 0; i < populatie.length; i++) {
            for (int j = 0; j < populatie[i].length; j++) {
                Organisme organisme = new Organisme(false);
                organisme.setPreferredSize(new Dimension(15, 15));
                organisme.setSelected(false);
                window.add(organisme, c);
                populatie[i][j] = organisme;
                //buttons[i].addActionListener(this::actionPerformed);
                c.gridy=j;
            }
            c.gridy = 0;
            c.gridx=i;
        }
        startbutton = new JButton("Start Life Cycle");
        startbutton.setPreferredSize(new Dimension(15*breete, 20));
        resetbutton = new JButton("Reset Life Cycle");
        resetbutton.setPreferredSize(new Dimension(15*breete, 20));
        randombutton = new JButton("Generate Random");
        randombutton.setPreferredSize(new Dimension(15*breete,20));
        c.gridy = breete;
        c.gridx = 0;
        c.gridwidth = breete;
        window.add(startbutton, c);
        c.gridy = breete+1;
        c.gridx = 0;
        c.gridwidth = breete;
        window.add(randombutton, c);
        c.gridy = breete+2;
        c.gridx = 0;
        c.gridwidth = breete;
        window.add(resetbutton, c);

        startbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    run=true;
                    start();
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
            }
        });
        resetbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < populatie.length - 1; i++) {
                    for (int j = 0; j < populatie[i].length - 1; j++) {
                        populatie[i][j].setState(false);
                    }
                }
            }
        });
        randombutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < populatie.length - 1; i++) {
                    for (int j = 0; j < populatie[i].length - 1; j++) {
                        populatie[i][j].setState(rd.nextBoolean());
                    }
                }
            }
        });
    }
    public void start() throws InterruptedException {
        /**
         * Deze functie kijk of de omgeving van het organisme (button) voldoed aan de regels
         * van de game of life en beslist of het desbetreffende organisme (button) dood gaat (false)
         * of levend blijft/word (true)
         */
        while (run) {
            for (int i = 0; i < populatie.length - 1; i++) {
                for (int j = 0; j < populatie[i].length - 1; j++) {
                    getseroundings(i, j);
                    if (levend < 2 && populatie[i][j].getState()) {
                        populatie[i][j].setState(false);
                    } else if (levend > 3 && populatie[i][j].getState()) {
                        populatie[i][j].setState(false);
                    } else if (levend == 3 && !populatie[i][j].getState()) {
                        populatie[i][j].setState(true);
                    } else if (populatie[i][j].getState() && levend == 2 | levend == 3) {
                    }
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
        }else if(y==0&&x==breete){
            //Hoek rechts boven
            omgeving[5]= populatie[y+1][x].getState();
            omgeving[6]= populatie[y+1][x-1].getState();
            omgeving[7]= populatie[y][x-1].getState();
        }else if(x==breete&&y==lengte){
            //Hoek rechts onder
            omgeving[0]= populatie[y-1][x-1].getState();
            omgeving[1]= populatie[y-1][x].getState();
            omgeving[7]= populatie[y][x-1].getState();
        }else if(x==0&&y==lengte) {
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
        }else if(x==breete){
            // Rechterzijde
            omgeving[0]= populatie[y-1][x-1].getState();
            omgeving[1]= populatie[y-1][x].getState();
            omgeving[5]= populatie[y+1][x].getState();
            omgeving[6]= populatie[y+1][x-1].getState();
            omgeving[7]= populatie[y][x-1].getState();
        }else if(y==lengte){
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
}