import java.io.*;
import java.util.Arrays;

public class RockClimbing {
    // https://www.hackerrank.com/contests/target-samsung-13-nov19/challenges/rock-climbing
    public static class Climb {
        public boolean found = false;
        public int height = 0;
    }
    public static void search(int[][] map, boolean[][] visits, int i, int j, int n, int m, Climb climb) {
        if(i<0 || j<0 || i>=n || j>=m)
            return;
        if (visits[i][j])
            return;
        visits[i][j] = true;
        if (map[i][j] == 3) {
            climb.found = true;
            return;
        }

        if(j+1<m && (map[i][j+1]==1 || map[i][j+1]==3) && !visits[i][j+1]) {
            search(map, visits, i, j + 1, n, m, climb);
        }
        if(j-1>=0 && (map[i][j-1]==1 || map[i][j-1]==3) && !visits[i][j-1]) {
            search(map, visits, i, j - 1, n, m, climb);
        }
        int h = 1;
        for(h=1;h<=climb.height;h++) {
            if(i-h>=0 && (map[i-h][j]==1 || map[i-h][j]==3) && !visits[i-h][j]) {
                search(map, visits, i-h, j, n, m, climb);
            }
        }
        for(h = 1;h<=climb.height;h++) {
            if(i+h<n && (map[i+h][j]==1 || map[i+h][j]==3) && !visits[i+h][j]) {
                search(map, visits, i+h, j, n, m, climb);
            }
        }
    }
    public static int solve(int[][] map, int n, int m) {
        boolean[][] visits = new boolean[n][m];
        Climb climb = new Climb();
        for (climb.height = 0; climb.height < n; climb.height++) {
            for (boolean[] visit : visits) {
                Arrays.fill(visit, false);
            }
            climb.found = false;
            search(map, visits, n-1, 0, n, m, climb);
            if (climb.found) {
                break;
            }
        }
        return climb.height;
    }
    public static void main(String[] args) throws IOException, Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] first = bufferedReader.readLine().replace("\\s+$", "").split(" ");
        int n, m;
        if (first.length >= 2) {
            n = Integer.parseInt(first[0]);
            m = Integer.parseInt(first[1]);
        } else if (first.length == 1) {
            n = Integer.parseInt(first[0]);
            m = Integer.parseInt(first[0]);
        } else {
            throw new Exception("Bruh");
        }
        int[][] map = new int[n][m];
        for (int i = 0; i < n; i++) {
            String[] row = bufferedReader.readLine().replace("\\s+$", "").split(" ");
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(row[j]);
            }
        }
        bufferedWriter.write(String.valueOf(solve(map, n, m)));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
