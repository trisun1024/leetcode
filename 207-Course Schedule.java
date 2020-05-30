import java.util.*;

class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> graph = new HashMap<>();

        for (int i = 0; i < numCourses; i++) {
            graph.put(i, new ArrayList<>());
        }

        for (int[] pre : prerequisites) {
            graph.get(pre[1]).add(pre[0]);
        }

        boolean[] visited = new boolean[numCourses];
        for (int i = 0; i < numCourses; i++) {
            if (!dfs(graph, visited, i)) {
                return false;
            }
        }
        return true;
    }

    private boolean dfs(Map<Integer, List<Integer>> graph, boolean[] visited, int course) {
        if (visited[course]) {
            return false;
        }

        visited[course] = true;
        for (int next : graph.get(course)) {
            if (!dfs(graph, visited, next)) {
                return false;
            }
        }
        visited[course] = false;
        return true;
    }

    // Topological Algorithm
    public boolean canFinishII(int numCourses, int[][] prerequisites) {
        int[] topologicalOrder = new int[numCourses];
        int[] incomingEdges = new int[numCourses];
        for (int i = 0; i < prerequisites.length; i++) {
            incomingEdges[prerequisites[i][0]] += 1;
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < incomingEdges.length; i++) {
            if (incomingEdges[i] == 0) {
                queue.offer(i);
            }
        }
        int index = 0;
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            topologicalOrder[index++] = cur;
            for (int i = 0; i < prerequisites.length; i++) {
                if (prerequisites[i][1] == cur) {
                    if (--incomingEdges[prerequisites[i][0]] == 0) {
                        queue.offer(prerequisites[i][0]);
                    }
                }
            }
        }
        return index == numCourses;
    }
}