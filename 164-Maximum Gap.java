import java.util.*;

class MaximumGap {

    // Bucket Sort: Time - O(n), Space: O(n)
    // gap = ceiling((max - min) / (n - 1))
    // number of bucket = [(max - min) / gap] + 1
    public int maximumGap(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return 0;
        }
        int min = nums[0], max = nums[0];
        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }
        if (min == max) {
            return 0;
        }
        int n = nums.length;
        int gap = (int) Math.ceil((double) (max - min) / (n - 1));
        int[] minBuckets = new int[n], maxBuckets = new int[n];
        Arrays.fill(minBuckets, Integer.MAX_VALUE);
        Arrays.fill(maxBuckets, Integer.MIN_VALUE);
        for (int num : nums) {
            int index = (num - min) / gap;
            minBuckets[index] = Math.min(minBuckets[index], num);
            maxBuckets[index] = Math.max(maxBuckets[index], num);
        }
        int maxGap = 0, prev = min;
        for (int i = 0; i < n; i++) {
            if (minBuckets[i] == Integer.MAX_VALUE || maxBuckets[i] == Integer.MIN_VALUE) {
                continue;
            }
            maxGap = Math.max(maxGap, minBuckets[i] - prev);
            prev = maxBuckets[i];
        }
        return maxGap;
    }

    //
    public int maximumGapII(int[] nums) {

        int len = nums.length;
        if (len < 2) {
            return 0;
        }

        sort(0, len - 1, nums);

        int max = Integer.MIN_VALUE;
        int dif = 0;
        for (int i = 1; i <= len - 1; i++) {
            dif = nums[i] - nums[i - 1];
            if (dif > max) {
                max = dif;
            }
        }
        return max;
    }

    public void sort(int s, int e, int[] nums) {
        if (s >= e) {
            return;
        }
        int pivot = nums[s];

        int is = s;
        int ie = e;
        while (is < ie) {
            // shift right side;
            while (is < ie && nums[ie] >= pivot) {
                ie--;
            }
            nums[is] = nums[ie];

            // shift left side
            while (is < ie && nums[is] <= pivot) {
                is++;
            }
            nums[ie] = nums[is];
        }

        nums[is] = pivot;

        sort(s, is - 1, nums);
        sort(is + 1, e, nums);
    }
}