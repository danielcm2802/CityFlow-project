package server;

import server.models.Graph;

public class main {
    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.addVertex((short) 1,false);
        graph.addVertex((short) 2,false);
        graph.addVertex((short) 3,false);
        graph.addVertex((short) 4,false);
        graph.addVertex((short) 5,false);
        graph.addVertex((short) 6,false);
        graph.connectVertices((short) 1, (short) 2, (short) 0,0.0F,0);
        graph.connectVertices((short) 2, (short) 3, (short) 0,0.0F,0);
        graph.connectVertices((short) 3, (short) 4, (short) 0,0.0F,0);
        graph.connectVertices((short) 4, (short) 5, (short) 0,0.0F,0);
        graph.connectVertices((short) 5, (short) 6, (short) 0,0.0F,0);
        graph.connectVertices((short) 6, (short) 1, (short) 0,0.0F,0);
        graph.connectVertices((short) 5, (short) 2, (short) 0,0.0F,0);
        graph.connectVertices((short) 6, (short) 3, (short) 0,0.0F,0);

        graph.printForApp();

    }
}
