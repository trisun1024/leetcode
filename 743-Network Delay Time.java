import java.util.*;

class Solution {
    // dfs solution
    // Time O(N^N+ElogE) Space O(N+E)
    public int networkDelayTime(int[][] times, int N, int K) {
        // build graph
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] edge : times) {
            if (!graph.containsKey(edge[0])) {
                graph.put(edge[0], new ArrayList<>());
            }
            graph.get(edge[0]).add(new int[] { edge[2], edge[1] });
        }
        // initial dist
        Map<Integer, Integer> dist = new HashMap<>();
        for (int node = 1; node <= N; node++) {
            dist.put(node, Integer.MAX_VALUE);
        }
        dfs(graph, K, 0, dist);
        int ans = 0;
        for (int cand : dist.values()) {
            // if candidate is not receive the signal then return -1
            if (cand == Integer.MAX_VALUE) {
                return -1;
            }
            ans = Math.max(ans, cand);
        }
        return ans;
    }

    private void dfs(Map<Integer, List<int[]>> graph, int node, int elapsed, Map<Integer, Integer> dist) {
        if (elapsed >= dist.get(node)) {
            return;
        }
        dist.put(node, elapsed);
        if (graph.containsKey(node)) {
            for (int[] info : graph.get(node)) {
                dfs(graph, info[1], elapsed + info[0], dist);
            }
        }
    }

    // Dijkstra's
    // basic implement
    public int networkDelayTimeII(int[][] times, int N, int K) {
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] edge : times) {
            if (!graph.containsKey(edge[0]))
                graph.put(edge[0], new ArrayList<int[]>());
            graph.get(edge[0]).add(new int[] { edge[1], edge[2] });
        }
        Map<Integer, Integer> dist = new HashMap<>();
        for (int node = 1; node <= N; ++node)
            dist.put(node, Integer.MAX_VALUE);

        dist.put(K, 0);
        boolean[] seen = new boolean[N + 1];

        while (true) {
            int candNode = -1;
            int candDist = Integer.MAX_VALUE;
            for (int i = 1; i <= N; ++i) {
                if (!seen[i] && dist.get(i) < candDist) {
                    candDist = dist.get(i);
                    candNode = i;
                }
            }

            if (candNode < 0)
                break;
            seen[candNode] = true;
            if (graph.containsKey(candNode))
                for (int[] info : graph.get(candNode))
                    dist.put(info[0], Math.min(dist.get(info[0]), dist.get(candNode) + info[1]));
        }

        int ans = 0;
        for (int cand : dist.values()) {
            if (cand == Integer.MAX_VALUE)
                return -1;
            ans = Math.max(ans, cand);
        }
        return ans;
    }

    // heap implement
    public int networkDelayTimeIII(int[][] times, int N, int K) {
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] edge : times) {
            if (!graph.containsKey(edge[0]))
                graph.put(edge[0], new ArrayList<int[]>());
            graph.get(edge[0]).add(new int[] { edge[1], edge[2] });
        }
        PriorityQueue<int[]> heap = new PriorityQueue<int[]>((info1, info2) -> info1[0] - info2[0]);
        heap.offer(new int[] { 0, K });

        Map<Integer, Integer> dist = new HashMap<>();

        while (!heap.isEmpty()) {
            int[] info = heap.poll();
            int d = info[0], node = info[1];
            if (dist.containsKey(node))
                continue;
            dist.put(node, d);
            if (graph.containsKey(node))
                for (int[] edge : graph.get(node)) {
                    int nei = edge[0], d2 = edge[1];
                    if (!dist.containsKey(nei))
                        heap.offer(new int[] { d + d2, nei });
                }
        }

        if (dist.size() != N)
            return -1;
        int ans = 0;
        for (int cand : dist.values())
            ans = Math.max(ans, cand);
        return ans;
    }

    // use original 
    public int networkDelayTimeIV(int[][] times, int N, int K) {
        int[][] dist = new int[N][];
        for (int i = 0; i < dist.length; i++) {
            dist[i] = new int[N];
            for (int j = 0; j < dist.length; j++) {
                dist[i][j] = -1;
            }
        }

        for (int[] edge : times)
            dist[edge[0] - 1][edge[1] - 1] = edge[2];
        int s = K - 1;
        int min = -1, minIndex = -1;
        for (int i = 0; i < N; i++) {
            if (s == i || dist[s][i] < 0)
                continue;
            if (min < 0 || dist[s][i] < min) {
                min = dist[s][i];
                minIndex = i;
            }
        }

        int t = minIndex;
        boolean[] visited = new boolean[N];
        visited[s] = true;
        while (t >= 0) {
            visited[t] = true;
            for (int i = 0; i < N; i++) {
                if (visited[i] || dist[t][i] < 0)
                    continue;
                int d = dist[s][t] + dist[t][i];
                if (dist[s][i] < 0 || d < dist[s][i]) {
                    dist[s][i] = d;
                }
            }

            min = -1;
            minIndex = -1;
            for (int i = 0; i < N; i++) {
                if (visited[i] || dist[s][i] < 0)
                    continue;
                if (min < 0 || dist[s][i] < min) {
                    min = dist[s][i];
                    minIndex = i;
                }
            }
            t = minIndex;
        }

        int max = 0;
        for (int i = 0; i < N; i++) {
            if (i == s)
                continue;
            int d = dist[s][i];
            if (d < 0)
                return -1;
            max = Math.max(max, d);
        }
        return max;
    }
}