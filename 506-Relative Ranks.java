import java.util.*;

class RelativeRanks {

    public String[] findRelativeRanks(int[] nums) {
        int[] temp = nums.clone();
        Arrays.sort(temp);
        Map<Integer, String> map = new HashMap<>();
        for (int i = nums.length - 1; i >= 0; i--) {
            if (i == temp.length - 1) {
                map.put(temp[i], "Gold Medal");
            } else if (i == temp.length - 2) {
                map.put(temp[i], "Silver Medal");
            } else if (i == temp.length - 3) {
                map.put(temp[i], "Bronze Medal");
            } else {
                map.put(temp[i], String.valueOf(temp.length - i));
            }
        }
        String[] res = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            res[i] = map.get(nums[i]);
        }
        return res;
    }
}