package server.algo;

import server.gui.body.CityMap;
import server.models.Edge;
import server.models.Graph;
import server.models.Vehicle;

import java.time.*;
import java.util.ArrayList;
import java.util.Iterator;

//the class for managing the traffic in the city
public class TrafficManager extends Thread {
    ArrayList<Vehicle> vehicles;
    Graph graph;
    CityMap cityMap;


    public TrafficManager(ArrayList<Vehicle> vehicles, Graph graph, CityMap cityMap) {
        this.vehicles = vehicles;
        this.graph = graph;
        this.cityMap = cityMap;
    }

    //updates the number of cars in the road that the vehicle came on
    public void updateEdges(Vehicle vehicle) {
        if(vehicle.routes.getFirst().id != -1) {
            Edge e = graph.findEdge(vehicle.routes.getFirst().id, vehicle.routes.get(2).id);
            e.cars++;
            if ((vehicle.current_edge != null)) {
                vehicle.current_edge.cars--;
            }
            vehicle.current_edge = e;
        }
        else {
            vehicle.current_edge.total_cars--;
        }

    }

    // the main thread
    public void run() {
        while (true) {
            LocalTime time_now = LocalTime.now();
            //cant allow 2 threads working on the same data.
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
            cityMap.repaint();
            //time between each iteration: 1/60. means that the map of the city runs on 60fps
            try {
                sleep(17);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
