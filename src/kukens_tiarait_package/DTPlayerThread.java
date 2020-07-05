package kukens_tiarait_package;

import lenz.htw.tiarait.ColorChange;
import lenz.htw.tiarait.net.NetworkClient;

public class DTPlayerThread extends Thread{
    
    AbstractBot threadBot;
    NetworkClient nC;
    int player;
    int botNumber;
    GameBoard myGameBoard;
    

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
                        botNumber,
                        myGameBoard.getBoardField((int) nC.getX(player, botNumber), (int) nC.getY(player, botNumber))
                );
                break;
                case(1):
            threadBot = new PainterBot(
                    myGameBoard,
                    player,
                    botNumber,
                    myGameBoard.getBoardField((int) nC.getX(player, botNumber), (int) nC.getY(player, botNumber)),nC
            );
                break;
            case(2):
                threadBot = new BigPainterBot(
                        myGameBoard,
                        player,
                        botNumber,
                        myGameBoard.getBoardField((int) nC.getX(player, botNumber), (int) nC.getY(player, botNumber))
                );
                break;
        }


    }

    public void run() {

        if (threadBot.getBotGoal() == null) {
            try {
                threadBot.setBotGoal(myGameBoard.getBoardField(15, 15));
            } catch (Exception e) {
                System.out.println("couldn't set threadBot " + player + " goal...");
            }
        }
        threadBot.checkAtGoal();

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


            threadBot.checkAtGoal();
            if (threadBot.isAtGoal()) {
                threadBot.setBotGoal(myGameBoard);
            }

            //else {
            try {
                if(botNumber!=0)
                    System.out.println("trying to set bot " + botNumber + " direction");
                threadBot.setDirection(myGameBoard);
                System.out.println("bot " + botNumber + " direction was set to " + threadBot.getDirection().getxDir() + "," + threadBot.getDirection().getyDir());
            } catch (Exception e) {
                System.out.println("couldn't set bot " + botNumber + " direction");
            }
            nC.setMoveDirection(botNumber, threadBot.getDirection().getxDir(), threadBot.getDirection().getyDir());
            //}
        }
    }
        }
