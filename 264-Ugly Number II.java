import java.util.*;

class UglyNumberII {

    // PriorityQueue store numbers
    public int nthUglyNumber(int n) {
        PriorityQueue<Long> pq = new PriorityQueue<>();
        Set<Long> set = new HashSet<>();
        pq.offer(1L);
        set.add(1L);
        while (n > 1) {
            long cur = pq.poll();
            if (set.add(2 * cur)) {
                pq.offer(2 * cur);
            }
            if (set.add(3 * cur)) {
                pq.offer(3 * cur);
            }
            if (set.add(5 * cur)) {
                pq.offer(5 * cur);
            }
            n--;
        }
        return (int) (long) pq.peek();
    }

    public int nthUglyNumberI(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        int index2 = 0, index3 = 0, index5 = 0;
        for (int i = 1; i < n; i++) {
            dp[i] = Math.min(dp[index2] * 2, Math.min(dp[index3] * 3, dp[index5] * 5));

            if (dp[i] == dp[index2] * 2)
                index2++;
            if (dp[i] == dp[index3] * 3)
                index3++;
            if (dp[i] == dp[index5] * 5)
                index5++;
        }
        return dp[n - 1];
    }
}