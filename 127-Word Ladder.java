import java.util.*;

// BFS
class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // build hashset to store the wordList for later fast access
        Set<String> dict = new HashSet<>(wordList);
        // terminal condition if wordList don't have endword, so it is not possible to
        // run
        if (!dict.contains(endWord)) {
            return 0;
        }
        // we use BFS to run, so we initial a queue
        Queue<String> queue = new ArrayDeque<>();
        queue.offer(beginWord);
        int count = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                char[] tmp = queue.poll().toCharArray();
                for (int j = 0; j < tmp.length; j++) {
                    // store current char first;
                    char old = tmp[j];
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == old) {
                            continue;
                        }
                        tmp[j] = c;
                        String newS = new String(tmp);
                        if (newS.equals(endWord)) {
                            return count + 1;
                        }
                        if (!dict.contains(newS)) {
                            continue;
                        }
                        dict.remove(newS);
                        queue.offer(newS);
                    }
                    // change back to origin
                    tmp[j] = old;
                }
            }
            count++;
        }
        return 0;
    }

    // Bidirectional BFS
    public int ladderLengthII(String beginWord, String endWord, List<String> wordList) {
        Set<String> dict = new HashSet<>();
        for (String word : wordList)
            dict.add(word);

        if (!dict.contains(endWord))
            return 0;

        Set<String> q1 = new HashSet<>();
        Set<String> q2 = new HashSet<>();
        q1.add(beginWord);
        q2.add(endWord);

        int l = beginWord.length();
        int steps = 0;

        while (!q1.isEmpty() && !q2.isEmpty()) {
            ++steps;

            if (q1.size() > q2.size()) {
                Set<String> tmp = q1;
                q1 = q2;
                q2 = tmp;
            }

            Set<String> q = new HashSet<>();

            for (String w : q1) {
                char[] chs = w.toCharArray();
                for (int i = 0; i < l; ++i) {
                    char ch = chs[i];
                    for (char c = 'a'; c <= 'z'; ++c) {
                        chs[i] = c;
                        String t = new String(chs);
                        if (q2.contains(t))
                            return steps + 1;
                        if (!dict.contains(t))
                            continue;
                        dict.remove(t);
                        q.add(t);
                    }
                    chs[i] = ch;
                }
            }

            q1 = q;
        }
        return 0;
    }
}