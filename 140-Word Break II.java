import java.util.*;

class Solution {

    public List<String> wordBreak(String s, List<String> wordDict) {
        List<String> res = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return res;
        }
        Set<String> dict = new HashSet<>(wordDict);
        boolean[] canBreak = new boolean[s.length()];
        Arrays.fill(canBreak, true);
        StringBuilder sb = new StringBuilder();
        dfs(s, 0, sb, res, canBreak, dict);
        return res;
    }

    private void dfs(String s, int start, StringBuilder sb, List<String> res, boolean[] canBreak, Set<String> dict) {
        if (start >= s.length()) {
            res.add(sb.toString());
            return;
        }
        if (!canBreak[start]) {
            return ;
        }
        for (int i = start; i < s.length(); i++) {
            String part = s.substring(start, i + 1);
            if (dict.contains(part)) {
                dfs(s, i + 1, sb.append(part + " "), res, canBreak, dict);
            }
        }
    }

}