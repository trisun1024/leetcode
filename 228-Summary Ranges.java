import java.util.*;

class Solution {
    public List<String> summaryRanges(int[] nums) {
        List<String> res = new ArrayList<>();
        if (nums.length == 0) {
            return res;
        }
        int start = nums[0];
        int next = start + 1;
        for (int i = 1; i <= nums.length; i++) {
            if (i == nums.length) {
                if (start == next - 1) {
                    res.add(String.valueOf(start));
                } else {
                    StringBuilder sb = new StringBuilder();
                    sb.append(start);
                    sb.append("->");
                    sb.append(next - 1);
                    res.add(sb.toString());
                }
                break;
            }
            int cur = nums[i];
            if (cur == next) {
                next = cur + 1;
            } else {
                if (start == next - 1) {
                    res.add(String.valueOf(start));
                } else {
                    StringBuilder sb = new StringBuilder();
                    sb.append(start);
                    sb.append("->");
                    sb.append(next - 1);
                    res.add(sb.toString());
                }
                start = cur;
                next = start + 1;
            }
        }
        return res;
    }
}