package Afvinkopdracht2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Organisme extends JButton implements ActionListener {
    private Boolean state;
    public Organisme(Boolean state){
        setState(state);
        this.addActionListener(this::actionPerformed);
    }

    public void setState(Boolean state) {
        this.state = state;
        if (this.state){
            setBackground(Color.green);
        }else{
            setBackground(Color.red);
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (getState()) {
            setState(false);
        } else if (!getState()) {
            setState(true);
        }
    }

    public Boolean getState() {
        return state;
    }
}
