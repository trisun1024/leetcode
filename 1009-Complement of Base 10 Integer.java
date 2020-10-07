
class ComplementOfBase10Integer {

    // Bit by bit flip
    public int bitwiseComplement(int N) {
        if (N == 0) {
            return 1;
        }
        int todo = N;
        int bit = 1;
        while (todo != 0) {
            N = N ^ bit;
            bit <<= 1;
            todo >>= 1;
        }
        return N;
    }

    // Compute length
    public int bitwiseComplementI(int N) {
        // l is a length of N in binary representation
        int l = (int) (Math.log(N) / Math.log(2)) + 1;
        // bitmask has the same length as num and contains only ones 1...1
        int bitmask = (1 << l) - 1;
        // flip all bits
        return bitmask ^ N;
    }
}