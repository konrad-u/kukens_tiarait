package kukens_tiarait_package;

import lenz.htw.tiarait.ColorChange;
import lenz.htw.tiarait.net.NetworkClient;

import java.io.IOException;

public class DTPlayer extends Thread{

    public static void main(String [] args) throws IOException {

        NetworkClient nC = new NetworkClient("127.0.0.1", "GenBot");

        int player = nC.getMyPlayerNumber(); // 0-3 (WARNING! different numbering than in ColorChange)

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
