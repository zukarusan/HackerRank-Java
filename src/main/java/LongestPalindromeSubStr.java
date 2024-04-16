import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LongestPalindromeSubStr {
    // https://www.geeksforgeeks.org/problems/longest-substring-whose-character-rearranged-can-form-a-palindrome/1?page=1&company=Samsung&difficulty=Hard&sortBy=submissions
    static final int CHAR_A_OFFSET = 'a';
    static final int COUNTER_SIZE = 'z' - 'a' + 1;
    static boolean isPalindromable(int odds, int len) {
        return odds == 0 || (odds == 1 && len % 2 == 1);
    }
    static int[] buildCounter(String str) {
        int len = str.length();
        int[] counter = new int[COUNTER_SIZE];
        Arrays.fill(counter, 0);
        for (int i = 0; i < len; i++) {
            char c = str.charAt(i);
            counter[c-CHAR_A_OFFSET] += 1;
        }
        return counter;
    }
    static int countOdds(int[] counter) {
        int odds = 0;
        for (int val : counter) {
            if (val % 2 == 1) {
                odds++;
            }
        }
        return odds;
    }
    static int recalculate(StringBuilder sb, int idx, int[] counter, boolean insertion, int odds) {
        char c = sb.charAt(idx);
        int n = counter[c-CHAR_A_OFFSET];
        if (n % 2 == 1) {
            odds -= 1;
        } else {
            odds += 1;
        }
        counter[c-CHAR_A_OFFSET] = n + (insertion ? 1 : -1);
        return odds;
    }
    static int findMaximum(StringBuilder sb, int[] counter, int odds, boolean toRight, int subLen, int permutes) {
        if (isPalindromable(odds, subLen)) {
            return subLen;
        }
        int len = sb.length();
        int leftmost = len-subLen-1 ;
        int delIdx= toRight ? subLen : leftmost;
        if (delIdx >= 0 && delIdx < len) {
            odds = recalculate(sb, delIdx, counter, false, odds);
        }
        for (int s = 0; s < permutes; s++) {
            if (isPalindromable(odds, subLen)) {
                return subLen;
            }
            if (s+subLen < len) {
                odds = recalculate(sb, toRight ? s+subLen : leftmost-s, counter, true, odds);
                odds = recalculate(sb, toRight ? s : len-1-s, counter, false, odds);
            }
        }
        toRight = !toRight;
        return findMaximum(sb, counter, odds, toRight, subLen-1, permutes+1);
    }
    static int longestSubstring(String S) {
        // code here
        int[] counter = buildCounter(S);
        return findMaximum(new StringBuilder(S), counter, countOdds(counter), false, S.length(), 1);
    }
    public static void main(String args[]) throws IOException {
        BufferedReader read =
                new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(read.readLine());
        while (t-- > 0) {
            String S = read.readLine();

            System.out.println(longestSubstring(S));
        }
    }
}
