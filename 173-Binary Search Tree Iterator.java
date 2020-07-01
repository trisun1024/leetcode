import java.util.*;

/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode
 * left; TreeNode right; TreeNode() {} TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) { this.val = val; this.left
 * = left; this.right = right; } }
 */
class BSTIterator {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    Deque<TreeNode> stack;
    TreeNode helper;

    public BSTIterator(TreeNode root) {
        stack = new ArrayDeque<>();
        helper = root;
        pushAllLeft();
    }

    private void pushAllLeft() {
        while (helper != null) {
            stack.offerFirst(helper);
            helper = helper.left;
        }
    }

    /** @return the next smallest number */
    public int next() {
        helper = stack.pollFirst();
        int val = helper.val;
        helper = helper.right;
        pushAllLeft();
        return val;
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stack.isEmpty();
    }
}

/**
 * Your BSTIterator object will be instantiated and called as such: BSTIterator
 * obj = new BSTIterator(root); int param_1 = obj.next(); boolean param_2 =
 * obj.hasNext();
 */