
class ShuffleArray {

    public int[] shuffle(int[] nums, int n) {
        if (nums.length != n * 2) {
            return new int[0];
        }
        int[] res = new int[n * 2];
        for (int i = 0; i < n; i++) {
            res[i * 2] = nums[i];
            res[i * 2 + 1] = nums[i + n];
        }
        return res;
    }
}