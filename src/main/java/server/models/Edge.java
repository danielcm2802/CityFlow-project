package server.models;

public class Edge {
    public static short counter = 0;
    public short id;
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
        counter++;
        id = counter;
    }

    public float calculateWeight() {
        return length * average_speed;
    }

    public void disconnect() {
        counter--;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "id=" + id +
                ", " + from +
                "-->" + to +
                ": lanes=" + lanes +
                ", cars=" + cars +
                ", length=" + length +
                ", average_speed=" + average_speed +
                '}';
    }
}
