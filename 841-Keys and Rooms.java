import java.util.*;

class KeysAndRooms {

    // Stack.
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int n = rooms.size();
        boolean[] seen = new boolean[n];
        seen[0] = true;
        Deque<Integer> stack = new ArrayDeque<>();
        stack.offerFirst(0);
        while (!stack.isEmpty()) {
            int cur = stack.pollFirst();
            for (int next : rooms.get(cur)) {
                if (!seen[next]) {
                    seen[next] = true;
                    stack.offerFirst(next);
                }
            }
        }
        // check if all seen
        for (boolean v : seen) {
            if (!v) {
                return false;
            }
        }
        return true;
    }

    // DFS.
    public boolean canVisitAllRoomsI(List<List<Integer>> rooms) {
        int n = rooms.size();
        boolean[] visited = new boolean[n];
        return dfs(rooms, 0, visited) == n;
    }

    private int dfs(List<List<Integer>> rooms, int index, boolean[] visited) {
        int res = 1;
        visited[index] = true;
        for (int next : rooms.get(index)) {
            if (!visited[next]) {
                res += dfs(rooms, next, visited);
            }
        }
        return res;
    }

}