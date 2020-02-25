class Solution {
    public int strangePrinter(String s) {
        int size = s.length();
        int[][] dp = new int[size + 1][size + 1];
        for (int x = 1; x <= size; x++) {
            for (int y = 1; y <= x; y++) {
                dp[y][x] = x - y + 1;
                for (int z = y; z < x; z++) {
                    dp[y][x] = Math.min(dp[y][x],
                            dp[y][z - 1] + dp[z][x - 1] + (s.charAt(x - 1) != s.charAt(z - 1) ? 1 : 0));
                }
            }
        }
        return size > 0 ? dp[1][size] : 0;
    }
}