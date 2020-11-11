
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
