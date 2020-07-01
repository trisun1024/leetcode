import java.util.*;

class EvaluateReversePolishNotation {

    // process 
    public int evalRPN(String[] tokens) {
        Deque<String> stack = new ArrayDeque<>();
        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i].equals("+")) {
                String s1 = stack.pollFirst();
                String s2 = stack.pollFirst();
                stack.offerFirst(add(s1, s2));
            } else if (tokens[i].equals("-")) {
                String s1 = stack.pollFirst();
                String s2 = stack.pollFirst();
                stack.offerFirst(minus(s2, s1));
            } else if (tokens[i].equals("*")) {
                String s1 = stack.pollFirst();
                String s2 = stack.pollFirst();
                stack.offerFirst(time(s1, s2));
            } else if (tokens[i].equals("/")) {
                String s1 = stack.pollFirst();
                String s2 = stack.pollFirst();
                stack.offerFirst(divided(s2, s1));
            } else {
                stack.offerFirst(tokens[i]);
            }
        }
        return stack.isEmpty() ? 0 : toNumber(stack.pollFirst());
    }

    private int toNumber(String s) {
        return Integer.valueOf(s);
    }

    private String add(String s1, String s2) {
        return String.valueOf(toNumber(s1) + toNumber(s2));
    }

    private String minus(String s1, String s2) {
        return String.valueOf(toNumber(s1) - toNumber(s2));
    }

    private String time(String s1, String s2) {
        return String.valueOf(toNumber(s1) * toNumber(s2));
    }

    private String divided(String s1, String s2) {
        return String.valueOf(toNumber(s1) / toNumber(s2));
    }

    // 
}