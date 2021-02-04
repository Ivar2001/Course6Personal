package Afvinkopdracht3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class SyntaxChecker extends JFrame{
    public JTextField filenaam1;
    public JButton openfile;
    public JFileChooser fileChooser;
    public JButton fileselecter1;
    public JButton Analyse;
    public StackerChecker analysis;
    public JTextArea output;

    public static void main(String[] args) {
        SyntaxChecker frame = new SyntaxChecker();
        frame.setSize(500, 300);
        frame.GUI();
        frame.setResizable(false);
        frame.setTitle("Script Checker");
        frame.setVisible(true);


    }

    private void GUI(){
        this.setDefaultCloseOperation(3);
        Container window = this.getContentPane();
        window.setLayout(new FlowLayout());
        window.setBackground(Color.lightGray);
        filenaam1 = new JTextField();
        filenaam1.setPreferredSize(new Dimension(320,20));
        fileselecter1 = new JButton("Blader");
        fileselecter1.setPreferredSize(new Dimension(150,20));
        Analyse = new JButton("Analyseer de java code");
        Analyse.setPreferredSize(new Dimension(475,20));
        output = new JTextArea();
        output.setPreferredSize(new Dimension(475,200));
        window.add(filenaam1);
        window.add(fileselecter1);
        window.add(Analyse);
        window.add(output);
        //Actinlistener die het script opend
        fileselecter1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String file1 = Selectfile();
                filenaam1.setText(file1);
            }
        });
        Analyse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                analysis = new StackerChecker(filenaam1.getText());
                output.setText(analysis.getMessage());
            }
        });
    }

    public String Selectfile(){
        /**
         * Selecteerd de bestanden
         * return; filepath
         */
        openfile = new JButton();
        fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new java.io.File("."));
        fileChooser.setDialogTitle("Select file");
        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
        }
        String filepath = fileChooser.getSelectedFile().getAbsolutePath();
        System.out.println(filepath);
        return filepath;
    }
}
