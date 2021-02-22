
class ArithmeticSlices {

    // Recursion.
    public int numberOfArithmeticSlices(int[] A) {
        int[] sum = new int[] { 0 };
        helper(A, A.length - 1, sum);
        return sum[0];
    }

    private int helper(int[] A, int i, int[] sum) {
        if (i < 2) {
            return 0;
        }
        int count = 0;
        if ((A[i] - A[i - 1]) == (A[i - 1] - A[i - 2])) {
            count = 1 - helper(A, i - 1, sum);
            sum[0] += count;
        } else {
            helper(A, i - 1, sum);
        }
        return count;
    }

    // DP. Time = O(N); Space = O(N);
    public int numberOfArithmeticSlicesI(int[] A) {
        int[] dp = new int[A.length];
        int sum = 0;
        for (int i = 2; i < dp.length; i++) {
            if ((A[i] - A[i - 1]) == (A[i - 1] - A[i - 2])) {
                dp[i] = 1 + dp[i - 1];
                sum += dp[i];
            }
        }
        return sum;
    }

    // DP. Time = O(N); Space = O(1);
    public int numberOfArithmeticSlicesII(int[] A) {
        int dp = 0;
        int sum = 0;
        for (int i = 2; i < A.length; i++) {
            if (A[i] - A[i - 1] == A[i - 1] - A[i - 2]) {
                dp = 1 + dp;
                sum += dp;
            } else {
                dp = 0;
            }
        }
        return sum;
    }
}