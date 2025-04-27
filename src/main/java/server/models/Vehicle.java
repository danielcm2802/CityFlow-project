package server.models;


import model.Route;

import java.time.*;
import java.util.LinkedList;

//class that represents a vehicle on the city road
public class Vehicle{
    public int id;
    public LinkedList<Route> routes;
    public LocalTime Start_time;
    public LocalTime ETA;
    public LocalTime next_removal;
    public Edge current_edge;
    public int last_route_id;


    public Vehicle(int id,LinkedList<Route> routes) {
        this.id = id;
        this.routes = routes;
        this.Start_time = LocalTime.now();
        this.ETA = Start_time.plusSeconds((long)this.calcTime());
        this.next_removal = Start_time.plusSeconds((long)this.routes.getFirst().seconds);
    }

    //calculates the ETA of a vehicle to it's destination
    public float calcTime() {
        return (float) routes.stream().mapToDouble(route -> route.seconds).sum();
    };


    //deletes the section that the vehicle passed
    public void removeRouteSection() {
        this.last_route_id = this.routes.getFirst().id;
        this.routes.removeFirst();
        this.next_removal = this.next_removal.plusSeconds((long)this.routes.getFirst().seconds);
        System.out.println("[T-MANAGER]:"+"vehicle " +this.id+ " got to: " + this.routes.getFirst().id);
    }

    //prints route
    public void printRoute() {
        for (Route route : this.routes) {
            System.out.println(route);
        }
    }
}
