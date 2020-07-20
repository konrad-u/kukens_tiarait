package kukens_tiarait_package;
import lenz.htw.tiarait.net.NetworkClient;
import java.io.IOException;

public class DTPlayer extends Thread{

    public static void main(String [] args) throws IOException {

        NetworkClient nC = new NetworkClient("127.0.0.1", "Team K");

        int player = nC.getMyPlayerNumber();

        GameBoard myGameBoard = new GameBoard(nC);

        myGameBoard.printBoard();

        DTPlayerThread bot0 = new DTPlayerThread(nC, player, 0, myGameBoard);
        DTPlayerThread bot1 = new DTPlayerThread(nC, player, 1, myGameBoard);
        DTPlayerThread bot2 = new DTPlayerThread(nC, player, 2, myGameBoard);

        bot0.start();
        bot1.start();
        bot2.start();
    }
}
