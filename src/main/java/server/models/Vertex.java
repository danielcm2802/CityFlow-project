package server.models;

import java.util.LinkedList;

//class for a vertex in a graph
public class Vertex {
    public short id;
    public boolean has_lights;
    public LinkedList<Edge> adjacent_edges;
    public float x_cord;
    public float y_cord;

    //constactor: creates a node/vertex that is not connected to anything
    public Vertex(short id, boolean has_lights,float x_cord,float y_cord) {

        this.id = id;
        this.has_lights = has_lights;
        this.adjacent_edges = new LinkedList<>();
        this.x_cord = x_cord;
        this.y_cord = y_cord;
    }

    //function calculates weight of the vertex based on the junction O(1)
    public float calculateWeight() {
        return 10 + (has_lights ? 20 : 0);
    }

    //function adds a new edge coming out of the current vertex O(1)
    public void addAdjacentEdge(int from, int to, short lanes, int length, int average_speed) {
        this.adjacent_edges.addFirst(new Edge(from, to, lanes, length, average_speed));
    }

    //function removes an edge that connected to the current vertex O(n)
    public void removeAdjacentEdge(int to) {
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
                ", x_cord=" + x_cord +
                ", y_cord=" + y_cord +
                '}';
    }



    public void printEdges() {
        for (Edge e : adjacent_edges) {
            System.out.println(e.toString());
        }
    }

    public String getAdjacentEdges() {
        String adjacentEdges = "";
        for (Edge e : this.adjacent_edges) {
            adjacentEdges += e.to + ", ";
        }
        return adjacentEdges;
    }
}
