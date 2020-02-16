import java.util.*;

class Solution {

    // sort by length, hashset store parent folder
    // Time O(N*(log(N)+M^2)) Space O(N*M)
    public List<String> removeSubfolders(String[] folder) {
        Arrays.sort(folder, Comparator.comparing(s -> s.length()));
        Set<String> seen = new HashSet<>();
        outer: for (String f : folder) {
            for (int i = 2; i < f.length(); ++i) {
                if (f.charAt(i) == '/' && seen.contains(f.substring(0, i))) {
                    continue outer;
                }
            }
            seen.add(f);
        }
        return new ArrayList<>(seen);
    }

    // sort folders
    // Time O(M*N*log(N)) Space O(1)
    public List<String> removeSubfoldersII(String[] folder) {
        Arrays.sort(folder);
        List<String> res = new ArrayList<>();
        for (String s : folder) {
            if (res.isEmpty() || !s.startsWith(res.get(res.size() - 1) + '/')) {
                res.add(s);
            }
        }
        return res;
    }

    // Trie
    public List<String> removeSubfoldersIII(String[] folder) {
        TrieNode root = buildTrie(folder);
        List<String> res = new ArrayList<>();
        return bfs(root, folder, res);
    }

    private TrieNode buildTrie(String[] folder) {
        TrieNode root = new TrieNode();
        for (int i = 0; i < folder.length; i++) {
            TrieNode cur = root;
            for (char c : folder[i].toCharArray()) {
                int index = c == '/' ? 26 : c - 'a';
                if (cur.children[index] == null) {
                    cur.children[index] = new TrieNode();
                }
                cur = cur.children[index];
            }
            cur.index = i;
        }
        return root;
    }

    private List<String> bfs(TrieNode root, String[] folder, List<String> res) {
        Queue<TrieNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TrieNode cur = queue.poll();
            if (cur.index >= 0) {
                res.add(folder[cur.index]);
            }
            for (int i = 0; i < 27; i++) {
                if (cur.children[i] != null && !(i == 26 && cur.index >= 0)) {
                    queue.offer(cur.children[i]);
                }
            }
        }
        return res;
    }

    static class TrieNode {
        TrieNode[] children;
        int index;

        TrieNode() {
            children = new TrieNode[27];
            index = -1;
        }
    }
    
}