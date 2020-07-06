import java.util.*;

class RemoveAllAdjacentDuplicatesInString {

    // Use Stack
    public String removeDuplicates(String S) {
        Deque<Character> stack = new ArrayDeque<>();
        for (char c : S.toCharArray()) {
            if (stack.isEmpty() || stack.peekFirst() != c) {
                stack.offerFirst(c);
            } else {
                stack.pollFirst();
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pollFirst());
        }
        return sb.reverse().toString();
    }

    // Use inplace
    public String removeDuplicatesII(String S) {
        int i = 0;
        int j = 1;
        char[] array = S.toCharArray();
        while (j < array.length) {
            if (i >= 0 && array[j] == array[i]) {
                i--;
            } else {
                array[++i] = array[j];
            }
            j++;
        }
        return new String(array, 0, i + 1);
    }

    public String removeDupliatesIII(String S) {
        int i = 0, n = S.length();
        char[] res = S.toCharArray();
        for (int j = 0; j < n; ++j, ++i) {
            res[i] = res[j];
            if (i > 0 && res[i - 1] == res[i]) // count = 2
                i -= 2;
        }
        return new String(res, 0, i);
    }
}