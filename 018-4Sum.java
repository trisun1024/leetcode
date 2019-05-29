class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new LinkedList<List<Integer>>();

        Arrays.sort(nums);
        int len = nums.length;
        for (int i = 0; i < len - 3; i++) {
            for (int j = i + 1; j < len - 2; j++) {
                int lo = j + 1;
                int hi = len - 1;
                while (lo < hi) {
                    int sum = nums[i] + nums[j] + nums[lo] + nums[hi];
                    if (sum > target) {
                        hi--;
                    } else if (sum < target) {
                        lo++;
                    } else if (sum == target) {
                        List<Integer> temp = new LinkedList<Integer>();
                        temp.add(nums[i]);
                        temp.add(nums[j]);
                        temp.add(nums[lo]);
                        temp.add(nums[hi]);
                        if (!res.contains(temp))
                            res.add(temp);
                        lo++;
                        hi--;
                    }
                }
            }
        }
        return res;
    }
}