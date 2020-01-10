package files;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.geom.*;
import java.io.*;

public class sudoku_model{

    int gamemode = 0;
    int sudoku_array[][] = new int[9][9];
    int num = 0;
    int x = -1;
    int y = -1;

    public void get_possible_number(int y){
        num = (y-5) / 65 + 1;
    }

    public boolean valid_placement(int x, int y){
        if(x < 5 || y < 5 || y > 5 + 65*9 || x > 5 + 65*9 || gamemode == 0){
            return false;
        }
        else{
            this.x = (x - 5) / 65;
            this.y = (y - 5) / 65;
            System.out.println("x is: "+ this.x + " y is: "+ this.y);
            return true;
        }
    }

    public void setSudokuEmpty(){
        for(int i = 0; i < 9; i++) {
            for (int k = 0; k < 9; k++) {
                sudoku_array[i][k] = 0;
            }
        }
    }

    public int make_placement(){
        sudoku_array[x][y] = num;
        return num;
    }

    public void setGamemode(int number){
        gamemode = number;
    }

    public sudoku_model(){
        setSudokuEmpty();
    }

    private boolean checkPuzzle(){
        int i = 0;
        int j = 0;
        int counter = 0;
        int holder = 0;
        for(i = 0; i < 9; i++){
            for(counter = 0; counter < 9; counter++){
                holder = sudoku_array[i][counter];
                for(j = 0; j < 9; j++){
                    if(holder == sudoku_array[i][j] && j != counter && holder != 0){
                        return false;
                    }

                }
            }
        }
        counter = 0;
        for(i = 0; i < 9; i++){
            for(counter = 0; counter < 9; counter++){
                holder = sudoku_array[counter][i];
                for(j = 0; j < 9; j++){
                    if(holder == sudoku_array[j][i] && j != counter && holder != 0){
                        return false;
                    }

                }
            }
        }
        counter = 0;

        for(int t = 0; t < 3; t++){
            for(int q = 0; q < 3; q++){
                for(int a = t*3; a < t*3+3; a++){
                    for(int b = q*3; b < q*3+3;b++){
                        holder = sudoku_array[a][b];
                        for(i = t*3; i < t*3+3; i++){
                            for(j = q*3; j < q*3+3;j++){
                                if(holder == sudoku_array[i][j] && i != a && j != b && holder != 0){
                                    return false;
                                }
                            }
                        }
                    }
                }
            }

        }
        return true;
    }

    private boolean checkFinished(){
        int i = 0;
        int j = 0;
        int holder = 0;
        for(i = 0; i < 9; i++){
            for(j = 0; j < 9; j++){
                holder = holder + sudoku_array[i][j];
            }
            if(holder != 45){
                return false;
            }
            holder = 0;
        }

        for(i = 0; i < 9; i++){
            for(j = 0; j < 9; j++){
                holder = holder + sudoku_array[j][i];
            }
            if(holder != 45){
                return false;
            }
            holder = 0;
        }

        holder = 0;

        for(int t = 0; t < 3; t++){
            for(int q = 0; q < 3; q++){
                for(int a = t*3; a < t*3+3; a++){
                    for(int b = q*3; b < q*3+3;b++){
                        holder = sudoku_array[a][b] + holder;
                    }
                }
                if(holder != 45){
                    return false;
                }
                holder = 0;
            }

        }
        return true;

    }

    private void solveSudoku(){
        int i = 0;
        int j = 0;
        int t = 0;
        int a = -1;
        int b = -1;
        int num[] = {0,0,0,0,0,0,0,0,0,0};
        for(i = 0; i < 9; i++){
            for(j = 0; j < 9; j++){
                if(sudoku_array[i][j] == 0){
                    if(t == 0){
                        a = i;
                        b = j;
                        t = 1;
                    }
                }
            }
        }
        if(b == -1 && a == -1){
            return;
        }
        j = 0;
        boolean w;
        for( i =1; i < 10; i++){
            sudoku_array[a][b] = i;
            w = checkPuzzle();
            sudoku_array[a][b] = 0;
            if( w){
                num[j] = i;
                j++;
            }
        }
        for(i = 0; i < j; i++){
            sudoku_array[a][b] = num[i];
            solveSudoku();
            w = checkFinished();
            if(w){
                return;
            }
            if(num[i+1] == 0){
                sudoku_array[a][b] = 0;
                return;
            }
            num[i] = 0;
        }


    }

    private int get_sudoku_number(){
        return 0;
    }

    public int[][] generate_puzzle(){
        int num;
        return null;
    }

    public boolean solve(){
        solveSudoku();
        return checkFinished();
    }

    public int[][] get_sudoku(){
        return sudoku_array;
    }


}
