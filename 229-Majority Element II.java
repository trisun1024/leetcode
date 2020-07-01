import java.util.*;

class MajorityElementII {

    // HashMap. Time = O(N); Space = O(N);
    public List<Integer> majorityElement(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int n : nums) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }
        int size = nums.length / 3;
        List<Integer> res = new ArrayList<>();
        for (Integer i : map.keySet()) {
            if (map.get(i) > size) {
                res.add(i);
            }
        }
        return res;
    }

    public List<Integer> majorityElementI(int[] nums) {
        List<Integer> result = new ArrayList<Integer>();

        if (nums == null || nums.length == 0)
            return result;

        int num1 = 0;
        int num2 = 0;
        int count1 = 0;
        int count2 = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == num1) {
                count1++;
            } else if (nums[i] == num2) {
                count2++;
            } else if (count1 == 0) {
                num1 = nums[i];
                count1++;
            } else if (count2 == 0) {
                num2 = nums[i];
                count2++;
            } else {
                count1--;
                count2--;
            }
        }

        count1 = 0;
        count2 = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == num1) {
                count1++;
            } else if (nums[i] == num2) {
                count2++;
            }
        }

        if (count1 * 3 > nums.length)
            result.add(num1);
        if (count2 * 3 > nums.length)
            result.add(num2);

        return result;
    }
}