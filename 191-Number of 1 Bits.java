 class NumberOf1Bits {

    // Loop for 32 Bits.
    public int hammingWeight(int n) {
        int bits = 0;
        int mask = 1;
        for (int i = 0; i < 32; i++) {
            if ((n & mask) != 0) {
                bits++;
            }
            mask <<= 1;
        }
        return bits;
    }

    // Bit Manipulation Trick. n & (n-1) trick 
    public int hammingWeightI(int n) {
        int c = 0;
        while (n != 0) {
            c++;
            n &= (n - 1);
        }
        return c;
    }
}