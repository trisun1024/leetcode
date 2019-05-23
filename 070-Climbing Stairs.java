class Solution {
    public int climbStairs(int n) {
        if (n == 0 || n == 1 || n == 2)
            return n;
        int[] set = new int[n];
        set[0] = 1;
        set[1] = 2;
        for (int i = 2; i < n; i++) {
            set[i] = set[i - 1] + set[i - 2];
        }
        return set[n - 1];
    }
}