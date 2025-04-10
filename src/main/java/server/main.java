package server;

import server.algo.CityBuilder;
import server.algo.MinCostRoute;
import server.models.Graph;
import server.models.Route;

import java.util.LinkedList;

public class main {
    public static void main(String[] args) {
        MainSystem ms = new MainSystem(5,4, 700, (short) 2, 300, (short) 1);
        ms.add_vehicle(18,3);

    }


}


