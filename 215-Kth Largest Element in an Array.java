import java.util.*;

class Solution {

    // Heap. Time = O(N*log(K)); Space = O(K);
    public int findKthLargest(int[] nums, int k) {
        if (nums.length == 0 || k == 0 || nums.length < k) {
            return 0;
        }
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(k, new Comparator<Integer>() {
            @Override
            public int compare(Integer i1, Integer i2) {
                if (i1.equals(i2)) {
                    return 0;
                }
                return i1 < i2 ? -1 : 1;
            }
        });
        for (int n : nums) {
            minHeap.offer(n);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
        return minHeap.peek();
    }

    // Quick Selection. Time = O(N); Space = O(1);
    public int findKthLargestI(int[] nums, int k) {
        partition(nums, 0, nums.length - 1, k);
        return nums[k - 1];
    }

    private void partition(int[] nums, int start, int end, int k) {
        if (start >= end)
            return;
        int left = start;
        int right = end;
        int pivot = median(nums, start, end);
        while (left <= right) {
            while (nums[left] > pivot) {
                left++;
            }
            while (nums[right] < pivot) {
                right--;
            }
            if (left <= right) {
                swap(nums, left++, right--);
            }
        }

        if (k <= left) {
            partition(nums, start, left - 1, k);
        } else {
            partition(nums, left, end, k);
        }
    }

    private void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

    private int median(int[] nums, int start, int end) {
        int center = (start + end) / 2;
        if (nums[start] < nums[center]) {
            swap(nums, start, center);
        } else if (nums[start] < nums[end]) {
            swap(nums, start, end);
        } else if (nums[center] < nums[end]) {
            swap(nums, center, end);
        }
        return nums[center];
    }
}