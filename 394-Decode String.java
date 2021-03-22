import java.util.*;

class DecodeString {

    // use two stack to store the information;
    // one stack store the numbers;
    // the other stack store string
    public String decodeString(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        Deque<StringBuilder> stack = new ArrayDeque<>();
        Deque<Integer> countStack = new ArrayDeque<>();
        StringBuilder cur = new StringBuilder();
        int num = 0;
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                num = num * 10 + (c - '0');
            } else if (c == '[') {
                countStack.offerFirst(num);
                num = 0;
                stack.offerFirst(cur);
                cur = new StringBuilder();
            } else if (c == ']') {
                StringBuilder temp = stack.pollFirst();
                int i = countStack.pollFirst();
                while (i > 0) {
                    temp.append(cur);
                    i--;
                }
                cur = temp;
            } else {
                cur.append(c);
            }
        }
        return cur.toString();
    }

    // DFS

    int i = 0;

    public String decodeStringII(String s) {
        StringBuilder sb = new StringBuilder();
        while (i < s.length()) {
            if (Character.isDigit(s.charAt(i))) {
                int n = 0;
                while (i < s.length() && Character.isDigit(s.charAt(i))) {
                    n = n * 10 + (int) (s.charAt(i) - '0');
                    i++;
                }

                i++; // skip '['
                String nested = decodeString(s);

                while (n-- > 0) {
                    sb.append(nested);
                }
            } else if (Character.isLetter(s.charAt(i))) {
                sb.append(s.charAt(i++));
            } else if (s.charAt(i) == ']') {
                i++;
                return sb.toString();
            }
        }
        return sb.toString();
    }
}