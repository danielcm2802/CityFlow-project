package server;

import server.algo.CityBuilder;
import server.algo.MinCostRoute;
import server.models.*;

import java.util.LinkedList;
import java.util.Random;

import static java.lang.Thread.sleep;

public class main {
    public static void main(String[] args) {

//        LinkedList<Route> routes = new LinkedList<>();
//        routes.add(new Route(RoadType.Intersection,1,4));
//        System.out.println(routes);
        MainSystem ms = new MainSystem(5,4, 30, (short) 2, 15, (short) 1);
        Random r = new Random();



        for(int i=0;i<30;i++) {
            ms.add_vehicle(i, r.nextInt(19), r.nextInt(19));
            try {
                sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }


}


