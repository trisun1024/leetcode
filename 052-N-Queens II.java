import java.util.*;

class NQueensII {
    public int totalNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        int[] cur = new int[n];
        boolean[] col = new boolean[n];
        boolean[] dig = new boolean[2 * n - 1];
        boolean[] rev = new boolean[2 * n - 1];
        dfs(n, 0, cur, res, col, dig, rev);
        return res.size();
    }

    private void dfs(int n, int row, int[] cur, List<List<String>> res, boolean[] col, boolean[] dig, boolean[] rev) {
        if (row == n) {
            res.add(toString(cur));
            return;
        }
        for (int i = 0; i < n; i++) {
            if (valid(n, row, i, col, dig, rev)) {
                mark(n, row, i, col, dig, rev);
                cur[row] = i;
                dfs(n, row + 1, cur, res, col, dig, rev);
                unMark(n, row, i, col, dig, rev);
            }
        }
    }

    private boolean valid(int n, int r, int c, boolean[] col, boolean[] dig, boolean[] rev) {
        return !col[c] && !dig[c + r] && !rev[c - r + n - 1];
    }

    private void mark(int n, int r, int c, boolean[] col, boolean[] dig, boolean[] rev) {
        col[c] = true;
        dig[c + r] = true;
        rev[c - r + n - 1] = true;
    }

    private void unMark(int n, int r, int c, boolean[] col, boolean[] dig, boolean[] rev) {
        col[c] = false;
        dig[c + r] = false;
        rev[c - r + n - 1] = false;
    }

    private List<String> toString(int[] cur) {
        List<String> list = new ArrayList<>();
        for (int n : cur) {
            char[] tmp = new char[cur.length];
            for (int i = 0; i < tmp.length; i++) {
                if (i == n) {
                    tmp[i] = 'Q';
                } else {
                    tmp[i] = '.';
                }
            }
            list.add(new String(tmp));
        }
        return list;
    }
}
