import java.util.*;

public class MinSumPartition {
    // https://www.geeksforgeeks.org/problems/minimum-sum-partition3317/1?page=1&company=Samsung&difficulty=Hard&sortBy=submissions
    public static int sum(List<Integer> set) {
        int sum = 0;
        for (Integer el : set) {
            sum += el;
        }
        return sum;
    }
    public static int permute(List<Integer> first, List<Integer> second, int min, int firstSum, int secondSum, Map<Integer, Integer> memo) {
        if (memo.containsKey(firstSum)) {
            return memo.get(firstSum);
        }
        min = Math.min(Math.abs(firstSum - secondSum), min);
        int len = first.size();
        for (int i = 0; i < len; i++) {
            Integer el = first.remove(i);
            second.add(el);
            min = Math.min(permute(first, second, min, firstSum-el, secondSum+el, memo), min);
            second.remove(second.size()-1);
            first.add(i, el);
        }
        memo.put(firstSum, min);
        return min;
    }
    public static int minDifference(int[] arr, int n)
    {
        // Your code goes here
        Arrays.sort(arr);
        List<Integer> first = new ArrayList<>();
        for (int el : arr) {
            first.add(el);
        }
        return permute(first, new ArrayList<>(), Integer.MAX_VALUE, sum(first), 0, new HashMap<>());
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t-->0)
        {
            int n = sc.nextInt();
            int A[] = new int[n];
            for(int i = 0;i<n;i++)
                A[i] = sc.nextInt();
            System.out.println(minDifference(A,n));
        }
    }

}
