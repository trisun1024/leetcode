import java.util.*;

class AllPathsFromSourceToTarget {

    // DFS Time = O(N!); Space = O(N)
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();
        cur.add(0);
        dfs(graph, res, cur, 0);
        return res;
    }

    public void dfs(int[][] graph, List<List<Integer>> res, List<Integer> cur, int index) {
        if (index == graph.length - 1) {
            res.add(new ArrayList<>(cur));
            return;
        }
        for (int nextNode : graph[index]) {
            cur.add(nextNode);
            dfs(graph, res, cur, nextNode);
            cur.remove(cur.size() - 1);
        }
    }

    // BFS
    public List<List<Integer>> allPathsSourceTargetII(int[][] graph) {
        List<List<Integer>> res = new ArrayList<>();
        int n = graph.length;
        Queue<List<Integer>> queue = new ArrayDeque<>();
        List<Integer> cur = new ArrayList<>();
        cur.add(0);
        queue.offer(cur);
        while (!queue.isEmpty()) {
            // System.out.println(cur);
            cur = queue.poll();
            if (cur.size() == 0) {
                continue;
            }
            if (cur.get(cur.size() - 1) == n - 1) {
                res.add(new ArrayList<>(cur));
            }
            int last = cur.get(cur.size() - 1);
            // System.out.println(last);
            for (int next : graph[last]) {
                System.out.println(next);
                List<Integer> tmp = new ArrayList<>(cur);
                tmp.add(next);
                // System.out.println(tmp);
                queue.offer(tmp);
            }
        }
        return res;
    }
}