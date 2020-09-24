import java.util.*;
import extensions.*;

class NestedListWeightSum {

    // DFS. Time = O(N);
    public int depthSum(List<NestedInteger> nestedList) {
        int[] depths = new int[100];
        for (NestedInteger i : nestedList) {
            dfs(i, depths, 1);
        }
        // System.out.println(Arrays.toString(depths));
        int sum = 0;
        for (int i = 0; i < depths.length; i++) {
            sum += i * depths[i];
        }
        return sum;
    }

    private void dfs(NestedInteger i, int[] depths, int depth) {
        if (i.isInteger()) {
            Integer num = i.getInteger();
            depths[depth] += num;
            return;
        } else {
            List<NestedInteger> list = i.getList();
            for (NestedInteger next : list) {
                dfs(next, depths, depth + 1);
            }
        }
    }

    // BFS 
    public int depthSumI(List<NestedInteger> nestedList) {
        Queue<NestedInteger> queue = new ArrayDeque<>();
        for(NestedInteger i: nestedList) {
            queue.offer(i);
        }
        int depth = 1;
        int sum = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                NestedInteger cur = queue.poll();
                if(cur.isInteger()) {
                    sum += depth * cur.getInteger();
                } else {
                    for(NestedInteger next: cur.getList()) {
                        queue.offer(next);
                    }
                }
            }
            depth++;
        }
        return sum;
    }
}