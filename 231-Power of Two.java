class PowerOfTwo {

    // Time = O(log(N))
    public boolean isPowerOfTwo(int n) {
        if(n <= 0) {
            return false;
        }
        while(n % 2 ==0 ) {
            n /= 2;
        }
        return n==1;
    }

    // Bitwise Operator Time = O(1)
    public boolean isPowerOfTwoII(int n ) {
        if(n <= 0) {
            return false;
        }
        long x  = (long) n;
        return (x & (-x)) == x;
    }

    public boolean isPowerOfTwoIII(int n ) {
        if(n <= 0) {
            return false;
        }
        long x = (long) n;
        return (x & (x-1)) == 0;
    }
}