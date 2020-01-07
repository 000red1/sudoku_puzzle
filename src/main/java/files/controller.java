package files;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class controller implements ActionListener {

    JFrame frame = new JFrame("Test");
    JPanel panel = new JPanel();

    //Main Menu
    JButton playButton = new JButton("Play Sudoku");
    JButton solverButton = new JButton("Sudoku Solver");
    JButton helpButton = new JButton("Help");
    JButton settingsButton = new JButton("Settings");
    JButton solveButton = new JButton("Solve");

    //Settings
    JTextField numberBoxes[][] = new JTextField[9][9];

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public controller(){

        panel.setLayout(null);
        panel.setPreferredSize(new Dimension(800,800));

        playButton.setSize(500, 160);
        playButton.setLocation(140,150);
        playButton.setFont(new Font(Font.DIALOG, Font.BOLD, 48));
        playButton.setOpaque(false);
        playButton.setContentAreaFilled(false);
        playButton.setBorderPainted(false);
        panel.add(playButton);

        solverButton.setSize(500, 160);
        solverButton.setLocation(140,350);
        solverButton.setFont(new Font(Font.DIALOG, Font.BOLD, 48));
        solverButton.setOpaque(false);
        solverButton.setContentAreaFilled(false);
        solverButton.setBorderPainted(false);
        panel.add(solverButton);

        helpButton.setSize(230, 80);
        helpButton.setLocation(140, 580);
        helpButton.setFont(new Font(Font.DIALOG, Font.BOLD, 24));
        helpButton.setOpaque(false);
        helpButton.setContentAreaFilled(false);
        helpButton.setBorderPainted(false);
        panel.add(helpButton);

        settingsButton.setSize(230, 80);
        settingsButton.setLocation(410, 580);
        settingsButton.setFont(new Font(Font.DIALOG, Font.BOLD, 24));
        settingsButton.setOpaque(false);
        settingsButton.setContentAreaFilled(false);
        settingsButton.setBorderPainted(false);
        panel.add(settingsButton);

        solveButton.setSize(250,120);
        solveButton.setLocation(280,600);
        solveButton.setFont(new Font(Font.DIALOG, Font.BOLD, 34));
        solveButton.setOpaque(false);
        solveButton.setContentAreaFilled(false);
        solveButton.setBorderPainted(false);
        solveButton.setVisible(false);
        solveButton.setEnabled(false);
        panel.add(solveButton);

        for(int i = 0; i < 9; i++){
            for(int k = 0; k < 9; k++){
                numberBoxes[i][k] = new JTextField();
                numberBoxes[i][k].setSize(65,65);
                numberBoxes[i][k].setLocation(5+ 65*i,5+65*k);
                numberBoxes[i][k].setFont(new Font(Font.DIALOG, Font.BOLD, 24));
                numberBoxes[i][k].setOpaque(false);
                //numberBoxes[i][k].setVisible(false);
                numberBoxes[i][k].setEnabled(false);
                panel.add(numberBoxes[i][k]);
            }
        }


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(panel);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String [] args){
        new controller();
    }
}
