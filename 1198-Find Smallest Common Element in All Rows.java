import java.util.*;

class FindSmallestCommonElementInAllRows {

    // Count Elements (HashMap).
    public int smallestCommonElementI(int[][] mat) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                map.put(mat[i][j], map.getOrDefault(mat[i][j], 0) + 1);
                if (map.get(mat[i][j]) == mat.length) {
                    return mat[i][j];
                }
            }
        }
        return -1;
    }

    // Count Elements (Array).
    public int smallestCommonElementII(int[][] mat) {
        int count[] = new int[10001];
        int n = mat.length, m = mat[0].length;
        for (int j = 0; j < m; ++j) {
            for (int i = 0; i < n; ++i) {
                if (++count[mat[i][j]] == n) {
                    return mat[i][j];
                }
            }
        }
        return -1;
    }

    // Binary Search. Time = O(M*N*log(M));
    public int smallestCommonElement(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        for (int j = 0; j < n; j++) {
            boolean found = true;
            for (int i = 0; i < m && found; i++) {
                found = Arrays.binarySearch(mat[i], mat[0][j]) >= 0;
            }
            if (found) {
                return mat[0][j];
            }
        }
        return -1;
    }

    // Row Positions.
    public int smallestCommonElementIII(int[][] mat) {
        int n = mat.length, m = mat[0].length;
        int pos[] = new int[n], cur_max = 0, cnt = 0;
        while (true) {
            for (int i = 0; i < n; ++i) {
                while (pos[i] < m && mat[i][pos[i]] < cur_max) {
                    ++pos[i];
                }
                if (pos[i] >= m) {
                    return -1;
                }
                if (mat[i][pos[i]] != cur_max) {
                    cnt = 1;
                    cur_max = mat[i][pos[i]];
                } else if (++cnt == n) {
                    return cur_max;
                }
            }
        }
    }

    // Row Position with Binary Search Improved
    private int metaSearch(int[] row, int pos, int val) {
        int sz = row.length, d = 1;
        while (pos < sz && row[pos] < val) {
            d <<= 1;
            if (row[Math.min(pos + d, sz - 1)] >= val) {
                d = 1;
            }
            pos += d;
        }
        return pos;
    }

    public int smallestCommonElementIV(int[][] mat) {
        int n = mat.length, m = mat[0].length;
        int pos[] = new int[n], cur_max = 0, cnt = 0;
        while (true) {
            for (int i = 0; i < n; ++i) {
                pos[i] = metaSearch(mat[i], pos[i], cur_max);
                if (pos[i] >= m) {
                    return -1;
                }
                if (mat[i][pos[i]] != cur_max) {
                    cnt = 1;
                    cur_max = mat[i][pos[i]];
                } else if (++cnt == n) {
                    return cur_max;
                }
            }
        }
    }
}