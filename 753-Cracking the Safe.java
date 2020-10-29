import java.util.*;

class CrackingTheSafe {

    // Hierholzer's Algorithm. Time = O(N*K^N); Space = O(N*K^N)
    public String crackSafe(int n, int k) {
        if (n == 1 && k == 1) {
            return "0";
        }
        Set<String> seen = new HashSet<>();
        StringBuilder res = new StringBuilder();
        StringBuilder cur = new StringBuilder();
        for (int i = 0; i < n - 1; i++) {
            cur.append("0");
        }
        String start = cur.toString();
        dfs(start, k, seen, res);
        res.append(start);
        return new String(res);
    }

    private void dfs(String node, int k, Set<String> seen, StringBuilder res) {
        for (int i = 0; i < k; i++) {
            String nei = node + i;
            if (!seen.contains(nei)) {
                seen.add(nei);
                dfs(nei.substring(1), k, seen, res);
                res.append(i);
            }
        }
    }

    // Inverse Burrows-Wheeler Transform.
    public String crackSafeI(int n, int k) {
        int M = (int) Math.pow(k, n - 1);
        int[] dp = new int[M * k];
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < M; j++) {
                dp[i * M + j] = i + j * k;
            }
        }

        StringBuilder res = new StringBuilder();
        for (int i = 0; i < M * k; i++) {
            int j = i;
            while (dp[j] >= 0) {
                res.append(String.valueOf(j / M));
                int v = dp[j];
                dp[j] = -1;
                j = v;
            }
        }
        for (int i = 0; i < n - 1; i++) {
            res.append("0");
        }
        return new String(res);
    }
}
