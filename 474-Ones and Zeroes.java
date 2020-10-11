
class OnesAndZeroes {

    // Recursion. Time = O(2^L); Space = O(L);
    public int findMaxFormI(String[] strs, int m, int n) {
        return calculate(strs, 0, m, n);
    }

    private int calculate(String[] strs, int i, int zeroes, int ones) {
        if (i == strs.length) {
            return 0;
        }
        int[] count = countZeroOne(strs[i]);
        int taken = -1;
        if (zeroes - count[0] >= 0 && ones - count[1] >= 0) {
            taken = calculate(strs, i + 1, zeroes - count[0], ones - count[1]);
        }
        int notTaken = calculate(strs, i + 1, zeroes, ones);
        return Math.max(taken, notTaken);
    }

    // Recursion + Memorization.
    public int findMaxFormII(String[] strs, int m, int n) {
        int[][][] memo = new int[strs.length][m + 1][n + 1];
        return calculateI(strs, 0, m, n, memo);
    }

    private int calculateI(String[] strs, int i, int zeroes, int ones, int[][][] memo) {
        if (i == strs.length) {
            return 0;
        }
        if (memo[i][zeroes][ones] != 0) {
            return memo[i][zeroes][ones];
        }
        int[] count = countZeroOne(strs[i]);
        int taken = -1;
        if (zeroes - count[0] >= 0 && ones - count[1] >= 0) {
            taken = calculateI(strs, i + 1, zeroes - count[0], ones - count[1], memo) + 1;
        }
        int not_taken = calculateI(strs, i + 1, zeroes, ones, memo);
        memo[i][zeroes][ones] = Math.max(taken, not_taken);
        return memo[i][zeroes][ones];
    }

    // 2D DP. Time = O(L*M*N); Space = O(M*N);
    public int findMaxForm(String[] strs, int m, int n) {
        int[][] dp = new int[m + 1][n + 1];
        for (String s : strs) {
            int[] count = countZeroOne(s); // [zeroes, ones]
            for (int zero = m; zero >= count[0]; zero--) {
                for (int one = n; one >= count[1]; one--) {
                    dp[zero][one] = Math.max(1 + dp[zero - count[0]][one - count[1]], dp[zero][one]);
                }
            }
        }
        return dp[m][n];
    }

    private int[] countZeroOne(String s) {
        int[] count = new int[2];
        for (char c : s.toCharArray()) {
            count[c - '0']++;
        }
        return count;
    }

    public int findMaxFormIII(String[] strs, int m, int n) {
        int len = strs.length;
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i < len; i++) {
            int one = 0, zero = 0;
            for (char c : strs[i].toCharArray()) {
                if (c == '1')
                    one++;
            }
            zero = strs[i].length() - one;
            for (int im = m; im >= zero; im--) {
                for (int in = n; in >= one; in--) {
                    dp[im][in] = Math.max(dp[im][in], dp[im - zero][in - one] + 1);
                }
            }
        }
        return dp[m][n];
    }
}