class MagicDictionary {

    TrieNode root;
    final int K;

    /** Initialize your data structure here. */
    public MagicDictionary() {
        root = new TrieNode();
        this.K = 1;
    }

    /** Build a dictionary through a list of words */
    public void buildDict(String[] dict) {
        for (String s : dict) {
            TrieNode cur = root;
            for (char ch : s.toCharArray()) {
                TrieNode next = cur.children[ch - 'a'];
                if (next == null) {
                    cur.children[ch - 'a'] = new TrieNode();
                }
                cur = cur.children[ch - 'a'];
            }
            cur.isWord = true;
        }
    }

    /**
     * Returns if there is any word in the trie that equals to the given word after
     * modifying exactly one character
     */
    public boolean search(String word) {
        return search(root, word.toCharArray(), 0, K);
    }

    public boolean search(TrieNode cur, char[] word, int index, int k) {
        // termination condition
        if (cur == null || k < 0) {
            return false;
        }
        // return res;
        if (index == word.length) {
            return cur.isWord && k == 0;
        }
        boolean flag = false;
        for (int i = 0; i < 26; ++i) {
            int change = word[index] - 'a' == i ? 0 : 1;
            flag |= search(cur.children[i], word, index + 1, k - change);
            // k = word[index] - 'a' == i ? k : k - 1;
            // flag |= search(cur.children[i], word, index + 1, k);
            // k = word[index] - 'a' == i ? k : k + 1; // eat one and return one 
        }
        return flag;
    }

    class TrieNode {
        TrieNode[] children;
        boolean isWord;

        TrieNode() {
            this.children = new TrieNode[26];
            this.isWord = false;
        }
    }
}