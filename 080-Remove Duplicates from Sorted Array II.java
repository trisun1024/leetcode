class Solution {

    // Two pointers Time = O(N)
    public int removeDuplicates(int[] nums) {
        // base case 
        if (nums.length == 0) {
            return 0;
        }
        // init point i and j 
        int i = 1;
        int j = 1;
        // init current number and count
        int cur = nums[0];
        int count = 1;
        while (j < nums.length) {
            // if see the same, then ++count and if count > 2 then j++; else copy j index val to i index
            if (nums[j] == cur) {
                ++count;
                if (count > 2) {
                    j++;
                } else {
                    nums[i++] = nums[j++];
                }
            } else {
                // reset count to zero and cur change to current j index val
                count = 0;
                cur = nums[j];
            }
        }
        return i;
    }
}