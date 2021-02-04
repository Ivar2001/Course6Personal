package Afvinkopdracht2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Scanner;

public class Opdracht1 extends JFrame {
    public static int yaxes = 0; // maakt int
    public static int xaxes = 0; // maakt int
    public Organisme[][] populatie; // maakt button array
    public JButton startbutton; // maakt button
    public JButton resetbutton; // maakt button
    public JButton randombutton; // maakt button
    public Random rd = new Random(); // maakt Random object
    public GridBagConstraints c; // maakt layout
    private boolean permission = false; // maakt boolean
    private Logica GOL;


    public static void main(String[] args) {
        getsizes();
        Opdracht1 frame = new Opdracht1();
        frame.Applicatie();
        frame.setTitle("Game of Life");
        frame.setVisible(true);
        frame.pack();
    }
    public static void getsizes(){
        /***
         * Deze functie haalt de gegeven grote van het game of life bord op.
         */
        Scanner myObj = new Scanner(System.in);
        System.out.println("Enter the size of the button array (example y x: 10 10): ");

        // Interger input voor breete van veld word opgehaald
        yaxes = myObj.nextInt();
        System.out.println(yaxes);
        // Interger input voor lengte van veld word opgehaald
        xaxes = myObj.nextInt();
        System.out.println(xaxes);
    }
    private void Applicatie() {
        /***
         * Deze functie zorgt voor dat alle elementen op de gui komen te staan en dat ze acties hebben die ze kunnen
         * uitvoeren.
         */
        this.setDefaultCloseOperation(3);
        Container window = this.getContentPane();
        window.setLayout(new GridBagLayout());
        c = new GridBagConstraints();
        window.setBackground(Color.GRAY);
        populatie = new Organisme[xaxes][yaxes];
        //Genereerd het veld van game of life
        for (int y = 0; y < populatie.length; y++) {
            for (int x = 0; x < populatie[y].length; x++) {
                Organisme organisme = new Organisme(false);
                organisme.setPreferredSize(new Dimension(15, 15));
                organisme.setSelected(false);
                organisme.setBorder(null);
                c.gridx=y;
                c.gridy=x;
                window.add(organisme, c);
                populatie[y][x] = organisme;
            }
        }
        // Definneer alle knoppen
        startbutton = new JButton("Start Life Cycle");
        startbutton.setPreferredSize(new Dimension(15* xaxes, 20));
        resetbutton = new JButton("Reset Life Cycle");
        resetbutton.setPreferredSize(new Dimension(15* xaxes, 20));
        randombutton = new JButton("Generate Random");
        randombutton.setPreferredSize(new Dimension(15* xaxes,20));
        // Voeg alle knoppen toe op de goede plek in het scherm
        c.gridy +=1;
        c.gridx = 0;
        c.gridwidth = xaxes;
        window.add(startbutton, c);
        c.gridy +=1;
        c.gridx = 0;
        c.gridwidth = xaxes;
        window.add(randombutton, c);
        c.gridy +=1;
        c.gridx = 0;
        c.gridwidth = xaxes;
        window.add(resetbutton, c);
        // Hieropvolgende actionlistener start de game of life
        startbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!permission){
                    permission=true;
                    GOL = new Logica(populatie, yaxes, xaxes, permission);
                    GOL.start();
                    startbutton.setText("Stop Life Cycle");
                }else if (permission){
                    permission=false;
                    GOL.setPermission(permission);
                    GOL.stop();
                    startbutton.setText("Start Life Cycle");
                }
            }
        });
        // Hieropvolgede actionlistener reset het hele veld naar dood
        resetbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int y = 0; y < populatie.length; y++) {
                    for (int x = 0; x < populatie[y].length; x++) {
                        populatie[y][x].setState(false);
                    }
                }
            }
        });
        // Hieropvolgende actionlistener genereerd een random veld met levend en dode cellen
        randombutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int y = 0; y < populatie.length; y++) {
                    for (int x = 0; x < populatie[y].length; x++) {
                        populatie[y][x].setState(rd.nextBoolean());
                    }
                }
            }
        });
    }
}