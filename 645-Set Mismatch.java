import java.util.*;

class SetMismatch {

    // Sort Array. Time = O(N*log(N));
    public int[] findErrorNumsI(int[] nums) {
        Arrays.sort(nums);
        int dup = -1, missing = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                dup = nums[i];
            } else if (nums[i] > nums[i - 1] + 1) {
                missing = nums[i - 1] + 1;
            }
        }
        return new int[] { dup, nums[nums.length - 1] != nums.length ? nums.length : missing };
    }

    // Extra Array. Time = O(N); Space = O(N);
    public int[] findErrorNumsII(int[] nums) {
        int[] freq = new int[nums.length + 1];
        for (int i : nums) {
            freq[i]++;
        }
        int dup = -1, missing = 1;
        for (int i = 1; i < freq.length; i++) {
            if (freq[i] == 0) {
                missing = i;
            } else if (freq[i] == 2) {
                dup = i;
            }
        }
        return new int[] { dup, missing };
    }

    // Loop Twice. Time = O(N); Space = O(1);
    public int[] findErrorNumsIII(int[] nums) {
        int dup = -1, missing = 1;
        for (int n : nums) {
            if (nums[Math.abs(n) - 1] < 0) {
                dup = Math.abs(n);
            } else {
                nums[Math.abs(n) - 1] *= -1;
            }
        }
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > 0) {
                missing = i + 1;
            }
        }
        return new int[] { dup, missing };
    }

    // XOR.
    public int[] findErrorNums(int[] nums) {
        int xor = 0, xor0 = 0, xor1 = 0;
        for (int n : nums) {
            xor ^= n;
        }
        for (int i = 1; i <= nums.length; i++) {
            xor ^= i;
        }
        int rightmostbit = xor & ~(xor - 1);
        for (int n : nums) {
            if ((n & rightmostbit) != 0) {
                xor1 ^= n;
            } else {
                xor0 ^= n;
            }
        }
        for (int i = 1; i <= nums.length; i++) {
            if ((i & rightmostbit) != 0) {
                xor1 ^= i;
            } else {
                xor0 ^= i;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == xor0) {
                return new int[] { xor0, xor1 };
            }
        }
        return new int[] { xor1, xor0 };
    }
}