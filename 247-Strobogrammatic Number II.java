import java.util.*;

class StrobogrammaticNumberII {

    private int[] match = new int[] { 0, 1, -1, -1, -1, -1, 9, -1, 8, 6 };

    public List<String> findStrobogrammatic(int n) {
        Set<String> res = new HashSet<>();
        char[] cur = new char[n];
        helper(0, n, cur, res);
        return new ArrayList<>(res);
    }

    private boolean check(char[] array) {
        return array.length > 1 ? array[0] != '0' : true;
    }

    private void helper(int index, int n, char[] cur, Set<String> res) {
        if (index * 2 >= n) {
            if (check(cur)) {
                res.add(new String(cur));
            }
            return;
        }
        for (int i = 0; i < match.length; i++) {
            if (match[i] != -1) {
                cur[index] = (char) (i + '0');
                if (n - index - 1 != index) {
                    cur[n - index - 1] = (char) (match[i] + '0');
                } else {
                    if (i != match[i]) {
                        continue;
                    }
                }
                helper(index + 1, n, cur, res);
            }
        }
    }
}