import java.util.*;

class MaximumProductOfWordLengths {

    // Brute Force. Time = O(N^2);
    public int maxProduct(String[] words) {
        int max = 0;
        for (int i = 0; i < words.length; i++) {
            for (int j = i + 1; j < words.length; j++) {
                if (!match(words[i], words[j])) {
                    max = Math.max(max, words[i].length() * words[j].length());
                }
            }
        }
        return max;
    }

    private boolean match(String a, String b) {
        int[] chars = new int[26];
        for (char c : a.toCharArray()) {
            chars[c - 'a']++;
        }
        for (char c : b.toCharArray()) {
            if (chars[c - 'a'] != 0) {
                return true;
            }
        }
        return false;
    }

    // bit operator
    public int maxProductI(String[] words) {
        int n = words.length;
        int[] mask = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < words[i].length(); j++) {
                mask[i] |= 1 << (words[i].charAt(j) - 'a');
            }
        }

        int ans = 0;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if ((mask[i] & mask[j]) == 0) {
                    ans = Math.max(ans, words[i].length() * words[j].length());
                }
            }
        }
        return ans;
    }

    // Heap.
    public int maxProductII(String[] words) {
        int n = words.length;
        Arrays.sort(words, (a, b) -> b.length() - a.length());
        Queue<int[]> queue = new PriorityQueue<>(
                (a, b) -> words[b[0]].length() * words[b[1]].length() - words[a[0]].length() * words[a[1]].length());
        for (int i = 0; i < n; i++)
            queue.offer(new int[] { i, 0 });
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int i = curr[0];
            int j = curr[1];
            if (i != j && (getCode(words[i]) & getCode(words[j])) == 0)
                return words[i].length() * words[j].length();
            if (j < n - 1)
                queue.offer(new int[] { i, j + 1 });
        }
        return 0;
    }

    private int getCode(String str) {
        int r = 0;
        for (int i = 0; i < str.length(); i++)
            r |= 1 << (str.charAt(i) - 'a');
        return r;
    }
}