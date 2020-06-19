class Solution {
    private final int[][] DIRS = new int[][] { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

    // DFS
    public boolean exist(char[][] board, String word) {
        // corner case
        if (board == null || word == null || word.length() == 0) {
            return false;
        }
        int rows = board.length;
        int cols = board[0].length;
        boolean[][] visited = new boolean[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == word.charAt(0) && dfs(board, i, j, word, 0, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, int i, int j, String word, int index, boolean[][] visited) {
        if (index >= word.length()) {
            return true;
        }
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] != word.charAt(index)
                || visited[i][j]) {
            return false;
        }
        // expand four directions
        visited[i][j] = true;
        for (int[] dir : DIRS) {
            int x = i + dir[0];
            int y = j + dir[1];
            if (dfs(board, x, y, word, index + 1, visited)) {
                return true;
            }
        }
        visited[i][j] = false;
        return false;
    }

    // TrieNode
    static class TrieNode {
        TrieNode[] children;
        boolean isWord;

        TrieNode() {
            this.children = new TrieNode[128]; // A-Z a-z
        }
    }

    public boolean existII(char[][] board, String word) {
        // corner case
        if (board == null || word == null || word.length() == 0) {
            return false;
        }
        TrieNode root = buildTrie(word);
        int rows = board.length;
        int cols = board[0].length;
        boolean[][] visited = new boolean[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == word.charAt(0) && helper(board, i, j, root, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    private TrieNode buildTrie(String word) {
        TrieNode root = new TrieNode();
        TrieNode cur = root;
        for (char c : word.toCharArray()) {
            if (cur.children[c - 'A'] == null) {
                cur.children[c - 'A'] = new TrieNode();
            }
            cur = cur.children[c - 'A'];
        }
        cur.isWord = true;
        return root;
    }

    private boolean helper(char[][] board, int x, int y, TrieNode root, boolean[][] visited) {
        if (root.isWord) {
            return true;
        }
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length || root.children[board[x][y]] == null
                || visited[x][y]) {
            return false;
        }
        visited[x][y] = true;
        TrieNode next = root.children[board[x][y]];
        boolean res = false;
        for (int[] dir : DIRS) {
            int nx = x + dir[0];
            int ny = y + dir[1];
            res = res || helper(board, nx, ny, next, visited);
        }
        visited[x][y] = false;
        return res;
    }
}