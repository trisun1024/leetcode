import java.util.*;

class DecodeWays {
    // Recursion.
    public int numDecodingsI(String s) {
        char[] arr = s.toCharArray();
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();
        dfs(arr, 0, cur, res);
        return res.size();
    }

    private void dfs(char[] arr, int index, List<Integer> cur, List<List<Integer>> res) {
        if (index == arr.length) {
            res.add(new ArrayList<>(cur));
            return;
        }
        cur.add((int) (arr[index] - '0'));
        dfs(arr, index + 1, cur, res);
        cur.remove(cur.size() - 1);
        char c = arr[index];
        char prev = arr[index + 1];
        if ((prev == '1' && c >= '0' && c <= '9') || (prev == '2' && c >= '0' && c <= '6')) {
            int num = 0;
            num = num * 10 + (c - '0');
            num = num * 10 + (prev - '0');
            cur.add(num);
            dfs(arr, index + 2, cur, res);
            cur.remove(cur.size() - 1);
        }
    }

    // DP.
    public int numDecodings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int n = s.length();
        char[] arr = s.toCharArray();
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = arr[0] == '0' ? 0 : 1;
        for (int i = 2; i <= n; i++) {
            char cur = arr[i - 1];
            char prev = arr[i - 2];
            if (cur >= '1' && cur <= '9') {
                dp[i] += dp[i - 1];
            }
            if ((prev == '1' && cur >= '0' && cur <= '9') || (prev == '2' && cur >= '0' && cur <= '6')) {
                dp[i] += dp[i - 2];
            }
        }
        return dp[n];
    }
}