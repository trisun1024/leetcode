import java.util.*;

class SentenceSimilarityII {

    // DFS. Time = O(N*P), where N is the length, P is length of pairs. Space =
    // O(P);
    public boolean areSentencesSimilarTwoI(String[] words1, String[] words2, List<List<String>> pairs) {
        // base case
        if (words1.length != words2.length) {
            return false;
        }
        // build graph
        Map<String, Set<String>> graph = new HashMap<>();
        for (List<String> p : pairs) {
            graph.putIfAbsent(p.get(0), new HashSet<>());
            graph.putIfAbsent(p.get(1), new HashSet<>());
            graph.get(p.get(0)).add(p.get(1));
            graph.get(p.get(1)).add(p.get(0));
        }
        // traverse all words
        int n = words1.length;
        for (int i = 0; i < n; i++) {
            // words equal then continue
            if (words1[i].equals(words2[i])) {
                continue;
            }
            // graph not contain words
            if (!graph.containsKey(words1[i]) || !graph.containsKey(words2[i])) {
                return false;
            }
            // traverse through graph
            if (!dfs(graph, words1[i], words2[i], new HashSet<>())) {
                return false;
            }
        }
        return true;
    }

    private boolean dfs(Map<String, Set<String>> graph, String start, String end, Set<String> visited) {
        if (graph.get(start).contains(end)) {
            return true;
        }
        if (visited.contains(start)) {
            return false;
        }
        visited.add(start);
        for (String next : graph.get(start)) {
            if (!visited.contains(next) && dfs(graph, next, end, visited)) {
                return true;
            }
        }
        return false;
    }

    // Union-Find. Time = O(N*log(P) + P); Space = O(P);
    public boolean areSentencesSimilarTwo(String[] words1, String[] words2, List<List<String>> pairs) {
        if (words1.length != words2.length)
            return false;

        // save the relationship of child-parent, key is child and value is parent
        Map<String, String> parent = new HashMap<>();

        for (List<String> s : pairs) {
            String p1 = findParent(s.get(0), parent);
            String p2 = findParent(s.get(1), parent);

            // if p1 doesn't equal to p2, we need setup relationship between them.
            // make one as parent of the other. Here I make p2 as parent of p1.
            if (!p1.equals(p2)) {
                parent.put(p1, p2);
            }
        }

        int len = words1.length;

        for (int i = 0; i < len; i++) {
            String p1 = findParent(words1[i], parent);
            String p2 = findParent(words2[i], parent);

            // If no relationship found for p1 and p2, that means they're not similar word.
            if (!p1.equals(p2)) {
                return false;
            }
        }

        return true;
    }

    // Find the very top parent of s. If no parent found for s, return s itself.
    private String findParent(String s, Map<String, String> parent) {
        if (parent.containsKey(s)) {
            return findParent(parent.get(s), parent);
        }
        return s;
    }
}
