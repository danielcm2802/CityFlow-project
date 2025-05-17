package server;

import java.util.Random;
import static java.lang.Thread.sleep;

public class main {
    public static void main(String[] args) {



        Server server = new Server();
        server.start();


        Random r = new Random();
        for(int i=0;i<100;i++) {
            server.mainSystem.add_vehicle(i, r.nextInt(29), r.nextInt(29));

            try {
                sleep(100*(i%10+1));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}




