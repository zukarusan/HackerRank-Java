import java.io.*;

public class FlipColumns {
    // https://www.hackerrank.com/contests/target-samsung-13-nov19/challenges/flip-columns
    static void flip(boolean[][] matrix, int column, int n){
        for(int i=0;i<n;i++){
            matrix[i][column] = !matrix[i][column];
        }
    }
    public static int solve(int n, int m, int k, boolean[][] matrix, int max) {
        if(k==0){
            int row_count=0;
            for(int x=0;x<n;x++){
                int count=0;
                for(int y=0;y<m;y++){
                    if(matrix[x][y])
                        count++;
                }
                if(count==m)
                    row_count++;
            }
            if(row_count > max)
                max=row_count;
            return max;
        }
        for(int i=0;i<m;i++){
            flip(matrix, i, n);
            int calc = solve(n, m , k-1, matrix, max);
            max = Math.max(calc, max);
            flip(matrix, i, n);
        }
        return max;
    }
    public static void main(String[] args)  throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] params = reader.readLine().replace("\\s+$", "").split(" ");
        int n = Integer.parseInt(params[0]);
        int m = Integer.parseInt(params[1]);
        int k = Integer.parseInt(params[2]);
        boolean[][] matrix = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            String[] row = reader.readLine().replace("\\s+$", "").split(" ");
            for (int j = 0; j < m; j++) {
                matrix[i][j] = row[j].equals("1");
            }
        }

        writer.write(String.valueOf(solve(n, m, k, matrix, 0)));
        writer.newLine();

        reader.close();
        writer.close();
    }
}
