import java.util.*;

class Solution {

    // TrieNode Time = O()
    public boolean wordBreakIII(String s, List<String> wordDict) {
        TrieNode root = buildTrie(wordDict);
        int n = s.length();
        boolean[] visited = new boolean[n + 1];
        visited[n] = true;
        for (int i = n - 1; i >= 0; i--) {
            TrieNode cur = root;
            for (int j = i; j < n && cur != null; j++) {
                cur = cur.children[s.charAt(j) - 'a'];
                if (cur != null && cur.isWord && visited[j + 1]) {
                    visited[i] = true;
                    break;
                }
            }
        }
        return visited[0];
    }

    private TrieNode buildTrie(List<String> wordDict) {
        TrieNode root = new TrieNode();
        for (String word : wordDict) {
            TrieNode cur = root;
            for (char c : word.toCharArray()) {
                if (cur.children[c - 'a'] == null) {
                    cur.children[c - 'a'] = new TrieNode();
                }
                cur = cur.children[c - 'a'];
            }
            cur.isWord = true;
        }
        return root;
    }

    static class TrieNode {
        boolean isWord;
        TrieNode[] children;

        TrieNode() {
            isWord = false;
            children = new TrieNode[128];
        }
    }

    // DP Time = O(N^2);
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> newWordDict = new HashSet<String>(wordDict);

        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && newWordDict.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }

    // DFS
    public boolean wordBreakII(String s, List<String> wordDict) {
        return dfs(s, wordDict, 0, new int[s.length()]);
    }

    private boolean dfs(String s, List<String> dict, int begin, int[] memo) {
        if (begin >= s.length() || memo[begin] == -1)
            return false;
        for (int i = 0; i < dict.size(); ++i) {
            int end = isSubStr(s, begin, dict.get(i));
            if (end != -1) {
                if (end == s.length() - 1 || dfs(s, dict, end + 1, memo))
                    return true;
            }
        }
        memo[begin] = -1;
        return false;
    }

    // check is p is substring starting at specific postion of s and return the end
    // postion of p in s
    private int isSubStr(String s, int begin, String p) {
        if (begin + p.length() > s.length())
            return -1;
        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) != s.charAt(begin + i))
                return -1;
        }
        return begin + p.length() - 1;
    }
}