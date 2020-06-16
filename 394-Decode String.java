import java.util.*;

class Solution {

    // use two stack to store the information; one stack store the numbers, the
    // other stack store string
    public String decodeString(String s) {
        if (s.length() == 0) {
            return "";
        }
        Deque<String> result = new ArrayDeque<>();
        Deque<Integer> count = new ArrayDeque<>();
        int i = 0;
        result.offerFirst("");
        while (i < s.length()) {
            char ch = s.charAt(i);
            if (ch >= '0' && ch <= '9') {
                int start = i;
                while (s.charAt(i + 1) >= '0' && s.charAt(i + 1) <= '9')
                    i++;
                count.offerFirst(Integer.parseInt(s.substring(start, i + 1)));
            } else if (ch == '[') {
                result.offerFirst("");
            } else if (ch == ']') {
                String str = result.pollFirst();
                StringBuilder sb = new StringBuilder();
                int times = count.pollFirst();
                for (int j = 0; j < times; j += 1) {
                    sb.append(str);
                }
                result.offerFirst(result.pollFirst() + sb.toString());
            } else {
                result.offerFirst(result.pollFirst() + ch);
            }
            i += 1;
        }
        return result.pollFirst();
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