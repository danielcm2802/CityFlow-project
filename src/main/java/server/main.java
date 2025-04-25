package server;

import java.util.Random;
import static java.lang.Thread.sleep;

public class main {
    public static void main(String[] args) {

        Random r = new Random();

        Server server = new Server();
        server.start();

        for(int i=0;i<1;i++) {
            server.mainSystem.add_vehicle(i, r.nextInt(19), r.nextInt(19));
            try {
                sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}




