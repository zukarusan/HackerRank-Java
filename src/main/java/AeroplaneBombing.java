import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AeroplaneBombing {
    public static int maxCoin(int[][] map, int row, int col, int calculated, int max, boolean bomb, int effect) {
        if (row < 0 || calculated < 0) {
            if (calculated > max) {
                max = calculated;
            }
            return max;
        }
        int type;
        for (int i = -1; i <= 1; i++) {
            if (col + i < 0 || col + i >= 5) {
                continue;
            }
            type = map[row][col+i];
            if (type == 1 || type == 0) {
                if (bomb) {
                    max = maxCoin(map, row-1, col+i, calculated+type, max, true, effect-1);
                } else {
                    max = maxCoin(map, row-1, col+i, calculated+type, max, false, effect);
                }
            } else {
                if (bomb) {
                    if (effect > 0) {
                        max = maxCoin(map, row-1, col+i, calculated, max, true, effect-1);
                    } else {
                        max = maxCoin(map, row-1, col+i, calculated-1, max, true, effect);
                    }
                } else { // use bomb whenever see opponent first
                    max = maxCoin(map, row-1, col+i, calculated, max, true, 5);
                }
            }
        }
        return max;
    }
    public static void main(String[] args) throws IOException, Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(bufferedReader.readLine().replace("\\s+$", ""));
        int n = 0;
        int[][] map;
        String[] result = new String[t];
        for (int i = 0; i < t; i++) {
            n = Integer.parseInt(bufferedReader.readLine().replace("\\s+$", ""));
            map = new int[n][5];
            for (int j = 0; j < n; j++) {
                String[] row = bufferedReader.readLine().replace("\\s+$", "").split(" ");
                for (int k = 0; k < 5; k++) {
                    map[j][k] = Integer.parseInt(row[k]);
                }
            }
            result[i] = String.format("#%1$d %2$d", i+1, maxCoin(map, n - 1, 2, 0, 0, false, 0));
        }
        for (String rs : result) {
            bufferedWriter.write(rs);
            bufferedWriter.newLine();
        }

        bufferedReader.close();
        bufferedWriter.close();
    }
}
