import java.util.*;

class Solution {

    // use Stack to solve this
    // Time O(N) Space O(N)
    public int calculator(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        Deque<Integer> stack = new ArrayDeque<>();
        char sign = '+';
        int val = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                val = val * 10 + (c - '0');
            }
            if ((!Character.isDigit(c) && c != ' ') || i == s.length() - 1) {
                if (sign == '+') {
                    stack.offerFirst(val);
                }
                if (sign == '-') {
                    stack.offerFirst(-val);
                }
                if (sign == '*') {
                    stack.offerFirst(stack.pollFirst() * val);
                }
                if (sign == '/') {
                    stack.offerFirst(stack.pollFirst() / val);
                }
                sign = c;
                val = 0;
            }
        }
        // empty the stack
        int res = 0;
        while (!stack.isEmpty()) {
            res += stack.pollFirst();
        }
        return res;
    }
}