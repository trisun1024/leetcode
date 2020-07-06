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
        Deque<Pair> counts = new ArrayDeque<>();
        for (int i = 0; i < s.length(); ++i) {
            if (counts.isEmpty() || s.charAt(i) != counts.peekFirst().ch) {
                counts.offerFirst(new Pair(1, s.charAt(i)));
            } else {
                if (++counts.peekFirst().cnt == k) {
                    counts.pollFirst();
                }
            }
        }
        StringBuilder b = new StringBuilder();
        while (!counts.isEmpty()) {
            Pair p = counts.pollFirst();
            for (int i = 0; i < p.cnt; i++) {
                b.append(p.ch);
            }
        }
        return b.reverse().toString();
    }

    static class Pair {
        int cnt;
        char ch;

        public Pair(int cnt, char ch) {
            this.ch = ch;
            this.cnt = cnt;
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