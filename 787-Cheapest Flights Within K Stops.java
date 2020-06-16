import java.util.*;

class Solution {

    // Graph matrix representation, Dijstra's
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        int[][] graph = new int[n][n];
        for (int[] f : flights) {
            graph[f[0]][f[1]] = f[2];
        }
        // Dijstra's
        int max = Integer.MAX_VALUE;
        boolean[] visited = new boolean[n];
        PriorityQueue<Node> minHeap = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.cost - o2.cost;
            }
        });
        minHeap.offer(new Node(src, 0, -1));
        while (!minHeap.isEmpty()) {
            Node cur = minHeap.poll();
            if (cur.pos == dst) {
                max = Math.min(max, cur.cost);
                break;
            }
            if (cur.step == K) {
                continue;
            }
            for (int i = 0; i < n; i++) {
                if (graph[cur.pos][i] != 0 && !visited[i]) { 
                    minHeap.offer(new Node(i, cur.cost + graph[cur.pos][i], cur.step + 1));
                }
            }
        }
        return max == Integer.MAX_VALUE ? -1 : max;
    }

    static class Node {
        int pos;
        int cost;
        int step;

        Node(int pos, int cost, int step) {
            this.pos = pos;
            this.cost = cost;
            this.step = step;
        }
    }

    // DFS

    public int findCheapestPriceII(int n, int[][] flights, int src, int dst, int K) {
        // initial graph
        int[][] graph = new int[n][n];
        for (int[] f : flights) {
            graph[f[0]][f[1]] = f[2];
        }
        boolean[] visited = new boolean[n];
        int[] max = new int[] { Integer.MAX_VALUE };
        dfs(graph, src, dst, 0, K, visited, max);
        return max[0] == Integer.MAX_VALUE ? -1 : max[0];
    }

    private void dfs(int[][] graph, int pos, int target, int curCost, int K, boolean[] visited, int[] max) {
        if (K < -1) {
            return;
        }
        if (pos == target) {
            max[0] = Math.min(max[0], curCost);
        }
        for (int i = 0; i < graph.length; i++) {
            if (graph[pos][i] != 0 && !visited[i]) {
                visited[i] = true;
                dfs(graph, i, target, curCost + graph[pos][i], K - 1, visited, max);
            }
        }
    }
}