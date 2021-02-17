import java.util.*;

class IsGraphBipartite {

    // BFS.
    public boolean isBipartite(int[][] graph) {
        int[] color = new int[graph.length];
        Arrays.fill(color, -1);
        for (int i = 0; i < graph.length; i++) {
            if (color[i] == -1 && !bfs(graph, color, i)) {
                return false;
            }
        }
        return true;
    }

    private boolean bfs(int[][] graph, int[] color, int node) {
        Queue<Integer> queue = new LinkedList<Integer>();
        color[node] = 0;
        queue.add(node);
        while (!queue.isEmpty()) {
            int n = queue.poll();
            for (int i = 0; i < graph[n].length; i++) {
                if (color[graph[n][i]] == -1) {
                    color[graph[n][i]] = color[n] ^ 1;
                    queue.add(graph[n][i]);
                } else {
                    if (color[graph[n][i]] == color[n]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    // DFS.
    public boolean isBipartiteI(int[][] graph) {
        Set<Integer> A = new HashSet<>();
        Set<Integer> B = new HashSet<>();
        for (int i = 0; i < graph.length; i++) {
            if (!A.contains(i) && !B.contains(i)) {
                A.add(i);
                if (!dfs(graph, i, A, B)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean dfs(int[][] graph, int node, Set<Integer> A, Set<Integer> B) {
        for (int i = 0; i < graph[node].length; i++) {
            if (!A.contains(graph[node][i]) && !B.contains(graph[node][i])) {
                if (A.contains(node)) {
                    B.add(graph[node][i]);
                } else {
                    A.add(graph[node][i]);
                }
                if (!dfs(graph, graph[node][i], A, B)) {
                    return false;
                }
            } else {
                if ((A.contains(node) && A.contains(graph[node][i]))
                        || (B.contains(node) && B.contains(graph[node][i]))) {
                    return false;
                }
            }
        }
        return true;
    }
}