package kukens_tiarait_package;

import lenz.htw.tiarait.ColorChange;
import lenz.htw.tiarait.net.NetworkClient;

import java.io.IOException;

public class DTPlayer {

    public static void main(String [] args) throws IOException {

        NetworkClient nC = new NetworkClient("127.0.0.1", "...");

        int player = nC.getMyPlayerNumber(); // 0-3 (WARNING! different numbering than in ColorChange)

        GameBoard myGameBoard = new GameBoard(nC);
        myGameBoard.printBoard();

        while (nC.isAlive()) {
            //int botNr = 0;
            // control
            //float x = nC.getX(player, botNr);
            //float y = nC.getY(player, botNr);

            nC.isWall(7, 11); //true when at coordinate 7,11 there is a blocker (e.g. wall)

            nC.setMoveDirection(0, 0.1f, -0.8f);
            nC.setMoveDirection(1, 0.1f, 1.8f);
            nC.setMoveDirection(2, -5.1f, -0.8f);
            //printPositions(player, nC);

            ColorChange cc;
            cc = nC.getNextColorChange();
            while (cc != null) {
                System.out.println("a color change happened at " + cc.x + "," + cc.y + " by player " + cc.newColor);
                myGameBoard.updateBoard(cc);
                myGameBoard.printBoard();
                // integrate cc into own structure
                //z.B.
                //brett[cc.x][cc.y] = cc.newColor;
                //cc.newColor; //0 = empty, 1-4 = player
            }
        }
    }
    
    public static void printPositions(int player, NetworkClient nC){
        for(int i = 0; i < 3; i++) {
            System.out.println("Player " + player + " Bot: " + i + " is at " + (int) nC.getX(player, i) + "," + (int) nC.getY(player, i));
        }
        System.out.println();
    }

}
