package com.mmu.tracker;
//import required libraries
import javax.swing.*;

public class Main {
    //add form items
    private JButton goButton;
    private JPanel mainPanel;
    private JTextField searchBar;
    //main code block
    public static void main(String[] args) {
        //load and display the form
        JFrame frame = new JFrame("COVIDASH");
        frame.setContentPane(new Main().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
