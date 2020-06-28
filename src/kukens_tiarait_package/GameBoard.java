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
    char[][] boardFields = new char[32][32];
    NetworkClient nC;

    int[] playerScores = new int[4];

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
        for(int k = 0; k < playerScores.length; k++){
            System.out.println("Player: " + k+1 + " Score: " + playerScores[k]);
        }

    }

    public void updateBoard(ColorChange cc){
        char newFieldValue = (char)(cc.newColor + '0');
        int boardFieldChar = Character.getNumericValue(boardFields[cc.x][cc.y]);

        System.out.println("..............." + cc.newColor);

        if(cc.newColor < 1 || boardFieldChar+1 != cc.newColor){
            playerScores[boardFieldChar]--;
            boardFields[cc.x][cc.y] = (char)0 + '0';
        }
        else{
            playerScores[cc.newColor-1]++;
            boardFields[cc.x][cc.y] = newFieldValue;
        }

        /*
        switch(newFieldValue){
            case('0'):
                playerScores[0]--;
                boardFields[cc.x][cc.y] = newFieldValue;

                break;
            case('1'):

                break;
            case('2'):

                break;

            case('3'):

                break;

        }
        if(newFieldValue == '0'){
            newFieldValue = ' ';
        }
        else {
            boardFields[cc.x][cc.y] = newFieldValue;
        }

         */
    }
}
