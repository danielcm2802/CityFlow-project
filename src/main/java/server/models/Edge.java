package server.models;

public class Edge {
    public int from;
    public int to;
    public short lanes;
    public int cars;
    public int length;
    public int speed_limit;

    public Edge(int from, int to, short lanes, int length, int speed_limit) {
        this.from = from;
        this.to = to;
        this.lanes = lanes;
        this.cars = 0;
        this.length = length;
        this.speed_limit = speed_limit;
    }

    public float calculateWeight() {
        return (float) (length/(Math.max(2,(this.speed_limit/3.6)*(1-0.9*cars/(this.lanes*this.length)))));
    }

    @Override
    public String toString() {
        return "Edge{" +
                ", " + from +
                "-->" + to +
                ": lanes=" + lanes +
                ", cars=" + cars +
                ", length=" + length +
                ", speed_limit=" + speed_limit +
                '}';
    }
}
