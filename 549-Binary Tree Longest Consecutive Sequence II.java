import extensions.TreeNode;

class BinaryTreeLongestConsecutiveSequenceII {

    public int longestConsecutive(TreeNode root) {
        int[] longest = new int[] { 0 };
        helper(root, longest);
        return longest[0];
    }

    private int[] helper(TreeNode root, int[] longest) {
        if (root == null) {
            return new int[] { 0, 0 };
        }
        int[] left = helper(root.left, longest);
        int[] right = helper(root.right, longest);
        int inc = 1, des = 1;
        if (root.left != null) {
            if (root.val - root.left.val == 1) {
                des = left[1] + 1;
            } else if (root.val - root.left.val == -1) {
                inc = left[0] + 1;
            }
        }
        if (root.right != null) {
            if (root.val - root.right.val == 1) {
                des = Math.max(des, right[1] + 1);
            } else if (root.val - root.right.val == -1) {
                inc = Math.max(inc, right[0] + 1);
            }
        }
        longest[0] = Math.max(longest[0], inc + des - 1);
        return new int[] { inc, des };
    }
}