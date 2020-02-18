import java.util.*;

class Solution {
    public int calculate(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        Deque<Integer> stack = new ArrayDeque<>();
        int num = 0;
        char sign = '+';

        int n = s.length();

        for (int i = 0; i < n; i++) {
            char current = s.charAt(i);
            if (Character.isDigit(current)) {
                num = 10 * num + (int) (current - '0');
            } else if (current == '(') {
                int j = i + 1;
                int braces = 1;
                for (; j < n; j++) {
                    if (s.charAt(j) == '(')
                        ++braces;
                    if (s.charAt(j) == ')')
                        --braces;
                    if (braces == 0)
                        break;
                }
                num = calculate(s.substring(i + 1, j));
                i = j;
            }

            if (current == '+' || current == '-' || current == '*' || current == '/' || i == n - 1) {
                switch (sign) {
                case '+':
                    stack.push(num);
                    break;
                case '-':
                    stack.push(-num);
                    break;
                case '*':
                    stack.push(stack.pop() * num);
                    break;
                case '/':
                    stack.push(stack.pop() / num);
                    break;
                }
                num = 0;
                sign = current;
            }
        }

        int result = 0;
        while (!stack.isEmpty())
            result += stack.pop();

        return result;
    }
}