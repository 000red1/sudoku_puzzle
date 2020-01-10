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
    int[][] tracker = new int[9][9];

    BufferedImage gameboard[][] = new BufferedImage[9][9];


    //Numbers
    BufferedImage imgNum1blue;
    BufferedImage imgNum2blue;
    BufferedImage imgNum3blue;
    BufferedImage imgNum4blue;
    BufferedImage imgNum5blue;
    BufferedImage imgNum6blue;
    BufferedImage imgNum7blue;
    BufferedImage imgNum8blue;
    BufferedImage imgNum9blue;
    BufferedImage imgNum1green;
    BufferedImage imgNum2green;
    BufferedImage imgNum3green;
    BufferedImage imgNum4green;
    BufferedImage imgNum5green;
    BufferedImage imgNum6green;
    BufferedImage imgNum7green;
    BufferedImage imgNum8green;
    BufferedImage imgNum9green;
    BufferedImage dragged;
    BufferedImage background;

    public void loadNumbers(){
        imgNum1blue = loadImage("1blue.png");
        imgNum2blue = loadImage("2blue.png");
        imgNum3blue = loadImage("3blue.png");
        imgNum4blue = loadImage("4blue.png");
        imgNum5blue = loadImage("5blue.png");
        imgNum6blue = loadImage("6blue.png");
        imgNum7blue = loadImage("7blue.png");
        imgNum8blue = loadImage("8blue.png");
        imgNum9blue = loadImage("9blue.png");
        imgNum1green = loadImage("1green.png");
        imgNum2green = loadImage("2green.png");
        imgNum3green = loadImage("3green.png");
        imgNum4green = loadImage("4green.png");
        imgNum5green = loadImage("5green.png");
        imgNum6green = loadImage("6green.png");
        imgNum7green = loadImage("7green.png");
        imgNum8green = loadImage("8green.png");
        imgNum9green = loadImage("9green.png");
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
            }

            g.drawImage(imgNum1blue, 615, 5,null);
            g.drawImage(imgNum2blue, 615, 5 + 65,null);
            g.drawImage(imgNum3blue, 615, 5 + 65*2,null);
            g.drawImage(imgNum4blue, 615, 5 + 65*3,null);
            g.drawImage(imgNum5blue, 615, 5 + 65*4,null);
            g.drawImage(imgNum6blue, 615, 5 + 65*5,null);
            g.drawImage(imgNum7blue, 615, 5 + 65*6,null);
            g.drawImage(imgNum8blue, 615, 5 + 65*7,null);
            g.drawImage(imgNum9blue, 615, 5 + 65*8,null);
            g.setColor(new Color(0,0,0));
            for(int i = 0; i <= 9; i++) {
                g.drawLine(5 + 65*i,5,5 + 65*i,5+ 65*9);
                g.drawLine(5,5 + 65*i,5+ 65*9,5 + 65*i);
                for (int k = 0; k < 9 && i < 9; k++) {
                    g.drawImage(gameboard[i][k], 5 + 65*i, 5 + 65*k, null);
                }
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
        if(y > 5 && y < 5 + 65){
            setmouse(true);
            dragged = imgNum1blue;
            y_difference = y - 5;
        }else if(y > 5 + 65 && y < 5 + 65*2){
            setmouse(true);
            dragged = imgNum2blue;
            y_difference = y - 65 - 5;
        }else if(y > 5 + 65*2 && y < 5 + 65*3){
            setmouse(true);
            dragged = imgNum3blue;
            y_difference = y - 65*2 - 5;
        }else if(y > 5 + 65*3 && y < 5 + 65*4){
            setmouse(true);
            dragged = imgNum4blue;
            y_difference = y - 65*3 - 5;
        }else if(y > 5 + 65*4 && y < 5 + 65*5){
            setmouse(true);
            dragged = imgNum5blue;
            y_difference = y - 65*4 - 5;
        }else if(y > 5 + 65*5 && y < 5 + 65*6){
            setmouse(true);
            dragged = imgNum6blue;
            y_difference = y - 65*5 - 5;
        }else if(y > 5 + 65*6 && y < 5 + 65*7){
            setmouse(true);
            dragged = imgNum7blue;
            y_difference = y - 65*6 - 5;
        }else if(y > 5 + 65*7 && y < 5 + 65*8){
            setmouse(true);
            dragged = imgNum8blue;
            y_difference = y - 65*7 - 5;
        }else if(y > 5 + 65*8 && y < 5 + 65*9){
            setmouse(true);
            dragged = imgNum9blue;
            y_difference = y - 65*8 - 5;
        }
        x_difference = x - 615;
    }

    public void updateboard(int number){
        int x = (mousex - 5) / 65;
        int y = (mousey - 5) / 65;
        tracker[x][y] = number;
        switch (number) {
            case 1:
                gameboard[x][y] = imgNum1blue;
                break;
            case 2:
                gameboard[x][y] = imgNum2blue;
                break;
            case 3:
                gameboard[x][y] = imgNum3blue;
                break;
            case 4:
                gameboard[x][y] = imgNum4blue;
                break;
            case 5:
                gameboard[x][y] = imgNum5blue;
                break;
            case 6:
                gameboard[x][y] = imgNum6blue;
                break;
            case 7:
                gameboard[x][y] = imgNum7blue;
                break;
            case 8:
                gameboard[x][y] = imgNum8blue;
                break;
            case 9:
                gameboard[x][y] = imgNum9blue;
                break;
            }

    }

    public void solver(int[][] sudoku_puzzle){
        for(int x = 0; x < 9; x++){
            for(int y = 0; y < 9; y++){
                int number = sudoku_puzzle[x][y];
                if(tracker[x][y] == 0 || (tracker[x][y] != 0 && tracker[x][y] != number)) {
                    switch (number) {
                        case 1:
                            gameboard[x][y] = imgNum1green;
                            break;
                        case 2:
                            gameboard[x][y] = imgNum2green;
                            break;
                        case 3:
                            gameboard[x][y] = imgNum3green;
                            break;
                        case 4:
                            gameboard[x][y] = imgNum4green;
                            break;
                        case 5:
                            gameboard[x][y] = imgNum5green;
                            break;
                        case 6:
                            gameboard[x][y] = imgNum6green;
                            break;
                        case 7:
                            gameboard[x][y] = imgNum7green;
                            break;
                        case 8:
                            gameboard[x][y] = imgNum8green;
                            break;
                        case 9:
                            gameboard[x][y] = imgNum9green;
                            break;
                    }
                }
            }
        }
    }

    public void gameboard(){
        for(int i = 0; i < 9; i++) {
            for (int k = 0; k < 9; k++) {
                gameboard[i][k] = null;
                tracker[i][k] = 0;
            }
        }
    }

    public sudoku_view(){
        background = loadImage("blank.png");
        gameboard();
    }
}
