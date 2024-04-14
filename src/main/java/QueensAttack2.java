
import java.io.*;
import java.util.*;
public class QueensAttack2 {

    /*
     * Complete the 'queensAttack' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. INTEGER k
     *  3. INTEGER r_q
     *  4. INTEGER c_q
     *  5. 2D_INTEGER_ARRAY obstacles
     */

    public static boolean isColIntersected(int constant, List<Integer> arg) {
        return arg.get(1) == constant;
    }
    public static boolean isRowIntersected(int constant, List<Integer> arg) {
        return arg.get(0) == constant;
    }
    public static boolean isDiagUpIntersected(int constant, List<Integer> arg) {
        return arg.get(0) + constant == arg.get(1);
    }
    public static boolean isDiagDownIntersected(int constant, List<Integer> arg) {
        return (-arg.get(0)) + constant == arg.get(1);
    }
    public static boolean isAnyIntersected(List<Integer> arg, int cCol, int cRow, int cDUp, int cDDown) {
        return isColIntersected(cCol, arg)
                || isRowIntersected(cRow, arg)
                || isDiagUpIntersected(cDUp, arg)
                || isDiagDownIntersected(cDDown, arg);
    }
    public static boolean isSameDirection (int vecVal1, int vecVal2) {
        return (vecVal1 > 0 && vecVal2 > 0)
                || (vecVal1 < 0 && vecVal2 < 0)
                || (vecVal1 == 0 && vecVal2 == 0);
    }
    public static boolean isWithinAttack(int[] attack, int[] obstacle) {
        return isSameDirection(attack[0], obstacle[0])
                && isSameDirection(attack[1], obstacle[1]);
    }
    public static int queensAttack(int n, int k, int r_q, int c_q, List<List<Integer>> obstacles) throws Exception {
        // Write your code here
        int cX = c_q,
                cY = r_q,
                cDUp = (-c_q) + r_q,
                cDDown = c_q + r_q;
        int[] queen = new int[2];
        queen[0] = c_q;
        queen[1] = r_q;

        List<int[]> intersects = new ArrayList<>();
        for (List<Integer> ob : obstacles) {
            if (isAnyIntersected(ob, cX, cY, cDUp, cDDown)) {
                int[] vector = new int[2];
                vector[0] = ob.get(1) - queen[0];
                vector[1] = ob.get(0) - queen[1];
                intersects.add(vector);
            }
        }
        int[] attack = new int[2], attLen = new int[2];
        List<int[]> temp = new ArrayList<>();
        int[] possibles = new int[8];
        int dir = 0;
        for (int i = 0, x = 0, _x = x, y = 1, _y = y; i < 8; i++) {
            attack[0] = x > 0 ? n - queen[0] : (x < 0 ? -(queen[0]-1) : 0);
            attack[1] = y > 0 ? n - queen[1] : (y < 0 ? -(queen[1]-1) : 0);
//            if (attack[0])
            attLen[0] = Math.abs(attack[0]);
            attLen[1] = Math.abs(attack[1]);
            if (attLen[1] == 0) {
                dir = 0;
            } else if (attLen[0] == 0) {
                dir = 1;
            } else if (attLen[0] < attLen[1]) {
                dir = 0;
            } else {
                dir = 1;
            }
            if (queen[0] + x <= n && queen[0] + x > 0 && queen[1] + y <= n && queen[1] + y > 0){
                possibles[i] = attLen[dir];
            }
            for (int[] ob : intersects) {
                if (isWithinAttack(attack, ob)) {
                    if (ob[dir] <= possibles[i]) {
                        possibles[i] = ob[dir] -1;
                    }
                    temp.add(ob);
                }
            }
            for (int[] ob : temp) {
                intersects.remove(ob);
            }
            // rotation
            x = _x + _y;
            y = (-_x) + _y;
            _x = Math.abs(x);
            _y = Math.abs(y);
            x = _x > 0 ? x / _x : x;
            y = _y > 0 ? y / _y : y;
            _x = x; _y = y;
        }
        int sum = 0;
        for (int t : possibles) {
            sum += t;
        }
        return sum;
    }

    public static void main(String[] args) throws IOException, Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int k = Integer.parseInt(firstMultipleInput[1]);

        String[] secondMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int r_q = Integer.parseInt(secondMultipleInput[0]);

        int c_q = Integer.parseInt(secondMultipleInput[1]);

        List<List<Integer>> obstacles = new ArrayList<>();

        for (int i = 0; i < k; i++) {
            String[] obstaclesRowTempItems = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

            List<Integer> obstaclesRowItems = new ArrayList<>();

            for (int j = 0; j < 2; j++) {
                int obstaclesItem = Integer.parseInt(obstaclesRowTempItems[j]);
                obstaclesRowItems.add(obstaclesItem);
            }

            obstacles.add(obstaclesRowItems);
        }

        int result = queensAttack(n, k, r_q, c_q, obstacles);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
