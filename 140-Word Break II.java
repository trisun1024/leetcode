import java.util.*;

class WordBreakII {

    // DFS + DP
    public List<String> wordBreak(String s, List<String> wordDict) {
        List<String> res = new ArrayList<String>();
        if (s == null || s.length() == 0) {
            return res;
        }
        // set worddict as hashset for quick access
        Set<String> dict = new HashSet<>(wordDict);
        int n = s.length();
        // use dp to find possible position
        boolean[] canBreak = new boolean[n + 1];
        canBreak[n] = true;
        for (int i = n - 1; i >= 0; --i) {
            for (int j = i; j < n; ++j) {
                if (dict.contains(s.substring(i, j + 1)) && canBreak[j + 1]) {
                    canBreak[i] = true;
                    break;
                }
            }
        }
        // if don't find any, return res;
        if (canBreak[0] == false) {
            return res;
        }
        // use dfs to get all
        helper(s, dict, new StringBuilder(), 0, res);
        return res;
    }

    public void helper(String s, Set<String> dict, StringBuilder path, int start, List<String> res) {
        if (start == s.length()) {
            res.add(path.toString().trim());
            return;
        }
        for (int i = start; i < s.length(); i++) {
            String word = s.substring(start, i + 1);
            if (dict.contains(word)) {
                helper(s, dict, path.append(word + " "), i + 1, res);
                path.delete(path.length() - word.length() - 1, path.length());
            }
        }
    }

    // DP
    public List<String> wordBreak(String s, Set<String> wordDict) {
        LinkedList<String>[] dp = new LinkedList[s.length() + 1];
        LinkedList<String> initial = new LinkedList<>();
        initial.add("");
        dp[0] = initial;
        for (int i = 1; i <= s.length(); i++) {
            LinkedList<String> list = new LinkedList<>();
            for (int j = 0; j < i; j++) {
                if (dp[j].size() > 0 && wordDict.contains(s.substring(j, i))) {
                    for (String l : dp[j]) {
                        list.add(l + (l.equals("") ? "" : " ") + s.substring(j, i));
                    }
                }
            }
            dp[i] = list;
        }
        return dp[s.length()];
    }

}