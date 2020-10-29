import java.util.*;

class MaximizeDistanceToClosestPerson {

    // Next Array. Time = O(N); Space = O(N);
    public int maxDistToClosestI(int[] seats) {
        int n = seats.length;
        int[] left = new int[n];
        int[] right = new int[n];
        Arrays.fill(left, n);
        Arrays.fill(right, n);

        for (int i = 0; i < n; ++i) {
            if (seats[i] == 1) {
                left[i] = 0;
            } else if (i > 0) {
                left[i] = left[i - 1] + 1;
            }
        }

        for (int i = n - 1; i >= 0; --i) {
            if (seats[i] == 1) {
                right[i] = 0;
            } else if (i < n - 1) {
                right[i] = right[i + 1] + 1;
            }
        }

        int res = 0;
        for (int i = 0; i < n; ++i) {
            if (seats[i] == 0) {
                res = Math.max(res, Math.min(left[i], right[i]));
            }
        }
        return res;
    }

    // Two Pointers. Time = O(N); Space = O(1);
    public int maxDistToClosestII(int[] seats) {
        int n = seats.length;
        int prev = -1;
        int future = 0;
        int res = 0;
        for (int i = 0; i < n; i++) {
            if (seats[i] == 1) {
                prev = i;
            } else {
                while (future < n && seats[future] == 0 || future < i) {
                    future++;
                }
                int leftDist = prev == -1 ? n : i - prev;
                int rightDist = future == n ? n : future - i;
                res = Math.max(res, Math.min(leftDist, rightDist));
            }
        }
        return res;
    }

    // Group By Zero
    public int maxDistToClosest(int[] seats) {
        int N = seats.length;
        int K = 0; // current longest group of empty seats
        int ans = 0;

        for (int i = 0; i < N; ++i) {
            if (seats[i] == 1) {
                K = 0;
            } else {
                K++;
                ans = Math.max(ans, (K + 1) / 2);
            }
        }

        for (int i = 0; i < N; ++i)
            if (seats[i] == 1) {
                ans = Math.max(ans, i);
                break;
            }

        for (int i = N - 1; i >= 0; --i)
            if (seats[i] == 1) {
                ans = Math.max(ans, N - 1 - i);
                break;
            }

        return ans;
    }

    
}