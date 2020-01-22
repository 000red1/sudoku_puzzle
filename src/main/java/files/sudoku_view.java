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

    public int imagedragged(int x, int y, boolean cas){
        int multi = (y - 5) / 65;
        if(cas){
            dragged = imgNum[multi][0];
            setmouse(true);
            y_difference = y - 5 - 65 * multi;
            x_difference = x - 615;
            return 0;
        }
        else{
            int x_position = (x-5)/ 65;
            if(given_puzzle[x_position][multi] == 0 && tracker[x_position][multi] != 0){
                int num;
                dragged = imgNum[(num = tracker[x_position][multi])-1][0];
                setmouse(true);
                gameboard[x_position][multi] = null;
                tracker[x_position][multi] = 0;
                y_difference = y - 5 - 65 * multi;
                x_difference = x -5 - 65 *x_position;
                System.out.println(num);
                return num;
            }
            dragged = null;
        }
        return -1;
    }

    public void updateboard(int number, boolean boardNumber){
        int x = (mousex - 5) / 65;
        int y = (mousey - 5) / 65;
        if(boardNumber && given_puzzle[x][y] == 0){
            tracker[x][y] = number;
            gameboard[x][y] = imgNum[number - 1][0];
            dragged = null;
        }
        else if(!boardNumber){
            tracker[x][y] = number;
            gameboard[x][y] = imgNum[number - 1][0];
            dragged = null;
        }
    }

    public void solver(int[][] sudoku_puzzle, boolean mode){
        for(int x = 0; x < 9; x++){
            for(int y = 0; y < 9; y++){
                int number = sudoku_puzzle[x][y];
                if(number != 0) {
                    if(mode){
                        gameboard[x][y] = imgNum[number-1][1];
                        tracker[x][y] = number;
                    }
                    else if (!mode && tracker[x][y] == 0 && given_puzzle[x][y] == 0){
                        gameboard[x][y] = imgNum[number-1][1];
                    }
                    given_puzzle[x][y] =  number;
                }
                else if(number == 0 && !mode && tracker[x][y] != 0){
                    number = tracker[x][y];
                    gameboard[x][y] = imgNum[number-1][2];
                }
            }
        }
    }

    public void reset_board() {
        for(int x = 0; x < 9; x++){
            for(int y = 0; y < 9; y++){
                int number = given_puzzle[x][y];
                tracker[x][y] = number;
                gameboard[x][y] = null;
                if(number != 0){
                    gameboard[x][y] = imgNum[number-1][1];
                }
            }
        }
    }

    public void checkPuzzle(int[][] solution){
        for(int x = 0; x < 9; x++){
            for(int y = 0; y < 9; y++){
                int number = solution[x][y];
                if(tracker[x][y] != 0 && tracker[x][y] == number) {
                    gameboard[x][y] = imgNum[number-1][1];
                    given_puzzle[x][y] = number;
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

    public void clearDragged(){
        dragged = null;
    }

    public sudoku_view(){
        background = loadImage("blank.png");
        gameboard();
    }
}
