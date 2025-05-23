package server.algo;

import server.models.Edge;
import server.models.Graph;
import server.models.Vertex;

public class CityBuilder {

    // the function builds a graph that represents a city grid
    public static Graph build_city_Grid(int rows, int cols, int hor_len, short hor_lanes, int ver_len, short ver_lanes){
        Graph graph = new Graph();
        for(int i=1; i<=rows*cols; i++){
            graph.addVertex((short)(i-1),true
                    ,(float)ver_len*((i-1)%cols),(float) hor_len*((i-1)/cols));
        }


        for (int i=0; i<rows; i++){
            for (int j=1; j<cols; j++){
                graph.connectVertices(i*cols + j-1,i*cols + j,hor_lanes,hor_len,50);
                graph.connectVertices(i*cols + j,i*cols + j-1,hor_lanes,hor_len,50);
            }
        }
        for (int i=0; i<cols; i++){
            for (int j=1; j<rows; j++){
                graph.connectVertices(i + (j-1)*cols,i + j*cols, ver_lanes,ver_len,50);
                graph.connectVertices(i + j*cols,i + (j-1)*cols, ver_lanes,ver_len,50);
            }
        }
        graph.cols = cols;
        graph.rows = rows;
        graph.hor_len = hor_len;
        graph.ver_len = ver_len;
        graph.ver_lanes = ver_lanes;
        graph.hor_lanes = hor_lanes;

        return graph;
    }

    // resets the number of vehicles in the city
    public static void reset_city(Graph cityGraph){
        for(Vertex v: cityGraph.vertices){
            for (Edge e: v.adjacent_edges){
                e.cars = 0;
            }
        }
    }
}
