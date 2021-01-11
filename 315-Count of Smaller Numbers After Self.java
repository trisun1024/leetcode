import java.util.*;

class CountOfSmallerNumbersAfterSelf {

    // Brute Force. Time = O(N^2);
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int count = 0;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] > nums[j]) {
                    count++;
                }
            }
            res.add(count);
        }
        return res;
    }

    // Merge Sort. Time = O(N*log(N));
    public List<Integer> countSmallerI(int[] nums) {
        int n = nums.length;
        int[] indexArray = initialIndex(nums);
        int[] countArray = new int[n];
        int[] helper = new int[n];
        mergesort(nums, indexArray, countArray, helper, 0, n - 1);
        List<Integer> res = new ArrayList<>();
        for (int i : countArray) {
            res.add(i);
        }
        return res;
    }

    private int[] initialIndex(int[] nums) {
        int[] arr = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            arr[i] = i;
        }
        return arr;
    }

    private void mergesort(int[] nums, int[] indexArray, int[] countArray, int[] helper, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = left + (right - left) / 2;
        mergesort(nums, indexArray, countArray, helper, left, mid);
        mergesort(nums, indexArray, countArray, helper, mid + 1, right);
        merge(nums, indexArray, countArray, helper, left, mid, right);
    }

    private void merge(int[] nums, int[] indexArray, int[] countArray, int[] helper, int left, int mid, int right) {
        copyArray(indexArray, helper, left, right);
        int l = left;
        int r = mid + 1;
        int cur = left;
        while (l <= mid) {
            if (r > right || nums[helper[l]] <= nums[helper[r]]) {
                countArray[helper[l]] += (r - mid - 1);
                indexArray[cur++] = helper[l++];
            } else {
                indexArray[cur++] = helper[r++];
            }
        }
    }

    private void copyArray(int[] indexArray, int[] helper, int left, int right) {
        for (int i = left; i <= right; i++) {
            helper[i] = indexArray[i];
        }
    }

    // Segment Tree.
    public List<Integer> countSmallerII(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        int n = nums.length;
        // find min value in array
        int min = Integer.MAX_VALUE;
        for (int i : nums) {
            min = Math.min(i, min);
        }
        // find max value in array, and relation with min in rank
        int[] rank = new int[n];
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            rank[i] = nums[i] - min + 1;
            max = Math.max(rank[i], max);
        }
        max = max + 1;
        int[] tree = new int[max * 2];
        for (int i = n - 1; i >= 0; i--) {
            res.add(0, query(tree, max, 0, rank[i] - 1));
            update(tree, max, rank[i]);
        }
        return res;
    }

    private int query(int[] tree, int max, int left, int right) {
        int l = left + max;
        int r = right + max;
        int sum = 0;
        while (l <= r) {
            if (l % 2 == 1) {
                sum += tree[l];
                l++;
            }
            if (r % 2 == 0) {
                sum += tree[r];
                r--;
            }
            l /= 2;
            r /= 2;
        }
        return sum;
    }

    private void update(int[] tree, int max, int rank) {
        int i = rank + max;
        tree[i]++;
        while (i > 1) {
            i /= 2;
            tree[i] = tree[i * 2] + tree[i * 2 + 1];
        }
    }
}
