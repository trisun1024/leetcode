import java.util.PriorityQueue;

class SuperUglyNumber {

    // PriorityQueue
    public int nthSuperUglyNumberI(int n, int[] primes) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int cur = 1;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < primes.length; j++) {
                if (primes[j] * (long) cur <= Integer.MAX_VALUE) {
                    pq.add(primes[j] * cur);
                }
            }
            while (pq.peek() == cur) {
                pq.poll();
            }
            cur = pq.poll();
        }
        return cur;
    }

    // array
    public int nthSuperUglyNumber(int n, int[] primes) {
        int[] ugly = new int[n];
        ugly[0] = 1;
        int length = primes.length;
        int[] factors = new int[length];
        int[] index = new int[length];

        for (int i = 0; i < length; i++) {
            factors[i] = primes[i];
        }
        int curMin = 1;
        for (int i = 1; i < n; i++) {
            curMin = factors[0];
            for (int j = 0; j < length; j++)
                curMin = Math.min(curMin, factors[j]);
            ugly[i] = curMin;
            for (int j = 0; j < length; j++) {
                if (curMin == factors[j]) {
                    factors[j] = primes[j] * ugly[++index[j]];
                }
            }

        }
        return ugly[n - 1];
    }
}