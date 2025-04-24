package server;

import model.Route;
import server.algo.CityBuilder;
import server.algo.MinCostRoute;
import server.algo.TrafficManager;
import server.gui.MainWindow;
import server.models.Graph;
import server.models.Vehicle;

import java.util.ArrayList;
import java.util.LinkedList;

public class MainSystem {
    public Graph cityGraph;
    public ArrayList<Vehicle> vehiclesRoutes;
    public MainWindow mainWindow;

    public MainSystem(int rows, int cols, int hor_len, short hor_lanes, int ver_len, short ver_lanes) {
        this.cityGraph = CityBuilder.build_city_Grid(rows,cols,hor_len,hor_lanes,ver_len,ver_lanes);
        this.vehiclesRoutes = new ArrayList<>();
        MinCostRoute.futureTraffic = CityBuilder.build_city_Grid(rows,cols,hor_len,hor_lanes,ver_len,ver_lanes);
        mainWindow = new MainWindow(cityGraph,vehiclesRoutes);
        TrafficManager tm = new TrafficManager(this.vehiclesRoutes, this.cityGraph,this.mainWindow.cityMap);
        tm.start();
    }

    public LinkedList<Route> add_vehicle(int id, int source, int destination) {
        synchronized (vehiclesRoutes) {
            Vehicle vehicle = new Vehicle(id, MinCostRoute.findMinCostRoute(this.cityGraph, vehiclesRoutes, source, destination));
            this.vehiclesRoutes.addLast(vehicle);
            System.out.println("vehicle " + id + " added: "+ source + " -> " + destination);
            return vehicle.routes;
        }

    }


}
