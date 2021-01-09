import java.util.*;

class ProbabilityOfTwoBoxesHavingSameNumberOfDistinctBalls {

    // DP.
    public double getProbability(int[] balls) {
        int m = balls.length;
        int S = 0;
        for (int b : balls) {
            S += b;
        }
        double[][] C = new double[S + 1][S / 2 + 1];
        C[0][0] = 1;
        for (int i = 1; i < S + 1; i++) {
            C[i][0] = 1;
            for (int j = 1; j < S / 2 + 1; j++) {
                C[i][j] = C[i - 1][j] + C[i - 1][j - 1];
            }
        }
        double[][] dp = new double[2 * m + 1][S / 2 + 1];
        dp[m][0] = 1;
        int sum = 0;
        for (int b : balls) {
            sum += b;
            double[][] ndp = new double[2 * m + 1][S / 2 + 1];
            for (int i = 0; i <= b; i++) {
                for (int j = 0; j < 2 * m + 1; j++) {
                    for (int k = 0; k < S / 2 + 1; k++) {
                        if (dp[j][k] == 0)
                            continue;
                        int nk = k + i;
                        int nr = sum - nk;
                        if (nk <= S / 2 && nr <= S / 2) {
                            int nj = (i == 0) ? j - 1 : (i == b) ? j + 1 : j;
                            ndp[nj][nk] += dp[j][k] * C[b][i];
                        }
                    }
                }
            }
            dp = ndp;
        }
        return dp[m][S / 2] / C[S][S / 2];
    }

    // DFS + Memorization.
    public double getProbabilityI(int[] balls) {
        double[] dp = new double[49];
        dp[0] = 1;
        int sum = Arrays.stream(balls).sum();
        for (int i = 1; i < 49; i++) {
            // this is for calculate C(a, b) as explained in helper method
            dp[i] = dp[i - 1] * i;
        }
        double validNumber = dfs(0, 0, 0, 0, balls, 0, dp);
        // how many different ways we can pick sum / 2 balls from sum
        double totalNumber = combination(sum, sum / 2, dp);
        return validNumber / totalNumber;
    }

    // count means the numebr of distinguish balls in one box, sum means total balls
    // in one box
    private double dfs(int count1, int count2, int sum1, int sum2, int[] balls, int i, double[] dp) {
        if (i == balls.length) {
            return (sum1 == sum2 && count1 == count2) ? 1 : 0;
        }
        double res = dfs(count1 + 1, count2, sum1 + balls[i], sum2, balls, i + 1, dp);
        res += dfs(count1, count2 + 1, sum1, sum2 + balls[i], balls, i + 1, dp);
        for (int j = 1; j < balls[i]; j++) {
            res += combination(balls[i], j, dp)
                    * dfs(count1 + 1, count2 + 1, sum1 + j, sum2 + balls[i] - j, balls, i + 1, dp);
        }
        return res;
    }

    // combination way to calculate pick b from a: C(a, b), eg C(4, 2) = dp[4] /
    // dp[2] / dp[4 - 2] = 6
    private double combination(int a, int b, double[] dp) {
        return dp[a] / dp[b] / dp[a - b];
    }
}
