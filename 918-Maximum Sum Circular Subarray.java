import java.util.*;

class Solution {

    // Next Array Time = O(N) Space = O(N)
    public int maxSubarraySumCircular(int[] A) {
        int N = A.length;
        int ans = A[0];
        int cur = A[0];
        for (int i = 1; i < N; i++) {
            cur = A[i] + Math.max(cur, 0);
            ans = Math.max(ans, cur);
        }
        int[] rightSums = new int[N];
        rightSums[N - 1] = A[N - 1];
        for (int i = N - 2; i >= 0; i--) {
            rightSums[i] = rightSums[i + 1] + A[i];
        }
        int[] maxRight = new int[N];
        maxRight[N - 1] = A[N - 1];
        for (int i = N - 2; i >= 0; i--) {
            maxRight[i] = Math.max(maxRight[i + 1], rightSums[i]);
        }
        int leftSum = 0;
        for (int i = 0; i < N - 2; i++) {
            leftSum += A[i];
            ans = Math.max(ans, leftSum + maxRight[i + 2]);
        }
        return ans;
    }

    // Prefix Sum solution Time = O(N) Space = O(N)
    public int maxSubarraySumCircularII(int[] A) {
        int N = A.length;

        // Compute P[j] = B[0] + B[1] + ... + B[j-1]
        // for fixed array B = A+A
        int[] P = new int[2 * N + 1];
        for (int i = 0; i < 2 * N; ++i)
            P[i + 1] = P[i] + A[i % N];

        // Want largest P[j] - P[i] with 1 <= j-i <= N
        // For each j, want smallest P[i] with i >= j-N
        int ans = A[0];
        // deque: i's, increasing by P[i]
        Deque<Integer> deque = new ArrayDeque<>();
        deque.offer(0);

        for (int j = 1; j <= 2 * N; ++j) {
            // If the smallest i is too small, remove it.
            if (deque.peekFirst() < j - N)
                deque.pollFirst();

            // The optimal i is deque[0], for cand. answer P[j] - P[i].
            ans = Math.max(ans, P[j] - P[deque.peekFirst()]);

            // Remove any i1's with P[i2] <= P[i1].
            while (!deque.isEmpty() && P[j] <= P[deque.peekLast()])
                deque.pollLast();

            deque.offerLast(j);
        }

        return ans;
    }

    // Kadane's Algorithm (Sign Variant) Time = O(N) Space = O(1)
    public int maxSubarraySumCircularIII(int[] A) {
        int nonCircularSum = kadaneMaxSum(A);
        int totalSum = 0;
        for (int i = 0; i < A.length; i++) {
            totalSum += A[i];
            A[i] = -A[i];
        }

        int circularSum = totalSum + kadaneMaxSum(A);
        if (circularSum <= 0)
            return nonCircularSum;
        return Math.max(circularSum, nonCircularSum);
    }

    int kadaneMaxSum(int[] A) {
        int currentSum = A[0];
        int maxSum = A[0];
        for (int i = 1; i < A.length; i++) {
            if (currentSum < 0)
                currentSum = 0;
            currentSum = A[i] + currentSum;
            maxSum = Math.max(maxSum, currentSum);
        }
        return maxSum;
    }

}