import java.util.*;

class RemoveInvalidParentheses {

    // DFS
    public List<String> removeInvalidParentheses(String s) {
        List<String> result = new ArrayList<>();
        char[] check = { '(', ')' };
        dfs(s, result, check, 0, 0);
        return result;
    }

    private void dfs(String s, List<String> result, char[] check, int startToCount, int startToDelete) {
        int i = startToCount;
        int count = 0;
        while (i < s.length() && count >= 0) {
            char c = s.charAt(i++);
            if (c == check[0])
                count++;
            else if (c == check[1])
                count--;
        }

        if (count >= 0) {
            String reverse = new StringBuilder(s).reverse().toString();

            if (check[0] == '(') {
                dfs(reverse, result, new char[] { check[1], check[0] }, 0, 0);
            } else {
                result.add(reverse);
            }
        } else {
            i--;
            for (int j = startToDelete; j <= i; j++) {
                if (s.charAt(j) == check[1] && (j == startToDelete || s.charAt(j - 1) != check[1])) {
                    dfs(s.substring(0, j) + s.substring(j + 1), result, check, i, j);
                }
            }
        }
    }

    // BFS
}