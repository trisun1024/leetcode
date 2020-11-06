
class DecodeWaysII {

    // Recursion + Memorization. 
    public int numDecodings(String s) {
        int M = 1000000007;
        Integer[] memo = new Integer[s.length()];
        return helper(s.toCharArray(), s.length() - 1, memo, M);
    }

    private int helper(char[] arr, int i, Integer[] memo, int M) {
        if (i < 0) {
            return 1;
        }
        if (memo[i] != null) {
            return memo[i];
        }
        long res;
        if (arr[i] == '*') {
            res = 9 * helper(arr, i - 1, memo, M);
            if (i > 0 && arr[i - 1] == '1') {
                res = (res + 9 * helper(arr, i - 2, memo, M)) % M;
            } else if (i > 0 && arr[i - 1] == '2') {
                res = (res + 6 * helper(arr, i - 2, memo, M)) % M;
            } else if (i > 0 && arr[i - 1] == '*') {
                res = (res + 15 * helper(arr, i - 2, memo, M)) % M;
            }
        } else {
            res = arr[i] != '0' ? helper(arr, i - 1, memo, M) : 0;
            if (i > 0 && arr[i - 1] == '1') {
                res = (res + helper(arr, i - 2, memo, M)) % M;
            } else if (i > 0 && arr[i - 1] == '2' && arr[i] <= '6') {
                res = (res + helper(arr, i - 2, memo, M)) % M;
            } else if (i > 0 && arr[i - 1] == '*') {
                res = (res + (arr[i] <= '6' ? 2 : 1) * helper(arr, i - 2, memo, M)) % M;
            }

        }
        memo[i] = (int) res;
        return memo[i];
    }
}
