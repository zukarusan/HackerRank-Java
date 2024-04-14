import java.io.*;
import java.util.Collections;

public class FisherMen {
    public static int solve(int n, int[] gp, int[] nf) {
        int min = Integer.MAX_VALUE;
        for (int i = 1; i <= n-nf[1]-nf[2]; i++) {
            for (int j = i + nf[0]; j <= n-nf[2]; j++) {
                for (int k = j + nf[1]; k <= n; k++) {
                    int sum = 0;
                    for (int p = i; p < i + nf[0]; p++)
                        sum += Math.abs(gp[0] - p);
                    for (int p = j; p < j + nf[1]; p++)
                        sum += Math.abs(gp[1] - p);
                    for (int p = k; p < k + nf[2]; p++)
                        sum += Math.abs(gp[2] - p);
                    sum += nf[0] + nf[1] + nf[2];
                    min = Math.min(sum, min);
                }
            }
        }
        return min;
    }
    public static void main(String[] args) throws IOException, Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(bufferedReader.readLine().replace("\\s+$", ""));
        String[] first = bufferedReader.readLine().replace("\\s+$", "").split(" ");
        String[] second = bufferedReader.readLine().replace("\\s+$", "").split(" ");
        int[] gp = new int[3], nf = new int[3];
        for (int i= 0; i < 3; i++) {
            gp[i] = Integer.parseInt(first[i]);
            nf[i] = Integer.parseInt(second[i]);
        }

        bufferedWriter.write(String.valueOf(solve(n, gp, nf)));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
