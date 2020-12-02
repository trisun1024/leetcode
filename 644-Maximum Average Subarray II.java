
class MaximumAverageSubarrayII {

    // Brute Force. Time = O(N^2);
    public double findMaxAverageI(int[] nums, int k) {
        double res = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length - k + 1; i++) {
            long sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (j - i + 1 >= k) {
                    res = Math.max(res, sum * 1.0 / (j - i + 1));
                }
            }
        }
        return res;
    }

    // Prefix Sum. Time = O(N^2); Space = O(N);
    public double findMaxAverageII(int[] nums, int k) {
        if (nums.length < k) {
            return 0;
        }
        int[] prefix = new int[nums.length + 1];
        for (int i = 1; i < prefix.length; i++) {
            prefix[i] = prefix[i - 1] + nums[i - 1];
        }
        if (nums.length == k) {
            return (prefix[prefix.length - 1] - prefix[0]) / (double) nums.length;
        }
        double res = Double.MIN_VALUE;
        for (int i = 0; i < nums.length - k + 1; i++) {
            for (int j = i + k; j <= nums.length; j++) {
                double temp = (prefix[j] - prefix[i]) / (double) (j - i);
                res = Math.max(res, temp);
            }
        }
        return res;
    }

    // Binary Search + Prefix Sum. Time = O(N*log(N)); Space = O(N);
    public double findMaxAverage(int[] nums, int k) {
        // compute partial sums
        int[] prefixSum = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            prefixSum[i + 1] = nums[i] + prefixSum[i];
        }
        int[] points = new int[nums.length];
        int pc = 0;
        int head = 0;
        double maxAvgSum = -Double.MAX_VALUE;
        for (int i = k; i <= nums.length; i++) {
            while (pc > 1) {
                double avg1 = avg(prefixSum, points[head + pc - 1], i - k);
                double avg2 = avg(prefixSum, points[head + pc - 2], points[head + pc - 1]);
                if (avg1 >= avg2) {
                    break;
                }
                pc--;
            }
            points[head + pc++] = i - k;
            assert pc <= nums.length / 2;
            while (pc > 1) {
                double avg1 = avg(prefixSum, points[head], points[head + 1]);
                double avg2 = avg(prefixSum, points[head], i);
                if (avg1 >= avg2) {
                    break;
                }
                pc--;
                head++;
            }
            double maxAvg = avg(prefixSum, points[head], i);
            maxAvgSum = Math.max(maxAvgSum, maxAvg);
        }
        return maxAvgSum;
    }

    private double avg(int[] prefixSum, int from, int to) {
        return (double) (prefixSum[to] - prefixSum[from]) / (to - from);
    }
}
