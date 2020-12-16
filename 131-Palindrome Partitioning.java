import java.util.*;

class PalindromePartitioning {
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        // base case
        if (s == null || s.length() == 0) {
            return res;
        }
        List<String> cur = new ArrayList<>();
        dfs(s, 0, cur, res);
        return res;
    }

    private void dfs(String s, int pos, List<String> cur, List<List<String>> res) {
        if (pos == s.length()) {
            res.add(new ArrayList<>(cur));
            return;
        }
        for (int i = pos + 1; i <= s.length(); i++) {
            if (!isPalindrome(s, pos, i - 1)) {
                continue;
            }
            cur.add(s.substring(pos, i));
            dfs(s, i, cur, res);
            cur.remove(cur.size() - 1);
        }
    }

    private boolean isPalindrome(String s, int l, int r) {
        while (l < r) {
            if (s.charAt(l) != s.charAt(r)) {
                return false;
            }
            l++;
            r--;
        }
        return true;
    }

}