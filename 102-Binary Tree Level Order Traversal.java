/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode
 * left; TreeNode right; TreeNode(int x) { val = x; } }
 */

// Recursive
class Solution1 {
    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null)
            return res;
        getCurrentLevel(root, 0);
        return res;
    }

    private void getCurrentLevel(TreeNode node, int n) {
        if (res.size() == n)
            res.add(new ArrayList<Integer>());

        res.get(n).add(node.val);
        if (node.left != null)
            getCurrentLevel(node.left, n + 1);
        if (node.right != null)
            getCurrentLevel(node.right, n + 1);
    }
}

// Iterative 
class Solution2 {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> levels = new ArrayList<List<Integer>>();
        if (root == null)
            return levels;

        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        int level = 0;
        while (!queue.isEmpty()) {
            // start the current level
            levels.add(new ArrayList<Integer>());

            // number of elements in the current level
            int level_length = queue.size();
            for (int i = 0; i < level_length; ++i) {
                TreeNode node = queue.remove();

                // fulfill the current level
                levels.get(level).add(node.val);

                // add child nodes of the current level
                // in the queue for the next level
                if (node.left != null)
                    queue.add(node.left);
                if (node.right != null)
                    queue.add(node.right);
            }
            // go to next level
            level++;
        }
        return levels;
    }
}