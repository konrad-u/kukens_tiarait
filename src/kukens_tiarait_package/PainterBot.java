package kukens_tiarait_package;

import lenz.htw.tiarait.net.NetworkClient;

public class PainterBot extends AbstractBot {

    NetworkClient nC;

    public PainterBot(GameBoard gameBoard, int playerNumber, int botNr, BoardField botPosition, NetworkClient nC) {
        super(gameBoard, playerNumber, botNr, botPosition);
        this.nC = nC;
    }

    @Override
    public void setBotGoal(GameBoard gameBoard){

        setBotGoal(getHighestScoringEnemyPainterBotPosition(gameBoard, nC));


    }

    public BoardField getHighestScoringEnemyPainterBotPosition(GameBoard gameBoard, NetworkClient nC){
        int highestEnemyScore = -1;
        int highestEnemy = getPlayerNumber();

        for(int player = 0; player < gameBoard.getPlayerScores().length; player++){

            int playerScore = gameBoard.getPlayerScores()[player];

            //if the player isn't us
            if(player != getPlayerNumber()){
                if(playerScore > highestEnemyScore){
                    highestEnemy = player;
                    highestEnemyScore = playerScore;
                }
            }
        }
        BoardField highestEnemyPainterBot = null;
        try {
            highestEnemyPainterBot = gameBoard.getBoardField((int) nC.getX(highestEnemy, 1), (int) nC.getY(highestEnemy, 1));
        }
        catch(Exception e){
            highestEnemyPainterBot = gameBoard.getBoardField(15,15);
        }
        return highestEnemyPainterBot;
    }
}
