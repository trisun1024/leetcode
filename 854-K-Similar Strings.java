import java.util.*;

class KSimilarStrings {

    public int kSimilarity(String s1, String s2) {
        return kSim(s1.toCharArray(), s2.toCharArray(), 0, s1.length() - 1);
    }

    public int kSim(char[] s1, char[] s2, int start, int end) {
        if (end <= start) {
            return 0;
        }
        if (s2[start] == '0') {
            return kSim(s1, s2, start + 1, end);
        }
        for (int i = start; i <= end; i++) {
            if (s1[i] != '0' && s1[i] == s2[i]) {
                char tmp = s1[i];
                s1[i] = '0';
                s2[i] = '0';
                int m = kSim(s1, s2, start, end);
                s1[i] = tmp;
                s2[i] = tmp;
                return m;
            }
        }
        int min = Integer.MAX_VALUE;
        for (int i = start; i <= end; i++) {
            if (s1[i] == s2[start] && s1[start] == s2[i]) {
                swap(s1, start, i);
                int m = kSim(s1, s2, start + 1, end);
                swap(s1, start, i);
                return m + 1;
            }
        }

        min = Integer.MAX_VALUE;
        for (int i = start; i <= end; i++) {
            if (s1[i] == s2[start]) {
                swap(s1, start, i);
                int m = kSim(s1, s2, start + 1, end);
                if (m < min) {
                    min = m;
                }
                swap(s1, start, i);
                if (s1[start] == s2[i]) {
                    return m + 1;
                }
            }

        }
        return min + 1;
    }

    // BFS.
    public int kSimilarityII(String s1, String s2) {
        Queue<String> q = new LinkedList<>();
        q.offer(s1);
        Set<String> seen = new HashSet<>();
        seen.add(s1);
        int level = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                String cur = q.poll();
                if (cur.equals(s2)) {
                    return level;
                }
                int i = 0;
                while (cur.charAt(i) == s2.charAt(i)) {
                    i++;
                }
                char[] chars = cur.toCharArray();
                for (int j = i + 1; j < s2.length(); j++) {
                    if (chars[j] != s2.charAt(i) || chars[j] == s2.charAt(j)) {
                        continue;
                    }
                    char temp = chars[i];
                    chars[i] = chars[j];
                    chars[j] = temp;
                    String next = String.valueOf(chars);
                    if (!seen.contains(next)) {
                        q.offer(next);
                        seen.add(next);
                    }
                    temp = chars[i];
                    chars[i] = chars[j];
                    chars[j] = temp;
                }
            }
            level++;
        }
        return level;
    }

    // Pruned BFS.
    public int kSimilarityI(String s1, String s2) {
        Queue<String> queue = new ArrayDeque<>();
        queue.offer(s1);

        Map<String, Integer> dist = new HashMap<>();
        dist.put(s1, 0);

        while (!queue.isEmpty()) {
            String cur = queue.poll();
            if (cur.equals(s2))
                return dist.get(cur);
            for (String next : neighbors(cur, s2)) {
                if (!dist.containsKey(next)) {
                    dist.put(next, dist.get(cur) + 1);
                    queue.offer(next);
                }
            }
        }

        throw null;
    }

    private List<String> neighbors(String s, String target) {
        List<String> res = new ArrayList<>();
        int i = 0;
        while (i < s.length()) {
            if (s.charAt(i) != target.charAt(i)) {
                break;
            }
            i++;
        }
        char[] arr = s.toCharArray();
        for (int j = i + 1; j < s.length(); j++) {
            if (s.charAt(j) == target.charAt(i)) {
                swap(arr, i, j);
                res.add(new String(arr));
                swap(arr, i, j);
            }
        }
        return res;
    }

    private void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}