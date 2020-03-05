class Solution {

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
}