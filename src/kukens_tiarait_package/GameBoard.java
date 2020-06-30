package kukens_tiarait_package;


/*
Class used to imitate the gameBoard.

boardFields copies gameBoard.
int[] pXScore count player scores, with the position indicating playerNumber.


 */

import lenz.htw.tiarait.ColorChange;
import lenz.htw.tiarait.net.NetworkClient;

import java.awt.*;

public class GameBoard {
    BoardField[][] boardFields = new BoardField[32][32];
    NetworkClient nC;

    int[] playerScores = new int[4];

    public GameBoard(NetworkClient nC){
        this.nC = nC;

        for(int i = 0; i < 32; i++){
            for(int j = 0; j < 32; j++){
                boardFields[i][j] = new BoardField(i,j,0);
                if(nC.isWall(i,j)){
                    boardFields[i][j].setFieldValue(5);
                }
            }
        }

    }

    public void printBoard(){
        System.out.println();
        for(int i = 31; i >= 0; i--) {
            for (int j = 0; j < 32; j++) {
                System.out.print(" ");
                if(boardFields[j][i].getFieldValue() == 5) {
                    System.out.print('*');
                }
                else if(boardFields[j][i].getFieldValue() == 0){
                    System.out.print(" ");
                }
                else{
                    System.out.print(boardFields[j][i].getFieldValue());
                }
                System.out.print(" ");
            }
            System.out.println();
        }
        for(int k = 0; k < playerScores.length; k++){
            System.out.println("Player: " + (k+1) + " Score: " + playerScores[k]);
        }

    }

    public void updateBoard(ColorChange cc){
        boardFields[cc.x][cc.y].setFieldValue(cc.newColor);
        updateScores();
    }

    public void updateScores(){
        for(int k = 0; k < playerScores.length; k++){
            playerScores[k] = 0;
        }
        for(int i = 0; i < 32; i++) {
            for (int j = 0; j < 32; j++) {
                switch (boardFields[i][j].getFieldValue()){
                    case(1):
                        playerScores[0]++;
                        break;
                    case(2):
                        playerScores[1]++;
                        break;
                    case(3):
                        playerScores[2]++;
                        break;
                    case(4):
                        playerScores[3]++;
                        break;
                }
            }
        }
    }
}
