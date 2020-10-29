import java.util.*;

class SummaryRanges {
    
    // Time = O(N);
    public List<String> summaryRanges(int[] nums) {
        List<String> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        int start = nums[0];
        for (int i = 1; i <= nums.length; i++) {
            if (i == nums.length || nums[i] != nums[i - 1] + 1) {
                res.add(extract(nums, start, i - 1));
                if (i < nums.length) {
                    start = nums[i];
                }
            }
        }
        return res;
    }

    private String extract(int[] nums, int startValue, int endIndex) {
        StringBuilder res = new StringBuilder();
        res.append(startValue);
        if (nums[endIndex] != startValue) {
            res.append("->");
            res.append(nums[endIndex]);
        }
        return res.toString();
    }
}