package kukens_tiarait_package;

import lenz.htw.tiarait.ColorChange;
import lenz.htw.tiarait.net.NetworkClient;

import java.io.IOException;

public class DTPlayer {

    public static void main(String [] args) throws IOException {

        NetworkClient networkClient = new NetworkClient("127.0.0.1", "...");

        int player = networkClient.getMyPlayerNumber(); // 0-3 (WARNING! different numbering than in ColorChange)

        while (networkClient.isAlive()) {
            int botNr = 0;
            // control
            float x = networkClient.getX(player, botNr);
            float y = networkClient.getY(player, botNr);

            networkClient.isWall(7, 11); //true when at coordinate 7,11 there is a blocker (e.g. wall)

            networkClient.setMoveDirection(0, 0.1f, -0.8f);
            networkClient.setMoveDirection(1, 0.1f, 1.8f);
            networkClient.setMoveDirection(2, -5.1f, -0.8f);

            ColorChange cc;
            while ((cc = networkClient.getNextColorChange()) != null) {
                // integrate cc into own structure
                //z.B.
                //brett[cc.x][cc.y] = cc.newColor;
                //cc.newColor; //0 = empty, 1-4 = player
            }
        }
    }
}
