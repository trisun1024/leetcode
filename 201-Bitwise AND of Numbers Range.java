class BitwiseNumbersRange {

    public int rangeBitwiseAnd(int m, int n) {
        int shift = 0;
        // find the common 1-bits
        while (m < n) {
            m >>= 1;
            n >>= 1;
            ++shift;
        }
        return m << shift;
    }

    public int rangeBitwiseAndII(int m, int n) {
        while (m < n) {
            n = n & (n - 1);
        }
        return m & n;
    }
}