class ImageOverlap {

    // Time = O(N^4); 
    public int largestOverlap(int[][] A, int[][] B) {
        if (A == null || B == null) {
            return 0;
        }
        int largestOverlap = 0;
        for (int row = -A.length + 1; row < A.length; row++) {
            for (int col = -A[0].length + 1; col < A[0].length; col++) {
                // find all submatrix of A, and check the match up with B
                largestOverlap = Math.max(largestOverlap, overlappingOnes(A, B, row, col));
            }
        }
        return largestOverlap;
    }

    private int overlappingOnes(int[][] A, int[][] B, int rowOffset, int colOffset) {
        int overlaps = 0;
        for (int r = 0; r < A.length; r++) {
            for (int c = 0; c < A[0].length; c++) {
                if ((r + rowOffset < 0 || r + rowOffset >= A.length)
                        || (c + colOffset < 0 || c + colOffset >= A[0].length)
                        || (A[r][c] + B[r + rowOffset][c + colOffset] != 2)) {
                    continue;
                }
                overlaps++;
            }
        }
        return overlaps;
    }
}