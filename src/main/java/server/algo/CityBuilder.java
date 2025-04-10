package server.algo;

import server.models.Graph;

public class CityBuilder {

    public static Graph build_city_Grid(int rows, int cols, int hor_len, short hor_lanes, int ver_len, short ver_lanes){
        Graph graph = new Graph();
        for(int i=1; i<=rows*cols; i++){
            graph.addVertex((short)(i-1),!(i%cols <=1 || i<=cols || i> cols*(rows-1))
                    ,(float)ver_len*(i%5),(float) hor_len*(i/5));
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
        return graph;
    }
}
