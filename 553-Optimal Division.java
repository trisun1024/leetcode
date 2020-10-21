
class OptimalDivision {

    // Math.
    public String optimalDivision(int[] nums) {
        StringBuilder result = new StringBuilder();
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            if (i > 0) {
                result.append("/");
            }
            if (i == 1 && n > 2) {
                result.append("(");
            }
            result.append(nums[i]);
            if (i == n - 1 && n > 2) {
                result.append(")");
            }
        }
        return result.toString();
    }

    // DFS + Memorization.
    public String optimalDivisionI(int[] nums) {
        T[][] memo = new T[nums.length][nums.length];
        T t = helper(nums, 0, nums.length - 1, "", memo);
        return t.maxStr;
    }

    private T helper(int[] nums, int start, int end, String res, T[][] memo) {
        if (memo[start][end] != null)
            return memo[start][end];
        T t = new T();
        if (start == end) {
            t.maxVal = nums[start];
            t.minVal = nums[start];
            t.minStr = "" + nums[start];
            t.maxStr = "" + nums[start];
            memo[start][end] = t;
            return t;
        }
        t.minVal = Float.MAX_VALUE;
        t.maxVal = Float.MIN_VALUE;
        t.minStr = t.maxStr = "";
        for (int i = start; i < end; i++) {
            T left = helper(nums, start, i, "", memo);
            T right = helper(nums, i + 1, end, "", memo);
            if (t.minVal > left.minVal / right.maxVal) {
                t.minVal = left.minVal / right.maxVal;
                t.minStr = left.minStr + "/" + (i + 1 != end ? "(" : "") + right.maxStr + (i + 1 != end ? ")" : "");
            }
            if (t.maxVal < left.maxVal / right.minVal) {
                t.maxVal = left.maxVal / right.minVal;
                t.maxStr = left.maxStr + "/" + (i + 1 != end ? "(" : "") + right.minStr + (i + 1 != end ? ")" : "");
            }
        }
        memo[start][end] = t;
        return t;
    }

    static class T {
        float maxVal;
        float minVal;
        String minStr;
        String maxStr;
    }
}