import java.util.*;

class SplitConcatenatedStrings {

    // DFS. Time = O(2^N);
    public String splitLoopedStringI(String[] strs) {
        String res = "";
        dfs(strs, 0, "", res);
        return res;
    }

    private void dfs(String[] strs, int index, String cur, String res) {
        if (index == strs.length) {
            for (int j = 0; j < cur.length(); j++) {
                String t = cur.substring(j) + cur.substring(0, j);
                if (t.compareTo(res.toString()) > 0)
                    res = t;
            }
        }
        dfs(strs, index + 1, cur + strs[index], res);
        dfs(strs, index + 1, cur + new StringBuffer(strs[index]).reverse().toString(), res);
    }

    // BFS.
    public String splitLoopedStringII(String[] strs) {
        Queue<String> queue = new ArrayDeque<>();
        String res = "";
        int i = 0;
        int j = 0;
        queue.offer("");
        while (i < strs.length) {
            String s = queue.poll();
            queue.offer(s + strs[i]);
            queue.offer(s + new StringBuffer(strs[i]).reverse().toString());
            j++;
            if (j == 1 << i) {
                i++;
                j = 0;
            }
        }

        while (!queue.isEmpty()) {
            String s = queue.poll();
            for (int k = 0; k < s.length(); k++) {
                String ss = s.substring(k) + s.substring(0, k);
                if (ss.compareTo(res) > 0) {
                    res = ss;
                }
            }
        }
        return res;
    }

    // Optimized.
    public String splitLoopedString(String[] strs) {
        StringBuilder sb = new StringBuilder();

        for (String str : strs) {
            String reverseStr = new StringBuilder(str).reverse().toString();

            if (str.compareTo(reverseStr) > 0) {
                sb.append(str);
            } else {
                sb.append(reverseStr);
            }
        }

        String loop = sb.toString();
        String result = "a";
        int index = 0;

        for (String str : strs) {
            String reverseStr = new StringBuilder(str).reverse().toString();
            String middle = loop.substring(index + str.length()) + loop.substring(0, index);

            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) >= result.charAt(0)) {
                    String candidate = str.substring(i) + middle + str.substring(0, i);

                    if (candidate.compareTo(result) > 0) {
                        result = candidate;
                    }
                }

                if (reverseStr.charAt(i) >= result.charAt(0)) {
                    String candidate = reverseStr.substring(i) + middle + reverseStr.substring(0, i);

                    if (candidate.compareTo(result) > 0) {
                        result = candidate;
                    }
                }
            }

            index += str.length();
        }

        return result;
    }
}