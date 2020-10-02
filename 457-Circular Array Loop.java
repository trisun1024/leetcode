import java.util.*;

class CircularArrayLoop {

    // Two Pointers.
    public boolean circularArrayLoop(int[] nums) {
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            if (nums[i] > 0) {
                if (checkCircle(nums, i, len)) {
                    return true;
                }
            } else if (nums[i] < 0) {
                if (checkCircle(nums, i, len)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkCircle(int[] nums, int start, int len) {
        boolean positive = false;
        if (nums[start] > 0) {
            positive = true;
        }
        int slow = start;
        int fast = start;
        while ((nums[slow] > 0) == positive && (nums[fast] > 0) == positive
                && (nums[getIndex(fast + nums[fast], len)] > 0) == positive) {
            slow = getIndex(slow + nums[slow], len);
            if (getIndex(slow + nums[slow], len) == slow) {
                break;
            }
            fast = getIndex(fast + nums[fast], len);
            fast = getIndex(fast + nums[fast], len);
            if (nums[slow] == nums[fast])
                return true;
        }
        return false;
    }

    private int getIndex(int input, int len) {
        if (input >= 0)
            return input % len;
        return len - (len - input) % len;
    }

    // DFS.
    public boolean circularArrayLoopI(int[] nums) {
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] > 0 && dfs(nums, i, true, new HashSet<Integer>()))
                return true;
            if (nums[i] < 0 && dfs(nums, i, false, new HashSet<Integer>()))
                return true;
        }
        return false;
    }

    private boolean dfs(int[] nums, int curr, boolean isForward, Set<Integer> visited) {
        if (visited.contains(curr))
            return true; // 有环
        if ((nums[curr] > 0) != isForward)
            return false; // 符号不对
        if (getIndex(curr + nums[curr], nums) == curr)
            return false; // 环中只有一个元素
        int next = getIndex(curr + nums[curr], nums);
        visited.add(curr);
        return dfs(nums, next, isForward, visited);
    }

    private int getIndex(int input, int[] nums) {
        int len = nums.length;
        if (input >= 0)
            return input % len;
        else
            return len - (len - input) % len;
    }
}