import java.util.*;

class JumpGameVI {

    // DP + Deque. Time = O(N); Space = O(N);
    public int maxResult(int[] nums, int k) {
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];
        Deque<Integer> deque = new ArrayDeque<>();
        deque.offerLast(0);

        for (int i = 1; i < n; i++) {
            while (deque.peekFirst() != null && deque.peekFirst() < i - k) {
                deque.pollFirst();
            }
            dp[i] = dp[deque.peekFirst()] + nums[i];
            while (deque.peekLast() != null && dp[i] >= dp[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.offerLast(i);
        }
        return dp[n - 1];
    }

    // DP + Heap. Time = O(N*log(N)); Space = O(N);
    public int maxResultI(int[] nums, int k) {
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        maxHeap.offer(new int[] { nums[0], 0 });
        for (int i = 1; i < n; i++) {
            // pop the old index
            while (maxHeap.peek()[1] < i - k) {
                maxHeap.poll();
            }
            dp[i] = nums[i] + dp[maxHeap.peek()[1]];
            maxHeap.offer(new int[] { dp[i], i });
        }
        return dp[n - 1];
    }

    // Segment Tree.
    public int maxResultII(int[] nums, int k) {
        int n = nums.length;
        int[] tree = new int[n * 2];
        update(0, nums[0], tree, n);
        for (int i = 1; i < n; i++) {
            int max = query(Math.max(0, i - k), i, tree, n);
            update(i, max + nums[i], tree, n);
        }
        return tree[2 * n - 1];
    }

    private void update(int index, int value, int[] tree, int n) {
        index += n;
        tree[index] = value;
        for (index >>= 1; index > 0; index >>= 1) {
            tree[index] = Math.max(tree[index << 1], tree[(index << 1) + 1]);
        }
    }

    private int query(int left, int right, int[] tree, int n) {
        int res = Integer.MIN_VALUE;
        for (left += n, right += n; left < right; left >>= 1, right >>= 1) {
            if ((left & 1) == 1) {
                res = Math.max(res, tree[left++]);
            }
            if ((right & 1) == 1) {
                res = Math.max(res, tree[--right]);
            }
        }
        return res;
    }

    // DP + Deque. Time = O(N); Space = O(K);
    public int maxResultIII(int[] nums, int k) {
        int n = nums.length;
        int score = nums[0];
        Deque<int[]> deque = new LinkedList<>();
        deque.offerLast(new int[] { 0, score });
        for (int i = 1; i < n; i++) {
            // pop the old index
            while (deque.peekFirst() != null && deque.peekFirst()[0] < i - k) {
                deque.pollFirst();
            }
            score = deque.peek()[1] + nums[i];
            // pop the smaller value
            while (deque.peekLast() != null && score >= deque.peekLast()[1]) {
                deque.pollLast();
            }
            deque.offerLast(new int[] { i, score });
        }
        return score;
    }

    // DP.
    public int maxResultIV(int[] nums, int k) {
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];
        for (int i = 1; i < n; i++) {
            dp[i] = dp[i - 1] + nums[i];
            for (int j = i - 1; j >= Math.max(0, i - k); j--) {
                dp[i] = Math.max(dp[i], dp[j] + nums[i]);
                // every positive must visit, so if visit positive number then break
                if (nums[j] > 0) {
                    break;
                }
            }
        }
        return dp[n - 1];
    }
}