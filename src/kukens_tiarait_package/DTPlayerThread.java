package kukens_tiarait_package;

import lenz.htw.tiarait.ColorChange;
import lenz.htw.tiarait.net.NetworkClient;

public class DTPlayerThread extends Thread{
    
    AbstractBot threadBot;
    EraserBot eraserBot;
    PainterBot painterBot;
    NetworkClient nC;
    int player;
    int botNumber;
    GameBoard myGameBoard;
    /*

    public DTPlayerThread(NetworkClient nC, int player, int botNumber, GameBoard myGameBoard){
        this.nC = nC;
        this.player = player;
        this.botNumber = botNumber;
        this.myGameBoard = myGameBoard;

        switch(botNumber){
            case(0):
                threadBot = new EraserBot(
                        myGameBoard,
                        player,
                        0,
                        myGameBoard.getBoardField((int) nC.getX(player, botNumber), (int) nC.getY(player, botNumber))
                );
                break;
                case(1):
            threadBot = new PainterBot(
                    myGameBoard,
                    player,
                    0,
                    myGameBoard.getBoardField((int) nC.getX(player, botNumber), (int) nC.getY(player, botNumber)),nC
            );
                break;
        }


    }

    public void run(){

        while (nC.isAlive()) {

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

            for (int i = 0; i < myGameBoard.getPlayerScores().length; i++) {
                System.out.println("p" + "score " + myGameBoard.getPlayerScores()[i]);
            }
            System.out.println();



            threadBot.setBotPosition(myGameBoard, (int) nC.getX(player, botNumber), (int) nC.getY(player, botNumber));

            if (threadBot.getBotGoal() == null) {
                try {
                    threadBot.setBotGoal(myGameBoard);
                } catch (Exception e) {
                    System.out.println("couldn't set threadBot " + player + " goal...");
                }
            }
            threadBot.checkAtGoal();

            //myGameBoard.printBoard();

            threadBot.setBotGoal(myGameBoard);
            try {
                System.out.println("trying to set threadBot " + botNumber + " direction...");
                threadBot.setDirection(myGameBoard);
                System.out.println("threadBots direction was set to " + threadBot.getDirection().getxDir() + "," + threadBot.getDirection().getyDir());
            } catch (Exception e) {
                System.out.println("couldn't set threadBot " + botNumber + " direction");
            }
            nC.setMoveDirection(botNumber, threadBot.getDirection().getxDir(), threadBot.getDirection().getyDir());

        }

    }

     */
    
}
