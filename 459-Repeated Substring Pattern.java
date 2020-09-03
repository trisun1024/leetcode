import java.util.regex.Pattern;

class RepeatedSubstringPattern {

    // Regex. Time = O(N^2);
    public boolean repeatedSubstringPattern(String s) {
        Pattern pat = Pattern.compile("^(.+)\\1+$");
        return pat.matcher(s).matches();
    }

    // Two Pointers
    public boolean repeatedSubstringPatternI(String s) {
        int n = s.length();
        for (int l = n / 2; l > 0; l--) {
            if (n % l == 0) {
                int i = 0;
                while (i + l < n && s.charAt(i) == s.charAt(i + l)) {
                    i++;
                }
                if (i + l == n) {
                    return true;
                }
            }
        }
        return false;
    }

    // DP
    public boolean repeatedSubstringPatternII(String s) {
        int n = s.length();
        int[] dp = new int[n];
        for (int i = 1; i < n; i++) {
            int j = dp[i - 1];
            while (j > 0 && s.charAt(i) != s.charAt(j)) {
                j = dp[j - 1];
            }
            if (s.charAt(i) == s.charAt(j)) {
                j++;
            }
            dp[i] = j;
        }
        int l = dp[n - 1];
        return l != 0 && (n - 1) == 0;
    }
}