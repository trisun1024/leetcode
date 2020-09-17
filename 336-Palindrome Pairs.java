import java.util.*;

class PalindromePairs {

    // Brute Force. Time = O(K*N^2);
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words.length; j++) {
                if (i == j) {
                    continue;
                }
                String combined = words[i].concat(words[j]);
                String reversed = new StringBuilder(combined).reverse().toString();
                if (combined.equals(reversed)) {
                    res.add(Arrays.asList(i, j));
                }
            }
        }
        return res;
    }

    // Trie. Time = O(N*K^2)
    public List<List<Integer>> palindromePairsI(String[] words) {
        TrieNode root = buildTrie(words);
        List<List<Integer>> res = new ArrayList<>();
        for(int i = 0; i < words.length; i++) {
            String word = words[i];
            TrieNode cur = root;
            for(int j = 0; j < word.length(); j++) {
                if(cur.wordEnding != -1 && hasPalindromeRemaining(word, j)) {
                    res.add(Arrays.asList(i, cur.wordEnding));
                }
                char c = word.charAt(j);
                cur = cur.children[c-'a'];
                if(cur == null) {
                    break;
                }
            }
            if(cur == null) {
                continue;
            }
            if(cur.wordEnding != -1 && cur.wordEnding != i) {
                res.add(Arrays.asList(i, cur.wordEnding));
            }
            for(int other: cur.prefixRemaining) {
                res.add(Arrays.asList(i, other));
            }
        }
        return res;
    }

    private TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            TrieNode cur = root;
            String reversed = new StringBuilder(word).reverse().toString();
            for (int j = 0; j < word.length(); j++) {
                if (hasPalindromeRemaining(reversed, j)) {
                    cur.prefixRemaining.add(i);
                }
                char c = reversed.charAt(j);
                if (cur.children[c - 'a'] == null) {
                    cur.children[c - 'a'] = new TrieNode();
                }
                cur = cur.children[c - 'a'];
            }
            cur.wordEnding = i;
        }
        return root;
    }

    private boolean hasPalindromeRemaining(String word, int index) {
        int left = index;
        int right = word.length() - 1;
        while (left < right) {
            if (word.charAt(left) != word.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    static class TrieNode {
        TrieNode[] children;
        int wordEnding;
        List<Integer> prefixRemaining;

        TrieNode() {
            this.children = new TrieNode[26];
            this.prefixRemaining = new ArrayList<>();
            this.wordEnding = -1; // initial set means no word ending here
        }
    }
}