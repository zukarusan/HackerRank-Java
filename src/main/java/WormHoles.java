import java.io.*;
import java.util.*;

public class WormHoles {
    // https://www.hackerrank.com/contests/target-samsung-13-nov19/challenges/warmholes
    public static String getMinimum(HashSet<String> unvisited, HashMap<String, Integer> minDist) {
        String minVertex = null;
        int dist;
        int min = Integer.MAX_VALUE;
        for (String vertex : unvisited) {
            dist = minDist.get(vertex);
            if (dist < min) {
                min = dist;
                minVertex = vertex;
            }
        }
        return minVertex;
    }
    public static int shortPath(HashMap<String, HashMap<String, Integer>> graph, String source, String dest) {
        HashSet<String> unvisited = new HashSet<>();

        HashMap<String, Integer> minDist = new HashMap<>();
        for (String key : graph.keySet()) {
            unvisited.add(key);
            minDist.put(key, Integer.MAX_VALUE);
        }
        HashSet<String> calculated = new HashSet<>();
        String vertex;
        minDist.put(source, 0);
        calculated.add(source);
        while (!unvisited.isEmpty()) {
            vertex = getMinimum(unvisited, minDist);
            if (null == vertex || vertex.equals(dest)) {
                break;
            }

            unvisited.remove(vertex);
            HashMap<String, Integer> adjacents = graph.get(vertex);
            for (String vAdj : adjacents.keySet()) {
                int dist = adjacents.get(vAdj) + minDist.get(vertex);
                if (unvisited.contains(vAdj) && calculated.contains(vertex) && dist < minDist.get(vAdj)) {
                    calculated.add(vAdj);
                    minDist.put(vAdj, dist);
                }
            }
        }
        return minDist.get(dest);
    }
    public static int distance(int[] from, int[] to) {
        return Math.abs(from[0] - to[0]) + Math.abs(from[1] - to[1]);
    }

    public static HashMap<String, Integer> getOrCreate(HashMap<String, HashMap<String, Integer>> graph, String el) {
        if (!graph.containsKey(el)) {
            HashMap<String, Integer> adj = new HashMap<>();
            graph.put(el, adj);
            return adj;
        } else {
            return graph.get(el);
        }
    }
    public static void putEdge(HashMap<String, HashMap<String, Integer>> graph, String v, String u, int cost) {
        getOrCreate(graph, v).put(u, cost);
        getOrCreate(graph, u).put(v, cost);
    }
    public static HashMap<String, HashMap<String, Integer>> constructGraph(int[] start, int[] dest, String[][] wormholes, int n) {
        HashMap<String, HashMap<String, Integer>> graph = new HashMap<>();

        // flatten
        HashMap<String, int[]> vSets = new HashMap<>();
        List<int[][]> wormholesCost = new ArrayList<>();
        vSets.put(String.format("%1$d%2$d", start[0], start[1]), start);
        vSets.put(String.format("%1$d%2$d", dest[0], dest[1]), dest);
        for (String[] wormhole : wormholes) {
            int[][] wm = new int[3][2];
            wm[0][0] = Integer.parseInt(wormhole[0]);
            wm[0][1] = Integer.parseInt(wormhole[1]);
            wm[1][0] = Integer.parseInt(wormhole[2]);
            wm[1][1] = Integer.parseInt(wormhole[3]);
            wm[2][0] = Integer.parseInt(wormhole[4]);
            wormholesCost.add(wm);
            vSets.put(String.format("%1$d%2$d", wm[0][0], wm[0][1]), wm[0]);
            vSets.put(String.format("%1$d%2$d", wm[1][0], wm[1][1]), wm[1]);
        }
        List<String> vertices = new ArrayList<>(vSets.keySet());
        int vLen = vertices.size();
        for (int i = 0; i < vLen - 1; i++) {
            String v = vertices.get(i);
            for (int j = i + 1; j < vLen; j++) {
                String u = vertices.get(j);
                putEdge(graph, v, u, distance(vSets.get(v), vSets.get(u)));
            }
        }
        for (int[][] wormhole : wormholesCost) {
            String v = String.format("%1$d%2$d", wormhole[0][0], wormhole[0][1]);
            String u = String.format("%1$d%2$d", wormhole[1][0], wormhole[1][1]);
            putEdge(graph, v, u, wormhole[2][0]);
        }
        return graph;
    }
    public static int solve(int[] start, int[] dest, String[][] wormholes, int n) {
        HashMap<String, HashMap<String, Integer>> graph = constructGraph(start, dest, wormholes, n);
        return shortPath(graph, String.format("%1$d%2$d", start[0], start[1]), String.format("%1$d%2$d", dest[0], dest[1]));
    }
    public static void main(String[] args)  throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(reader.readLine().replace("\\s+$", ""));
        int[] solutions = new int[t];
        int[] source = new int[2];
        int[] dest = new int[2];
        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(reader.readLine().replace("\\s+$", ""));
            String[] sd = reader.readLine().replace("\\s+$", "").split(" ");
            source[0] = Integer.parseInt(sd[0]);
            source[1] = Integer.parseInt(sd[1]);
            dest[0] = Integer.parseInt(sd[2]);
            dest[1] = Integer.parseInt(sd[3]);
            String[][] wormholes = new String[n][];
            for (int j = 0; j < n; j++) {
                String[] wm = reader.readLine().replace("\\s+$", "").split(" ");
                wormholes[j] = wm;
            }
            solutions[i] = solve(source, dest, wormholes, n);
        }
        for (int sol : solutions) {
            writer.write(String.valueOf(sol));
            writer.newLine();
        }

        reader.close();
        writer.close();
    }
}
