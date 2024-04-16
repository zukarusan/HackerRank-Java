package Algorithms;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIf;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.api.condition.JRE;
import sun.security.util.ArrayUtil;

import java.util.Arrays;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.condition.JRE.JAVA_8;

class DijkstraTest {
    @Test
    @EnabledOnJre(JAVA_8)
    public void testShortestPath() throws Exception {
        int raw[][]
                = new int[][] { { 0, 4, 0, 0, 0, 0, 0, 8, 0 },
                { 4, 0, 8, 0, 0, 0, 0, 11, 0 },
                { 0, 8, 0, 7, 0, 4, 0, 0, 2 },
                { 0, 0, 7, 0, 9, 14, 0, 0, 0 },
                { 0, 0, 0, 9, 0, 10, 0, 0, 0 },
                { 0, 0, 4, 14, 10, 0, 2, 0, 0 },
                { 0, 0, 0, 0, 0, 2, 0, 1, 6 },
                { 8, 11, 0, 0, 0, 0, 1, 0, 7 },
                { 0, 0, 2, 0, 0, 0, 6, 7, 0 } };
        HashMap<Object, HashMap<Object, Double>> graph = new HashMap<>();
        int vertex = 0;
        for(int[] edges : raw) {
            HashMap<Object, Double> adjacents = new HashMap<>();
            graph.put(vertex, adjacents);
            int adjV = 0;
            for (int dis : edges) {
                if (dis != 0) {
                    adjacents.put(adjV, (double) dis);
                }
                adjV++;
            }
            vertex++;
        }
        Double[] expDists = ArrayUtils.toObject(new double[] {0.0,4.0,12.0,19.0,21.0,11.0,9.0,8.0,14.0});
        Dijkstra.DijkstraResult result = Dijkstra.calculateGraph(graph, 0, null);
        assertArrayEquals(expDists, result.getMinimumDistances().values().toArray(new Double[0]));
        //result.getMinimumDistances().forEach((x,y)->System.out.println(y));
        assertThrows(Exception.class, ()->Dijkstra.calculateGraph(null, 0, null));
        assertThrows(Exception.class, ()->Dijkstra.calculateGraph(graph, null, null));
    }
}