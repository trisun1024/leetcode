import java.util.*;

class AccountsMerge {

    // DFS.
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, Set<String>> graph = new HashMap<>(); // <email, email>
        Map<String, String> name = new HashMap<>(); // <email, username>
        for (List<String> acc : accounts) {
            String username = acc.get(0);
            for (int i = 1; i < acc.size(); i++) {
                name.put(acc.get(i), username);
                if (!graph.containsKey(acc.get(i))) {
                    graph.put(acc.get(i), new HashSet<>());
                }
                if (i == 1) {
                    continue;
                }
                graph.get(acc.get(i)).add(acc.get(i - 1));
                graph.get(acc.get(i - 1)).add(acc.get(i));
            }
        }
        // DFS
        Set<String> visited = new HashSet<>();
        List<List<String>> res = new ArrayList<>();
        for (String email : name.keySet()) {
            List<String> list = new ArrayList<>();
            if (!visited.contains(email)) {
                visited.add(email);
                dfs(graph, email, visited, list);
                Collections.sort(list);
                list.add(0, name.get(email));
                res.add(list);
            }
        }
        return res;
    }

    private void dfs(Map<String, Set<String>> graph, String email, Set<String> visited, List<String> list) {
        list.add(email);
        for (String next : graph.get(email)) {
            if (visited.add(next)) {
                dfs(graph, next, visited, list);
            }
        }
    }

    // Union-Find.
    public List<List<String>> accountsMergeI(List<List<String>> accounts) {
        int[] parents = new int[accounts.size()];
        for (int i = 0; i < accounts.size(); i++) {
            parents[i] = i;
        }
        Map<String, Integer> owners = new HashMap<>();
        for (int i = 0; i < accounts.size(); i++) {
            for (int j = 1; j < accounts.get(i).size(); j++) {
                String email = accounts.get(i).get(j);
                if (owners.containsKey(email)) {
                    int person = owners.get(email);
                    int p1 = findParent(parents, i);
                    int p2 = findParent(parents, person);
                    if (p1 != p2) {
                        parents[p2] = p1;
                    }
                } else {
                    owners.put(email, i);
                }
            }
        }

        Map<Integer, TreeSet<String>> users = new HashMap<>();
        for (int i = 0; i < accounts.size(); i++) {
            int parent = findParent(parents, i);
            List<String> emails = accounts.get(i);
            users.putIfAbsent(parent, new TreeSet<String>());
            users.get(parent).addAll(emails.subList(1, emails.size()));
        }

        List<List<String>> res = new ArrayList<List<String>>();
        for (Integer idx : users.keySet()) {
            String name = accounts.get(idx).get(0);
            ArrayList<String> emails = new ArrayList<>(users.get(idx));
            emails.add(0, name);
            res.add(emails);
        }
        return res;
    }

    private int findParent(int[] parents, int idx) {
        while (idx != parents[idx]) {
            parents[idx] = parents[parents[idx]];
            idx = parents[idx];
        }
        return idx;
    }
}