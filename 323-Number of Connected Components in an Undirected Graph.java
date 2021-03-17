import java.util.*;

class NumberOfConnectedComponentsInAnUndirectedGraph {

    // DFS or BFS
    public int countComponentsII(int n, int[][] edges) {
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

    // Union Find.
    public int countComponentsI(int n, int[][] edges) {
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        int count = n;
        for (int[] edge : edges) {
            int p = findParent(parent, edge[0]);
            int q = findParent(parent, edge[1]);
            if (p != q) {
                parent[p] = q;
                count--;
            }
        }
        return count;
    }

    private int findParent(int[] parent, int i) {
        while (i != parent[i]) {
            i = parent[i];
        }
        return i;
    }

    // Union Find with Rank.
    public int countComponents(int n, int[][] edges) {
        // union find
        UnionFind uf = new UnionFind(n);
        for (int[] e : edges) {
            uf.union(e[0], e[1]);
        }

        // return
        return uf.getCount();
    }

    class UnionFind {
        int[] parent;
        int[] rank;
        int count;

        UnionFind(int n) {
            this.count = n;
            this.parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
            this.rank = new int[n];
        }

        public boolean union(int i, int j) {
            int pi = find(i);
            int pj = find(j);

            if (pi == pj)
                return false;

            if (rank[pi] > rank[pj])
                parent[pj] = pi;
            else if (rank[pi] < rank[pj])
                parent[pi] = pj;
            else {
                parent[pj] = pi;
                rank[pi]++;
            }

            count--;
            return true;
        }

        private int find(int i) {
            if (parent[i] != i) {
                parent[i] = find(parent[i]);
            }
            return parent[i];
        }

        private int getCount() {
            return this.count;
        }
    }

}