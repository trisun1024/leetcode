class Trie {

    static class TrieNode {

        TrieNode root;
        TrieNode[] children;
        boolean isWord;
    }

    TrieNode root;

    /** Initialize your data structure here. */
    public Trie() {
        root = new TrieNode();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode cur = root;
        for (char c : word.toCharArray()) {
            TrieNode temp = cur.children[c - 'a'];
            if (temp == null) {
                temp = new TrieNode();
            }
            cur = cur.children[c];
        }
        root.isWord = true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode cur = root;
        for (char c : word.toCharArray()) {
            TrieNode temp = cur.children[c - 'a'];
            if (temp == null) {
                return false;
            }
            cur = temp;
        }
        return cur.isWord;
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        TrieNode cur = root;
        for (char c : prefix.toCharArray()) {
            TrieNode next = cur.children[c - 'a'];
            if (next == null) {
                return false;
            }
            cur = next;
        }
        return true;
    }
}

/**
 * Your Trie object will be instantiated and called as such: Trie obj = new
 * Trie(); obj.insert(word); boolean param_2 = obj.search(word); boolean param_3
 * = obj.startsWith(prefix);
 */