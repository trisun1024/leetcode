import java.util.*;

// Stack
class LongestParentheses {
    public int longestValidParentheses(String s) {
        Deque<Integer> stack = new ArrayDeque<Integer>();
        stack.offerFirst(-1);
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.offerFirst(i);
            } else {
                stack.pollFirst();
                if (stack.isEmpty()) {
                    stack.offerFirst(i);
                } else {
                    max = Math.max(max, i - stack.peekFirst());
                }
            }
        }
        return max;
    }

    // Brute Force
    public boolean isValid(String s) {
        Deque<Character> stack = new ArrayDeque<Character>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push('(');
            } else if (!stack.isEmpty() && stack.peek() == '(') {
                stack.pop();
            } else {
                return false;
            }
        }
        return stack.isEmpty();
    }

    public int longestValidParenthesesI(String s) {
        int maxlen = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 2; j <= s.length(); j += 2) {
                if (isValid(s.substring(i, j))) {
                    maxlen = Math.max(maxlen, j - i);
                }
            }
        }
        return maxlen;
    }
}


