package files;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import javax.swing.event.*;
import java.io.*;
import javax.imageio.*;

public class controller implements ActionListener, MouseMotionListener, MouseListener {

    JFrame frame = new JFrame("Sudoku Game Version 0.2");
    sudoku_view panel = new sudoku_view();
    sudoku_model model = new sudoku_model();
    Timer time;

    // 0: Title Screen
    // 1: Game
    // 2: Solver
    // 3: Help
    int menu_mode = 0;


    //Main Menu
    JButton playButton = new JButton("Play Sudoku");
    JButton solverButton = new JButton("Sudoku Solver");
    JButton helpButton = new JButton("Help");
    JButton settingsButton = new JButton("Settings");
    JButton solveButton = new JButton("Solve");
    JButton backButton = new JButton("Back");
    JButton resetButton = new JButton("Reset");

    //Settings
    JTextField numberBoxes[][] = new JTextField[9][9];
/*    JLabel mousepressX = new JLabel("X coordinate of Press: ");
    JLabel mousepressY = new JLabel("Y coordinate of Press: ");
    JLabel mousepositionX = new JLabel("X coordinate of mouse: ");
    JLabel mousepositionY = new JLabel("Y coordinate of mouse: ");*/

    // Mouse Listener Methods

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("mouse released");
        if(model.valid_placement(e.getX(),e.getY())){
            int move = model.make_placement();
            panel.updateboard(move);
        }
        panel.setmouse(false);
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    // Mouse Motion Listener Methods
    @Override
    public void mouseDragged(MouseEvent e) {

        if(panel.blnGame && e.getX() >= 615 && e.getX() <= 680 && !panel.mouse){
            System.out.println("mouse dragged");
            panel.imagedragged(e.getX(),e.getY());
            model.get_possible_number(e.getY());
        }
        panel.mousex = e.getX();
        panel.mousey = e.getY();

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    //Action Listener Methods
    @Override
    public void actionPerformed(ActionEvent evt) {
        if(evt.getSource() == solverButton){

            changeButtonDisplay(playButton,false);
            changeButtonDisplay(solverButton, false);
            changeButtonDisplay(helpButton, false);
            changeButtonDisplay(settingsButton, false);
            changeButtonDisplay(solveButton, true);
            changeButtonDisplay(backButton, true);
            changeButtonDisplay(resetButton,true);
            changeTextBoxesVisibility(true);
            panel.blnGame = true;
            model.setGamemode(2);
            panel.repaint();

        }
        else if(evt.getSource() == backButton) {

            changeButtonDisplay(playButton, true);
            changeButtonDisplay(solverButton, true);
            changeButtonDisplay(helpButton, true);
            changeButtonDisplay(settingsButton, true);
            changeButtonDisplay(solveButton, false);
            changeButtonDisplay(backButton, false);
            changeButtonDisplay(resetButton, false);

            changeTextBoxesVisibility(false);
            panel.gameboard();
            model.setSudokuEmpty();
            panel.blnGame = false;
        }
        else if(evt.getSource() == time){
            panel.repaint();
        }
        else if(evt.getSource() == solveButton){
            System.out.println(model.solve());
            if(model.solve()){
                panel.solver(model.get_sudoku());
            }
        }
        else if(evt.getSource() == resetButton){
            panel.gameboard();
            model.setSudokuEmpty();
        }

    }

    public void changeButtonDisplay(JButton e, boolean flag){
        e.setEnabled(flag);
        e.setVisible(flag);
    }


    public void changeTextBoxesVisibility(boolean flag){
        for(int i = 0; i < 9; i++){
            for(int k = 0; k < 9; k++){
                numberBoxes[i][k].setVisible(flag);
            }
        }
    }

    public controller(){

        panel.setLayout(null);
        panel.setPreferredSize(new Dimension(680,680));

        playButton.setSize(500, 160);
        playButton.setLocation(100,80);
        playButton.setFont(new Font(Font.DIALOG, Font.BOLD, 48));
        playButton.setOpaque(false);
        playButton.setContentAreaFilled(false);
        playButton.setBorderPainted(false);
        panel.add(playButton);


        solverButton.setSize(500, 160);
        solverButton.setLocation(100,280);
        solverButton.setFont(new Font(Font.DIALOG, Font.BOLD, 48));
        solverButton.setOpaque(false);
        solverButton.setContentAreaFilled(false);
        solverButton.setBorderPainted(false);
        panel.add(solverButton);
        solverButton.addActionListener(this);

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

        solveButton.setSize(140,120);
        solveButton.setLocation(230,580);
        solveButton.setFont(new Font(Font.DIALOG, Font.BOLD, 34));
        solveButton.setOpaque(false);
        solveButton.setContentAreaFilled(false);
        solveButton.setBorderPainted(false);
        solveButton.setVisible(false);
        solveButton.setEnabled(false);
        panel.add(solveButton);
        solveButton.addActionListener(this);

        backButton.setSize(140,120);
        backButton.setLocation(0,580);
        backButton.setFont(new Font(Font.DIALOG, Font.BOLD, 34));
        backButton.setOpaque(false);
        backButton.setContentAreaFilled(false);
        backButton.setBorderPainted(false);
        backButton.setVisible(false);
        backButton.setEnabled(false);
        panel.add(backButton);
        backButton.addActionListener(this);

        resetButton.setSize(140,120);
        resetButton.setLocation(480,580);
        resetButton.setFont(new Font(Font.DIALOG, Font.BOLD, 34));
        resetButton.setOpaque(false);
        resetButton.setContentAreaFilled(false);
        resetButton.setBorderPainted(false);
        resetButton.setVisible(false);
        resetButton.setEnabled(false);
        panel.add(resetButton);
        resetButton.addActionListener(this);

        for(int i = 0; i < 9; i++){
            for(int k = 0; k < 9; k++){
                numberBoxes[i][k] = new JTextField();
                numberBoxes[i][k].setSize(65,65);
                numberBoxes[i][k].setLocation(5+ 65*i,5+65*k);
                numberBoxes[i][k].setFont(new Font(Font.DIALOG, Font.BOLD, 24));
                numberBoxes[i][k].setOpaque(false);
                numberBoxes[i][k].setBorder(null);
                numberBoxes[i][k].setVisible(false);
                numberBoxes[i][k].setEnabled(false);
                panel.add(numberBoxes[i][k]);
            }
        }

        time = new Timer(100/60, this);

        panel.addMouseListener(this);
        panel.addMouseMotionListener(this);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(panel);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
        panel.loadNumbers();

        time.start();
    }

    public static void main(String [] args){
        new controller();
    }

}
