package server.algo;

import server.models.Edge;
import server.models.Graph;
import server.models.Vehicle;

import java.time.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

public class TrafficManager extends Thread {
    ArrayList<Vehicle> vehicles;
    Graph graph;
    public TrafficManager(ArrayList<Vehicle> vehicles, Graph graph) {
        this.vehicles = vehicles;
        this.graph = graph;
    }

    public Edge findEdge(int from, int to) {
        for(Edge e:graph.vertices.get(from).adjacent_edges){
            if(e.to == to){
                return e;
            }
        }
        return null;
    }

    public void updateEdges(Vehicle vehicle) {
        if(vehicle.routes.getFirst().id != -1) {
            Edge e = findEdge(vehicle.routes.getFirst().id, vehicle.routes.get(2).id);
            e.cars++;
            vehicle.current_edge = e;
        }
        else
            vehicle.current_edge.cars--;

    }

    public void run() {
        while (true) {
            LocalTime time_now = LocalTime.now();
            synchronized (vehicles) {
                Iterator<Vehicle> iter = vehicles.iterator();
                while (iter.hasNext()) {
                    Vehicle vehicle = iter.next();
                    if (vehicle.routes.size() == 1) {
                        iter.remove();
                    }
                    if (time_now.isAfter(vehicle.next_removal) && vehicle.routes.size() > 1) {
                        updateEdges(vehicle);
                        vehicle.removeRouteSection();
                    }

                }
            }

            try {
                sleep(17);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
