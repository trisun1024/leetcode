import java.util.*;

class MinimumUniqueWordAbbreviation {


    public static int min = 0;

    public static int best = 0;

    public static String minAbbreviation(String target, String[] dictionary) {
        char[] t = target.toCharArray();
        int N = t.length;
        int M = 0;
        for (String word : dictionary) {
            if (word.length() == N) {
                M++;
            }
        }
        if (M == 0) {
            return String.valueOf(N);
        }
        int[] words = new int[M];
        int index = 0;
        int cands = 0;
        for (String word : dictionary) {
            if (word.length() == N) {
                char[] w = word.toCharArray();
                int status = 0;
                for (int j = 0; j < N; j++) {
                    if (t[j] != w[j]) {
                        status |= 1 << j;
                    }
                }
                words[index++] = status;
                cands |= status;
            }
        }
        if (cands == 0) {
            return target;
        }
        min = Integer.MAX_VALUE;
        best = 0;
        dfs(words, N, cands, 0, 0);
        StringBuilder builder = new StringBuilder();
        int count = 0;
        for (int i = 0; i < N; i++) {
            if ((best & (1 << i)) == 0) {
                count++;
            } else {
                if (count > 0) {
                    builder.append(count);
                }
                builder.append(t[i]);
                count = 0;
            }
        }
        if (count > 0) {
            builder.append(count);
        }
        return builder.toString();
    }

    public static void dfs(int[] words, int N, int cands, int fix, int index) {
        if (!fix(words, fix)) {
            for (int i = index; i < N; i++) {
                if ((cands & (1 << i)) != 0) {
                    dfs(words, N, cands, fix | (1 << i), i + 1);
                }
            }
        } else {
            int ans = parts(fix, N);
            if (ans < min) {
                min = ans;
                best = fix;
            }
        }
    }

    public static int parts(int fix, int N) {
        int count = N;
        int limit = 1 << N;
        for (int check = 3; check < limit; check <<= 1)
            if ((fix & check) == 0) {
                count--;
            }
        return count;
    }

    public static boolean fix(int[] words, int fix) {
        for (int word : words) {
            if ((fix & word) == 0) {
                return false;
            }
        }
        return true;
    }

    // Trie & DFS.
    public String minAbbreviationI(String target, String[] dictionary) {
        List<String> dict = new ArrayList<>();
        int len = target.length();
        for (String s : dictionary) {
            if (s.length() == len) {
                dict.add(s);
            }
        }
        if (dict.isEmpty()) {
            return "" + len;
        }
        TrieNode root = buildTrie(dict);
        char[] array = target.toCharArray();
        String res = null;
        int min = 1;
        int max = len;
        while (max >= min) {
            int mid = min + (max - min) / 2;
            List<String> abbs = new ArrayList<>();
            getAbbs(array, 0, mid, new StringBuilder(), abbs);
            boolean conflict = true;
            for (String ab : abbs) {
                if (!findAbbs(root, ab, 0)) {
                    conflict = false;
                    res = ab;
                    break;
                }
            }
            if (conflict) {
                min = mid + 1;
            } else {
                max = mid - 1;
            }
        }
        return res;
    }

    private void getAbbs(char[] array, int index, int len, StringBuilder sb, List<String> abbs) {
        boolean preNum = (sb.length() > 0) && (sb.charAt(sb.length() - 1) >= '0')
                && (sb.charAt(sb.length() - 1) <= '9');
        if (len == 1) {
            if (index < array.length) {
                if (index == array.length - 1)
                    abbs.add(sb.toString() + array[index]); // add one char
                if (!preNum)
                    abbs.add(sb.toString() + (array.length - index)); // add a number
            }
        } else if (len > 1) {
            int last = sb.length();
            for (int i = index + 1; i < array.length; i++) {
                if (!preNum) { // add a number
                    sb.append(i - index);
                    getAbbs(array, i, len - 1, sb, abbs);
                    sb.delete(last, sb.length());
                }
                if (i == index + 1) { // add one char
                    sb.append(array[index]);
                    getAbbs(array, i, len - 1, sb, abbs);
                    sb.delete(last, sb.length());
                }
            }
        }
    }

    private boolean findAbbs(TrieNode root, String word, int num) {
        if (num > 0) {
            for (TrieNode ch : root.children) {
                if (ch != null && findAbbs(ch, word, num - 1)) {
                    return true;
                }
            }
            return false;
        } else {
            if (word.length() == 0) {
                return root.isWord;
            }
            int index = 0;
            while (index < word.length() && word.charAt(index) >= '0' && word.charAt(index) <= '9') {
                num = num * 10 + (word.charAt(index) - '0');
            }
            if (num > 0) {
                return findAbbs(root, word.substring(index), num);
            } else {
                if (root.children[word.charAt(0) - 'a'] != null) {
                    return findAbbs(root.children[word.charAt(0) - 'a'], word.substring(1), 0);
                } else {
                    return false;
                }
            }
        }
    }

    private TrieNode buildTrie(List<String> dict) {
        TrieNode root = new TrieNode();
        for (String s : dict) {
            TrieNode cur = root;
            for (char c : s.toCharArray()) {
                if (cur.children[c - 'a'] == null) {
                    cur.children[c - 'a'] = new TrieNode();
                }
                cur = cur.children[c - 'a'];
            }
            cur.isWord = true;
        }
        return root;
    }

    static class TrieNode {
        TrieNode[] children;
        boolean isWord;

        TrieNode() {
            this.children = new TrieNode[26];
            this.isWord = false;
        }
    }

}