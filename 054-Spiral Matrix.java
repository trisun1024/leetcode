class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new LinkedList<>();
        if (matrix.length == 0)
            return res;
        int rowLeft = 0, rowRight = matrix.length - 1;
        int colLeft = 0, colRight = matrix[0].length - 1;
        while (rowLeft <= rowRight && colLeft <= colRight) {
            for (int i = colLeft; i <= colRight; i++)
                res.add(matrix[rowLeft][i]);
            for (int j = rowLeft + 1; j <= rowRight; j++)
                res.add(matrix[j][colRight]);
            if (rowLeft < rowRight && colLeft < colRight) {
                for (int m = colRight - 1; m > colLeft; m--)
                    res.add(matrix[rowRight][m]);
                for (int n = rowRight; n > rowLeft; n--)
                    res.add(matrix[n][colLeft]);
            }
            rowLeft++;
            rowRight--;
            colLeft++;
            colRight--;
        }
        return res;
    }
}