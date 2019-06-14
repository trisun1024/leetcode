/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode
 * left; TreeNode right; TreeNode(int x) { val = x; } }
 */
// Brute Force: timeout

// DFS time O(n)
class SolutionDFS {
    public int countNodes(TreeNode root) {

        int leftDepth = leftDepth(root);
        int rightDepth = rightDepth(root);

        if (leftDepth == rightDepth)
            return (1 << leftDepth) - 1;
        else
            return 1 + countNodes(root.left) + countNodes(root.right);

    }

    private int rightDepth(TreeNode root) {
        // TODO Auto-generated method stub
        int dep = 0;
        while (root != null) {
            root = root.right;
            dep++;
        }
        return dep;
    }

    private int leftDepth(TreeNode root) {
        // TODO Auto-generated method stub
        int dep = 0;
        while (root != null) {
            root = root.left;
            dep++;
        }
        return dep;
    }
}

// shortest answer time O(log(n))
// Answer used a idea of complete tree is binary. So only need find out the
// deepest subtrees in left or right, it can reduce search range in half.
class Solution {
    int height(TreeNode root) {
        return root == null ? -1 : 1 + height(root.left);
    }

    public int countNodes(TreeNode root) {
        int h = height(root);
        return h < 0 ? 0
                : height(root.right) == h - 1 ? (1 << h) + countNodes(root.right)
                        : (1 << h - 1) + countNodes(root.left);
    }
}