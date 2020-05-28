import java.util.*;

class Solution {
    // DFS
    public boolean possibleBipartition(int N, int[][] dislikes) {

        ArrayList<Integer>[] graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; ++i) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : dislikes) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }

        Map<Integer, Integer> color = new HashMap<>();
        for (int node = 1; node <= N; ++node) {
            if (!color.containsKey(node) && !dfs(graph, color, node, 0)) {
                return false;
            }
        }
        return true;
    }

    public boolean dfs(ArrayList<Integer>[] graph, Map<Integer, Integer> color, int node, int c) {
        if (color.containsKey(node)) {
            return color.get(node) == c;
        }
        color.put(node, c);

        for (int nei : graph[node]) {
            if (!dfs(graph, color, nei, c ^ 1)) {
                return false;
            }
        }
        return true;
    }

    // BFS
    public boolean possibleBipartitionII(int N, int[][] dislikes) {
        List<GraphNode> graph = buildGraph(dislikes, N);
        Map<GraphNode, Integer> visited = new HashMap<>();
        for (GraphNode node : graph) {
            if (!bfs(node, visited)) {
                return false;
            }
        }
        return true;
    }

    private boolean bfs(GraphNode node, Map<GraphNode, Integer> visited) {
        if (visited.containsKey(node)) {
            return true;
        }
        Queue<GraphNode> q = new ArrayDeque<>();
        q.offer(node);
        visited.put(node, 0);
        while (!q.isEmpty()) {
            GraphNode cur = q.poll();
            int curGroup = visited.get(cur);
            int neiGroup = curGroup == 0 ? 1 : 0;
            for (GraphNode nei : cur.neighbors) {
                if (!visited.containsKey(nei)) {
                    visited.put(nei, neiGroup);
                    q.offer(nei);
                } else if (visited.get(nei) != neiGroup) {
                    return false;
                }
            }
        }
        return true;
    }

    private List<GraphNode> buildGraph(int[][] dislikes, int N) {
        List<GraphNode> graph = new ArrayList<>();
        Map<Integer, GraphNode> map = new HashMap<>();
        for (int i = 0; i < N; i++) {
            GraphNode node = new GraphNode(i + 1);
            graph.add(node);
            map.put(i,node );
        }
        for (int[] d : dislikes) {
            GraphNode n1 = map.get(d[0]-1);
            GraphNode n2 = map.get(d[1]-1);
            graph.get(d[0] - 1).addEdge(n2);
            graph.get(d[1] - 1).addEdge(n1);
        }
        return graph;
    }

    static class GraphNode {
        int key;
        List<GraphNode> neighbors;

        GraphNode(int key) {
            this.key = key;
            this.neighbors = new ArrayList<>();
        }

        void addEdge(GraphNode node) {
            for (GraphNode n : neighbors) {
                if (n == node) {
                    return;
                }
            }
            neighbors.add(node);
        }
    }
}