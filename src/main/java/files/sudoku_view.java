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
    boolean test = false;

    BufferedImage gameboard[][] = new BufferedImage[9][9];


    //Numbers
    BufferedImage imgNum1;
    BufferedImage imgNum2;
    BufferedImage imgNum3;
    BufferedImage imgNum4;
    BufferedImage imgNum5;
    BufferedImage imgNum6;
    BufferedImage imgNum7;
    BufferedImage imgNum8;
    BufferedImage imgNum9;
    BufferedImage dragged;
    BufferedImage background;

    public void loadNumbers(){
        imgNum1 = loadImage("1.png");
        imgNum2 = loadImage("2.png");
        imgNum3 = loadImage("3.png");
        imgNum4 = loadImage("4.png");
        imgNum5 = loadImage("5.png");
        imgNum6 = loadImage("6.png");
        imgNum7 = loadImage("7.png");
        imgNum8 = loadImage("8.png");
        imgNum9 = loadImage("9.png");
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
            g.drawImage(imgNum1, 600, 5,null);
            g.drawImage(imgNum2, 600, 5 + 65,null);
            g.drawImage(imgNum3, 600, 5 + 65*2,null);
            g.drawImage(imgNum4, 600, 5 + 65*3,null);
            g.drawImage(imgNum5, 600, 5 + 65*4,null);
            g.drawImage(imgNum6, 600, 5 + 65*5,null);
            g.drawImage(imgNum7, 600, 5 + 65*6,null);
            g.drawImage(imgNum8, 600, 5 + 65*7,null);
            g.drawImage(imgNum9, 600, 5 + 65*8,null);

            if(mouse){
                g.drawImage(dragged, mousex - x_difference, mousey- y_difference, null);
            }

            for(int i = 0; i < 9; i++) {
                for (int k = 0; k < 9; k++) {
                    g.drawImage(gameboard[i][k], 5 + 65*i, 5 + 65*k, null);
                    if(test){
                        System.out.println("something");
                    }
                }
            }

        }

    }

    public void setmouse(boolean flag){
        mouse = flag;
    }

    public void imagedragged(int x, int y){
        if(y > 5 && y < 5 + 65){
            setmouse(true);
            dragged = imgNum1;
            y_difference = y - 5;
        }else if(y > 5 + 65 && y < 5 + 65*2){
            setmouse(true);
            dragged = imgNum2;
            y_difference = y - 65 - 5;
        }else if(y > 5 + 65*2 && y < 5 + 65*3){
            setmouse(true);
            dragged = imgNum3;
            y_difference = y - 65*2 - 5;
        }else if(y > 5 + 65*3 && y < 5 + 65*4){
            setmouse(true);
            dragged = imgNum4;
            y_difference = y - 65*3 - 5;
        }else if(y > 5 + 65*4 && y < 5 + 65*5){
            setmouse(true);
            dragged = imgNum5;
            y_difference = y - 65*4 - 5;
        }else if(y > 5 + 65*5 && y < 5 + 65*6){
            setmouse(true);
            dragged = imgNum6;
            y_difference = y - 65*5 - 5;
        }else if(y > 5 + 65*6 && y < 5 + 65*7){
            setmouse(true);
            dragged = imgNum7;
            y_difference = y - 65*6 - 5;
        }else if(y > 5 + 65*7 && y < 5 + 65*8){
            setmouse(true);
            dragged = imgNum8;
            y_difference = y - 65*7 - 5;
        }else if(y > 5 + 65*8 && y < 5 + 65*9){
            setmouse(true);
            dragged = imgNum9;
            y_difference = y - 65*8 - 5;
        }
        x_difference = x - 600;
    }

    public void updateboard(int number){
        int x = (mousex - 5) / 65;
        int y = (mousey - 5) / 65;
        System.out.println("x is: "+x+" y is: "+y);
        System.out.println("number  is: "+number);

        switch (number){
            case 1:
                gameboard[x][y] = imgNum1;
                break;
            case 2:
                gameboard[x][y] = imgNum2;
                break;
            case 3:
                gameboard[x][y] = imgNum3;
                break;
            case 4:
                gameboard[x][y] = imgNum4;
                break;
            case 5:
                gameboard[x][y] = imgNum5;
                break;
            case 6:
                gameboard[x][y] = imgNum6;
                break;
            case 7:
                gameboard[x][y] = imgNum7;
                break;
            case 8:
                gameboard[x][y] = imgNum8;
                break;
            case 9:
                gameboard[x][y] = imgNum9;
                break;
        }

    }

    public void solver(int[][] sudoku_puzzle){
        for(int x = 0; x < 9; x++){
            for(int y = 0; y < 9; y++){
                int number = sudoku_puzzle[x][y];
                switch (number){
                    case 1:
                        gameboard[x][y] = imgNum1;
                        break;
                    case 2:
                        gameboard[x][y] = imgNum2;
                        break;
                    case 3:
                        gameboard[x][y] = imgNum3;
                        break;
                    case 4:
                        gameboard[x][y] = imgNum4;
                        break;
                    case 5:
                        gameboard[x][y] = imgNum5;
                        break;
                    case 6:
                        gameboard[x][y] = imgNum6;
                        break;
                    case 7:
                        gameboard[x][y] = imgNum7;
                        break;
                    case 8:
                        gameboard[x][y] = imgNum8;
                        break;
                    case 9:
                        gameboard[x][y] = imgNum9;
                        break;
                }
            }
        }
    }

    public void gameboard(){
        for(int i = 0; i < 9; i++) {
            for (int k = 0; k < 9; k++) {
                gameboard[i][k] = null;
            }
        }
    }

    public sudoku_view(){
        background = loadImage("blank.png");
        gameboard();
    }
}
