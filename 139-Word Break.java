import java.util.*;

// 6ms DP
class Solution {
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
}

//
class Solution2 {
    public boolean wordBreak(String s, List<String> wordDict) {
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