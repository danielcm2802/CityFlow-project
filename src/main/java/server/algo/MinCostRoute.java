package server.algo;
import server.models.*;

import java.time.Duration;
import java.time.LocalTime;
import java.util.*;


public class MinCostRoute {
    public static Graph futureTraffic;

    //heuristic function O(1)
    private static float heuristic(int source,int destination) {
        Vertex s = futureTraffic.vertices.get(source);
        Vertex e = futureTraffic.vertices.get(destination);

        return (float) (Math.pow(s.x_cord-e.x_cord,2) + Math.pow(s.y_cord-e.y_cord,2));
    }

    private static void getFutureTraffic(ArrayList<Vehicle> vehicles, LocalTime Wanted_time) {
        CityBuilder.reset_city(futureTraffic);
        for(Vehicle vehicle : vehicles) {
            putOnMap(vehicle,Wanted_time);
        }


    }

    private static void putOnMap(Vehicle vehicle, LocalTime Wanted_time) {
        LocalTime next_time = vehicle.next_removal;
        int pos = 0;
        Iterator<Route> it = vehicle.routes.iterator();
        it.next();
        while(it.hasNext() && next_time.isBefore(Wanted_time)) {
            Route route = it.next();
            next_time = next_time.plusSeconds((long) route.seconds);
            pos++;
        }
        if(it.hasNext() && vehicle.routes.get(pos).id == -1) {
            int id = vehicle.last_route_id;
            if(pos!=0)
                id = vehicle.routes.get(pos-1).id;
            Edge e = futureTraffic.findEdge(id, vehicle.routes.get(pos+1).id);
            e.cars++;
        }
    }

    //builds the path from the parent array O(n)
    private static LinkedList<Route> createPath(Graph graph,int[] parent,float[] distance, int destination) {
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
    public static LinkedList<Route> findMinCostRoute(Graph graph,ArrayList<Vehicle> vehicles,int source,int destination) {

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
                        if (distance[o1] + heuristic(o1,destination) < distance[o2]+heuristic(o1,destination)) {
                            return -1;
                        }
                        else if (distance[o1] + heuristic(o1,destination) > distance[o2]+heuristic(o1,destination)){
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
            getFutureTraffic(vehicles,LocalTime.now().plusSeconds((long)distance[current]));
            for(Edge e : graph.vertices.get(current).adjacent_edges) {
                if(!visited[e.to]){
                    queue.remove(e.to);
                    if(e.calculateWeight()+distance[current] + futureTraffic.vertices.get(current).calculateWeight() < distance[e.to]){
                        distance[e.to] = distance[current] + e.calculateWeight() + futureTraffic.vertices.get(current).calculateWeight();
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
