import java.util.*;

class SortMatrixDiagonally {

    // HashMap + Heap. Time = O(N*M*log(MIN(N,M)));
    public int[][] diagonalSort(int[][] mat) {
        // hashmap to keep the diagonals
        HashMap<Integer, PriorityQueue<Integer>> h = new HashMap<>();

        // fill the hashmap
        int n = mat.length, m = mat[0].length;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                h.putIfAbsent(i - j, new PriorityQueue<>());
                h.get(i - j).add(mat[i][j]);
            }
        }

        // build output
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                mat[i][j] = h.get(i - j).poll();
            }
        }

        return mat;
    }

    // Sort Diagonals by using Queue.
    public int[][] diagonalSortI(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;

        // sort all diagonals in the lower left corner
        for (int i = 0; i < n; ++i) {
            sortDiagonal(mat, i, 0, n, m);
        }
        // sort all diagonals in the upper right corner
        for (int j = 0; j < m; ++j) {
            sortDiagonal(mat, 0, j, n, m);
        }
        return mat;
    }

    public void sortDiagonal(int[][] mat, int i, int j, int n, int m) {
        // max heap -> to keep max element always on top
        Queue<Integer> diagonal = new PriorityQueue<>((o1, o2) -> o2 - o1);

        // store the current diagonal in the heap
        while (i < n && j < m) {
            diagonal.add(mat[i][j]);
            ++i;
            ++j;
        }

        // push the sorted values back into the matrix
        while (i > 0 && j > 0) {
            --i;
            --j;
            mat[i][j] = diagonal.remove();
        }
    }

    // Sort Diagonal by using Sort.
    public int[][] diagonalSortII(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;

        // sort all diagonals
        // in the lower left corner
        for (int i = 0; i < n; ++i) {
            sort(mat, i, 0, n, m);
        }
        // sort all diagonals
        // in the upper right corner
        for (int j = 0; j < m; ++j) {
            sort(mat, 0, j, n, m);
        }
        return mat;
    }

    public void sort(int[][] mat, int i, int j, int n, int m) {
        List<Integer> diagonal = new ArrayList<>();
        // store the current diagonal
        // in the list
        while (i < n && j < m) {
            diagonal.add(mat[i][j]);
            ++i;
            ++j;
        }

        // sort the diagonal values
        Collections.sort(diagonal);

        // push the sorted values
        // back into the matrix
        while (i > 0 && j > 0) {
            --i;
            --j;
            mat[i][j] = diagonal.remove(diagonal.size() - 1);
        }
    }

}