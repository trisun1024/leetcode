class Solution {

    // Time = O(N+K); Space = O(K);
    public void rotate(int[] nums, int k) {
        int step = k % nums.length;
        // create a tmp array to store index[len-k:len] which will move to front later
        int[] tmp = new int[step];
        int i = nums.length - 1;
        while (step > 0) {
            tmp[step - 1] = nums[i--];
            step--;
        }
        // move reset to the right position
        int j = nums.length - 1;
        while (i >= 0) {
            nums[j--] = nums[i--];
        }
        // move back tmp array numbers
        while (j >= 0) {
            nums[j] = tmp[j];
            j--;
        }
    }
}