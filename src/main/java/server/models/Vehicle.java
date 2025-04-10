package server.models;


import java.time.*;
import java.util.LinkedList;

public class Vehicle{
    public LinkedList<Route> routes;
    public LocalTime Start_time;
    public LocalTime ETA;
    public LocalTime next_removal;

    public Vehicle(LinkedList<Route> routes) {
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
        this.next_removal = Start_time.plusSeconds((long)this.routes.getFirst().seconds);
        System.out.println("car got to: " + this.routes.getFirst().id);
    }
}
