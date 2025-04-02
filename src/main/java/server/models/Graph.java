package server.models;

import java.util.LinkedList;

public class Graph {
    private LinkedList<Vertex> vertices;
    public Graph() {
        vertices = new LinkedList<>();
    }


    public void addVertex(short id, boolean has_lights) {
        vertices.addFirst(new Vertex(id, has_lights));
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
        for (Vertex v : vertices) {
            if(v.id == from_id) {
                v.addAdjacentEdge(to_id, lanes, length, average_speed);
                return;
            }
        }

    }

    public void disconnectVertices(short from_id, short to_id) {
        for (Vertex v : vertices) {
            if(v.id == from_id) {
                v.removeAdjacentEdge(to_id);
            }
        }
    }

    public void printGraph() {
        System.out.println("vertices: ");
        for (Vertex v : vertices) {
            System.out.println(v.toString());
        }
        System.out.println("edges: ");
        for(Vertex v : vertices) {
            for(Edge e: v.adjacent_edges){
                System.out.println(e.toString());
            }
        }
    }

    public void printForApp() {
        for (Vertex v : vertices) {
            System.out.println(v.id);
        }

        for(Vertex v : vertices) {
            for(Edge e: v.adjacent_edges){
                System.out.println(e.from + " " + e.to);
            }
        }
    }
}
