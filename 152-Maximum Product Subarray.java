class Solution {
    public int maxProduct(int[] nums) {
        int max = 1;
        int min = 1;
        int res = Integer.MIN_VALUE;
        for (int i : nums) {
            int temp1 = max * i;
            int temp2 = min * i;
            max = Math.max(i, Math.max(temp1, temp2));
            min = Math.min(i, Math.min(temp1, temp2));
            res = Math.max(res, max);
        }
        return res;
    }
}