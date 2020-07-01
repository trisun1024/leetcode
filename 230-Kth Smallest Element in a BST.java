import java.util.*;

class KthSmallestInBST {

    // inorder traversal to solve. Time = O(N); Space = O(N)
    public int kthSmallest(TreeNode root, int k) {
        List<Integer> nums = new ArrayList<>();
        inorder(root, nums);
        return nums.get(k - 1);
    }

    private void inorder(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        inorder(root.left, res);
        res.add(root.val);
        inorder(root.right, res);
    }

    // inorder traversal iteration. Time = O(Height + K); Space = O(Height + K)
    public int kthSmallestII(TreeNode root, int k) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode helper = root;
        while (!stack.isEmpty() || helper != null) {
            if (helper != null) {
                stack.offerFirst(helper);
                helper = helper.left;
            } else {
                helper = stack.pollFirst();
                if (--k == 0) {
                    return helper.val;
                }
                helper = helper.right;
            }
        }
        return -1;
    }

    //
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
        this.val = val;
    }

}