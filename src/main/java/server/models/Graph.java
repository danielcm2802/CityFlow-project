package server.models;

import java.io.Serializable;
import java.util.ArrayList;


//graph class for representing the city
public class Graph implements Cloneable{
    public ArrayList<Vertex> vertices;
    public int rows;
    public int cols;
    public int hor_len;
    public short hor_lanes;
    public int ver_len;
    public short ver_lanes;

    //constactor: creates an empty graph
    public Graph() {
        vertices = new ArrayList<>();
    }

    //adds vertex to the graph O(1)
    public void addVertex(short id, boolean has_lights,float x_cord, float y_cord) {
        vertices.addLast(new Vertex(id, has_lights, x_cord, y_cord));
    }

    //removes vertex from graph O(n)
    public void removeVertex(int idx) {
        vertices.remove(idx);
    }

    //connects 2 vertices O(1)
    public void connectVertices(int from_idx, int to_idx, short lanes, int length, int average_speed) {
        vertices.get(from_idx).addAdjacentEdge(from_idx,to_idx, lanes, length, average_speed);

    }

    //disconnects 2 vertices O(n)
    public void disconnectVertices(short from_idx, short to_idx) {
        vertices.get(from_idx).removeAdjacentEdge(to_idx);
    }

    public Edge findEdge(int from_idx, int to_idx) {
        for(Edge e:this.vertices.get(from_idx).adjacent_edges){
            if(e.to == to_idx){
                return e;
            }
        }
        return null;
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
