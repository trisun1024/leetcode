import java.util.*;

class Solution {

    // prefix hash
    public String replaceWordsII(List<String> roots, String sentence) {
        Set<String> rootset = new HashSet<>();
        for (String root : roots)
            rootset.add(root);

        StringBuilder ans = new StringBuilder();
        for (String word : sentence.split("\\s+")) {
            String prefix = "";
            for (int i = 1; i <= word.length(); ++i) {
                prefix = word.substring(0, i);
                if (rootset.contains(prefix))
                    break;
            }
            if (ans.length() > 0)
                ans.append(" ");
            ans.append(prefix);
        }
        return ans.toString();
    }

    // Trie
    public String replaceWords(List<String> dict, String sentence) {
        TrieNode root = new TrieNode();
        for (String d : dict) {
            TrieNode cur = root;
            for (char c : d.toCharArray()) {
                if (cur.children[c - 'a'] == null) {
                    cur.children[c - 'a'] = new TrieNode();
                }
                cur = cur.children[c - 'a'];
            }
            cur.word = d;
        }

        StringBuilder res = new StringBuilder();
        for (String word : sentence.split(" ")) {
            if (res.length() > 0) {
                res.append(" ");
            }
            TrieNode cur = root;
            for (char c : word.toCharArray()) {
                if (cur.children[c - 'a'] == null || cur.word != null) {
                    break;
                }
                cur = cur.children[c - 'a'];
            }
            res.append(cur.word != null ? cur.word : word);
        }
        return res.toString();
    }

    static class TrieNode {
        TrieNode[] children;
        String word;

        TrieNode() {
            children = new TrieNode[26];
            word = "";
        }
    }
}