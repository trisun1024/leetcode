
class NumbersAtMostNGivenDigitSet {

    // DP. Time = O(log(N)); Space = O(log(N));
    public int atMostNGivenDigitSet(String[] digits, int n) {
        String s = String.valueOf(n);
        int len = s.length();
        int[] dp = new int[len + 1];
        dp[len] = 1;
        for (int i = len - 1; i >= 0; i--) {
            int num = s.charAt(i) - '0';
            for (String digit : digits) {
                if (Integer.valueOf(digit) < num) {
                    dp[i] += Math.pow(digits.length, len - i - 1);
                } else if (Integer.valueOf(digit) == num) {
                    dp[i] += dp[i + 1];
                }
            }
        }

        for (int i = 1; i < len; i++) {
            dp[0] += Math.pow(digits.length, i);
        }
        return dp[0];
    }

    // Mathematics.
    public int atMostNGivenDigitSetI(String[] digits, int n) {
        int len = digits.length; // bijective-base B
        char[] ca = String.valueOf(n).toCharArray();
        int K = ca.length;
        int[] A = new int[K];
        int t = 0;

        for (char c : ca) {
            int c_index = 0; // Largest such that c >= D[c_index - 1]
            boolean match = false;
            for (int i = 0; i < len; ++i) {
                if (c >= digits[i].charAt(0)) {
                    c_index = i + 1;
                }
                if (c == digits[i].charAt(0)) {
                    match = true;
                }
            }

            A[t++] = c_index;
            if (match) {
                continue;
            }

            if (c_index == 0) { // subtract 1
                for (int j = t - 1; j > 0; --j) {
                    if (A[j] > 0) {
                        break;
                    }
                    A[j] += len;
                    A[j - 1]--;
                }
            }

            while (t < K) {
                A[t++] = len;
            }
            break;
        }

        int ans = 0;
        for (int x : A) {
            ans = ans * len + x;
        }
        return ans;
    }

}