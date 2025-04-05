package server.models;

import java.util.ArrayList;
import java.util.LinkedList;


//graph class for representing the city
public class Graph {
    public ArrayList<Vertex> vertices;

    //constactor: creates an empty graph
    public Graph() {
        vertices = new ArrayList<>();
    }

    //adds vertex to the graph O(1)
    public void addVertex(short id, boolean has_lights) {
        vertices.addLast(new Vertex(id, has_lights));
    }

    //removes vertex from graph O(n)
    public void removeVertex(int idx) {
        vertices.remove(idx);
    }

    //connects 2 vertices O(1)
    public void connectVertices(int from_idx, int to_idx, short lanes, float length, int average_speed) {
        vertices.get(from_idx).addAdjacentEdge(from_idx,to_idx, lanes, length, average_speed);

    }

    //disconnects 2 vertices O(n)
    public void disconnectVertices(short from_idx, short to_idx) {
        vertices.get(from_idx).removeAdjacentEdge(to_idx);
    }

    public void printGraph() {
        System.out.println("vertices: ");
        for (Vertex v : vertices) {
            System.out.println(v.toString()+": "+ v.getAdjacentEdges());
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
