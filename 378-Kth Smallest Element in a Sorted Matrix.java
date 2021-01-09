import java.util.*;


class KthSmallestElementInSortedMatrix {

// Heap. Time = O(K*log(M*N));
public int kthSmallestI(int[][] matrix, int k ) {
    int rows = matrix.length;
    int cols = matrix[0].length;
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
    for (int r = 0; r < rows; r++) {
        for (int c = 0; c < cols; c++) {
            maxHeap.offer(matrix[r][c]);
            if (maxHeap.size() > k) {
                maxHeap.poll();
            }
        }
    }
    return maxHeap.poll();
}

// Binary Search. Time = O(log(M+N));
public int kthSmallest(int[][] matrix, int k) {
    int l = matrix[0][0];
    int r = matrix[matrix.length - 1][matrix[0].length - 1];
    while (l <= r) {
        int m = l + (r - l) / 2;
        int count = findLessEqualThan(m, matrix);
        if (count < k) {
            l = m + 1;
        } else {
            r = m - 1;
        }
    }
    return l;
}

private int findLessEqualThan(int target, int[][] matrix) {
    int count = 0;
    int i = 0;
    int j = matrix.length - 1;
    while (i < matrix.length && j >= 0) {
        if (matrix[i][j] <= target) {
            count += j + 1;
            i++;
        } else {
            j--;
        }
    }
    return count;
}
}