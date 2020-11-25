import java.util.*;

class PrimeNumberOfSetBitsInBinaryRepresentation {

    Set<Integer> primes = new HashSet<>(Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29));

    public int countPrimeSetBits(int L, int R) {
        int count = 0;
        for (int i = L; i <= R; i++) {
            int bits = 0;
            for (int n = i; n > 0; n >>= 1) {
                bits += n & 1;
            }
            count += primes.contains(bits) ? 1 : 0;
        }
        return count;
    }
}