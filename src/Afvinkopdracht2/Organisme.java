package Afvinkopdracht2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Organisme extends JButton implements ActionListener {
    private Boolean state;
    // Constructor voor Organisme
    public Organisme(Boolean state){
        setState(state);
        this.addActionListener(this::actionPerformed);
    }
    // Setter voor de kleur van elk item
    public void setState(Boolean state) {
        this.state = state;
        if (this.state){
            setBackground(Color.black);
        }else{
            setBackground(Color.white);
        }
    }

    public void actionPerformed(ActionEvent e) {
        /***
         * Geeft elke item een kleun op basis van zijn status
         */
        if (getState()) {
            setState(false);
        } else if (!getState()) {
            setState(true);
        }
    }
    // getter voor de status van een item
    public Boolean getState() {
        return state;
    }
}
