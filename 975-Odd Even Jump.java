class Solution1s {
    public int oddEvenJumps(int[] A) {
        int size = A.length;
        boolean[][] dp = new boolean[size][2];
        // setup odd --> 0 and even --> 1
        dp[size - 1][0] = dp[size - 1][1] = true;

        // input initial
        TreeMap<Integer, Integer> tm = new TreeMap<>();
        tm.put(A[size - 1], size - 1);

        // count
        int ret = 1;
        for (int i = size - 2; i >= 0; --i) {
            Integer ceil = tm.ceilingKey(A[i]), floor = tm.floorKey(A[i]); // the greatest key <= the given key or null

            if (ceil != null)
                dp[i][0] = dp[tm.get(ceil)][1];
            if (floor != null)
                dp[i][1] = dp[tm.get(floor)][0];
            if (dp[i][0])
                ret++;

            tm.put(A[i], i); // as a result, it will always keep a biggest pos of A[i].
        }
        return ret;
    }
}
//