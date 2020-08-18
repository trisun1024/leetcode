import java.util.*;

class NumbersWithSameConsecutiveDifferences {

    // DFS. Time = O(N*2^N); Space = O(2^N);
    public int[] numsSameConsecDiff(int N, int K) {
        if (N == 1) {
            return new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        }
        List<Integer> res = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            dfs(N - 1, i, K, res);
        }
        return res.stream().mapToInt(i -> i).toArray();
    }

    private void dfs(int N, int num, int K, List<Integer> res) {
        if (N == 0) {
            res.add(num);
            return;
        }
        List<Integer> nextDigits = new ArrayList<>();
        int tailDigit = num % 10;
        nextDigits.add(tailDigit + K);
        if (K != 0) {
            nextDigits.add(tailDigit - K);
        }
        for (Integer next : nextDigits) {
            if (next >= 0 && next < 10) {
                Integer newNum = num * 10 + next;
                dfs(N - 1, newNum, K, res);
            }
        }
    }

    // BFS
    public int[] numsSameConsecDiffI(int N, int K) {
        if (N == 1) {
            return new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        }
        List<Integer> queue = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
        for (int level = 1; level < N; level++) {
            List<Integer> nextQueue = new ArrayList<>();
            for (Integer num : queue) {
                Integer tailDigit = num % 10;
                List<Integer> nextDigits = new ArrayList<>();
                nextDigits.add(tailDigit + K);
                if (K != 0) {
                    nextDigits.add(tailDigit - K);
                }
                for (Integer next : nextDigits) {
                    if (next >= 0 && next < 10) {
                        Integer nextNum = num * 10 + next;
                        nextQueue.add(nextNum);
                    }
                }
            }
            queue = nextQueue;
        }
        return queue.stream().mapToInt(i -> i).toArray();
    }
}