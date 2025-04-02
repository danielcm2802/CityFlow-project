package server.models;

import java.util.LinkedList;

public class Graph {
    private LinkedList<Vertex> vertices;
    private LinkedList<Edge> edges;
    public Graph() {
        vertices = new LinkedList<>();
        edges = new LinkedList<>();
    }

    public Graph(LinkedList<Vertex> vertices, LinkedList<Edge> edges) {
        this.vertices = (LinkedList<Vertex>) vertices.clone();
        this.edges =  (LinkedList<Edge>) edges.clone();
    }

    public void addVertex(boolean has_lights) {
        vertices.addFirst(new Vertex(has_lights));
    }

    public void removeVertex(short id) {
        for (Vertex v : vertices) {
            if(v.id == id) {
                vertices.remove(v);
                return;
            }
        }
    }

    public void connectVertices(short from_id, short to_id, short lanes, float length, int average_speed) {
        edges.addFirst(new Edge(from_id,to_id, lanes, length, average_speed));

    }

    public void disconnectVertices(short from_id, short to_id) {
        for (Edge e : edges) {
            if (e.from == from_id && e.to == to_id) {
                edges.remove(e);
                e.disconnect();
                return;
            }
        }
    }

    public void printGraph() {
        System.out.println("vertices: ");
        for (Vertex v : vertices) {
            System.out.println(v.toString());
        }
        System.out.println("edges: ");
        for (Edge e : edges) {
            System.out.println(e.toString());
        }
    }

    public void printForApp() {
        for (Vertex v : vertices) {
            System.out.println(v.id);
        }
        for (Edge e : edges) {
            System.out.println(e.from + " " + e.to);
        }
    }
}
