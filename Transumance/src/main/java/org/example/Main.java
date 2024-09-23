package org.example;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Create a simple Swing GUI
        JFrame frame = new JFrame("Swing Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        JLabel label = new JLabel("Hello, Swing!", SwingConstants.CENTER);
        frame.add(label);

        frame.setVisible(true);
    }
}
