package com.mmu.tracker;

import javax.swing.*;

public class mainForm {
    private JPanel mainPanel;
    private JTextField textField1;
    private JButton goButton;

    public mainForm() {
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("COVIDASH");
        frame.setContentPane(new mainForm().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
