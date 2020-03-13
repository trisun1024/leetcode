import java.util.*;

class Solution {
    // topological sort DFS
    // Time O(N) Space O(N)
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] inDegrees = new int[numCourses];
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] edge : prerequisites) {
            int start = edge[1];
            int end = edge[0];
            inDegrees[end]++;
            if (!graph.containsKey(start)) {
                graph.put(start, new ArrayList<>());
            }
            graph.get(start).add(end);
        }
        Queue<Integer> queue = new LinkedList<>();
        int[] res = new int[numCourses];
        int index = 0;
        for (int i = 0; i < numCourses; i++) {
            if (inDegrees[i] == 0) {
                queue.offer(i);
            }
        }
        while (!queue.isEmpty()) {
            int course = queue.poll();
            if (graph.containsKey(course)) {
                for (int neighbor : graph.get(course)) {
                    if (--inDegrees[neighbor] == 0) {
                        queue.offer(neighbor);
                    }
                }
            }
            res[index++] = course;
        }
        return index == numCourses ? res : new int[0];
    }
}