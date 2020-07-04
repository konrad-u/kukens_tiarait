package kukens_tiarait_package;

/*
EraserBot is the upside down pyramid.
Moves at 100% Speed
Erases everything

Should avoid erasing own color

setBotGoal is the method used to determine what the

 */


import java.lang.reflect.Array;
import java.util.ArrayList;

public class EraserBot extends AbstractBot {

    public EraserBot(GameBoard gameBoard, int playerNumber, int botNr, BoardField botPosition) {
        super(gameBoard, playerNumber, botNr, botPosition);
    }



    /*
    First version:
    find closest color field which isn't of own color
    go to it
    find next



    Second version:
    get player with highest score
    create ArrayList of all fields in their color
    sort them by distance between each other (TBI)
    This ArrayList makes up the goals for the eraserBot

    Preference weighting can later be implemented to consider running over other enemy fields, and avoiding own fields (TBI)
     */

    @Override
    public void setBotGoal(GameBoard gameBoard) {

        int lowestDistance = 999;
        BoardField closestEnemyField = null;

        //1. scan whole board for all enemy fields, adding to the allEnemyFields arraylist
        for(int i = 0; i < 32; i++){
            for(int j = 0; j < 32; j++){

                BoardField bf = gameBoard.getBoardField(i,j);

                if(bf.isEnemyPainted(getPlayerNumber())){
                    int distanceToEnemyField = getBotPosition().distanceTo(bf);

                    if(distanceToEnemyField < lowestDistance){
                        lowestDistance = distanceToEnemyField;
                        closestEnemyField = bf;
                    }

                }
            }
        }
        if(closestEnemyField == null){
            setBotGoal(getBotPosition());
        }
        else {
            setBotGoal(closestEnemyField);
        }
    }
}
