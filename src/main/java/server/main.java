package server;

import server.algo.MinCostRoute;
import server.models.Graph;

import java.util.LinkedList;

public class main {
    public static void main(String[] args) {
        Graph graph = build_city_Grid(5,4, 1.5F, (short) 1, 1F, (short) 1);

        graph.printGraph();
        graph.printForApp();
        LinkedList<Integer> route = MinCostRoute.findMinCostRoute(graph, 18, 3);
        System.out.println(route);

    }

    public static Graph build_city_Grid(int rows, int cols, float hor_len, short hor_lanes, float ver_len, short ver_lanes){
        Graph graph = new Graph();
        for(int i=1; i<=rows*cols; i++){
            graph.addVertex((short)(i-1),!(i%cols <=1 || i<=cols || i> cols*(rows-1))
                    ,(float)ver_len*(i%5),(float) hor_len*(i/5));
        }

        for (int i=0; i<rows; i++){
            for (int j=1; j<cols; j++){
                graph.connectVertices(i*cols + j-1,i*cols + j,hor_lanes,hor_len,35);
                graph.connectVertices(i*cols + j,i*cols + j-1,hor_lanes,hor_len,35);
            }
        }
        for (int i=0; i<cols; i++){
            for (int j=1; j<rows; j++){
                graph.connectVertices(i + (j-1)*cols,i + j*cols, ver_lanes,ver_len,35);
                graph.connectVertices(i + j*cols,i + (j-1)*cols, ver_lanes,ver_len,35);
            }
        }
        return graph;
    }
}


