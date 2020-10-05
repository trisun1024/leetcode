import java.util.*;

class CircularArrayLoop {

    // Two Pointers.
    public boolean circularArrayLoop(int[] nums) {
        if (nums.length <= 1) {
            return false;
        }

        for (int i = 0; i < nums.length; ++i) {
            nums[i] %= nums.length;
        }

        boolean[] visited = new boolean[nums.length];

        for (int i = 0; i < nums.length; ++i) {
            int slow = i;
            int fast = i;

            if (visited[slow]) {
                continue;
            }

            while (nums[slow] * nums[next(nums, fast)] > 0 && nums[slow] * nums[next(nums, next(nums, fast))] > 0) {
                slow = next(nums, slow);
                fast = next(nums, next(nums, fast));

                if (visited[slow]) {
                    break;
                }

                if (slow == fast) {
                    return true;
                }
                visited[slow] = true;
            }
        }
        return false;
    }

    private int next(int[] nums, int i) {
        int n = nums.length;
        return (i + nums[i] + n) % n;
    }

    // DFS.
    public boolean circularArrayLoopI(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++)
            if (!map.containsKey(i) && search(i, nums, map))
                return true;
        return false;
    }

    public boolean search(int i, int[] arr, HashMap<Integer, Integer> map) {
        int slow = i, fast = i;
        while (fast >= 0 && arr[fast] * arr[i] > 0 && next(fast, arr) >= 0 && arr[next(fast, arr)] * arr[i] > 0) {
            if (map.getOrDefault(fast, i) != i)
                return false;
            map.put(fast, i);
            slow = next(slow, arr);
            fast = next(fast, arr);
            fast = next(fast, arr);
            // System.out.println(slow+" "+fast);
            if (slow == -1 || fast == -1)
                return false;
            // if(ct==1 && fast==slow) return false;
            if (fast == slow)
                return true;
        }
        // System.out.println("false");
        return false;
    }

    public int next(int itr, int[] arr) {
        int ans = (itr + arr[itr] + 1000 * arr.length) % arr.length;
        return (ans == itr) ? -1 : ans;
    }
}