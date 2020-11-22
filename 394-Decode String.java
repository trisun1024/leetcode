import java.util.*;

class DecodeString {

    // use two stack to store the information; one stack store the numbers, the
    // other stack store string
    public String decodeString(String s) {
        if (s.length() == 0) {
            return "";
        }
        Deque<Integer> countStack = new ArrayDeque<>();
        Deque<StringBuilder> stringStack = new ArrayDeque<>();
        StringBuilder currentString = new StringBuilder();
        int k = 0;
        for (char ch : s.toCharArray()) {
            if (Character.isDigit(ch)) {
                k = k * 10 + ch - '0';
            } else if (ch == '[') {
                // push the number k to countStack
                countStack.push(k);
                // push the currentString to stringStack
                stringStack.push(currentString);
                // reset currentString and k
                currentString = new StringBuilder();
                k = 0;
            } else if (ch == ']') {
                StringBuilder decodedString = stringStack.pop();
                // decode currentK[currentString] by appending currentString k times
                for (int currentK = countStack.pop(); currentK > 0; currentK--) {
                    decodedString.append(currentString);
                }
                currentString = decodedString;
            } else {
                currentString.append(ch);
            }
        }
        return currentString.toString();
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