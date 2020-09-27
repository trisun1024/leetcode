
class RotateFunction {

    // DP
    // F(K) = F(K-1) + SUM - N * (( K-1)th element from end of array)
    public int maxRotateFunction(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        int sum = 0;
        int F0 = 0;
        for (int i = 0; i < A.length; i++) {
            sum += A[i];
            F0 += i * A[i];
        }
        int[] dp = new int[A.length];
        dp[0] = F0;
        int max = dp[0];
        for (int i = 1; i < A.length; i++) {
            dp[i] = dp[i - 1] + sum - A.length * A[A.length - i];
            max = Math.max(max, dp[i]);
        }
        return max;
    }
}