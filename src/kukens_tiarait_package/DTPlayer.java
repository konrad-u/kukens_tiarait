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
        EraserBot eraserBot = new EraserBot(
                myGameBoard,
                player,
                0,
                myGameBoard.getBoardField((int)nC.getX(player, 0), (int)nC.getY(player, 0))
        );

        //eraserBot.setBotGoal(myGameBoard);

        while (nC.isAlive()) {

            nC.setMoveDirection(1, 0.1f, 1.8f);
            nC.setMoveDirection(2, -5.1f, -0.8f);

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

            eraserBot.setBotPosition(myGameBoard, (int)nC.getX(player, 0), (int)nC.getY(player,0));

            if(eraserBot.getBotGoal() == null){
                eraserBot.setBotGoal(eraserBot.getBotPosition());
            }
            eraserBot.checkAtGoal();



            myGameBoard.printBoard();

            if(!eraserBot.isAtGoal()){
                eraserBot.setBotGoal(myGameBoard);
                try {
                    eraserBot.setDirection(myGameBoard);
                }
                catch (Exception e){
                    System.out.println("couldn't set eraserBots direction...");
                }
                nC.setMoveDirection(0, eraserBot.getDirection().getxDir(), eraserBot.getDirection().getyDir());
                //eraserBot.checkAtGoal();
            }
            else{
                eraserBot.setBotGoal(myGameBoard);
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
