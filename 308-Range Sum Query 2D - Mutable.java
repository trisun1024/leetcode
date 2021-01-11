class NumMatrixMutable {

    // Brute Force.
    class NumMatrix {
        private int[][] matrix;

        public NumMatrix(int[][] matrix) {
            this.matrix = matrix;
        }

        public void update(int row, int col, int val) {
            this.matrix[row][col] = val;
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            int sum = 0;
            for (int r = row1; r <= row2; r++) {
                for (int c = col1; c <= col2; c++) {
                    sum += matrix[r][c];
                }
            }
            return sum;
        }
    }

    // Prefix Sum.
    class NumMatrixI {

        private int prefix[][];
        private int matrix[][];

        public NumMatrixI(int[][] matrix) {
            if (matrix == null || matrix.length == 0) {
                return;
            }
            prefix = new int[matrix.length + 1][matrix[0].length + 1];
            this.matrix = new int[matrix.length][matrix[0].length];
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    update(i, j, matrix[i][j]);
                }
            }
        }

        public void update(int row, int col, int val) {
            int delta = val - matrix[row][col];
            matrix[row][col] = val;
            for (int i = row + 1; i < prefix.length; i += i & (-i)) { // also equals to i |= i + 1
                for (int j = col + 1; j < prefix[0].length; j += j & (-j)) {
                    prefix[i][j] += delta;
                }
            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            return getSum(row2 + 1, col2 + 1) - getSum(row1, col2 + 1) - getSum(row2 + 1, col1) + getSum(row1, col1);
        }

        private int getSum(int row, int col) {
            int sum = 0;
            for (int i = row; i > 0; i -= i & (-i)) {
                for (int j = col; j > 0; j -= j & (-j)) {
                    sum += prefix[i][j];
                }
            }
            return sum;
        }
    }

    // Segment Tree.
    class NumMatrixII {

        private SegmentTree root;
        private int M, N;
        private int[][] matrix;

        public NumMatrixII(int[][] matrix) {
            if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
                return;
            this.M = matrix.length;
            this.N = matrix[0].length;
            this.matrix = matrix;
            root = build(0, 0, M - 1, N - 1);
        }

        private SegmentTree build(int row1, int col1, int row2, int col2) {
            if (row1 > row2 || col1 > col2)
                return null;
            SegmentTree node = new SegmentTree(row1, col1, row2, col2);
            if (row1 == row2 && col1 == col2) {
                node.sum = matrix[row1][col1];
                return node;
            }
            int midRow = row1 + (row2 - row1) / 2;
            int midCol = col1 + (col2 - col1) / 2;

            node.n11 = build(row1, col1, midRow, midCol);
            node.n12 = build(row1, midCol + 1, midRow, col2);
            node.n21 = build(midRow + 1, col1, row2, midCol);
            node.n22 = build(midRow + 1, midCol + 1, row2, col2);
            node.sum += node.n11 == null ? 0 : node.n11.sum;
            node.sum += node.n12 == null ? 0 : node.n12.sum;
            node.sum += node.n21 == null ? 0 : node.n21.sum;
            node.sum += node.n22 == null ? 0 : node.n22.sum;
            return node;
        }

        public void update(int row, int col, int val) {
            if (M == 0 || N == 0)
                return;
            update(root, row, col, val - matrix[row][col]);
            matrix[row][col] = val;
        }

        private void update(SegmentTree node, int row, int col, int delta) {
            if (node.row1 == node.row2 && node.col1 == node.col2) {
                node.sum += delta;
                return;
            }
            int midRow = node.row1 + (node.row2 - node.row1) / 2;
            int midCol = node.col1 + (node.col2 - node.col1) / 2;
            if (row <= midRow) {// top
                if (col <= midCol)
                    update(node.n11, row, col, delta); // top-left
                else
                    update(node.n12, row, col, delta); // top-right
            } else {// bottom
                if (col <= midCol)
                    update(node.n21, row, col, delta); // bottom-left
                else
                    update(node.n22, row, col, delta); // bottom-right
            }
            node.sum += delta;
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            if (M == 0 || N == 0)
                return 0;
            return sumRegion(root, row1, col1, row2, col2);
        }

        private int sumRegion(SegmentTree node, int row1, int col1, int row2, int col2) {
            if (node == null)
                return 0;
            if (node.row1 >= row1 && node.row2 <= row2 && node.col1 >= col1 && node.col2 <= col2)
                return node.sum;
            int res = 0;
            if (node.n11 != null && row1 <= node.n11.row2 && col1 <= node.n11.col2)
                res += sumRegion(node.n11, row1, col1, row2, col2);
            if (node.n12 != null && row1 <= node.n12.row2 && col2 >= node.n12.col1)
                res += sumRegion(node.n12, row1, col1, row2, col2);
            if (node.n21 != null && row2 >= node.n21.row1 && col1 <= node.n21.col2)
                res += sumRegion(node.n21, row1, col1, row2, col2);
            if (node.n22 != null && row2 >= node.n22.row1 && col2 >= node.n22.col1)
                res += sumRegion(node.n22, row1, col1, row2, col2);
            return res;
        }

        class SegmentTree {
            SegmentTree n11, n12, n21, n22;
            int sum;
            int row1, col1, row2, col2;

            public SegmentTree(int row1, int col1, int row2, int col2) {
                this.row1 = row1;
                this.col1 = col1;
                this.row2 = row2;
                this.col2 = col2;
            }
        }
    }

}
