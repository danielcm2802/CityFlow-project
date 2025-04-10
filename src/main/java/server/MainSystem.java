package server;

import server.algo.CityBuilder;
import server.algo.MinCostRoute;
import server.algo.TrafficManager;
import server.models.Graph;
import server.models.Vehicle;

import java.time.LocalTime;
import java.util.ArrayList;

public class MainSystem {
    public Graph cityGraph;
    public ArrayList<Vehicle> vehiclesRoutes;

    public MainSystem(int rows, int cols, int hor_len, short hor_lanes, int ver_len, short ver_lanes) {
        this.cityGraph = CityBuilder.build_city_Grid(rows,cols,hor_len,hor_lanes,ver_len,ver_lanes);
        this.vehiclesRoutes = new ArrayList<>();
        TrafficManager tm = new TrafficManager(this.vehiclesRoutes, this.cityGraph);
        tm.start();
    }

    public void add_vehicle(int source, int destination) {
        this.vehiclesRoutes.addLast(
                new Vehicle(MinCostRoute.findMinCostRoute(this.cityGraph,source,destination)));

        System.out.println(this.vehiclesRoutes.getLast().routes.toString());
    }

    public void remove_vehicle(int indx) {
        this.vehiclesRoutes.remove(indx);
    }

}
