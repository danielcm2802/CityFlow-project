package server;

import server.models.Graph;

public class main {
    public static void main(String[] args) {
        Graph g = new Graph();
        g.addVertex(false);
        g.addVertex(false);
        g.addVertex(false);
        g.connectVertices((short) 1, (short) 2, (short) 0, 0.0F,0);
        g.connectVertices((short) 1, (short) 3, (short) 0, 0.0F,0);
        g.connectVertices((short) 2, (short) 3, (short) 0, 0.0F,0);
        g.printForApp();
    }
}
