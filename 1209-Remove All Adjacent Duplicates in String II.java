import java.util.*;

class RemoveAllAdjacentDuplicatesInStringII {

    // Stack. Time = O(N); Space = O(N);
    public String removeDuplicatesI(String s, int k) {
        StringBuilder sb = new StringBuilder(s);
        Deque<Integer> counts = new ArrayDeque<>();
        for (int i = 0; i < sb.length(); ++i) {
            if (i == 0 || sb.charAt(i) != sb.charAt(i - 1)) {
                counts.offerFirst(1);
            } else {
                int incremented = counts.pollFirst() + 1;
                if (incremented == k) {
                    sb.delete(i - k + 1, i + 1);
                    i = i - k;
                } else {
                    counts.offerFirst(incremented);
                }
            }
        }
        return sb.toString();
    }

    // Stack with reconstruction. Time = O(N); Space = O(N);
    public String removeDuplicatesII(String s, int k) {
        Deque<Node> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            if (stack.isEmpty() || s.charAt(i) != stack.peekFirst().ch) {
                stack.offerFirst(new Node(s.charAt(i), 1));
            } else {
                if (++stack.peekFirst().count == k) {
                    stack.pollFirst();
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            Node cur = stack.pollFirst();
            for (int i = 0; i < cur.count; i++) {
                sb.append(cur.ch);
            }
        }
        return sb.reverse().toString();
    }

    static class Node {
        char ch;
        int count;

        Node(char ch, int count) {
            this.ch = ch;
            this.count = count;
        }
    }

    public String removeDuplicates(String s, int k) {
        Deque<Integer> counts = new ArrayDeque<>();
        char[] sa = s.toCharArray();
        int j = 0;
        for (int i = 0; i < s.length(); ++i, ++j) {
            sa[j] = sa[i];
            if (j == 0 || sa[j] != sa[j - 1]) {
                counts.offerFirst(1);
            } else {
                int incremented = counts.pollFirst() + 1;
                if (incremented == k) {
                    j = j - k;
                } else {
                    counts.offerFirst(incremented);
                }
            }
        }
        return new String(sa, 0, j);
    }
}