/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode
 * left; TreeNode right; TreeNode(int x) { val = x; } }
 */
class ConvertSortedArrayToBST {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums.length == 0)
            return null;
        return builder(0, nums.length - 1, nums);
    }

    private TreeNode builder(int start, int end, int[] nums) {
        if (start > end)
            return null;
        if (start == end)
            return new TreeNode(nums[start]);
        int mid = (start + end) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = builder(start, mid - 1, nums);
        root.right = builder(mid + 1, end, nums);
        return root;
    }
}