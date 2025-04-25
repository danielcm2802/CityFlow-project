package server.algo;
import model.RoadType;
import model.Route;
import server.models.*;

import java.time.LocalTime;
import java.util.*;


public class MinCostRoute {
    public static Graph futureTraffic;

    //heuristic function
    private static float heuristic(int source,int destination) {
        Vertex s = futureTraffic.vertices.get(source);
        Vertex e = futureTraffic.vertices.get(destination);

        return (float) (Math.pow(s.x_cord-e.x_cord,2) + Math.pow(s.y_cord-e.y_cord,2));
    }

    //updates the future traffic of the city
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

    //builds the path from the parent array
    private static LinkedList<Route> createPath(Graph graph,int[] parent,float[] distance, int destination) {
        LinkedList<Route> path = new LinkedList<>();
        path.addFirst(new Route(RoadType.Intersection,destination, 0));
        int current = destination;
        while (parent[current] != -1) {
            path.addFirst(new Route(RoadType.Road,-1, distance[current]-distance[parent[current]]-graph.vertices.get(current).calculateWeight()));
            graph.findEdge(parent[current], current).total_cars++;
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
        Edge[] fromParent = new Edge[graph.vertices.size()];
        float[] averagelanes = new float[graph.vertices.size()];
        int[] roadsfromsource = new int[graph.vertices.size()];
        for (int i = 0; i < graph.vertices.size(); i++) {
            visited[i] = false;
            parent[i] = -1;
            distance[i] = Float.MAX_VALUE;
            fromParent[i] = null;
            averagelanes[i] = 0;
            roadsfromsource[i] = 0;

        }

        //building priority queue based on the parameters
        PriorityQueue<Integer> queue = new PriorityQueue<>(
                (o1, o2) -> {
                    if (Math.round(distance[o1] + heuristic(o1,destination)) <
                            Math.round(distance[o2]+heuristic(o1,destination))) {
                        return -1;
                    }
                    if (Math.round(distance[o1] + heuristic(o1,destination)) >
                            Math.round(distance[o2]+heuristic(o1,destination))) {
                        return 1;
                    }
                    return (int)(averagelanes[o1]*10 - averagelanes[o2]*10);

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
                    if(e.calculateWeight()+distance[current] +
                            futureTraffic.vertices.get(current).calculateWeight() < distance[e.to]){
                        distance[e.to] = distance[current] + e.calculateWeight() +
                                futureTraffic.vertices.get(current).calculateWeight();
                        parent[e.to] = current;
                        fromParent[e.to] = e;
                        roadsfromsource[e.to] = roadsfromsource[current]+1;
                        averagelanes[e.to] = (averagelanes[current]*roadsfromsource[current] + e.lanes)/roadsfromsource[e.to];
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
