package server.algo;
import server.models.Edge;
import server.models.Graph;
import server.models.Vertex;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;


public class MinCostRoute {

    public static float heuristic(Graph graph,int source,int destination) {
        Vertex s = graph.vertices.get(source);
        Vertex e = graph.vertices.get(destination);

        return (float) (Math.pow(s.x_cord-e.x_cord,2) + Math.pow(s.y_cord-e.y_cord,2));
    }
    public static LinkedList<Integer> createPath(int[] parent, int destination) {
        LinkedList<Integer> path = new LinkedList<>();
        int current = destination;
        while (current != -1) {
            path.addFirst(current);
            current = parent[current];
        }
        return path;
    }

    public static LinkedList<Integer> findMinCostRoute(Graph graph,int source,int destination) {
        boolean[] visited = new boolean[graph.vertices.size()];
        int[] parent = new int[graph.vertices.size()];
        float[] distance = new float[graph.vertices.size()];


        for (int i = 0; i < graph.vertices.size(); i++) {
            visited[i] = false;
            parent[i] = -1;
            distance[i] = Float.MAX_VALUE;
        }



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

        distance[source] = 0;
        for (int i = 0; i < graph.vertices.size(); i++) {
            queue.add(i);
        }
        boolean found = false;
        while (!queue.isEmpty() && !found) {
            int current = queue.poll();
            for(Edge e : graph.vertices.get(current).adjacent_edges) {
                if(!visited[e.to]){
                    queue.remove(e.to);
                    if(e.calculateWeight()+distance[current] < distance[e.to]){
                        distance[e.to] = distance[current] + e.calculateWeight();
                        parent[e.to] = current;
                    }
                    queue.add(e.to);
                    found = (e.to==destination);
                }
            }
            visited[current] = true;
        }

        return createPath(parent,destination);
    }
}
