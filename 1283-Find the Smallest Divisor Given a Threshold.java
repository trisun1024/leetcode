
class FindSmallestDivisorGivenAThreshold {

    // Brute Force.
    public int smallestDivisorI(int[] nums, int threshold) {
        int divisor = 1;
        while (computeSum(nums, divisor) > threshold) {
            divisor++;
        }
        return divisor;
    }

    private long computeSum(int[] nums, int d) {
        long sum = 0;
        for (int n : nums) {
            sum += n / d + (n % d == 0 ? 0 : 1);
        }
        return sum;
    }

    // Brute Force + Binary Search. Time = O(log(N));
    public int smallestDivisorII(int[] nums, int threshold) {
        int left = 1;
        int right = 2;
        while (computeSum(nums, right) > threshold) {
            left = right;
            right <<= 1;
        }
        // binary serach
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            long num = computeSum(nums, mid);
            if (num > threshold) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    // Math + Binary Search.
    public int smallestDivisorIII(int[] nums, int threshold) {
        int left = 1;
        int right = nums[nums.length - 1];
        while (left <= right) {
            int mid = left + (right - left) / 2;
            long num = computeSum(nums, mid);
            if (num > threshold) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    public int smallestDivisor(int[] nums, int threshold) {
        // find maximum number.
        int max = nums[0];
        for (int num : nums) {
            max = Math.max(num, max);
        }
        // binary search
        int left = 1;
        int right = max;
        while (left < right) {
            int mid = left + (right - left) / 2;
            int res = 0;
            for (int num : nums) {
                res += (num + mid - 1) / mid;
            }
            if (res <= threshold) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}