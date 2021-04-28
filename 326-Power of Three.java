class PowerOfThree {

    // Loop Iteration
    public boolean isPowerOfThree(int n) {
        if (n < 1) {
            return false;
        }
        while (n > 1) {
            if (n % 3 != 0) {
                return false;
            }
            n = n / 3;
        }
        return true;
    }

    // Integer Limitation.
    public boolean isPowerOfThreeI(int n) {
        return n > 0 && 1162261467 % n == 0;
    }
}