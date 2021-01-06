import extensions.TreeNode;
import java.util.*;

class AllNodesDistanceKInBinaryTree {

    // BFS. Time = O(N);
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        List<Integer> res = new ArrayList<>();
        // construct parents map
        Map<TreeNode, TreeNode> parents = new HashMap<>();
        dfs(root, parents, null);
        // traversal by Queue
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(null);
        queue.offer(target);
        Set<TreeNode> visited = new HashSet<>();
        visited.add(target);
        visited.add(null);

        int dist = 0;
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (cur == null) {
                if (dist == K) {
                    for (TreeNode next : queue) {
                        res.add(next.val);
                    }
                    return res;
                }
                queue.offer(null);
                dist++;
            } else {
                if (!visited.contains(cur.left)) {
                    visited.add(cur.left);
                    queue.offer(cur.left);
                }
                if (!visited.contains(cur.right)) {
                    visited.add(cur.right);
                    queue.offer(cur.right);
                }
                TreeNode parent = parents.get(cur);
                if (!visited.contains(parent)) {
                    visited.add(parent);
                    queue.offer(parent);
                }
            }
        }
        return res;
    }

    private void dfs(TreeNode root, Map<TreeNode, TreeNode> parents, TreeNode parent) {
        if (root == null) {
            return;
        }
        parents.put(root, parent);
        dfs(root.left, parents, root);
        dfs(root.right, parents, root);
    }
}