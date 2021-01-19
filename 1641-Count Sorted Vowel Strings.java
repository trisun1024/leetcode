
class CountSortedVowelStrings {

    // Recursion.
    public int countVowelStrings(int n) {
        return helper(n, 1);
    }

    private int helper(int n, int vowels) {
        if (n == 0) {
            return 1;
        }
        int res = 0;
        for (int i = vowels; i <= 5; i++) {
            res += helper(n - 1, i);
        }
        return res;
    }

    // DP.
    public int countVowelStringsI(int n) {
        int[][] dp = new int[n + 1][6];
        for (int vowels = 1; vowels <= 5; vowels++) {
            dp[1][vowels] = vowels;
        }
        for (int nValue = 2; nValue <= n; nValue++) {
            dp[nValue][1] = 1;
            for (int vowels = 2; vowels <= 5; vowels++) {
                dp[nValue][vowels] = dp[nValue][vowels - 1] + dp[nValue - 1][vowels];
            }
        }
        return dp[n][5];
    }

    // Math.
    public int countVowelStringsII(int n) {
        return (n + 4) * (n + 3) * (n + 2) * (n + 1) / 24;
    }
}