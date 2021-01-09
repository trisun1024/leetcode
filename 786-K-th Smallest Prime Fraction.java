import java.util.*;

class KthSmallestPrimeFraction {

    // Binary Search. Time = O(N*log(W)); Space = O(1);
    public int[] kthSmallestPrimeFractionI(int[] A, int K) {
        double left = 0, right = 1;
        int[] res = new int[] { 0, 1 };

        while (right - left > 1e-9) {
            double mid = left + (right - left) / 2.0;
            int[] temp = under(mid, A);
            if (temp[0] < K) {
                left = mid;
            } else {
                res[0] = temp[1];
                res[1] = temp[2];
                right = mid;
            }
        }
        return res;
    }

    private int[] under(double x, int[] primes) {
        int number = 0;
        int denom = 1;
        int count = 0;
        int i = -1;
        for (int j = 1; j < primes.length; j++) {
            while (primes[i + 1] < primes[j] * x) {
                i++;
            }
            count += i + 1;
            if (i >= 0 && number * primes[j] < denom * primes[i]) {
                number = primes[i];
                denom = primes[j];
            }
        }
        return new int[] { count, number, denom };
    }

    // Heap. Time = O(K*log(N)); Space = O(N);
    public int[] kthSmallestPrimeFraction(int[] A, int K) {
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a, b) -> A[a[0]] * A[b[1]] - A[a[1]] * A[b[0]]);

        for (int i = 1; i < A.length; ++i)
            pq.add(new int[] { 0, i });

        while (--K > 0) {
            int[] frac = pq.poll();
            if (frac[0]++ < frac[1])
                pq.offer(frac);
        }

        int[] ans = pq.poll();
        return new int[] { A[ans[0]], A[ans[1]] };
    }
}