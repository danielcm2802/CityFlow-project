package server.algo;
import server.models.*;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;


public class MinCostRoute {
    //heuristic function O(1)
    public static float heuristic(Graph graph,int source,int destination) {
        Vertex s = graph.vertices.get(source);
        Vertex e = graph.vertices.get(destination);

        return (float) (Math.pow(s.x_cord-e.x_cord,2) + Math.pow(s.y_cord-e.y_cord,2));
    }


    //builds the path from the parent array O(n)
    public static LinkedList<Route> createPath(Graph graph,int[] parent,float[] distance, int destination) {
        LinkedList<Route> path = new LinkedList<>();
        path.addFirst(new Route(RoadType.Intersection,destination, 0));
        int current = destination;
        while (parent[current] != -1) {
            path.addFirst(new Route(RoadType.Road,-1, distance[current]-distance[parent[current]]));
            current = parent[current];
            path.addFirst(new Route(RoadType.Intersection,current,graph.vertices.get(current).calculateWeight()));

        }
        path.getFirst().seconds = 0;
        return path;
    }

    //algorithm to find the min cost route in the city. based on A*
    public static LinkedList<Route> findMinCostRoute(Graph graph,int source,int destination) {

        //instalizing basic parameters
        boolean[] visited = new boolean[graph.vertices.size()];
        int[] parent = new int[graph.vertices.size()];
        float[] distance = new float[graph.vertices.size()];

        for (int i = 0; i < graph.vertices.size(); i++) {
            visited[i] = false;
            parent[i] = -1;
            distance[i] = Float.MAX_VALUE;
        }


        //building priority queue based on the parameters
        PriorityQueue<Integer> queue = new PriorityQueue<>(
                new Comparator<Integer>() {
                    @Override
                    public int compare(Integer o1, Integer o2){
                        if (distance[o1] + heuristic(graph,o1,destination) < distance[o2]+heuristic(graph,o1,destination)) {
                            return -1;
                        }
                        else if (distance[o1] + heuristic(graph,o1,destination) > distance[o2]+heuristic(graph,o1,destination)){
                            return 1;
                        }
                        return 0;
                    }
                }
        );

        //making starting point distance 0
        distance[source] = 0;

        //adding all nodes to queue
        for (int i = 0; i < graph.vertices.size(); i++) {
            queue.add(i);
        }


        boolean found = false;
        //main loop
        while (!queue.isEmpty() && !found) {
            int current = queue.poll();
            for(Edge e : graph.vertices.get(current).adjacent_edges) {
                if(!visited[e.to]){
                    queue.remove(e.to);
                    if(e.calculateWeight()+distance[current] + graph.vertices.get(current).calculateWeight() < distance[e.to]){
                        distance[e.to] = distance[current] + e.calculateWeight() + graph.vertices.get(current).calculateWeight();
                        parent[e.to] = current;
                    }
                    queue.add(e.to);
                    found = (e.to==destination);
                }
            }
            visited[current] = true;
        }

        //creates rout based on the parent and distance arrays
        return createPath(graph,parent,distance,destination);
    }
}
