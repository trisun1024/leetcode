import java.util.*;

class ScoreOfParentheses {

    // Stack. Time = O(N); Space = O(N);
    public int scoreOfParentheses(String S) {
        Deque<Integer> stack = new ArrayDeque<>();
        stack.offerFirst(0);
        for (char c : S.toCharArray()) {
            if (c == '(') {
                stack.offerFirst(0);
            } else {
                int v = stack.pollFirst();
                int w = stack.pollFirst();
                stack.offerFirst(w + Math.max(2 * v, 1));
            }
        }
        return stack.pollFirst();
    }

    // Count Cores. Time = O(N); Space = O(1);
    public int scoreOfParenthesesI(String S) {
        int ans = 0, bal = 0;
        for (int i = 0; i < S.length(); ++i) {
            if (S.charAt(i) == '(') {
                bal++;
            } else {
                bal--;
                if (S.charAt(i - 1) == '(') {
                    ans += 1 << bal;
                }
            }
        }
        return ans;
    }
}