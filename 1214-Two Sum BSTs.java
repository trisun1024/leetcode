import extensions.TreeNode;
import java.util.*;

class TwoSumBSTs {

    // Recursion.
    public boolean twoSumBSTs(TreeNode root1, TreeNode root2, int target) {
        if (root1 == null || root2 == null)
            return false;
        int sum = root1.val + root2.val;
        if (sum == target) {
            return true;
        } else if (sum > target) {
            return twoSumBSTs(root1.left, root2, target) || twoSumBSTs(root1, root2.left, target);
        } else {
            return twoSumBSTs(root1.right, root2, target) || twoSumBSTs(root1, root2.right, target);
        }
    }

    // HashSet.
    public boolean twoSumBSTsI(TreeNode root1, TreeNode root2, int target) {
        if (root1 == null || root2 == null)
            return false;
        Set<Integer> set = new HashSet<>();
        add(root1, set, target);
        return find(root2, set, target);
    }

    private void add(TreeNode root, Set<Integer> set, int target) {
        if (root == null) {
            return;
        }
        set.add(root.val);
        add(root.left, set, target);
        add(root.right, set, target);
    }

    private boolean find(TreeNode root, Set<Integer> set, int target) {
        if (root == null)
            return false;
        if (set.contains(target - root.val))
            return true;
        return find(root.left, set, target) || find(root.right, set, target);
    }

    // Binary Search.
    public boolean twoSumBSTsII(TreeNode root1, TreeNode root2, int target) {
        if (root1 == null) {
            return false;
        }
        if (find(target - root1.val, root2)) {
            return true;
        }
        return twoSumBSTs(root1.left, root2, target) || twoSumBSTs(root1.right, root2, target);
    }

    private boolean find(int val, TreeNode r) {
        if (r == null) {
            return false;
        }
        TreeNode pt = r;
        while (pt != null) {
            if (pt.val > val)
                pt = pt.left;
            else if (pt.val == val)
                return true;
            else
                pt = pt.right;
        }
        return false;
    }
}