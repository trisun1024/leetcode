
class Solution1 {

    // Mathematical Deduction C_n
    public int numTrees(int n) {
        long C = 1;
        for (int i = 0; i < n; ++i) {
            C = C * 2 * (2 * i + 1) / (i + 2);
        }
        return (int) C;
    }

    // DP
    /*
    * base case 
    * M[0] = 1; 
    * M[1] = 1;
    * induction rule:
    * M[i] represents number of combinations of tree; we use j and i-j to check the previous numbers, and do math
    */
    public int numTreesII(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i] += dp[j - 1] * dp[i - j];
            }
        }
        return dp[n];
    }
}
