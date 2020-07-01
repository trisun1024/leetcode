import java.util.*;

class PalindromePartitioning {
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return res;
        }
        List<String> cur = new ArrayList<>();
        dfs(s, 0, cur, res);
        dfsII(s,cur,res);
        return res;
    }

    private void dfs(String s, int pos, List<String> cur, List<List<String>> res) {
        if (pos == s.length()) {
            res.add(new ArrayList<>(cur));
            return;
        }
        for (int i = pos + 1; i <= s.length(); i++) {
            String prefix = s.substring(pos, i);
            if (!isPalindrome(prefix)) {
                continue;
            }
            cur.add(prefix);
            dfs(s, i, cur, res);
            cur.remove(cur.size() - 1);
        }
    }

    private boolean isPalindrome(String s) {
        int l = 0;
        int r = s.length() - 1;
        while (l < r) {
            if (s.charAt(l) != s.charAt(r)) {
                return false;
            }
            l++;
            r--;
        }
        return true;
    }

    // method 2
    
    private void dfsII(String s, List<String> cur, List<List<String>> res) {
        if (s.length() == 0) {
            res.add(new ArrayList<>(cur));
            return;
        }
        for (int i = 1; i <= s.length(); i++) {
            boolean flag = true;
            for (int j = 0; j < i / 2; j++) {
                if (s.charAt(j) != s.charAt(i - j - 1)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                cur.add(s.substring(0, i));
                dfsII(s.substring(i), cur, res);
                cur.remove(cur.size() - 1);
            }
        }
    }
}