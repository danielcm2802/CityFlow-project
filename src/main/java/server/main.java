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
//        MainSystem ms = new MainSystem(6,5, 200, (short) 2, 150, (short) 1);
//        Random r = new Random();

//        System.out.println(ms.cityGraph.vertices.get(0).adjacent_edges.get(1).calculateWeight());

//        for(int i=0;i<1;i++) {
//            ms.add_vehicle(i, r.nextInt(19), r.nextInt(19));
//            System.out.println(ms.vehiclesRoutes.get(0).routes);
//            try {
//                sleep(100);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        }
//
//    }
        Server server = new Server();
        server.start();
    }


}


