class MajorityElement {
    public int majorityElement(int[] nums) {
        if (nums.length == 0) {
            return -1;
        }
        int candidate = nums[0];
        int count = 1;
        for (int i = 1; i < nums.length; i++) {
            if (count == 0) {
                count++;
                candidate = nums[i];
            } else if (candidate == nums[i]) {
                count++;
            } else {
                count--;
            }
        }
        return candidate;
    }
}