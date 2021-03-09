import java.util.*;

class Solution {

    // Brute Force Time = O(N*isConfusing(num))
    public int confusingNumberII(int N) {
        int num = 1;
        int count = 0;
        while (num <= N) {
            if (isConfusing(num)) {
                count++;
            }
            num++;
        }
        return count;
    }

    private final int[] key = { 0, 1, 6, 8, 9 };
    private final int[] val = { 0, 1, 9, 8, 6 };

    private boolean isConfusing(int n) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < key.length; i++) {
            map.put(key[i], val[i]);
        }
        int m = 0;
        int k = n;
        while (k != 0) {
            int rem = k % 10;
            if (!map.containsKey(rem)) {
                return false;
            } else {
                m = m * 10 + map.get(rem);
                k /= 10;
            }

        }
        return m != n;
    }

    // Brute Force DFS
    public int confusingNumberIIA(int N) {
        Set<Integer> res = new HashSet<>();
        int[] map = { 0, 1, 6, 8, 9 };
        List<Integer> cur = new ArrayList<>();
        int level = 0;
        int num = N;
        while (num > 0) {
            num = num / 10;
            level++;
        }
        dfs(N, level, map, 0, cur, res);
        return res.size();
    }

    private void dfs(int N, int level, int[] map, int index, List<Integer> cur, Set<Integer> res) {

        if (cur.size() > level) {
            return;
        }
        Integer val = toValue(cur, map);
        if (val != null && val != 0 && val <= N) {
            res.add(val);
        }
        for (int i = 0; i < map.length; i++) {
            if (i == 0 && index == 0) {
                continue;
            } else {
                cur.add(i);
                dfs(N, level, map, index + 1, cur, res);
                cur.remove(cur.size() - 1);
            }
        }
    }

    private Integer toValue(List<Integer> cur, int[] map) {
        int res = 0;
        for (int i = 0; i < cur.size(); i++) {
            res = res * 10 + map[cur.get(i)];
        }
        int rev = 0;
        for (int i = cur.size() - 1; i >= 0; i--) {
            int match = cur.get(i);
            if (match == 2) {
                match = 4;
            } else if (match == 4) {
                match = 2;
            }
            rev = rev * 10 + map[match];
        }
        return res == rev ? null : res;
    }

    // fast approach
    public int confusingNumberIIB(int N) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 0);
        map.put(1, 1);
        map.put(8, 8);
        map.put(6, 9);
        map.put(9, 6);
        return helper(map, 0, 0, N, 1);
    }

    private int helper(Map<Integer, Integer> map, int num, int rotate, int N, int base) {
        if (num > N || num < 0) {
            return 0;
        }
        int result = 0;
        if (num != rotate && num != 0) {
            result++;
        }
        for (int key : map.keySet()) {
            if ((num == 0 && key == 0) || num > 100000000) {
                continue;
            }
            result += helper(map, num * 10 + key, map.get(key) * base + rotate, N, base * 10);
        }
        return result;
    }
}