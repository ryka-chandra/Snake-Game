package org.cis1200.snake;

// imports necessary libraries for Java swing and File I/O
import java.awt.*;
import javax.swing.*;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;

/**
 * Game Main class that specifies the frame and widgets of the GUI
 */
public class RunSnake implements Runnable {
    
    /**
     * Runs the Snake game
     */
    public void run() {
        // reads the file for File I/O implementation
        int size = 300;
        try {
            File f = new File("files/config.txt");
            if (f.exists()) {
                FileReader fileReader = new FileReader(f);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                size = Integer.parseInt(bufferedReader.readLine());
                bufferedReader.close();
            } else {
                f.createNewFile();
                FileWriter fileWriter = new FileWriter(f);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.write("300");
                bufferedWriter.close();
            }
        } catch (Exception e) {
            System.out.println("exception caught");
        }

        // Top-level frame in which game components live.
        final JFrame frame = new JFrame("SNAKE");
        frame.setLocation(300, 300);

        // Status panel
        final JPanel status_panel = new JPanel();
        frame.add(status_panel, BorderLayout.SOUTH);
        final JLabel status = new JLabel("Running...");
        status_panel.add(status);

        // Main playing area
        final GameCourt court = new GameCourt(status);
        court.setSize(size);
        frame.add(court, BorderLayout.CENTER);

        // Reset button
        final JPanel control_panel = new JPanel();
        frame.add(control_panel, BorderLayout.NORTH);

        // Note here that when we add an action listener to the reset button, we
        // define it as an anonymous inner class that is an instance of
        // ActionListener with its actionPerformed() method overridden. When the
        // button is pressed, actionPerformed() will be called.
        final JButton reset = new JButton("Reset");
        reset.addActionListener(e -> {
            court.reset();
            frame.pack();
            }
        );
        control_panel.add(reset);

        // adds button and changes contents of config file for File I/O
        final JButton setSize = new JButton("Size of Grid");
        setSize.addActionListener(e -> {
            String input = JOptionPane.showInputDialog(
                "Set the size of the grid (between 200 and 500 for easy visibility)");
            int s = Integer.parseInt(input);
            court.setSize(s);
            frame.pack();
            try {
                FileWriter fileWriter = new FileWriter("files/config.txt", false);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.write(Integer.toString(s));
                bufferedWriter.close();
            } catch (Exception x) {
                System.out.println("exception caught");
            }
        });
        control_panel.add(setSize);

        // Put the frame on the screen
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Start game
        court.reset();
    }
}