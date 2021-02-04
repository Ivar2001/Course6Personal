package Afvinkopdracht5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Gui extends JFrame {
    public GridBagConstraints c;
    public JLabel resultstitle;
    public JButton calculate; // maakt button
    public JButton selectfile; // maakt button
    public JTextArea results;
    public JTextArea Gene1;
    public JTextArea Gene2;
    public JTextArea Gene3;
    public JScrollPane scroll;
    public JTextField filename;
    public JButton openfile;
    public JFileChooser fileChooser;
    public BufferedReader bestand;
    public String file1 = null;
    public ArrayList<String> listone = new ArrayList<String>();
    public ArrayList<String> listtwo = new ArrayList<String>();
    public ArrayList<String> listtre = new ArrayList<String>();
    public ArrayList<String> match = new ArrayList<String>();
    public Boolean continu;
    public static void main(String[] args) {
        Gui frame = new Gui();
        frame.App();
        frame.setTitle("Gene Finder");
        frame.setVisible(true);
        frame.pack();
    }
    public void App(){
        this.setDefaultCloseOperation(3);
        Container window = this.getContentPane();
        window.setLayout(new GridBagLayout());
        c = new GridBagConstraints();
        filename = new JTextField();
        filename.setPreferredSize(new Dimension(450, 20));
        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 9;
        c.gridheight = 1;
        window.add(filename,c);
        selectfile = new JButton("Select File");
        selectfile.setPreferredSize(new Dimension(200, 25));
        c.gridx = 10;
        c.gridy = 1;
        c.gridwidth = 2;
        c.gridheight = 1;
        window.add(selectfile,c);
        Gene1 = new JTextArea();
        Gene1.setPreferredSize(new Dimension(150, 300));
        Gene1.setBorder(BorderFactory.createLineBorder(Color.black));
        scroll = new JScrollPane(Gene1);
        c.gridx = 1;
        c.gridy = 2;
        c.gridwidth = 3;
        c.gridheight = 1;
        window.add(scroll, c);
        Gene2 = new JTextArea();
        Gene2.setPreferredSize(new Dimension(150, 300));
        Gene2.setBorder(BorderFactory.createLineBorder(Color.black));
        c.gridx = 4;
        c.gridy = 2;
        c.gridwidth = 3;
        c.gridheight = 1;
        window.add(Gene2,c);
        Gene3 = new JTextArea();
        Gene3.setPreferredSize(new Dimension(150, 300));
        Gene3.setBorder(BorderFactory.createLineBorder(Color.black));
        c.gridx = 7;
        c.gridy = 2;
        c.gridwidth = 3;
        c.gridheight = 1;
        window.add(Gene3,c);
        resultstitle = new JLabel("MATCHING GENES ACCROSS THE TREE LISTS:");
        c.gridx = 1;
        c.gridy = 3;
        c.gridwidth = 9;
        c.gridheight = 1;
        window.add(resultstitle,c);
        results = new JTextArea();
        results.setPreferredSize(new Dimension(450,100));
        c.gridx = 1;
        c.gridy = 4;
        c.gridwidth = 9;
        c.gridheight = 1;
        window.add(results,c);
        calculate = new JButton("Compare");
        calculate.setPreferredSize(new Dimension(100, 25));
        c.gridx = 10;
        c.gridy = 4;
        c.gridwidth = 2;
        c.gridheight = 1;
        window.add(calculate,c);
        selectfile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                file1 = Selectfile();
                FileAnalyser(file1);
            }
        });
        calculate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Checkdata();
                if (continu) {
                    Compare();
                }
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
    public void FileAnalyser(String file1){
        try{
            bestand = new BufferedReader(new FileReader(file1));
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
            String[] genes = line.split(",");
            listone.add(genes[0]);
            listtwo.add(genes[1]);
            listtre.add(genes[2]);
        }
        for(String gen1 : listone){
            Gene1.append(gen1 + "\n");
        }
        for(String gen2 : listtwo){
            Gene2.append(gen2 + "\n");
        }
        for(String gen2 : listtre){
            Gene3.append(gen2 + "\n");
        }
    }
    public void Checkdata(){
        if (listone.isEmpty() && listtwo.isEmpty() && listtre.isEmpty()){
            if (file1==null){
                String s1[] = Gene1.getText().split("\\r?\\n");
                String s2[] = Gene2.getText().split("\\r?\\n");
                String s3[] = Gene3.getText().split("\\r?\\n");
                listone.addAll(Arrays.asList(s1));
                listtwo.addAll(Arrays.asList(s2));
                listtre.addAll(Arrays.asList(s3));
                continu = true;
            }else{
                results.setText("Please submit data by uploading a csv file or via user input at the test Area's to compare the lists.");
                continu = false;
            }
        }else{
            results.setText("Data succesfully submitted....");
            continu = true;
        }
    }
    public void Compare(){
        ArrayList<String> Match12 = new ArrayList<>();
        for (String item : listone)
            if (listtwo.contains(item)) {
                Match12.add(item);
            }
        for (String item2 : Match12){
            if (listtre.contains(item2)){
                match.add(item2);
            }
        }
        results.setText("Matches:\n");
        for(String mat : match){
            results.append(mat + "\n");
        }
    }
}