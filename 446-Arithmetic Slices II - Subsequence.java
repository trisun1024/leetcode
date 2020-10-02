import java.util.*;

class ArithmeticSlicesIISubsequence {

    // Brute Force. Time = O(2^N); Space = O(N);
    public int numberOfArithmeticSlicesI(int[] A) {
        int[] count = new int[] { 0 };
        List<Long> cur = new ArrayList<>();
        dfs(A, 0, cur, count);
        return count[0];
    }

    private void dfs(int[] A, int index, List<Long> cur, int[] count) {
        if (index == A.length) {
            if (cur.size() < 3) {
                return;
            }
            long diff = cur.get(1) - cur.get(0);
            for (int i = 1; i < cur.size(); i++) {
                if (cur.get(i) - cur.get(i - 1) != diff) {
                    return;
                }
            }
            count[0]++;
            return;
        }
        dfs(A, index + 1, cur, count);
        cur.add((long) A[index]);
        dfs(A, index + 1, cur, count);
        cur.remove(cur.size() - 1);
    }

    // DP. Time = O(N^2); Space = O(N^2);
    public int numberOfArithmeticSlices(int[] A) {
        int count = 0;
        int n = A.length;
        HashMap<Integer, Integer>[] dp = new HashMap[n];
        for (int i = 0; i < n; i++) {
            dp[i] = new HashMap<>(i);
            for (int j = 0; j < i; j++) {
                long delta = (long) A[i] - (long) A[j];
                if (delta < Integer.MIN_VALUE || delta > Integer.MAX_VALUE) {
                    continue;
                }
                int diff = (int) delta;
                int sum = dp[j].getOrDefault(diff, 0);
                int origin = dp[i].getOrDefault(diff, 0);
                dp[i].put(diff, origin + sum + 1);
                count += sum;
            }
        }
        return count;
    }

    // DP improve
    public int numberOfArithmeticSlicesII(int[] A) {
        int n = A.length;
        int[][] dp = new int[n][n];
        Map<Long, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.putIfAbsent((long) A[i], new ArrayList<>());
            map.get((long) A[i]).add(i);
        }

        int res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                long target = 2 * (long) A[j] - A[i];
                if (map.containsKey(target)) {
                    for (int k : map.get(target)) {
                        if (k < j) {
                            dp[i][j] += (dp[j][k] + 1);
                        }
                    }
                }
                res += dp[i][j];
            }
        }

        return res;
    }
}