package server;

import server.models.Graph;

public class main {
    public static void main(String[] args) {
        Graph graph = build_city_Grid(5,4, 1F, (short) 1, 1F, (short) 1);

        graph.printGraph();

    }

    public static Graph build_city_Grid(int rows, int cols, float hor_len, short hor_lanes, float ver_len, short ver_lanes){
        Graph graph = new Graph();
        for(int i=1; i<=rows*cols; i++){
            graph.addVertex((short)(i-1),i%cols <=1 || i<=cols || i> cols*(rows-1));
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


