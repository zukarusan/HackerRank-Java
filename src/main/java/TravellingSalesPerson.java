import java.io.*;

import static java.lang.Math.min;

public class TravellingSalesPerson {
    // https://www.hackerrank.com/contests/target-samsung-13-nov19/challenges/travelling-salesman-4
    public static void swap(int[] arr, int from, int to) {
        int val = arr[from];
        arr[from] = arr[to];
        arr[to] = val;
    }
    static int findMinPath(int[][] costs, int[] path, int lastPath, int minimum) {
        int cost = 0;
        cost += costs[0][path[0]];
        cost += costs[path[lastPath]][0];
        for(int i=0;i<lastPath;i++)
            cost += costs[path[i]][path[i+1]];
        return min(minimum, cost);
    }
    static int permute(int[][] costs, int[] path, int from, int lastPath, int minimum) {
        if(from==lastPath) {
            return Math.min(findMinPath(costs, path, lastPath, minimum), minimum);
        } else {
            for(int q=from;q<=lastPath;q++) {
                swap(path, q, from);
                minimum = min(permute(costs, path, from+1, lastPath, minimum), minimum);
                swap(path, q, from);
            }
            return minimum;
        }
    }

    public static int solve(int n, int[][] costs) {
        int[] path = new int[n-1];
        for (int i = 0; i < n -1; i++) {
            path[i] = i+1;
        }
        return permute(costs, path, 0, path.length-1, Integer.MAX_VALUE);
    }
    public static void main(String[] args) throws IOException, Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        int t = Integer.parseInt(reader.readLine().replaceAll("\\s+$", ""));
        int[] solutions = new int[t];
        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(reader.readLine().replaceAll("\\s+$", ""));
            int[][] matrix = new int[n][n];
            for (int j = 0; j < n; j++) {
                String[] row = reader.readLine().replaceAll("\\s+$", "").split("\\s+");
                for (int k = 0; k < n; k++) {
                    matrix[j][k] = Integer.parseInt(row[k]);
                }
            }
            solutions[i] = solve(n, matrix);
        }
        for (int sol : solutions) {
            writer.write(String.valueOf(sol));
            writer.newLine();
        }

        reader.close();
        writer.close();
    }
}
