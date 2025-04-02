package server.models;

import java.util.LinkedList;

public class Vertex {
    public short id;
    public boolean has_lights;
    public LinkedList<Edge> adjacent_edges;

    public Vertex(short id, boolean has_lights) {

        this.id = id;
        this.has_lights = has_lights;
        this.adjacent_edges = new LinkedList<>();
    }

    public float calculateWeight() {
        return 10 + (has_lights ? 40 : 0);
    }

    public void addAdjacentEdge(short to, short lanes, float length, int average_speed) {
        this.adjacent_edges.addFirst(new Edge(this.id, to, lanes, length, average_speed));
    }

    public void removeAdjacentEdge(short to) {
        for (Edge e : this.adjacent_edges) {
            if (e.to == to) {
                this.adjacent_edges.remove(e);
                return;
            }
        }
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "id=" + id +
                ", has_lights=" + has_lights +
                '}';
    }

    public void printEdges() {
        for (Edge e : adjacent_edges) {
            System.out.println(e.toString());
        }
    }

}
