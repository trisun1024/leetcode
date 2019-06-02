/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode
 * left; TreeNode right; TreeNode(int x) { val = x; } }
 */
// Recursive time:O(n) space:O(n)
class Solution1 {
    public boolean isValidBST(TreeNode root) {
        return checker(root, null, null);
    }

    private boolean checker(TreeNode node, Integer lower, Integer upper) {
        if (node == null)
            return true;

        int i = node.val;
        if ((lower != null && i <= lower) || (upper != null && i >= upper))
            return false;

        return checker(node.right, i, upper) && checker(node.left, lower, i);
    }
}

// Iterative time:O(n) space:O(n)
class Solution2 {
    LinkedList<TreeNode> stack = new LinkedList();
    LinkedList<Integer> uppers = new LinkedList(), lowers = new LinkedList();

    public void update(TreeNode root, Integer lower, Integer upper) {
        stack.add(root);
        lowers.add(lower);
        uppers.add(upper);
    }

    public boolean isValidBST(TreeNode root) {
        Integer lower = null, upper = null, val;
        update(root, lower, upper);

        while (!stack.isEmpty()) {
            root = stack.poll();
            lower = lowers.poll();
            upper = uppers.poll();

            if (root == null)
                continue;
            val = root.val;
            if (lower != null && val <= lower)
                return false;
            if (upper != null && val >= upper)
                return false;
            update(root.right, val, upper);
            update(root.left, lower, val);
        }
        return true;
    }
}
// Inorder time:O(n) space:O(n)
class Solution3 {
    public boolean isValidBST(TreeNode root) {
        Stack<TreeNode> stack = new Stack();
        double val = -Double.MAX_VALUE;

        while (!stack.Empty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (root.val <= val)
                return false;
            val = root.val;
            root = root.right;
        }
        return true;
    }
}