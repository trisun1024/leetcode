import java.util.*;

/**
 * Definition for a binary tree node. public
 */
class BinaryTreeLevelOrderTraversalII {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null)
            return res;
        Queue<TreeNode> q = new LinkedList<>();
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
            res.add(0, temp);
        }
        return res;
    }
}