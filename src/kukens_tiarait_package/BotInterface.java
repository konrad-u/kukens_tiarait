package kukens_tiarait_package;

/*
Bot interface determines everything that each bot needs to be able to do.

Each bot must be able to:
- determine a goal for movement
    - the goal is bot-dependent, as each bot has different skills which maximize my score

- calculate how to get there (most likely via an A* pathfinding algorithm

- give the NetworkClient the appropriate parameters for its
  nC.setMoveDirection(0, 0.1f, -0.8f);
  method (botNumber 0-2, xDirFloat, yDirFloat)

- scan the board, checking if goal is still in line with the current game setup

 */

public interface BotInterface {
    //0 = pyramid on head, eraser 110% speed
    //1 = rectangle, colors 100% speed
    //2 = pyramid, colors 2x 65% speed
    


}
