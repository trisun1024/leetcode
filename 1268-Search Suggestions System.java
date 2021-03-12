import java.util.*;

class SearchSuggestionsSystem {

    // Trie.
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        TrieNode root = buildTrie(products);

        List<List<String>> res = new ArrayList<>();
        for (int end = 0; end < searchWord.length(); end++) {
            List<String> cur = new ArrayList<>();
            dfs(root, searchWord, 0, end, cur);
            res.add(cur);
        }
        return res;
    }

    private void dfs(TrieNode root, String searchWord, int index, int endIndex, List<String> cur) {
        if (root == null) {
            return;
        }
        if (index > endIndex && root.word != null && cur.size() < 3) {
            cur.add(root.word);
        }
        if (index <= endIndex) {
            char c = searchWord.charAt(index);
            dfs(root.children[c - 'a'], searchWord, index + 1, endIndex, cur);
        } else {
            for (int i = 0; i < 26; i++) {
                if (root.children[i] != null && cur.size() < 3) {
                    dfs(root.children[i], searchWord, index, endIndex, cur);
                }
            }
        }
    }

    private TrieNode buildTrie(String[] products) {
        TrieNode root = new TrieNode();
        for (String p : products) {
            TrieNode cur = root;
            for (char c : p.toCharArray()) {
                if (cur.children[c - 'a'] == null) {
                    cur.children[c - 'a'] = new TrieNode();
                }
                cur = cur.children[c - 'a'];
            }
            cur.word = p;
        }
        return root;
    }

    static class TrieNode {

        TrieNode[] children;
        String word;

        TrieNode() {
            this.children = new TrieNode[26];
            this.word = null;
        }
    }
}