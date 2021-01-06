import extensions.TreeNode;

class ClosestBSTValue {
    
    // Recursive
    public int closestValue(TreeNode root, double target) {
        int a = root.val;
        if (a == target)
            return a;
        TreeNode kid = a < target ? root.right : root.left;
        if (kid == null)
            return a;
        int b = closestValue(kid, target);
        return Math.abs(a - target) < Math.abs(b - target) ? a : b;
    }

    // Iterative
    public int closestValueII(TreeNode root, double target) {
        int ret = root.val;
        while (root != null) {
            if (Math.abs(target - root.val) < Math.abs(target - ret)) {
                ret = root.val;
            }
            root = root.val > target ? root.left : root.right;
        }
        return ret;
    }

    // Binary Search
    public int closestValueIII(TreeNode root, double target) {
        int val = root.val;
        int closest = root.val;
        while (root != null) {
            val = root.val;
            closest = Math.abs(val - target) < Math.abs(closest - target) ? val : closest;
            root = target < root.val ? root.left : root.right;
        }
        return closest;
    }
}
