// Brute Force
class PowerFunction {
    public double myPow(double x, int n) {
        long N = n;
        if (N < 0) {
            x = 1 / x;
            N = -N;
        }
        double res = 1;
        for (long i = 0; i < N; i++) {
            res *= x;
        }
        return res;
    }

    // Fast Power Recursive
    // Consider x^n as x^(2*n) to downsize time O(n) to O(logN)
    public double myPowI(double x, int n) {
        int N = n;
        if (N < 0) {
            x = 1 / x;
            N = -N;
        }
        return fastPow(x, N);
    }

    private double fastPow(double x, int n) {
        if (n == 0)
            return 1.0;
        double half = fastPow(x, n / 2);
        if (n % 2 == 0)
            return half * half;
        else
            return half * half * x;
    }
}