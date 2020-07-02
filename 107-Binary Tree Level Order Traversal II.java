import java.util.*;

class BinaryTreeLevelOrderTraversalII {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // Recursion
    public List<List<Integer>> levelOrderBottomRec(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        helper(root, 0, res);
        Collections.reverse(res);
        return res;
    }

    private void helper(TreeNode root, int level, List<List<Integer>> res) {
        if (root == null) {
            return;
        }
        if (res.size() == level) {
            res.add(new ArrayList<>());
        }
        res.get(level).add(root.val);
        if (root.left != null) {
            helper(root.left, level + 1, res);
        }
        if (root.right != null) {
            helper(root.right, level + 1, res);
        }
    }

    // Iteration
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null)
            return res;
        Queue<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        while (!q.isEmpty()) {
            List<Integer> temp = new ArrayList<>();
            int s = q.size();
            for (int i = 0; i < s; i++) {
                TreeNode head = q.poll();
                temp.add(head.val);
                if (head.left != null) {
                    q.offer(head.left);
                }
                if (head.right != null) {
                    q.offer(head.right);
                }
            }
            // add beginning then is reverse order
            res.add(0, temp);
        }
        return res;
    }
}