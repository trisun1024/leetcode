import java.util.*;
class MinimumWindowSubstring {

    // Sliding Window
    public String minWindow(String s, String t) {
        if (s.length() < t.length())
            return "";
        HashMap<Character, Integer> goal = new HashMap<>();
        int goalSize = t.length();
        int minLen = Integer.MAX_VALUE;
        String result = "";
        for (int i = 0; i < t.length(); i++) {
            goal.put(t.charAt(i), goal.getOrDefault(t.charAt(i), 0) + 1);
        }

        int i = 0;
        int total = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        for (int j = 0; j < s.length(); j++) {
            char c = s.charAt(j);
            if (!goal.containsKey(c)) {
                continue;
            }

            // if c is a target character in the goal, and count is < goal, increase the
            // total
            int count = map.getOrDefault(c, 0);
            if (count < goal.get(c)) {
                total++;
            }

            map.put(c, count + 1);

            // when total reaches the goal, trim from left until no more chars can be
            // trimmed.
            if (total == goalSize) {
                while (!goal.containsKey(s.charAt(i)) || map.get(s.charAt(i)) > goal.get(s.charAt(i))) {
                    char pc = s.charAt(i);
                    if (goal.containsKey(pc) && map.get(pc) > goal.get(pc)) {
                        map.put(pc, map.get(pc) - 1);
                    }

                    i++;
                }

                if (minLen > j - i + 1) {
                    minLen = j - i + 1;
                    result = s.substring(i, j + 1);
                }
            }
        }

        return result;
    }

    public String minWindowI(String s, String t) {
        int[] map = new int[128];
        for (char c : t.toCharArray())
            map[c]++;
        int begin = 0, end = 0, head = 0, d = Integer.MAX_VALUE, cnt = t.length();
        while (end < s.length()) {
            if (map[s.charAt(end++)]-- > 0)
                cnt--;
            while (cnt == 0) {
                if (d > end - begin)
                    d = end - (head = begin);
                if (map[s.charAt(begin++)]++ == 0)
                    cnt++;
            }
        }
        return d == Integer.MAX_VALUE ? "" : s.substring(head, head + d);
    }
}