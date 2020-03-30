import java.util.*;

class Solution {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, Map<String, Double>> graph = new HashMap<>();
        buildGraph(graph, equations, values);
        double[] res = new double[queries.size()];
        Arrays.fill(res, -1.0);
        for (int i = 0; i < queries.size(); i++) {
            String a = queries.get(i).get(0);
            String b = queries.get(i).get(1);
            if (!graph.containsKey(a) || !graph.containsKey(b)) {
                continue;
            } else {
                dfs(graph, a, b, res, i, new HashSet<>(), 1.0);
            }
        }
        return res;
    }

    private void dfs(Map<String, Map<String, Double>> graph, String a, String b, double[] res, int index,
            Set<String> visited, double cur) {
        if (graph.get(a) == null || graph.get(a).size() == 0) {
            return;
        }
        if (graph.get(a).containsKey(b)) {
            res[index] = graph.get(a).get(b) * cur;
            return;
        }
        for (String next : graph.get(a).keySet()) {
            if (visited.contains(next)) {
                continue;
            }
            dfs(graph, next, b, res, index, visited, cur * graph.get(a).get(next));
        }
    }

    private void buildGraph(Map<String, Map<String, Double>> graph, List<List<String>> equations, double[] values) {
        int index = 0;
        for (List<String> eq : equations) {
            String a = eq.get(0);
            String b = eq.get(1);
            graph.putIfAbsent(a, new HashMap<>());
            graph.putIfAbsent(b, new HashMap<>());
            graph.get(a).put(b, values[index]);
            graph.get(b).put(a, 1.0 / values[index]);
            index++;
            graph.get(a).put(a, 1.0);
            graph.get(b).put(b, 1.0);
        }
    }
}