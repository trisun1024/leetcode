import java.util.*;

class LexicographicalNumbers {

    // DFS. Time = O(10^N);
    public List<Integer> lexicalOrder(int n) {
        List<Integer> res = new ArrayList<>();
        dfs(n, 0, res);
        return res;
    }

    private void dfs(int n, int cur, List<Integer> res) {
        if (cur != 0) {
            res.add(cur);
        }
        for (int i = 0; i < 10; i++) {
            if (cur == 0 && i == 0) {
                continue;
            }
            if (cur * 10 + i > n) {
                break;
            }
            dfs(n, cur * 10 + i, res);
        }
    }
}