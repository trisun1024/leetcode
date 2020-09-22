class CarPooling {

    // Bucket Sort. Time = O(max(N,1001)); Space = O(1);
    public boolean carPooling(int[][] trips, int capacity) {
        int[] timestamp = new int[1001];
        for (int[] trip : trips) {
            timestamp[trip[1]] += trip[0];
            timestamp[trip[2]] -= trip[0];
        }
        int ued_capacity = 0;
        for (int number : timestamp) {
            ued_capacity += number;
            if (ued_capacity > capacity) {
                return false;
            }
        }
        return true;
    }

    // Time = O(N); Space = O(1);
    public boolean carPoolingI(int[][] trips, int capacity) {
        int max = 0; // To get end of the journey
        for (int[] trip : trips) {
            max = max > trip[2] ? max : trip[2];
        }

        int[] dp = new int[max + 1]; // Initializing the space

        for (int[] trip : trips) {
            dp[trip[1]] += trip[0]; // Incrementing the Passengers at boarding
            dp[trip[2]] -= trip[0]; // Decrementing the Passengers at the time of get down
        }

        int sum = 0;
        for (int i = 0; i < dp.length; i++) {
            sum += dp[i];
            dp[i] = sum; // Reassign till sum from 0 to get total passengers for a particular stop

            if (sum > capacity)
                return false;
        }

        return true;
    }
}