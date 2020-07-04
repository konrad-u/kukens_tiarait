package kukens_tiarait_package;

import lenz.htw.tiarait.ColorChange;
import lenz.htw.tiarait.net.NetworkClient;

import java.io.IOException;

public class DTPlayer {

    public static void main(String [] args) throws IOException {

        NetworkClient nC = new NetworkClient("127.0.0.1", "GenBot");

        int player = nC.getMyPlayerNumber(); // 0-3 (WARNING! different numbering than in ColorChange)

        GameBoard myGameBoard = new GameBoard(nC);
        myGameBoard.printBoard();
        GenericBot gBot0 = new GenericBot(
                myGameBoard,
                player,
                0,
                myGameBoard.getBoardField((int)nC.getX(player, 0), (int)nC.getY(player, 0))
        );

        gBot0.setBotGoal(myGameBoard.getBoardField(20,20));
        //gBot0.createPath(myGameBoard);

        /*
        System.out.println("..............testing pathfinding methods, gBot0 goal set to "
                + gBot0.getBotGoal().getX()
                + ","
                + gBot0.getBotGoal().getY());
        System.out.println("gBot0 is at "
                + gBot0.getBotPosition().getX()
                + ","
                + gBot0.getBotPosition().getY());
        myGameBoard.printBoard();

         */

        while (nC.isAlive()) {

            /*
            float x = nC.getX(player, botNr);
            float y = nC.getY(player, botNr);

            nC.isWall(7, 11); //true when at coordinate 7,11 there is a blocker (e.g. wall)

            nC.setMoveDirection(0, 0.5f, -0.5f);

            printPositions(player, nC);

             */
            //nC.setMoveDirection(1, 0.1f, 1.8f);
            //nC.setMoveDirection(2, -5.1f, -0.8f);

            ColorChange cc;
            cc = nC.getNextColorChange();

            while (cc != null) {
                System.out.println("a color change happened at "
                        + cc.x + ","
                        + cc.y + " by player "
                        + cc.newColor);
                myGameBoard.updateBoard(cc);
                cc = null;
            }

            gBot0.setBotPosition(myGameBoard, (int)nC.getX(player, 0), (int)nC.getY(player,0));
            gBot0.checkAtGoal();
            //THIS IS THE EXTRA PRINTING: 6 = x, 7 = o, 8 = S, 9 = G
            myGameBoard.printBoard();

            gBot0.setDirection(myGameBoard);
            // if bot isn't at it's goal, send direction via bots .direction.getxDir, .direction.getyDir
            if(!gBot0.isAtGoal()){
                nC.setMoveDirection(0, gBot0.getDirection().getxDir(), gBot0.getDirection().getyDir());
                gBot0.checkAtGoal();
                gBot0.setDirection(myGameBoard);
            }
            // if it is at it's goal, give it a new one and reset the atGoal boolean to false
            else{
            }
        }
    }
    
    public static void printPositions(int player, NetworkClient nC){
        for(int i = 0; i < 3; i++) {
            System.out.println("Player "
                    + player
                    + " Bot: "
                    + i
                    + " is at "
                    + (int) nC.getX(player, i)
                    + ","
                    + (int) nC.getY(player, i));
        }
        System.out.println();
    }

}
