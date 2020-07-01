import java.util.*;

class GenerateParentheses {

    // Backtracking
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList();
        backtrack(res, "", 0, 0, n);
        return res;
    }

    private void backtrack(List<String> list, String s, int open, int close, int max) {
        if (s.length() == max * 2) {
            list.add(s);
            return;
        }
        if (open < max)
            backtrack(list, s + "(", open + 1, close, max);
        if (close < open)
            backtrack(list, s + ")", open, close + 1, max);
    }
}

/*
 * // Closure Number class Solution { public List<String>
 * generateParenthesis(int n) { List<String> res = new ArrayList(); if(n==0) {
 * res.add(""); } else { for(int i=0; i<n; ++i) { for(String left:
 * generateParenthesis(i)) for(String right: generateParenthesis(n-1-i))
 * res.add("(" + left + ")" + right); } } return res; } }
 */