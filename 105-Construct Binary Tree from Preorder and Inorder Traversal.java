import java.util.*;
import extensions.TreeNode;

class ConstructBinaryTreeFromPreorderAndInorderTraversal {

    // utilizing inorder sequence to determine the size of left or right subtrees
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // inorder node and position mapping
        Map<Integer, Integer> inMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inMap.put(inorder[i], i);
        }
        return construct(preorder, inMap, 0, inorder.length - 1, 0, preorder.length - 1);
    }

    private TreeNode construct(int[] preorder, Map<Integer, Integer> inMap, int inLeft, int inRight, int preLeft,
            int preRight) {
        if (inLeft > inRight) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[preLeft]);
        int inRoot = inMap.get(root.val);
        root.left = construct(preorder, inMap, inLeft, inRoot - 1, preLeft + 1, preLeft + inRoot - inLeft);
        root.right = construct(preorder, inMap, inRoot + 1, inRight, preRight + inRoot - inRight + 1, preRight);
        return root;
    }

    // method 2. traverse and construct the binary tree at the same time
    public TreeNode buildTreeII(int[] preorder, int[] inorder) {
        int[] pre = new int[] { 0 };
        int[] in = new int[] { 0 };
        return helper(preorder, inorder, pre, in, Integer.MAX_VALUE);
    }

    private TreeNode helper(int[] preorder, int[] inorder, int[] pre, int[] in, int target) {
        if (in[0] >= inorder.length || inorder[in[0]] == target) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[pre[0]]);
        pre[0]++;
        root.left = helper(preorder, inorder, pre, in, root.val);
        in[0]++;
        root.right = helper(preorder, inorder, pre, in, target);
        return root;
    }

}