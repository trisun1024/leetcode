import extensions.TreeNode;
import java.util.*;

class FindNearestRightNodeInBinaryTree {

    // BFS.
    public TreeNode findNearestRightNode(TreeNode root, TreeNode u) {
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = 0;
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                if (cur == u) {
                    return i == size - 1 ? null : queue.poll();
                }
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
        }
        return null;
    }

    // DFS.
    TreeNode res = null;
    int level = -1;
    int target;

    public TreeNode findNearestRightNodeI(TreeNode root, TreeNode u) {
        target = u.val;
        helper(root, 0);
        return res;
    }

    private void helper(TreeNode root, int l) {
        if (root == null || res != null)
            return;

        if (root.val == target) {
            level = l;
            return;
        }

        if (level != -1 && level == l) {
            res = root;
            return;
        }

        helper(root.left, l + 1);
        helper(root.right, l + 1);
    }
}