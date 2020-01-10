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

    public BufferedImage loadImage(String strNum){
        BufferedImage image = null;
        String strname = "images\\" + strNum;
        try{
            image = ImageIO.read(new File("1.png"));
        }catch (IOException e){
            System.out.println(strname);
        }
        return image;
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        if(blnGame){
            g.drawImage(imgNum1, 500, 500,null);
        }

    }




    public sudoku_view(){
        background = loadImage("blank.png");
    }
}
