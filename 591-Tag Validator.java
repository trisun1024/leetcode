import java.util.*;

class TagValidator {

    // Stack.
    public boolean isValid(String code) {
        Deque<String> stack = new ArrayDeque<>();
        for (int i = 0; i < code.length();) {
            // base case
            if (i > 0 && stack.isEmpty()) {
                return false;
            }
            if (code.startsWith("<![CDATA[", i)) {
                int j = i + 9;
                i = code.indexOf("]]>", j);
                if (i < 0) {
                    return false;
                }
                i += 3;
            } else if (code.startsWith("</", i)) {
                int j = i + 2;
                i = code.indexOf('>', j);
                if (i < 0 || i == j || i - j > 9) {
                    return false;
                }
                for (int k = j; k < i; k++) {
                    if (!Character.isUpperCase(code.charAt(k))) {
                        return false;
                    }
                }
                String s = code.substring(j, i++);
                if (stack.isEmpty() || !stack.pollFirst().equals(s)) {
                    return false;
                }
            } else if (code.startsWith("<", i)) {
                int j = i + 1;
                i = code.indexOf('>', j);
                if (i < 0 || i == j || i - j > 9) {
                    return false;
                }
                for (int k = j; k < i; k++) {
                    if (!Character.isUpperCase(code.charAt(k))) {
                        return false;
                    }
                }
                String s = code.substring(j, i++);
                stack.offerFirst(s);
            } else {
                i++;
            }
        }
        return stack.isEmpty();
    }
}