import java.util.*;

class ParallelCoursesII {

    // Graph + PQ BFS.
    public int minNumberOfSemesters(int n, int[][] dependencies, int k) {
        List<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        int[] indegree = new int[n];
        for (int[] dependency : dependencies) {
            graph[dependency[0] - 1].add(dependency[1] - 1);
            indegree[dependency[1] - 1]++;
        }
        // PQ
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> indegree[b] - indegree[a]);
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) {
                pq.offer(i);
            }
        }
        int step = 0;
        while (!pq.isEmpty()) {
            List<Integer> next = new ArrayList<>();
            for (int i = 0; i < k; i++) {
                int cur = pq.poll();
                for (int nei : graph[cur]) {
                    if (--indegree[nei] == 0) {
                        next.add(nei);
                    }
                    if (pq.isEmpty()) {
                        break;
                    }
                }
            }
            pq.addAll(next);
            step++;
        }
        return step;
    }
}