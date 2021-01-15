
class GetMaximumInGeneratedArray {

    // Time = O(N); Space = O(N);
    public int getMaximumGenerated(int n) {
        if (n == 0) {
            return 0;
        }
        int[] nums = new int[n + 1];
        int max = 1;
        nums[1] = 1;
        for (int i = 1; i <= n / 2; i++) {
            nums[i * 2] = nums[i];
            if (i * 2 + 1 <= n) {
                nums[i * 2 + 1] = nums[i] + nums[i + 1];
                max = Math.max(max, nums[i * 2 + 1]);
            }
        }
        return max;
    }

    // Division. Time = O(N); Space = O(N);
    public int getMaximumGeneratedI(int n) {
        if (n == 0) {
            return 0;
        }
        int[] nums = new int[n + 1];
        int maximumValue = 1;
        nums[1] = 1;
        for (int i = 2; i <= n; i++) {
            if (i % 2 == 0) {
                nums[i] = nums[i / 2];
            } else {
                nums[i] = nums[i / 2] + nums[i / 2 + 1];
            }
            maximumValue = Math.max(maximumValue, nums[i]);
        }
        return maximumValue;
    }
}