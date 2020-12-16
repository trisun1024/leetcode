
import extensions.TreeNode;
import java.util.*;

class SmallestSubtreeWithAlltheDeepestNodes {

    public TreeNode subtreeWithAllDeepestII(TreeNode root) {
        Map<TreeNode, Integer> depth = new HashMap<>();
        depth.put(null, -1);
        dfs(root, null, depth);
        int[] maxDepth = new int[] { -1 };
        for (Integer d : depth.values())
            maxDepth[0] = Math.max(maxDepth[0], d);

        return answer(root, depth, maxDepth);
    }

    public void dfs(TreeNode node, TreeNode parent, Map<TreeNode, Integer> depth) {
        if (node != null) {
            depth.put(node, depth.get(parent) + 1);
            dfs(node.left, node, depth);
            dfs(node.right, node, depth);
        }
    }

    public TreeNode answer(TreeNode node, Map<TreeNode, Integer> depth, int[] maxDepth) {
        if (node == null || depth.get(node) == maxDepth[0])
            return node;
        TreeNode L = answer(node.left, depth, maxDepth), R = answer(node.right, depth, maxDepth);
        if (L != null && R != null)
            return node;
        if (L != null)
            return L;
        if (R != null)
            return R;
        return null;
    }

    // DFS.
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        return dfs(root).node;
    }

    private Result dfs(TreeNode root) {
        if (root == null) {
            return new Result(null, 0);
        }
        Result left = dfs(root.left);
        Result right = dfs(root.right);
        TreeNode node = root;
        int dist = left.dist + 1;
        if (left.dist > right.dist) {
            node = left.node;
        } else if (left.dist < right.dist) {
            node = right.node;
            dist = right.dist + 1;
        }
        return new Result(node, dist);
    }

    static class Result {
        TreeNode node;
        int dist;

        Result(TreeNode node, int dist) {
            this.node = node;
            this.dist = dist;
        }
    }

    // BFS.
    public TreeNode subtreeWithAllDeepestI(TreeNode root) {
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        List<TreeNode> leaves = new ArrayList<>();
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<TreeNode> temp = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                temp.add(cur);
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
            leaves = temp;
        }
        TreeNode res = leaves.get(0);
        for (TreeNode cur : leaves) {
            res = lca(root, cur, res);
        }
        return res;
    }

    private TreeNode lca(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        TreeNode left = lca(root.left, p, q);
        TreeNode right = lca(root.right, p, q);
        if (left != null && right != null) {
            return root;
        }
        return left != null ? left : right;
    }
}