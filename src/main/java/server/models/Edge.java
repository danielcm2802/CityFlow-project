package server.models;

public class Edge {
    public short from;
    public short to;
    public short lanes;
    public int cars;
    public float length;
    public int average_speed;

    public Edge(short from, short to, short lanes, float length, int average_speed) {
        this.from = from;
        this.to = to;
        this.lanes = lanes;
        this.cars = 0;
        this.length = length;
        this.average_speed = average_speed;
    }

    public float calculateWeight() {
        return length * average_speed;
    }

    @Override
    public String toString() {
        return "Edge{" +
                ", " + from +
                "-->" + to +
                ": lanes=" + lanes +
                ", cars=" + cars +
                ", length=" + length +
                ", average_speed=" + average_speed +
                '}';
    }
}
