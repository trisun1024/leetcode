import java.util.*;

class NumberOfConnectedComponentsInAnUndirectedGraph {

    // DFS or BFS
    public int countComponents(int n, int[][] edges) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] item : edges) {
            graph.get(item[1]).add(item[0]);
            graph.get(item[0]).add(item[1]);
        } // done with building graph

        HashSet<Integer> visited = new HashSet<>();
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (!visited.contains(i)) {
                count++;
                // dfs(i,graph,visited);
                bfs(graph, i, visited);
            }
        }
        return count;
    }

    public void bfs(List<List<Integer>> graph, int i, HashSet<Integer> visited) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(i);
        visited.add(i);
        while (!q.isEmpty()) {
            int curr = q.poll();
            for (int neighbor : graph.get(curr)) {
                if (!visited.contains(neighbor)) {
                    q.offer(neighbor);
                    visited.add(neighbor);
                }
            }
        }
    }

    public void dfs(int i, List<List<Integer>> graph, HashSet<Integer> visited) {
        visited.add(i);
        for (int num : graph.get(i)) {
            if (!visited.contains(num)) {
                dfs(num, graph, visited);
            }
        }
    }
}