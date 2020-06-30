class Solution {

    // DP 
    public int countPrimes(int n) {
        boolean[] dp = new boolean[n];
        int count = 0;
        for(int i = 2; i < n ;i++) {
            if(dp[i] == false) {
                ++count;
                for(int j = 2; i*j < n ;j++) {
                    dp[i*j] = true;
                }
            }
        }
        return count;
    }
}