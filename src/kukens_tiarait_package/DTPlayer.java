package kukens_tiarait_package;

import lenz.htw.tiarait.ColorChange;
import lenz.htw.tiarait.net.NetworkClient;

import java.io.IOException;

public class DTPlayer {

    public static void main(String [] args) throws IOException {

        NetworkClient networkClient = new NetworkClient("127.0.0.1", "...");

        int player = networkClient.getMyPlayerNumber(); // 0-3 (ACHTUNG! andere Nummerierung als beim ColorChange)

        while (networkClient.isAlive()) {
            int botNr = 0;
            // steuerung
            float x = networkClient.getX(player, botNr);
            float y = networkClient.getY(player, botNr);

            networkClient.isWall(7, 11); //true wenn bei Koordinate 7,11 ein Hindernis steht

            networkClient.setMoveDirection(0, 0.1f, -0.8f);
            networkClient.setMoveDirection(1, 0.1f, 1.8f);
            networkClient.setMoveDirection(2, -5.1f, -0.8f);

            ColorChange cc;
            while ((cc = networkClient.getNextColorChange()) != null) {
                // cc in eigene Struktur einarbeiten
                //z.B.brett[cc.x][cc.y] = cc.newColor;
                //cc.newColor; //0 = leer, 1-4 = spieler
            }
        }
    }
}
