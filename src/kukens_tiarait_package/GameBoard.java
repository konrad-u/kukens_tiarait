package kukens_tiarait_package;


/*
Class used to imitate the gameBoard.

boardFields copies gameBoard.


 */

import lenz.htw.tiarait.ColorChange;
import lenz.htw.tiarait.net.NetworkClient;

import java.awt.*;

public class GameBoard {
    char[][] boardFields = new char[32][32];
    NetworkClient nC;

    public GameBoard(NetworkClient nC){
        this.nC = nC;
        for(int i = 0; i < 32; i++){
            for(int j = 0; j < 32; j++){
                if(nC.isWall(i,j)){
                    boardFields[i][j] = '*';
                }
                else{
                    boardFields[i][j] = ' ';
                }
            }
        }
    }

    public void printBoard(){
        System.out.println();
        for(int i = 31; i >= 0; i--) {
            for (int j = 0; j < 32; j++) {
                System.out.print(" ");
                System.out.print(boardFields[j][i]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    public void updateBoard(ColorChange cc){
        boardFields[cc.x][cc.y] = (char) cc.newColor;
    }
}
