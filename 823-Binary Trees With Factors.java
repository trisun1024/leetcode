import java.util.*;

class BinaryTreeWithFactors {
    // DP.
    public int numFactoredBinaryTrees(int[] arr) {
        int MOD = 1_000_000_007;
        int n = arr.length;
        Arrays.sort(arr);
        long[] dp = new long[n];
        Arrays.fill(dp, 1);
        Map<Integer, Integer> index = new HashMap<>();
        for (int i = 0; i < n; i++) {
            index.put(arr[i], i);
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (arr[i] % arr[j] == 0) {
                    int right = arr[i] / arr[j];
                    if (index.containsKey(right)) {
                        dp[i] = (dp[i] + dp[j] * dp[index.get(right)]) % MOD;
                    }
                }
            }
        }
        long ans = 0;
        for (long x : dp) {
            ans += x;
        }
        return (int) (ans % MOD);
    }

    // DP with early stop.
    public int numFactoredBinaryTreesI(int[] arr) {
        Map<Integer, Integer> inv = new HashMap<>();
        long res = 0;
        int mod = (int) 1e9 + 7;
        int n = arr.length;
        long[] dp = new long[n];
        Arrays.sort(arr);
        Arrays.fill(dp, 1);
        for (int i = 0; i < n; i++) {
            inv.put(arr[i], i);
        }
        for (int i = 0; i < n; i++) {
            for (int j = i; j >= 0; j--) {
                long prod = (long) arr[i] * (long) arr[j];
                if (prod > Integer.MAX_VALUE) {
                    continue;
                }
                if (inv.containsKey(arr[i] * arr[j])) {
                    int index = inv.get(arr[i] * arr[j]);
                    dp[index] = (dp[index] + (dp[i] * dp[j]) % mod * (i == j ? 1L : 2L) % mod) % mod;
                }
            }
        }
        for (long x : dp) {
            res = (res + x) % mod;
        }
        return (int) res;
    }
}