import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KimAndRefrigerators {
    static final int CASES = 10;
    public static int calculateDistance(int[] from, int[] to) {
        return Math.abs(from[0] - to[0]) + Math.abs(from[1] - to[1]);
    }
    public static int traverse(int[] home, int[] current, List<int[]> unvisited, int distance, int minimum) {
        if (unvisited.isEmpty()) {
            distance += calculateDistance(current, home);
            if (distance < minimum) {
                minimum = distance;
            }
            return minimum;
        }
        int visitDist;
        for (int i = 0; i < unvisited.size(); i++) {
            int[] customer = unvisited.get(i);
            unvisited.remove(customer);
            visitDist = traverse(home, customer, unvisited, distance+calculateDistance(current, customer), minimum);
            unvisited.add(i, customer);
            minimum = Math.min(visitDist, minimum);
        }
        return minimum;
    }
    public static int solve(int[] office, int[] home, int[][] customers, int nCustomers) {
        List<int[]> unvisited = new ArrayList<>(Arrays.asList(customers));
        return traverse(home, office, unvisited, 0, Integer.MAX_VALUE);
    }

    public static void main(String[] args) throws IOException, Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));


        int[] home = new int[2], office = new int[2];
        int[] solutions = new int[CASES];
        for (int i = 0; i < CASES; i++) {
            int nCustomers = Integer.parseInt(bufferedReader.readLine().replace("\\s+$", ""));
            int[][] customers = new int[nCustomers][2];
            String[] row = bufferedReader.readLine().replace("\\s+$", "").split(" ");
            office[0] = Integer.parseInt(row[0]);
            office[1] = Integer.parseInt(row[1]);
            home[0] = Integer.parseInt(row[2]);
            home[1] = Integer.parseInt(row[3]);
            for (int j = 0, k = 4; j < nCustomers; j++, k+=2) {
                customers[j][0] = Integer.parseInt(row[k]);
                customers[j][1] = Integer.parseInt(row[k+1]);
            }
            solutions[i] = solve(office, home, customers, nCustomers);
        }

        for (int i = 0; i < CASES; i++) {
            bufferedWriter.write(String.format("# %1$d %2$d", i+1, solutions[i]));
            bufferedWriter.newLine();
        }

        bufferedReader.close();
        bufferedWriter.close();
    }
}
