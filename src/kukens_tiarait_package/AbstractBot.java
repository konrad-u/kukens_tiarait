package kukens_tiarait_package;

/*
AbstracBot determines everything that each bot needs to be able to do.

Each bot must be able to:
- determine a goal for movement
    - the goal is bot-dependent, as each bot has different skills which maximize my score

- calculate how to get there (most likely via an A* pathfinding algorithm)
-- this can return a direction object, which holds an xDir and yDir float

- give the NetworkClient the appropriate parameters for its
  nC.setMoveDirection(0, 0.1f, -0.8f);
  method (botNumber 0-2, xDirFloat, yDirFloat)

- scan the board, checking if goal is still in line with the current game setup

 */

public abstract class AbstractBot {
//0 = pyramid on head, eraser 110% speed
//1 = rectangle, colors 100% speed
//2 = pyramid, colors 2x 65% speed

    int playerNumber;
    int botNr;
    BoardField botPosition;
    BoardField botGoal;
    BotDirection direction;
    boolean atGoal;

    public AbstractBot(int playerNumber, int botNr, BoardField botPosition){
        this.playerNumber = playerNumber;
        this.botNr = botNr;
        this.botPosition = botPosition;
        atGoal = false;
    }

    //updates goal based on the current gameBoard
    public void setBotGoal(GameBoard currentBoard){
    }

    public void setBotPosition(int xPos, int yPos){
        botPosition = new BoardField(xPos, yPos, playerNumber);
    }

    public void setDirection(GameBoard currentBoard) {
        this.direction = new BotDirection(currentBoard, botPosition, botGoal);
    }

    // class holds the two axis directional floats used in nc.setMoveDirection
    public class BotDirection {

        private float xDir;
        private float yDir;

        //where the pathfinding happens
        public BotDirection(GameBoard gameBoard, BoardField botPosition, BoardField botGoal){
            
        };

        public float getxDir() {
            return xDir;
        }

        public void setxDir(float xDir) {
            this.xDir = xDir;
        }

        public float getyDir() {
            return yDir;
        }

        public void setyDir(float yDir) {
            this.yDir = yDir;
        }
    }
}
