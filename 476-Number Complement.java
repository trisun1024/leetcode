class NumberComplement {

    public int findComplement(int num) {
        // n is a length of num in binary representation
        int n = (int) (Math.log(num) / Math.log(2)) + 1;
        // bitmask has the same length as num and contains only ones 1...1
        int bitmask = (1 << n) - 1;
        // flip all bits
        return bitmask ^ num;
    }


    public int findComplementII(int num) {
        int todo = num, bit = 1;
        while (todo != 0) {
            // flip current bit
            num = num ^ bit;
            // prepare for the next run
            bit = bit << 1;
            todo = todo >> 1;
        }
        return num;
    }
}