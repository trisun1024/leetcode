import java.util.*;

class MinimumHeightTrees {

    // Topologic

    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> res = new ArrayList<>();
        int[] degrees = new int[n];
        if (n == 1) {
            res.add(0);
            return res;
        }
        // build graph
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int[] e : edges) {
            if (!map.containsKey(e[0])) {
                map.put(e[0], new ArrayList<>());
            }
            if (!map.containsKey(e[1])) {
                map.put(e[1], new ArrayList<>());
            }
            map.get(e[0]).add(e[1]);
            map.get(e[1]).add(e[0]);
            degrees[e[0]]++;
            degrees[e[1]]++;
        }
        // traverse
        Queue<Integer> queue = new ArrayDeque<>();
        for(int i = 0; i < n; i++) {
            if(degrees[i]==1) {
                queue.offer(i);
            }
        }
        // BFS
        while(!queue.isEmpty() ) {
            int size = queue.size();
            List<Integer> curList = new ArrayList<>();
            for(int i = 0 ; i < size; i++) {
                int cur = queue.poll();
                curList.add(cur);
                for(int neighbor: map.get(cur)) {
                    if(--degrees[neighbor] == 1) {
                        queue.offer(neighbor);
                    }
                }
                res = curList;
            }
        }
        return res;
    }
}