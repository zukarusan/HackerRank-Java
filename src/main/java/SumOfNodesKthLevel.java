import java.io.*;
import java.util.Stack;

public class SumOfNodesKthLevel {
    public static int solve (int k, String tree) {
        Stack<Boolean> levelTree = new Stack<>();
        int len = tree.length();
        StringBuilder number = new StringBuilder();
        int sum = 0;
        k = k + 1;
        for (int i = 0; i < len;) {
            char comp = tree.charAt(i);
            if (comp == '(') {
                levelTree.add(true);
                i++;
            } else if (comp == ')') {
                levelTree.pop();
                i++;
            } else if (levelTree.size() == k) {
                number.setLength(0);
                for (int j = i; j < len; j++) {
                    comp = tree.charAt(j);
                    if (comp == '(' || comp == ')') {
                        break;
                    }
                    number.append(comp);
                    i++;
                }
                sum += Integer.parseInt(number.toString());
            } else {
                i++;
            }
        }
        return sum;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int k = Integer.parseInt(reader.readLine().replace("\\s+$", ""));
        String tree = reader.readLine().replace("\\s+$", "");

        writer.write(String.valueOf(solve(k, tree)));
        writer.newLine();

        reader.close();
        writer.close();
    }
}
