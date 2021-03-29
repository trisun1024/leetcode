import java.util.*;

class ParallelCourses {

    // Graph + BFS. Time = O(E+V);
    public int minimumSemesters(int n, int[][] relations) {
        Map<Integer, List<Integer>> graph = new HashMap<>(); // build graph
        int[] inDegree = new int[n + 1]; // inDegree[i]: number of prerequisites for i.
        for (int[] r : relations) {
            graph.computeIfAbsent(r[0], l -> new ArrayList<>()).add(r[1]); // construct graph.
            ++inDegree[r[1]]; // count prerequisites for r[1].
        }
        Queue<Integer> queue = new ArrayDeque<>(); // save current 0 in-degree vertices.
        for (int i = 1; i <= n; ++i) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }
        int semester = 0;
        while (!queue.isEmpty()) { // BFS traverse all currently 0 in degree vertices.
            for (int size = queue.size(); size > 0; --size) { // sz is the search breadth.
                int cur = queue.poll();
                --n;
                // c's in-degree is currently 0, but it has no prerequisite.
                if (!graph.containsKey(cur)) {
                    continue;
                }
                for (int course : graph.get(cur)) {
                    // decrease the in-degree of course's neighbors.
                    if (--inDegree[course] == 0) {
                        // add current 0 in-degree vertex into Queue.
                        queue.offer(course);
                    }
                }
            }
            ++semester; // need one more semester.
        }
        return n == 0 ? semester : -1;
    }

    // Graph + DFS.
    public int minimumSemestersI(int n, int[][] relations) {
        Map<Integer, List<Integer>> graph = new HashMap<>(); // build graph
        for (int[] r : relations) {
            graph.computeIfAbsent(r[0], l -> new ArrayList<>()).add(r[1]); // construct graph.
        }
        int[] visited = new int[n + 1];
        for (int node = 1; node <= n; node++) {
            if (isCycle(node, graph, visited) == -1) {
                return -1;
            }
        }
        // if no cycle,
        int[] visitedLength = new int[n + 1];
        int maxLength = 1;
        for (int i = 1; i <= n; i++) {
            int len = dfsMaxPath(i, graph, visitedLength);
            maxLength = Math.max(maxLength, len);
        }
        return maxLength;
    }

    private int isCycle(int cur, Map<Integer, List<Integer>> graph, int[] visited) {
        // return -1 if has a cycle
        // return 1 if does not have any cycle
        if (visited[cur] != 0) {
            return visited[cur];
        } else {
            // mark as visiting
            visited[cur] = -1;
        }
        for (int endNode : graph.get(cur)) {
            if (isCycle(endNode, graph, visited) == -1) {
                // we meet a cycle!
                return -1;
            }
        }
        // mark as visited
        visited[cur] = 1;
        return 1;
    }

    private int dfsMaxPath(int node, Map<Integer, List<Integer>> graph, int[] visitedLength) {
        // return the longest path (inclusive)
        if (visitedLength[node] != 0) {
            return visitedLength[node];
        }
        int maxLength = 1;
        for (int endNode : graph.get(node)) {
            int length = dfsMaxPath(endNode, graph, visitedLength);
            maxLength = Math.max(length + 1, maxLength);
        }
        // store it
        visitedLength[node] = maxLength;
        return maxLength;
    }
}