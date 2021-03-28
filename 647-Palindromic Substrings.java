
class PalindromicSubstrings {

    // Centre Out. Time = O(N^2);
    public int countSubstrings(String s) {
        int res = 0;
        int len = s.length();
        for (int i = 0; i < len / 2 - 1; i++) {
            int l = i / 2;
            int r = l + i % 2;
            while (l >= 0 && r < len && s.charAt(l) == s.charAt(r)) {
                res++;
                l--;
                r++;
            }
        }
        return res;
    }
    // DP.
    public int countSubstringsII(String s) {
        int ans = 0;
        if (s.length() <= 0) {
            return ans;
        }
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
            ans++;
        }
        for (int i = 0; i < n - 1; i++) {
            dp[i][i + 1] = (s.charAt(i) == s.charAt(i + 1));
            ans += (dp[i][i + 1] ? 1 : 0);
        }
        for (int len = 3; len <= n; len++) {
            for (int i = 0, j = i + len - 1; j < n; i++, j++) {
                dp[i][j] = dp[i + 1][j - 1] && (s.charAt(i) == s.charAt(j));
                ans += (dp[i][j] ? 1 : 0);
            }
        }
        return ans;
    }

    // Manacher's Algorithm. Time = O(N); Space = O(N);
    public int countSubstringsI(String s) {
        char[] arr = new char[2 * s.length() + 3];
        arr[0] = '@';
        arr[1] = '#';
        arr[arr.length - 1] = '$';
        int t = 2;
        for (char c : s.toCharArray()) {
            arr[t++] = c;
            arr[t++] = '#';
        }

        int[] Z = new int[arr.length];
        int center = 0, right = 0;
        for (int i = 1; i < Z.length - 1; ++i) {
            if (i < right) {
                Z[i] = Math.min(right - i, Z[2 * center - i]);
            }
            while (arr[i + Z[i] + 1] == arr[i - Z[i] - 1]) {
                Z[i]++;
            }
            if (i + Z[i] > right) {
                center = i;
                right = i + Z[i];
            }
        }
        int ans = 0;
        for (int v : Z) {
            ans += (v + 1) / 2;
        }
        return ans;
    }
}
