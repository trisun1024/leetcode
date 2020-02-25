import java.util.*;

class Solution {

    // vertical iteration
    // Time O(M*N) Space O(1)
    public int[] kWeakestRows(int[][] mat, int k) {
        int m = mat.length;
        int n = mat[0].length;
        int[] indexes = new int[k];
        int nextInsertIndex = 0;
        for (int i = 0; i < n && nextInsertIndex < k; i++) {
            for (int j = 0; j < m && nextInsertIndex < k; j++) {
                if (mat[j][i] == 0 && (i == 0 || mat[j][i - 1] == 1)) {
                    indexes[nextInsertIndex] = j;
                    nextInsertIndex++;
                }
            }
        }
        for (int i = 0; nextInsertIndex < k; i++) {
            if (mat[i][n - 1] == 1) {
                indexes[nextInsertIndex] = i;
                nextInsertIndex++;
            }
        }
        return indexes;
    }

    // binary search and priority queue
    // Time O(M*log(N*K)) Space O(K)
    private int binarySearch(int[] row) {
        int low = 0;
        int high = row.length;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (row[mid] == 1) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    public int[] kWeakestRowsII(int[][] mat, int k) {
        int m = mat.length;
        int n = mat[0].length;

        // Create a Priority Queue that measures firstly on strength and then indexes.
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            if (a[0] == b[0])
                return b[1] - a[1];
            else
                return b[0] - a[0];
        });

        // Add strength/index pairs to the pq. Whenever length > k, remove the largest.
        for (int i = 0; i < m; i++) {
            int strength = binarySearch(mat[i]);
            int[] entry = new int[] { strength, i };
            pq.add(entry);
            if (pq.size() > k) {
                pq.poll();
            }
        }

        // Pull the indexes out of the priority queue.
        int[] indexes = new int[k];
        for (int i = k - 1; i >= 0; i--) {
            int[] pair = pq.poll();
            indexes[i] = pair[1];
        }

        return indexes;
    }
}