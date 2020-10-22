
class ArrayNesting {

    // Brute Force. Time = O(N^2); Space = O(1);
    public int arrayNestingI(int[] nums) {
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            max = Math.max(max, search(nums, i));
        }
        return max;
    }

    private int search(int[] nums, int i) {
        int start = nums[i], count = 0;
        do {
            start = nums[start];
            count++;
        } while (start != nums[i]);
        return count;
    }

    // Arrays. Time = O(N); Space = O(N);
    public int arrayNestingII(int[] nums) {
        boolean[] visited = new boolean[nums.length];
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            if (!visited[i]) {
                int start = nums[i];
                int count = 0;
                do {
                    start = nums[start];
                    count++;
                    visited[start] = true;
                } while (start != nums[i]);
                max = Math.max(max, count);
            }
        }
        return max;
    }

    // Inplace.
    public int arrayNesting(int[] nums) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != Integer.MAX_VALUE) {
                int start = nums[i], count = 0;
                while (nums[start] != Integer.MAX_VALUE) {
                    int temp = start;
                    start = nums[start];
                    count++;
                    nums[temp] = Integer.MAX_VALUE;
                }
                res = Math.max(res, count);
            }
        }
        return res;
    }
}
