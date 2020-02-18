import java.util.*;

class Solution {
    public int calculate(String s) {
        Deque<Integer> stack = new ArrayDeque<>();
        int val = 0;
        int res = 0;
        int sign = 1; // 1 positive, -1 negative
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                val = val * 10 + (c - '0');
            } else if (c == '+') {
                res += sign * val;
                sign = 1;
                val = 0;
            } else if (c == '-') {
                res += sign * val;
                sign = -1;
                val = 0;
            } else if (c == '(') {
                stack.offerFirst(res);
                stack.offerFirst(sign);
                sign = 1;
                res = 0;
            } else if (c == ')') {
                res += sign * val;
                res *= stack.pollFirst();
                res += stack.pollFirst();
                val = 0;
            }
        }
        return res + (sign * val);
    }
}