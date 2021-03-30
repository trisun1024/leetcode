import java.util.*;

class SortAnArray {

    // Merge Sort + Divide and Conqure.
    public int[] sortArray(int[] nums) {
        if (nums.length < 2) {
            return nums;
        }
        int[] helper = new int[nums.length];
        mergesort(nums, helper, 0, nums.length - 1);
        return nums;
    }

    private void mergesort(int[] nums, int[] helper, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = left + (right - left) / 2;
        mergesort(nums, helper, left, mid);
        mergesort(nums, helper, mid + 1, right);
        merge(nums, helper, left, mid, right);
    }

    private void merge(int[] nums, int[] helper, int left, int mid, int right) {
        for (int i = left; i <= right; i++) {
            helper[i] = nums[i];
        }
        int l = left;
        int r = mid + 1;
        while (l <= mid && r <= right) {
            if (helper[l] <= helper[r]) {
                nums[left++] = helper[l++];
            } else {
                nums[left++] = helper[r++];
            }
        }
        while (l <= mid) {
            nums[left++] = helper[l++];
        }
    }

    // Quick Sort.
    public int[] sortArrayI(int[] nums) {
        if (nums.length < 2) {
            return nums;
        }
        quicksort(nums, 0, nums.length - 1);
        return nums;
    }

    private void quicksort(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }
        int pivotPos = partition(nums, left, right);
        quicksort(nums, left, pivotPos - 1);
        quicksort(nums, pivotPos + 1, right);
    }

    private int partition(int[] nums, int left, int right) {
        int pivotIndex = left + (int) (Math.random() * (right - left + 1));
        int pivot = nums[pivotIndex];
        swap(nums, pivotIndex, right);
        int l = left;
        int r = right - 1;
        while (l <= r) {
            if (nums[l] < pivot) {
                l++;
            } else if (nums[r] > pivot) {
                r--;
            } else {
                swap(nums, l++, r--);
            }
        }
        swap(nums, l, right);
        return l;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}