package files;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import javax.swing.event.*;
import java.io.*;
import javax.imageio.*;


public class sudoku_view extends JPanel {

    //Properties
    public boolean blnGame = false;
    public boolean mouse = false;
    int mousex;
    int mousey;
    int x_difference;
    int y_difference;
    int[][] tracker = new int[9][9];
    int[][] given_puzzle = new int[9][9];
    int select_x = -1;
    int select_y = -1;

    BufferedImage gameboard[][] = new BufferedImage[9][9];
    BufferedImage imgNum[][] = new BufferedImage[9][3];


    //Numbers
    BufferedImage dragged;
    BufferedImage background;

    public void loadNumbers(){
        String color = "";
        for(int i = 0; i<= 2; i++){
            switch(i){
                case 0:
                    color = "blue.png";
                    break;
                case 1:
                    color = "green.png";
                    break;
                case 2:
                    color = "yellow.png";
                    break;
            }
            for(int k = 0; k < 9; k++){

                String name = (k+1) + color;
                imgNum[k][i] = loadImage(name);
            }
        }
    }

    public BufferedImage loadImage(String strNum) {
        BufferedImage image = null;
        String strname = "src/main/java/images/" + strNum;
        try {
            image = ImageIO.read(new File(strname));
        } catch (IOException e) {
            System.out.println(strname);
            e.printStackTrace();
        }


        return image;
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        if(blnGame){

            g.setColor(new Color(175,0,0));
            g.drawLine(615,5,615,5+ 65*9);
            g.drawLine(679,5,679,5+ 65*9);
            for(int i = 0; i<= 9; i++){
                g.drawLine(615,5 + 65*i,679,5+ 65*i);
                if( i < 9) {
                    g.drawImage(imgNum[i][0], 615, 5 + 65*i,null);
                }
            }
            g.setColor(new Color(89, 167, 44));
            for(int i = 0; i <= 9; i++) {
                g.drawLine(5 + 65*i,5,5 + 65*i,5+ 65*9);
                g.drawLine(5,5 + 65*i,5+ 65*9,5 + 65*i);
                for (int k = 0; k < 9 && i < 9; k++) {
                    g.drawImage(gameboard[i][k], 5 + 65*i, 5 + 65*k, null);
                }
            }
            g.setColor(new Color(0, 0, 0));
            for(int i = 0; i<= 3;i++) {
                g.drawLine(5+ 65*3*i, 5, 5 + 65*i*3, 5 + 65 * 9);
                g.drawLine(5,5 + 65*i*3,5+ 65*9,5 + 65*i*3);
            }

            if(mouse){
                g.drawImage(dragged, mousex - x_difference, mousey- y_difference, null);
            }
        }

    }

    public void setmouse(boolean flag){
        mouse = flag;
    }

    public void imagedragged(int x, int y){
        int multi = (y-5)/ 65;
        dragged = imgNum[multi ][0];
        setmouse(true);
        y_difference = y - 5 -65*multi;
        x_difference = x - 615;
    }

    public void updateboard(int number, boolean boardNumber, boolean mode){
        int x = (mousex - 5) / 65;
        int y = (mousey - 5) / 65;
        tracker[x][y] = number;
        if(boardNumber && select_x != -1 && select_y != -1){
            tracker[select_x][select_y] = 0;
            gameboard[select_x][select_y] = null;
        }
        gameboard[x][y] = imgNum[number - 1][0];
        if(!mode || given_puzzle[x][y] == 0) {
            gameboard[x][y] = imgNum[number - 1][0];
        }
    }

    public void solver(int[][] sudoku_puzzle, boolean mode){
        for(int x = 0; x < 9; x++){
            for(int y = 0; y < 9; y++){
                int number = sudoku_puzzle[x][y];
                if(number != 0) {
                    gameboard[x][y] = imgNum[number-1][1];
                }
            }
        }
        if(mode){
            given_puzzle = sudoku_puzzle;
            tracker = sudoku_puzzle;
        }
    }

    public void checkPuzzle(int[][] solution){
        for(int x = 0; x < 9; x++){
            for(int y = 0; y < 9; y++){
                int number = solution[x][y];
                if(tracker[x][y] != 0 && tracker[x][y] == number) {
                    gameboard[x][y] = imgNum[number-1][1];
                }
                if(tracker[x][y] != 0 && tracker[x][y] != number) {
                    gameboard[x][y] = imgNum[tracker[x][y]-1][2];
                }
            }
        }
    }

    public void gameboard(){
        for(int i = 0; i < 9; i++) {
            for (int k = 0; k < 9; k++) {
                gameboard[i][k] = null;
                tracker[i][k] = 0;
                given_puzzle[i][k] =0;
            }
        }
    }

    public int getSelectedBoardNumber(int x, int y){
        select_x = (x-5)/65;
        select_y = (y-5)/65;
        if(x == -1 && y == -1){
            return -1;
        }
        return tracker[select_x][select_y];
    }

    public sudoku_view(){
        background = loadImage("blank.png");
        gameboard();
    }
}
