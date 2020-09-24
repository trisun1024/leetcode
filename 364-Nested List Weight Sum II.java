import java.util.*;
import extensions.*;

class NestedListWeightSumII {

    // DFS
    public int depthSumInverse(List<NestedInteger> nestedList) {
        List<Integer> depths = new ArrayList<>();
        for (NestedInteger i : nestedList) {
            dfs(i, depths, 0);
        }
        int sum = 0;
        for (int i = 0; i < depths.size(); i++) {
            sum += depths.get(i) * (depths.size() - i);
        }
        return sum;
    }

    private void dfs(NestedInteger i, List<Integer> depths, int d) {
        if (i.isInteger()) {
            Integer num = i.getInteger();
            // match size to depth
            while (depths.size() <= d) {
                depths.add(0);
            }
            int val = depths.get(d);
            depths.set(d, val += num);
            return;
        } else {
            List<NestedInteger> list = i.getList();
            for (NestedInteger next : list) {
                dfs(next, depths, d + 1);
            }
        }
    }
}