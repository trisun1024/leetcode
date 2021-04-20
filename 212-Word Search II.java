import java.util.*;

class WordSearchII {

    static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isWord;
    }

    // Trie + Backtracking. Time = O(M*(4*3^L)); Space = O(M*N);
    // not use visited, use in-place Space = O(1); 
    public List<String> findWords(char[][] board, String[] words) {
        int row = board.length;
        int col = board[0].length;
        Set<String> res = new HashSet<>();
        if (board == null || row == 0 || col == 0 || words == null || words.length == 0) {
            return new ArrayList<>(res);
        }
        TrieNode root = buildTrie(words);
        boolean[][] visited = new boolean[row][col];
        StringBuilder cur = new StringBuilder();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                dfs(board, i, j, root, cur, res, visited);
            }
        }
        return new ArrayList<>(res);
    }

    private TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        for (String w : words) {
            TrieNode cur = root;
            for (int i = 0; i < w.length(); i++) {
                TrieNode next = cur.children[w.charAt(i) - 'a'];
                if (next == null) {
                    next = new TrieNode();
                    cur.children[w.charAt(i) - 'a'] = next;
                }
                cur = next;
            }
            cur.isWord = true;
        }
        return root;
    }

    private void dfs(char[][] board, int x, int y, TrieNode root, StringBuilder cur, Set<String> res,
            boolean[][] visited) {
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length || visited[x][y]) {
            return;
        }
        char c = board[x][y];
        if (root.children[c - 'a'] == null) {
            return;
        }
        cur.append(c);
        root = root.children[c - 'a'];
        if (root.isWord) {
            res.add(cur.toString());
        }
        visited[x][y] = true;
        for (int[] dir : DIRS) {
            int neiX = dir[0] + x;
            int neiY = dir[1] + y;
            dfs(board, neiX, neiY, root, cur, res, visited);
        }
        visited[x][y] = false;
        cur.deleteCharAt(cur.length() - 1);
    }

    private static final int[][] DIRS = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
}