import java.util.*;

class TernaryExpressionParser {

    // Stack. Time = O(N);
    public String parseTernary(String expression) {
        if (expression == null || expression.length() == 0) {
            return "";
        }
        Deque<Character> stack = new ArrayDeque<>();
        for (int i = expression.length() - 1; i >= 0; i--) {
            char c = expression.charAt(i);
            if (!stack.isEmpty() && stack.peekFirst() == '?') {
                stack.pollFirst();
                char first = stack.pollFirst();
                stack.pollFirst();
                char second = stack.pollFirst();
                if (c == 'T') {
                    stack.offerFirst(first);
                } else {
                    stack.offerFirst(second);
                }
            } else {
                stack.offerFirst(c);
            }
        }
        return String.valueOf(stack.peekFirst());
    }

    // DFS.
    public String parseTernaryI(String expression) {
        if (expression == null || expression.length() == 0) {
            return expression;
        }
        char[] array = expression.toCharArray();
        return dfs(array, 0, array.length - 1) + "";
    }

    private char dfs(char[] array, int start, int end) {
        if (start == end) {
            return array[start];
        }
        int count = 0;
        int i = start;
        while (i <= end) {
            if (array[i] == '?') {
                count++;
            } else if (array[i] == ':') {
                count--;
                if (count == 0) {
                    break;
                }
            }
            i++;
        }
        return array[start] == 'T' ? dfs(array, start + 2, i - 1) : dfs(array, i + 1, end);
    }

    // Recursion
    public String parseTernaryII(String expression) {
        if (expression == null || expression.length() == 0) {
            return expression;
        }
        for (int i = 0; i < expression.length(); i++) {
            if (expression.charAt(i) == '?') {
                int flag = 1;
                for (int j = i + 1; j < expression.length(); j++) {
                    if (expression.charAt(j) == '?')
                        flag++;
                    if (expression.charAt(j) == ':')
                        flag--;
                    if (flag == 0) {
                        if (expression.charAt(i - 1) == 'T')
                            return parseTernary(expression.substring(i + 1, j));
                        else
                            return parseTernary(expression.substring(j + 1, expression.length()));
                    }
                }
            }
        }

        return expression;
    }
}