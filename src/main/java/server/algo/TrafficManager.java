package server.algo;

import server.models.Graph;
import server.models.Vehicle;

import java.time.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class TrafficManager extends Thread {
    ArrayList<Vehicle> vehicles;
    Graph graph;
    public TrafficManager(ArrayList<Vehicle> vehicles, Graph graph) {
        this.vehicles = vehicles;
        this.graph = graph;
    }

    public void run() {
        while (true) {
            LocalTime time_now = LocalTime.now();
            for (Vehicle vehicle : vehicles) {
                if(time_now.isAfter(vehicle.next_removal))
                    vehicle.removeRouteSection();

            }
            try {
                sleep(17);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
