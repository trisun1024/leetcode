
class UniqueSubstringsInWraparoundString {

    // DP. Time = O(N); Space = O(N);
    public int findSubstringInWraproundString(String p) {
        if (p == null || p.length() == 0) {
            return 0;
        }
        int[] dp = new int[p.length()];
        int[] chars = new int[26];
        dp[0] = 1;
        chars[p.charAt(0) - 'a'] = 1;
        char last = p.charAt(0);
        char cur;
        for (int i = 1; i < p.length(); i++) {
            cur = p.charAt(i);
            if ((last + 1 == cur) || (last == 'z' && cur == 'a')) {
                dp[i] = dp[i - 1] + 1;
            } else {
                dp[i] = 1;
            }
            chars[cur - 'a'] = Math.max(chars[cur - 'a'], dp[i]);
            last = cur;
        }
        // sum
        int sum = 0;
        for (int i : chars) {
            sum += i;
        }
        return sum;
    }

    // DP. Time = O(N); Space = O(1);
    public int findSubstringInWraproundStringI(String p) {
        if (p.length() <= 1) {
            return p.length();
        }
        int[] counts = new int[26];
        int n = p.length();
        int res = 0;
        int len = 0;
        int prevCharacter = 0;
        for (int i = 0; i < n; i++) {
            int curCharacter = p.charAt(i) - 'a';
            if (i > 0 && (curCharacter - prevCharacter == 1 || curCharacter - prevCharacter == -25)) {
                len++;
            } else {
                len = 1;
            }
            counts[curCharacter] = Math.max(counts[curCharacter], len);
            prevCharacter = curCharacter;
        }
        // sum result
        for (int cnt : counts) {
            res += cnt;
        }
        return res;
    }
}