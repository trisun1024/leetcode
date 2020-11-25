
class SmallestIntegerDivisibleByK {

    public int smallestRepunitDivByK(int K) {
        int rem = 0;
        for (int len = 1; len <= K; len++) {
            rem = (rem * 10 + 1) % K;
            if (rem == 0) {
                return len;
            }
        }
        return -1;
    }
}