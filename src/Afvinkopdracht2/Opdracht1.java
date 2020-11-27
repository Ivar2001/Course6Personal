package Afvinkopdracht2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Scanner;

public class Opdracht1 extends JFrame {
    public static int xas = 0; // maakt int
    public static int yas = 0; // maakt int
    public Organisme[][] populatie; // maakt button array
    public JButton startbutton; // maakt button
    public JButton resetbutton; // maakt button
    public JButton randombutton; // maakt button
    public Random rd = new Random(); // maakt Random object
    public GridBagConstraints c;
    private boolean permission = false;
    private Logica iets;


    public static void main(String[] args) {
        getsizes();
        Opdracht1 frame = new Opdracht1();
        frame.Applicatie();
        frame.setTitle("Game of Life");
        frame.setVisible(true);
        frame.pack();
    }
    public static void getsizes(){
        Scanner myObj = new Scanner(System.in);
        System.out.println("Enter the size of the button array (example x y: 10 10): ");

        // Interger input voor breete van veld word opgehaald
        xas = myObj.nextInt();
        System.out.println(xas);
        // Interger input voor lengte van veld word opgehaald
        yas = myObj.nextInt();
        System.out.println(yas);
    }
    private void Applicatie() {
        this.setDefaultCloseOperation(3);
        Container window = this.getContentPane();
        window.setLayout(new GridBagLayout());
        c = new GridBagConstraints();
        window.setBackground(Color.GRAY);
        populatie = new Organisme[yas][xas];
        for (int y = 0; y < populatie.length; y++) {
            for (int x = 0; x < populatie[y].length; x++) {
                //System.out.println("1: "+y +", "+ x);
                Organisme organisme = new Organisme(false);
                organisme.setPreferredSize(new Dimension(15, 15));
                organisme.setSelected(false);
                organisme.setBorder(null);
                c.gridx=y;
                c.gridy=x;
                window.add(organisme, c);
                populatie[y][x] = organisme;
                //buttons[y].addActionListener(this::actionPerformed);
            }
        }
        startbutton = new JButton("Start Life Cycle");
        startbutton.setPreferredSize(new Dimension(15* xas, 20));
        resetbutton = new JButton("Reset Life Cycle");
        resetbutton.setPreferredSize(new Dimension(15* xas, 20));
        randombutton = new JButton("Generate Random");
        randombutton.setPreferredSize(new Dimension(15* xas,20));
        c.gridy +=1;
        c.gridx = 0;
        c.gridwidth = xas;
        window.add(startbutton, c);
        c.gridy +=1;
        c.gridx = 0;
        c.gridwidth = xas;
        window.add(randombutton, c);
        c.gridy +=1;
        c.gridx = 0;
        c.gridwidth = xas;
        window.add(resetbutton, c);

        startbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!permission){
                    permission=true;
                    iets = new Logica(populatie, xas, yas, permission);
                    iets.start();
                    startbutton.setText("Stop Life Cycle");
                }else if (permission){
                    permission=false;
                    iets.setPermission(permission);
                    iets.stop();
                    startbutton.setText("Start Life Cycle");
                }
            }
        });
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
        randombutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int y = 0; y < populatie.length; y++) {
                    for (int x = 0; x < populatie[y].length; x++) {
                        //System.out.println("2: "+y +", "+ x);
                        populatie[y][x].setState(rd.nextBoolean());
                    }
                }
            }
        });
    }
}