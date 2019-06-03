/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode
 * left; TreeNode right; TreeNode(int x) { val = x; } }
 */
class Solution {
    HashMap<Integer, Integer> inorderMap = new HashMap<>();
    int preIndex;

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        preIndex = postorder.length - 1;
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }
        TreeNode node = constructor(inorder, postorder, 0, inorder.length - 1);
        return node;
    }

    private TreeNode constructor(int[] inorder, int[] postorder, int inLeft, int inRight) {
        if (inLeft > inRight)
            return null;
        int rootVal = postorder[preIndex];
        TreeNode root = new TreeNode(rootVal);
        int index = inorderMap.get(rootVal);
        preIndex--;
        root.right = constructor(inorder, postorder, index + 1, inRight);
        root.left = constructor(inorder, postorder, inLeft, index - 1);
        return root;
    }
}