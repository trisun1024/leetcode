import java.util.*;

class MinimumGeneticMutation {

    // BFS. Time = O(N * N * M * K)
    public int minMutation(String start, String end, String[] bank) {
        if (start.equals(end)) {
            return 0;
        }
        // Convert bank to HashSet for later fast access
        Set<String> bankSet = new HashSet<>();
        for (String b : bank) {
            bankSet.add(b);
        }
        // all possible char set
        char[] charSet = new char[] { 'A', 'C', 'G', 'T' };
        int level = 0;
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new ArrayDeque<>();
        queue.offer(start);
        visited.add(start);
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size  > 0) {
                String curr = queue.poll();
                if (curr.equals(end)) {
                    return level;
                }
                char[] currArray = curr.toCharArray();
                for (int i = 0; i < currArray.length; i++) {
                    char old = currArray[i];
                    for (char c : charSet) {
                        currArray[i] = c;
                        String next = new String(currArray);
                        if (!visited.contains(next) && bankSet.contains(next)) {
                            visited.add(next);
                            queue.offer(next);
                        }
                    }
                    currArray[i] = old;
                }
                size--;
            }
            level++;
        }
        return -1;
    }

    // DFS
    public int minMutationI(String start, String end, String[] bank) {
        int[] count = new int[] { Integer.MAX_VALUE };
        dfs(start, end, bank, 0, new HashSet<String>(), count);
        return count[0] == Integer.MAX_VALUE ? -1 : count[0];
    }

    private void dfs(String start, String end, String[] bank, int soFar, Set<String> visited, int[] count) {
        if (start.intern() == end.intern()) {
            count[0] = Math.min(count[0], soFar);
        }

        for (String e : bank) {
            int diff = 0;
            for (int i = 0; i < e.length(); i++) {
                if (start.charAt(i) != e.charAt(i)) {
                    diff++;
                    if (diff > 1)
                        break;
                }
            }
            if (diff == 1 && !visited.contains(e)) {
                visited.add(e);
                dfs(e, end, bank, soFar + 1, visited, count);
                visited.remove(e);
            }
        }
    }
}