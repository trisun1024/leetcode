class Solution {
    public boolean isCousins(TreeNode root, int x, int y) {
        if (root == null) {
            return false;
        }
        int[] levels = new int[] { -1, -1 };
        TreeNode[] parents = new TreeNode[2];
        helper(root, null, 0, x, y, levels, parents);
        return levels[0] == levels[1] && parents[0] != parents[1];
    }

    private void helper(TreeNode root, TreeNode parent, int level, int x, int y, int[] levels, TreeNode[] parents) {
        if (root == null) {
            return;
        }
        if (root.val == x) {
            levels[0] = level;
            parents[0] = parent;
        }
        if (root.val == y) {
            levels[1] = level;
            parents[1] = parent;
        }
        helper(root.left, root, level + 1, x, y, levels, parents);
        helper(root.right, root, level + 1, x, y, levels, parents);
    }
}

class TreeNode {

    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}