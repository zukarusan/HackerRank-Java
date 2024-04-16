import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class FindLastDigitLargeAB {
    static int mod(String num, int by) {
        int res = 0;
        int len = num.length();
        for (int i = 0; i < len; i++) {
            res = (res * 10 + num.charAt(i) - '0') % by;
        }
        return res;
    }
    static int getLastDigit(String a, String b) {
        // code here
        if (b.length() == 1 && b.charAt(0) - '0' == 0) {
            return 1;
        }
        int base = a.charAt(a.length()-1) - '0';
        List<Integer> pattern = new ArrayList<>();
        int last = base;
        do {
            pattern.add(last);
            last *= base;
            last %= 10;
        } while (last != base);
        int n = pattern.size();
        int idx = mod(b, n) - 1 % n;
        idx = idx < 0 ? n + idx : idx;
        return  pattern.get(idx);
    }
    public static void main(String args[]) throws IOException {
        BufferedReader read =
                new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(read.readLine());
        while (t-- > 0) {
            String S[] = read.readLine().split(" ");

            String a = S[0];
            String b = S[1];

            System.out.println(getLastDigit(a,b));
        }
    }
}
