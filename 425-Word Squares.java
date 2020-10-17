import java.util.*;

class WordSquares {

    // Trie + DFS.
    public List<List<String>> wordSquares(String[] words) {
        List<List<String>> res = new ArrayList<>();
        // base case
        if (words == null || words.length == 0) {
            return res;
        }
        // construct the Trie
        TrieNode root = constructTrie(words);
        // loop for each word as starting
        for (String word : words) {
            List<String> temp = new ArrayList<>();
            temp.add(word);
            dfs(root, temp, res);
        }
        return res;
    }

    public void dfs(TrieNode root, List<String> temp, List<List<String>> res) {
        if (temp.size() == temp.get(0).length()) {
            res.add(new ArrayList<>(temp));
            return;
        }
        int size = temp.size();
        TrieNode cur = root;
        for (int i = 0; i < size; i++) {
            char c = temp.get(i).charAt(size);
            if (cur.child[c - 'a'] == null) {
                return;
            }
            cur = cur.child[c - 'a'];
        }
        List<String> next = cur.startWith;
        for (String s : next) {
            temp.add(s);
            dfs(root, temp, res);
            temp.remove(temp.size() - 1);
        }
    }

    public TrieNode constructTrie(String[] words) {
        TrieNode root = new TrieNode();
        for (String word : words) {
            TrieNode cur = root;
            for (char c : word.toCharArray()) {
                if (cur.child[c - 'a'] == null) {
                    cur.child[c - 'a'] = new TrieNode();
                }
                cur = cur.child[c - 'a'];
                cur.startWith.add(word);
            }
        }
        return root;
    }

    class TrieNode {
        TrieNode[] child;
        List<String> startWith;

        public TrieNode() {
            child = new TrieNode[26];
            startWith = new ArrayList<>();
        }
    }

}
