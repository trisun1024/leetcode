import java.util.*;
class StrobogrammaticNumberIII {

    // Brute Force. 
    public int strobogrammaticInRangeI(String low, String high) {
        int count = 0;
        for (int i = Integer.valueOf(low); i <= Integer.valueOf(high); i++) {
            if (isStrobogrammatic(String.valueOf(i))) {
                count++;
            }
        }
        return count;
    }

    private boolean isStrobogrammatic(String num) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 0);
        map.put(1, 1);
        map.put(6, 9);
        map.put(9, 6);
        map.put(8, 8);
        StringBuilder sb = new StringBuilder();
        for (int i = num.length() - 1; i >= 0; i--) {
            int c = num.charAt(i) - '0';
            if (map.containsKey(c)) {
                sb.append(String.valueOf(map.get(c)));
            } else {
                return false;
            }
        }
        return sb.toString().equals(num);
    }

    private static final char[][] pairs = { { '0', '0' }, { '1', '1' }, { '6', '9' }, { '8', '8' }, { '9', '6' } };
    
    public int strobogrammaticInRange(String low, String high) {
        int[] count = { 0 };
        for (int len = low.length(); len <= high.length(); len++) {
            char[] c = new char[len];
            dfs(low, high, c, 0, len - 1, count);
        }
        return count[0];
    }
    
    public void dfs(String low, String high, char[] c, int left, int right, int[] count) {
        if (left > right) {
            String s = new String(c);
            if ((s.length() == low.length() && s.compareTo(low) < 0)
                    || (s.length() == high.length() && s.compareTo(high) > 0)) {
                return;
            }
            count[0]++;
            return;
        }
        for (char[] p : pairs) {
            c[left] = p[0];
            c[right] = p[1];
            if (c.length != 1 && c[0] == '0') {
                continue;
            }
            if (left == right && p[0] != p[1]) {
                continue;
            }
            dfs(low, high, c, left + 1, right - 1, count);
        }
    }
}