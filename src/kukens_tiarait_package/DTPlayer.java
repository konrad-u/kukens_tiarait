package kukens_tiarait_package;

import lenz.htw.tiarait.ColorChange;
import lenz.htw.tiarait.net.NetworkClient;

import java.io.IOException;

public class DTPlayer {

    public static void main(String [] args) throws IOException {

        NetworkClient nC = new NetworkClient("127.0.0.1", "KonradDummyPlayer");

        int player = nC.getMyPlayerNumber(); // 0-3 (WARNING! different numbering than in ColorChange)

        GameBoard myGameBoard = new GameBoard(nC);
        myGameBoard.printBoard();
        GenericBot gBot1 = new GenericBot(
                myGameBoard,
                player,
                0,
                myGameBoard.getBoardField((int)nC.getX(player, 0), (int)nC.getY(player, 0))
        );



        while (nC.isAlive()) {
            /*
            float x = nC.getX(player, botNr);
            float y = nC.getY(player, botNr);

            nC.isWall(7, 11); //true when at coordinate 7,11 there is a blocker (e.g. wall)

            nC.setMoveDirection(0, 0.5f, -0.5f);
            nC.setMoveDirection(1, 0.1f, 1.8f);
            nC.setMoveDirection(2, -5.1f, -0.8f);
            printPositions(player, nC);

             */

            ColorChange cc;
            cc = nC.getNextColorChange();

            while (cc != null) {
                //cc = nC.getNextColorChange();
                System.out.println("a color change happened at "
                        + cc.x + ","
                        + cc.y + " by player "
                        + cc.newColor);
                myGameBoard.updateBoard(cc);
                myGameBoard.printBoard();
                cc = null;
            }
            // if bot isn't at it's goal, send direction via bots .direction.getxDir, .direction.getyDir
            if(!gBot1.atGoal){
                nC.setMoveDirection(0, gBot1.direction.getxDir(), gBot1.direction.getyDir());
                gBot1.checkAtGoal();
            }
            // if it is at it's goal, give it a new one and reset the atGoal boolean to false
            else{
                gBot1.setBotGoal(myGameBoard);
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
