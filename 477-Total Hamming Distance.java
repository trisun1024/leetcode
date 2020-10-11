
class TotalHammingDistance {

    // Arrays. Time = O(N); Space = O(N);
    public int totalHammingDistance(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int[] counts = new int[32];
        for (int num : nums) {
            for (int i = 0; i < 31; i++) {
                counts[i] += (num >> i) & 1;
            }
        }
        // calculate the sum
        int sum = 0;
        for (int i = 0; i < 31; i++) {
            sum += counts[i] * (nums.length - counts[i]);
        }
        return sum;
    }
}