import java.util.*;

/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode
 * left; TreeNode right; TreeNode(int x) { val = x; } }
 */
class Solution {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public List<TreeNode> generateTrees(int n) {
        return n == 0 ? new LinkedList<TreeNode>() : generateNTrees(1, n);
    }

    private List<TreeNode> generateNTrees(int start, int end) {
        List<TreeNode> allTrees = new LinkedList<>();
        if (start > end) {
            allTrees.add(null);
            return allTrees;
        }

        // generate from start to end
        for (int i = start; i <= end; i++) {
            List<TreeNode> leftTrees = generateNTrees(start, i - 1);
            List<TreeNode> rightTrees = generateNTrees(i + 1, end);

            for (TreeNode l : leftTrees) {
                for (TreeNode r : rightTrees) {
                    TreeNode curTree = new TreeNode(i);
                    curTree.left = l;
                    curTree.right = r;
                    allTrees.add(curTree);
                }
            }
        }
        return allTrees;
    }
}