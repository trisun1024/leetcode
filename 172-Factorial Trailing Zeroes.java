class FactorialTrailingZeroes {
    // recursion
    public static int trailingZeroes(int n) {
        return n < 5 ? 0 : (n / 5 + trailingZeroes(n / 5));
    }

    // iteration
    // Time O(log(N)) Space O(1)
    public static int trailingZeroes2(int n) {
        if (n < 1) {
            return 0;
        }
        int fives = 0;
        for (long i = 5; i <= n; i *= 5) { // i is 5, 25, 125, 625 ...
            fives += n / i; // to count how many multiples of "i" are in range 1...n, we just do n/i
        }
        return fives;
    }
}