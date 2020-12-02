import java.util.*;

class JumpGameIII {

    // BFS. Time = O(N); Space = O(N);
    private final int[] DIRS = { 1, -1 };

    public boolean canReach(int[] arr, int start) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(start);
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            if (arr[cur] == 0) {
                return true;
            }
            if (arr[cur] < 0) {
                continue;
            }
            for (int dir : DIRS) {
                int next = cur + dir * arr[cur];
                if (next >= 0 && next < arr.length) {
                    queue.offer(next);
                }
            }
            arr[cur] = -arr[cur];
        }
        return false;
    }

    // DFS. Time = O(N); Space = O(N);
    public boolean canReachI(int[] arr, int start) {
        // base case
        if (start < 0 || start > arr.length || arr[start] < 0) {
            return false;
        }
        if (arr[start] == 0) {
            return true;
        }
        arr[start] = -arr[start];
        return canReach(arr, start + arr[start]) || canReach(arr, start - arr[start]);
    }
}