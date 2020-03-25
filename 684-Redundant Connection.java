class Solution {

    //
    public int[] findRedundantConnectionII(int[][] edges) {
        UnionFind uf = new UnionFind(edges.length);
        for (int[] edge : edges) {
            int from = edge[0] - 1;
            int to = edge[1] - 1;
            if (uf.find(from, to)) {
                return new int[] { from + 1, to + 1 };
            }
            uf.union(from, to);
        }
        return null;
    }

    static class UnionFind {
        int[] idx;

        UnionFind(int n) {
            this.idx = new int[n];
            for (int i = 0; i < idx.length; i++) {
                idx[i] = i;
            }
        }

        void union(int f, int t) {
            int id = idx[t];
            for (int i = 0; i < idx.length; i++) {
                if (idx[i] == id) {
                    idx[i] = idx[f];
                }
            }
        }

        boolean find(int f, int t) {
            return idx[f] == idx[t];
        }
    }

    // leetcode solution

    int MAX_EDGE_VAL = 1000;

    public int[] findRedundantConnection(int[][] edges) {
        DSU dsu = new DSU(MAX_EDGE_VAL + 1);
        for (int[] edge : edges) {
            if (!dsu.union(edge[0], edge[1]))
                return edge;
        }
        throw new AssertionError();
    }
}

class DSU {
    int[] parent;
    int[] rank;

    public DSU(int size) {
        parent = new int[size];
        for (int i = 0; i < size; i++)
            parent[i] = i;
        rank = new int[size];
    }

    public int find(int x) {
        if (parent[x] != x)
            parent[x] = find(parent[x]);
        return parent[x];
    }

    public boolean union(int x, int y) {
        int xr = find(x), yr = find(y);
        if (xr == yr) {
            return false;
        } else if (rank[xr] < rank[yr]) {
            parent[xr] = yr;
        } else if (rank[xr] > rank[yr]) {
            parent[yr] = xr;
        } else {
            parent[yr] = xr;
            rank[xr]++;
        }
        return true;
    }
}