class NumMatrix {
    
    int[][] prefix;

    public NumMatrix(int[][] matrix) {
         if (matrix.length == 0 || matrix[0].length == 0) {return;}
        prefix = new int[matrix.length+1][matrix[0].length+1];
        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[0].length; j++) {
                prefix[i+1][j+1] = prefix[i+1][j] + prefix[i][j+1] + matrix[i][j] - prefix[i][j];
            }
        }
    }
    
    public int sumRegion(int row1, int col1, int row2, int col2) {
        return prefix[row2+1][col2+1] - prefix[row1][col2+1] - prefix[row2+1][col1] + prefix[row1][col1];
    }
}
