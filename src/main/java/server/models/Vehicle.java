package server.models;


import java.time.*;
import java.util.LinkedList;

public class Vehicle{
    public int id;
    public LinkedList<Route> routes;
    public LocalTime Start_time;
    public LocalTime ETA;
    public LocalTime next_removal;
    public Edge current_edge;

    public Vehicle(int id,LinkedList<Route> routes) {
        this.id = id;
        this.routes = routes;
        this.Start_time = LocalTime.now();
        this.ETA = Start_time.plusSeconds((long)this.calcTime());
        this.next_removal = Start_time.plusSeconds((long)this.routes.getFirst().seconds);
    }

    public float calcTime() {
        return (float) routes.stream().mapToDouble(route -> route.seconds).sum();
    };

    public void removeRouteSection() {
        this.routes.removeFirst();
        this.next_removal = this.next_removal.plusSeconds((long)this.routes.getFirst().seconds);
        System.out.println("vehicle " +this.id+ " got to: " + this.routes.getFirst().id);
    }

    public void printRoute() {
        for (Route route : this.routes) {
            System.out.println(route);
        }
    }
}
