package kukens_tiarait_package;

import lenz.htw.tiarait.ColorChange;
import lenz.htw.tiarait.net.NetworkClient;

import java.io.IOException;

public class DTPlayer {

    public static void main(String [] args) throws IOException {

        NetworkClient nC = new NetworkClient("127.0.0.1", "...");

        int player = nC.getMyPlayerNumber(); // 0-3 (WARNING! different numbering than in ColorChange)

        while (nC.isAlive()) {
            int botNr = 0;
            // control
            float x = nC.getX(player, botNr);
            float y = nC.getY(player, botNr);

            nC.isWall(7, 11); //true when at coordinate 7,11 there is a blocker (e.g. wall)

            nC.setMoveDirection(0, 0.1f, -0.8f);
            nC.setMoveDirection(1, 0.1f, 1.8f);
            nC.setMoveDirection(2, -5.1f, -0.8f);
            printPositions(player, nC);

            ColorChange cc;
            while ((cc = nC.getNextColorChange()) != null) {
                // integrate cc into own structure
                //z.B.
                //brett[cc.x][cc.y] = cc.newColor;
                //cc.newColor; //0 = empty, 1-4 = player
            }
        }
    }
    
    public static void printPositions(int player, NetworkClient nC){
        System.out.println("Player " + player + " Bot 0: " + nC.getX(player, 0) + "," + nC.getY(player, 0));
        System.out.println("Player " + player + " Bot 1: " + nC.getX(player, 1) + "," + nC.getY(player, 0));
        System.out.println("Player " + player + " Bot 2: " + nC.getX(player, 2) + "," + nC.getY(player, 0));
        System.out.println();
    }

}
