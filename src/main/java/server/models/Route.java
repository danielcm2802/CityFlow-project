package server.models;

import java.util.LinkedList;

public class Route {
    public RoadType type;
    public int id;
    public float seconds;

    public Route(RoadType type,int id, float seconds) {
        this.type = type;
        this.seconds = seconds;
        this.id = id;
    }


    @Override
    public String toString() {
        return "" + type + ": " + id + ", " + seconds + "\n";
    }
}
