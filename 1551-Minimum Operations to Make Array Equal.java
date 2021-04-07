
class MinimumOperationsToMakeArrayEqual {
    // Math.
    public int minOperations(int n) {
        return n % 2 == 0 ? n * n / 4 : (n * n - 1) / 4;
    }

    // Math
    public int minOperationsI(int n) {
        int sum = 0;
        while (n > 0) {
            sum += n - 1;
            n -= 2;
        }
        return sum;
    }
}