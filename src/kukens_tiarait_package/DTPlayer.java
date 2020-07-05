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
                    myGameBoard.getBoardField((int) nC.getX(player, 0), (int) nC.getY(player, 0))
            );

            PainterBot painterBot = new PainterBot(
                    myGameBoard,
                    player,
                    1,
                    myGameBoard.getBoardField((int) nC.getX(player, 1), (int) nC.getY(player, 1)),
                    nC
            );

        while (nC.isAlive()) {

            for(int i = 0; i < myGameBoard.getPlayerScores().length; i++){
                System.out.println("p" + "score " + myGameBoard.getPlayerScores()[i]);
            }
            System.out.println();

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

            //printAllBotPositions(nC);

            //nC.setMoveDirection(1, 0.1f, 1.8f);
            nC.setMoveDirection(2, -5.1f, -0.8f);

            eraserBot.setBotPosition(myGameBoard, (int)nC.getX(player, 0), (int)nC.getY(player,0));
            painterBot.setBotPosition(myGameBoard, (int)nC.getX(player, 1), (int)nC.getY(player,1));


            if(eraserBot.getBotGoal() == null){
                try {
                    eraserBot.setBotGoal(myGameBoard);
                }
                catch (Exception e){
                    System.out.println("couldn't set eraserBots goal...");
                }
            }
            eraserBot.checkAtGoal();
            painterBot.checkAtGoal();

            //myGameBoard.printBoard();

            eraserBot.setBotGoal(myGameBoard);

            //System.out.println("eraserBots pos is: " + eraserBot.getBotPosition().getX() + "," + eraserBot.getBotPosition().getY());
            //System.out.println("eraserBots goal is: " + eraserBot.getBotGoal().getX() + "," + eraserBot.getBotGoal().getY());
            if(!eraserBot.isAtGoal()){
                try {
                    System.out.println("trying to set eraserBots direction...");
                    eraserBot.setDirection(myGameBoard);
                    System.out.println("eraserBots direction was set to " + eraserBot.getDirection().getxDir() + "," + eraserBot.getDirection().getyDir());
                }
                catch (Exception e){
                    System.out.println("couldn't set eraserBots direction");
                }
                try {
                    System.out.println("trying to set networkClient bot 0 direction...");
                    nC.setMoveDirection(0, eraserBot.getDirection().getxDir(), eraserBot.getDirection().getyDir());
                }
                catch (Exception e){
                    System.out.println("couldn't set the networkClient eraserBots direction");
                }
                //eraserBot.checkAtGoal();
            }
            else{
                eraserBot.setBotGoal(myGameBoard);
            }

            painterBot.setBotGoal(myGameBoard);

            //System.out.println("painterBots pos is: " + painterBot.getBotPosition().getX() + "," + painterBot.getBotPosition().getY());
            //System.out.println("painterBots goal is: " + eraserBot.getBotGoal().getX() + "," + eraserBot.getBotGoal().getY());
            if(!painterBot.isAtGoal()){
                try {
                    System.out.println("trying to set painterBots direction...");
                    painterBot.setDirection(myGameBoard);
                    System.out.println("painterBots direction was set to " + painterBot.getDirection().getxDir() + "," + painterBot.getDirection().getyDir());
                }
                catch (Exception e){
                    System.out.println("couldn't set painterBots direction");
                }
                try {
                    System.out.println("trying to set networkClient bot 1 direction...");
                    nC.setMoveDirection(1, painterBot.getDirection().getxDir(), painterBot.getDirection().getyDir());
                }
                catch (Exception e){
                    System.out.println("couldn't set the networkClient painterBots direction");
                }
                //eraserBot.checkAtGoal();
            }
            else{
                painterBot.setBotGoal(myGameBoard);
            }
        }
    }
    
    public static void printPositions(int player, NetworkClient nC){
        for(int i = 0; i < 3; i++) {
            System.out.println("P"
                    + (player+1)
                    + " Bot: "
                    + i
                    + " is at "
                    + (int) nC.getX(player, i)
                    + ","
                    + (int) nC.getY(player, i));
        }
        System.out.println();
    }

    public static void printAllBotPositions(NetworkClient nC){
        for(int j = 0; j < 4; j++){
            System.out.println("Talking about player " + (j+1) );
            printPositions(j, nC);
        }
    }


}
