class WordDictionary {

    TrieNode root;

    /** Initialize your data structure here. */
    public WordDictionary() {
        root = new TrieNode();
    }

    /** Adds a word into the data structure. */
    public void addWord(String word) {
        TrieNode cur = root;
        for (int i = 0; i < word.length(); i++) {
            TrieNode next = cur.children[word.charAt(i) - 'a'];
            if (next == null) {
                next = new TrieNode();
                cur.children[word.charAt(i) - 'a'] = next;
            }
            cur = next;
        }
        cur.isWord = true;

    }

    /**
     * Returns if the word is in the data structure. A word could contain the dot
     * character '.' to represent any one letter.
     */
    public boolean search(String word) {
        if (word == null || word.length() == 0)
            return false;
        return dfs(word.toCharArray(), root, 0);
    }

    private boolean dfs(char[] words, TrieNode root, int i) {
        if (i == words.length) {
            return root.isWord;
        }
        if (root == null) {
            return false;
        }
        char c = words[i];
        if (c != '.') {
            TrieNode next = root.children[c - 'a'];
            return next == null ? false : dfs(words, next, i + 1);
        } else {
            for (TrieNode next : root.children) {
                if (next != null && dfs(words, next, i + 1)) {
                    return true;
                }
            }
        }
        return false;
    }

    static class TrieNode {
        TrieNode[] children;
        boolean isWord;

        TrieNode() {
            this.children = new TrieNode[26];
            this.isWord = false;
        }
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary(); obj.addWord(word); boolean param_2
 * = obj.search(word);
 */