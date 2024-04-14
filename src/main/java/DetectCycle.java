import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DetectCycle {
    // https://www.hackerrank.com/contests/target-samsung-13-nov19/challenges/detect-cycle-in-directed-graph

    public static class Cycle {
        public List<Integer> cycle;
        public int minSum;
    }

    public static void detectCycle(boolean[][] graph, int[] stack, boolean[] visited, int node, int top, Cycle cycle) {
        if (visited[node] && top >= 0) {
            int i, sum = stack[top];
            for (i = top -1; i >= 0 && stack[i] != node; i--) {
                sum += stack[i];
            }
            if (i >= 0 && sum < cycle.minSum) {
                cycle.cycle.clear();
                cycle.minSum = sum;
                cycle.cycle.add(stack[top]);
                for(i=top-1;i>=0 && stack[i]!=node;i--) {
                    cycle.cycle.add(stack[i]);
                }
            }
        } else {
            visited[node] = true;
            for (int i = 0; i < visited.length; i++) {
                if (graph[node][i]) {
                    stack[top+1] = i;
                    detectCycle(graph, stack, visited, i, top+1, cycle);
                }
            }
            visited[node] = false;
        }
    }
    public static Cycle answerAndDetect(boolean[][] graph, int n, int m) {
        int[] stack = new int[n+4];
        boolean[] visited = new boolean[n];
        Cycle answer = new Cycle();
        answer.cycle = new ArrayList<>();
        answer.minSum = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                stack[0] = i;
                detectCycle(graph, stack, visited, i, 0, answer);
            }
        }
        return answer;
    }
    public static void main(String[] args) throws IOException, Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] first = bufferedReader.readLine().replace("\\s+$", "").split(" ");
        int n = Integer.parseInt(first[0]);
        int m = Integer.parseInt(first[1]);

        String[] edges = bufferedReader.readLine().replace("\\s+$", "").split(" ");
        boolean[][] graph = new boolean[n][n];
        int t1, t2;
        int _2m = m + m;
        for (int i = 0; i < _2m; i += 2) {
            t1 = Integer.parseInt(edges[i]);
            t2 = Integer.parseInt(edges[i+1]);
            graph[t1-1][t2-1] = true;
        }

        Cycle result = answerAndDetect(graph, n, m);
        Collections.sort(result.cycle);
        StringBuilder sb = new StringBuilder();
        for (int node : result.cycle) {
            sb.append(String.format("%d ", node+1));
        }

        bufferedWriter.write(sb.toString().replace("\\s+$", ""));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
