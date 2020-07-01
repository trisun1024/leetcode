import java.util.*;

class PerfectSquares {

    // DP. Time = O(N); Space = O(N);
    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        // pre-calculate the square numbers.
        int maxSquareIndex = (int) Math.sqrt(n) + 1;
        int squareNums[] = new int[maxSquareIndex];
        for (int i = 1; i < maxSquareIndex; ++i) {
            squareNums[i] = i * i;
        }

        for (int i = 1; i <= n; ++i) {
            for (int s = 1; s < maxSquareIndex; ++s) {
                if (i < squareNums[s])
                    break;
                dp[i] = Math.min(dp[i], dp[i - squareNums[s]] + 1);
            }
        }
        return dp[n];
    }

    // Greedy. Time = O(N^H) H is maximum number of recursion could happen; Space =
    // O(sqrt(N));
    public int numSquaresII(int n) {
        // build a hashset contains all i from 1 to sqrt(n) which have i^2 <= n
        Set<Integer> set = new HashSet<>();
        for (int i = 1; i * i <= n; i++) {
            set.add(i * i);
        }
        // start from 1 to n, test possible condition
        int count = 1;
        while (count <= n) {
            if (isDivided(n, count, set)) {
                return count;
            }
            count++;
        }
        return count;
    }

    private boolean isDivided(int n, int count, Set<Integer> set) {
        if (count == 1) {
            return set.contains(n);
        }
        for (Integer i : set) {
            if (isDivided(n - i, count - 1, set)) {
                return true;
            }
        }
        return false;
    }

    // Greedy + BFS. Time = O(N^H) H is maximum number of recursion could happen;
    // Space = O(sqrt(N)^H);
    public int numSquaresIII(int n) {
        List<Integer> squareNum = new ArrayList<>();
        for (int i = 1; i * i <= n; i++) {
            squareNum.add(i * i);
        }
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(n);
        int level = 0;
        while (!queue.isEmpty()) {
            ++level;
            Queue<Integer> nextQueue = new ArrayDeque<>();
            for (Integer rem : queue) {
                for (Integer square : squareNum) {
                    if (rem.equals(square)) {
                        return level;
                    } else if (rem < square) {
                        break;
                    } else {
                        nextQueue.offer(rem - square);
                    }
                }
            }
            queue = nextQueue;
        }
        return level;
    }
}