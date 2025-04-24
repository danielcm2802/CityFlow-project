package model;


import java.io.Serializable;

// a basic route component, represents a section of the road.
public class Route implements Serializable {
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
