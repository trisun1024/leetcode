import java.util.*;

class AlienDictionary {

    // BFS Khan's Algorithm
    public String alienOrderBFS(String[] words) {
        // build graph
        Map<Character, List<Character>> graph = new HashMap<>();
        Map<Character, Integer> indegrees = new HashMap<>();
        // find all characters in words
        for (String word : words) {
            for (char c : word.toCharArray()) {
                indegrees.put(c, 0);
                graph.put(c, new ArrayList<>());
            }
        }
        // init edges
        for (int i = 0; i < words.length - 1; i++) {
            String w1 = words[i];
            String w2 = words[i + 1];
            // words list are
            if (w1.length() > w2.length() && w1.startsWith(w2)) {
                return "";
            }
            // add edges
            for (int j = 0; j < Math.min(w1.length(), w2.length()); j++) {
                if (w1.charAt(j) != w2.charAt(j)) {
                    graph.get(w1.charAt(j)).add(w2.charAt(j));
                    indegrees.put(w2.charAt(j), indegrees.get(w2.charAt(j)) + 1);
                    break;
                }
            }
        }
        // Khan's algorithm
        StringBuilder sb = new StringBuilder();
        Queue<Character> queue = new ArrayDeque<>();
        for (Character c : indegrees.keySet()) {
            if (indegrees.get(c).equals(0)) {
                queue.add(c);
            }
        }
        while (!queue.isEmpty()) {
            Character c = queue.poll();
            sb.append(c);
            for (Character next : graph.get(c)) {
                indegrees.put(next, indegrees.get(next) - 1);
                if (indegrees.get(next).equals(0)) {
                    queue.add(next);
                }
            }
        }
        if (sb.length() < indegrees.size()) {
            return "";
        }
        return sb.toString();
    }

    // DFS Tajan's Algorithm
    public String alienOrderDFS(String[] words) {
        // build graph
        Map<Character, List<Character>> graph = new HashMap<>();
        Map<Character, Boolean> visited = new HashMap<>();
        for (String word : words) {
            for (char c : word.toCharArray()) {
                graph.put(c, new ArrayList<>());
            }
        }
        // init edges
        for (int i = 0; i < words.length - 1; i++) {
            String w1 = words[i];
            String w2 = words[i + 1];
            if (w1.length() > w2.length() && w1.startsWith(w2)) {
                return "";
            }
            for (int j = 0; j < Math.min(w1.length(), w2.length()); j++) {
                if (w1.charAt(j) != w2.charAt(j)) {
                    graph.get(w2.charAt(j)).add(w1.charAt(j));
                    break;
                }
            }
        }
        // Tarjan
        StringBuilder sb = new StringBuilder();
        for (Character c : graph.keySet()) {
            if (!dfs(graph, visited, c, sb)) {
                return "";
            }
        }
        if (sb.length() != graph.size()) {
            return "";
        }
        return sb.toString();
    }

    private boolean dfs(Map<Character, List<Character>> graph, Map<Character, Boolean> visited, Character c,
            StringBuilder sb) {
        if (visited.containsKey(c)) {
            return visited.get(c);
        }
        visited.put(c, false);
        for (Character next : graph.get(c)) {
            if (!dfs(graph, visited, next, sb)) {
                return false;
            }
        }
        visited.put(c, true);
        sb.append(c);
        return true;
    }

    // DFS use graph matrix
    public String alienOrder(String[] words) {
        final int N = 26;
        // Build Graph and then TopSort
        boolean[][] adj = new boolean[N][N];
        int[] visited = new int[N];
        StringBuilder sb = new StringBuilder();
        Arrays.fill(visited, -1);

        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            for (char c : word.toCharArray())
                visited[c - 'a'] = 0;
            if (i > 0) {
                boolean found = false;
                String a = word, b = words[i - 1];
                int minLength = Math.min(a.length(), b.length());
                for (int j = 0; j < minLength; j++) {
                    char c1 = a.charAt(j), c2 = b.charAt(j);
                    if (c1 != c2) {
                        adj[c1 - 'a'][c2 - 'a'] = true;
                        found = true;
                        break;
                    }
                }
                if (!found && b.length() > a.length())
                    return "";
            }
        }

        for (int i = 0; i < N; i++) {
            if (visited[i] == 0) {
                if (!dfs(adj, visited, sb, i, N))
                    return "";
            }
        }

        return sb.toString();
    }

    boolean dfs(boolean[][] adj, int[] visited, StringBuilder sb, int i, int N) {
        visited[i] = 1;
        for (int j = 0; j < N; j++) {
            if (adj[i][j]) {
                if (visited[j] == 1)
                    return false;
                if (visited[j] == 0) {
                    if (!dfs(adj, visited, sb, j, N))
                        return false;
                }
            }
        }
        visited[i] = 2;
        sb.append((char) (i + 'a'));
        return true;
    }
}