import javax.xml.stream.events.StartDocument;

class Solution {

    // DP
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return nums[0];
        }
        int[] startFromFirst = new int[n + 1];
        int[] startFromSecond = new int[n + 1];
        startFromFirst[0] = 0;
        startFromFirst[1] = nums[0];
        startFromSecond[0] = 0;
        startFromSecond[1] = 0;
        for (int i = 2; i <= n; i++) {
            startFromFirst[i] = Math.max(startFromFirst[i - 1], startFromFirst[i - 2] + nums[i - 1]);
            startFromSecond[i] = Math.max(startFromSecond[i - 1], startFromSecond[i - 2] + nums[i - 1]);
        }
        return Math.max(startFromFirst[n - 1], startFromSecond[n]);
    }

    // 
    public int robII(int[] nums) {
        if (nums.length == 1)
            return nums[0];
        return Math.max(rob(nums, 0, nums.length - 2), rob(nums, 1, nums.length - 1));
    }

    private int rob(int[] num, int lo, int hi) {
        int include = 0, exclude = 0;
        for (int j = lo; j <= hi; j++) {
            int i = include, e = exclude;
            include = e + num[j];
            exclude = Math.max(e, i);
        }
        return Math.max(include, exclude);
    }
}