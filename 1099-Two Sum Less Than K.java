import java.util.*;

class TwoSumLessThanK {

    // Brute Forece. Time = O(N^2); Space = O(1);
    public int twoSumLessThanK(int[] A, int K) {
        int target = -1;
        for (int i = 0; i < A.length; i++) {
            for (int j = i + 1; j < A.length; j++) {
                if (A[i] + A[j] < K) {
                    target = Math.max(target, A[i] + A[j]);
                }
            }
        }
        return target;
    }

    // Two Pointers. Time = O(N*log(N));
    public int twoSumLessThanKI(int[] A, int K) {
        int S = -1;
        Arrays.sort(A);
        int lo = 0, hi = A.length - 1;
        while (lo < hi) {
            if (A[lo] + A[hi] < K) {
                S = Math.max(S, A[lo] + A[hi]);
                ++lo;
            } else
                --hi;
        }
        return S;
    }

    // Binary Search. Time = O(N*log(N));
    public int twoSumLessThanKII(int[] A, int K) {
        int S = -1;
        Arrays.sort(A);
        for (int i = 0; i < A.length; ++i) {
            var idx = Arrays.binarySearch(A, i + 1, A.length, K - A[i] - 1);
            int j = (idx >= 0 ? idx : ~idx);
            if (j == A.length || A[j] > K - A[i] - 1)
                --j;
            if (j > i) {
                S = Math.max(S, A[i] + A[j]);
            }
        }
        return S;
    }

    // Counting Sort.
    public int twoSumLessThanKIII(int[] A, int K) {
        int S = -1;
        int[] count = new int[1001];
        for (int i : A)
            ++count[i];
        int lo = 1, hi = 1000;
        while (lo <= hi) {
            if (lo + hi >= K || count[hi] == 0)
                --hi;
            else {
                if (count[lo] > (lo < hi ? 0 : 1))
                    S = Math.max(S, lo + hi);
                ++lo;
            }
        }
        return S;
    }

}