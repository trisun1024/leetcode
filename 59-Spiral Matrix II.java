class Solution {
    public int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        if (n == 0) {
            return res;
        }
        int left = 0;
        int right = n - 1;
        int up = 0;
        int down = n - 1;
        int num = 1;
        while (left <= right && up <= down) {
            for (int i = left; i <= right; i++) {
                res[up][i] = num++;
            }
            for (int i = up + 1; i <= down; i++) {
                res[i][right] = num++;
            }
            for (int i = right - 1; i >= left; i--) {
                res[down][i] = num++;
            }
            for (int i = down - 1; i > up; i--) {
                res[i][left] = num++;
            }
            left++;
            right--;
            up++;
            down--;
        }
        return res;
    }
}