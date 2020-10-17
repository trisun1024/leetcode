import java.util.*;

class FreedomTrail {

    // 2D DP. Time = O(M*N); Space = O(M*N);
    /*
     * dp[i][j] rows key.length cols ring.length; dp[i][j] key index i and ring
     * start from index j of minimum steps
     */
    public int findRotateSteps(String ring, String key) {
        int n = ring.length(), m = key.length();
        int[][] dp = new int[m + 1][n];
        for (int i = m - 1; i >= 0; --i) {
            for (int j = 0; j < n; ++j) {
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = 0; k < n; ++k) {
                    if (ring.charAt(k) == key.charAt(i)) {
                        int diff = Math.abs(j - k);
                        int step = Math.min(diff, n - diff);
                        dp[i][j] = Math.min(dp[i][j], step + dp[i + 1][k]);
                    }
                }
            }
        }
        return dp[0][0] + m;
    }

    // DFS + Memorization.
    public int findRotateStepsI(String ring, String key) {
        if (ring == null || ring.length() == 0 || key == null || key.length() == 0) {
            return 0;
        }
        List<Integer>[] ringArray = new List[26];
        for (int i = 0; i < ring.length(); i++) {
            int index = ring.charAt(i) - 'a';
            if (ringArray[index] == null) {
                ringArray[index] = new ArrayList<>();
            }
            ringArray[index].add(i);
        }
        int[][] cache = new int[ring.length()][key.length()];
        return search(ringArray, ring.length(), 0, key, 0, cache);
    }

    private int search(List<Integer>[] ring, int len, int p, String key, int index, int[][] cache) {
        if (index == key.length()) {
            return 0;
        }
        if (cache[p][index] > 0) {
            return cache[p][index];
        }
        char c = key.charAt(index);
        List<Integer> indices = ring[c - 'a'];
        int min = Integer.MAX_VALUE;
        for (int i : indices) {
            int oneDir = Math.abs(p - i);
            int otherDir = len - oneDir;
            min = Math.min(min, 1 + Math.min(oneDir, otherDir) + search(ring, len, i, key, index + 1, cache));
        }
        cache[p][index] = min;
        return min;
    }
}