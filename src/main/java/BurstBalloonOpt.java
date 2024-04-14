import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BurstBalloonOpt {
    // https://www.hackerrank.com/contests/target-samsung-13-nov19/challenges/burst-balloons-1
    public static int shootMax(List<Integer> balloons, int score, int max, int bullets) {
        int len = balloons.size();
        if (len == 0 || bullets == 0) {
            max = Math.max(score, max);
            return max;
        }
        int scorePerm;
        for (int i = 0; i < len; i++) {
            if (i == 0 && (i + 1) < len) {
                scorePerm = score + balloons.get(i+1);
            } else if (i == (len - 1) && (i - 1) >= 0) {
                scorePerm = score + balloons.get(i-1);
            } else if (len > 2) {
                scorePerm = score + balloons.get(i-1) * balloons.get(i+1);
            } else  {
                scorePerm = score + balloons.get(i);
            }
            Integer balloon = balloons.remove(i);
            scorePerm = shootMax(balloons, scorePerm, max, bullets-1);
            balloons.add(i, balloon);
            max = Math.max(scorePerm, max);
        }
        return max;
    }
    public static int solve(int bullets, List<Integer> balloons){
        return shootMax(balloons, 0, Integer.MIN_VALUE, bullets);
    }
    public static void main(String[] args) throws IOException, Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int bullets = Integer.parseInt(bufferedReader.readLine().replace("\\s+$", ""));
        String[] row = bufferedReader.readLine().replace("\\s+$", "").split(" ");
        List<Integer> balloons = new ArrayList<>();
        for (String b : row) {
            balloons.add(Integer.parseInt(b));
        }

        bufferedWriter.write(String.format("%d", solve(bullets, balloons)));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
