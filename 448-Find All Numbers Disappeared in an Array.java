import java.util.*;

class AllNumbersDisappearedInArray {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int index = nums[i] - 1;
            while (index != nums[i]) {
                swap(nums, index, i);
                index = nums[i] - 1;
                if (nums[index] == nums[i]) {
                    break;
                }
            }
        }
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (i != nums[i] - 1) {
                res.add(i + 1);
            }
        }
        return res;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}