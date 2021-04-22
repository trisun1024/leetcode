
class TwentyFour {

    // Backtracking.
    public boolean judgePoint24(int[] nums) {
        int n = nums.length;
        double[] records = new double[n];
        for (int i = 0; i < n; i++) {
            records[i] = nums[i];
        }
        return dfs(records, n);
    }

    private boolean dfs(double[] nums, int n) {
        if (n == 1) {
            boolean result = Math.abs(nums[0] - 24.0) < 1e-6;
            return result;
        }
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                double a = nums[i];
                double b = nums[j];
                nums[j] = nums[n - 1];
                // a + b
                nums[i] = a + b;
                if (dfs(nums, n - 1)) {
                    return true;
                }
                // a - b
                nums[i] = a - b;
                if (dfs(nums, n - 1)) {
                    return true;
                }
                // b - a
                nums[i] = b - a;
                if (dfs(nums, n - 1)) {
                    return true;
                }
                // a * b
                nums[i] = a * b;
                if (dfs(nums, n - 1)) {
                    return true;
                }
                // a / b
                if (b != 0) {
                    nums[i] = a / b;
                    if (dfs(nums, n - 1)) {
                        return true;
                    }
                }
                // b / a
                if (a != 0) {
                    nums[i] = b / a;
                    if (dfs(nums, n - 1)) {
                        return true;
                    }
                }
                nums[i] = a;
                nums[j] = b;
            }
        }
        return false;
    }
}