package Algorithms;

import java.util.*;
import java.util.stream.IntStream;

public class Dijkstra {
    public static class DijkstraResult {
        private boolean allVisited;
        private Object source;
        private Object destination;
        private HashMap<Object, Double> minDist;
        private HashMap<Object, Object> lastPath;
        private DijkstraResult(HashMap<Object, Double> minDist, HashMap<Object, Object> lastPath, boolean allVisited, Object source, Object destination) {
            this.minDist = minDist;
            this.lastPath = lastPath;
            this.allVisited = allVisited;
            this.source = source;
            this.destination = destination;
        }
        public HashMap<Object, Double> getMinimumDistances() {
            return this.minDist;
        }
        public HashMap<Object, Object> getPaths() {
            return lastPath;
        }

    }
    private static Object getMinimumDistanceVertex(HashSet<Object> unvisited, HashMap<Object, Double> minDistances) {
        return unvisited.stream().min(Comparator.comparingDouble(vertex -> minDistances.get(vertex))).orElse(-1);
    }
    public static DijkstraResult calculateGraph(HashMap<Object, HashMap<Object, Double>> graph, Object source, Object destination) throws Exception {
        if (null == graph || source == null) {
            throw new Exception("Graph or vertex source cannot be null");
        }
        int n = graph.size();
        HashMap<Object, Double> minDistances = new HashMap<>();
        graph.forEach((x,y)->minDistances.put(x, Double.MAX_VALUE));
        if (!graph.containsKey(source)) {
            throw new Exception("Vertex source is not found in the graph");
        }
        minDistances.put(source, 0.0);
        HashMap<Object, Object> lastPath = new HashMap<>();
        HashSet<Object> unvisited = new HashSet<>();
        IntStream.range(0, n).forEach(unvisited::add); // fill HashSets
        Object vertex;
        lastPath.put(source, source);
        while (!unvisited.isEmpty()) {
            vertex = getMinimumDistanceVertex(unvisited, minDistances);
            // visit
            unvisited.remove(vertex);
            // calculate adjacent vertices
            HashMap<Object, Double> adjacents = graph.get(vertex);
            Object finalVertex = vertex;
            adjacents.forEach((adjVertex, dist) -> {
                double supposedDist = minDistances.get(finalVertex) + adjacents.get(adjVertex);
                if (minDistances.containsKey(adjVertex) && lastPath.containsKey(finalVertex)
                        && supposedDist < minDistances.get(adjVertex) && unvisited.contains(adjVertex)) {
                    lastPath.put(adjVertex, finalVertex);
                    minDistances.put(adjVertex, supposedDist);
                }
            });
            if (null != destination && lastPath.containsKey(destination) && !unvisited.contains(destination)) {
                break;
            }
        }
        return new DijkstraResult(minDistances, lastPath, unvisited.isEmpty(), source, destination);
    }

}
