class RemoveDuplicates {

    // return the length of new array
    public int removeDuplicates(int[] nums) {
        int f = 1;
        int s = 1;
        while (f < nums.length) {
            if (nums[f] != nums[f - 1]) {
                nums[s++] = nums[f];
            }
            f++;
        }
        return s;
    }

    public int removeDuplicatesI(int[] nums) {
        int s = 0, f = 0;
        while (f < nums.length) {
            while (f + 1 < nums.length && nums[f] == nums[f + 1]) {
                f++;
            }
            nums[s++] = nums[f++];
        }
        return s;
    }
}