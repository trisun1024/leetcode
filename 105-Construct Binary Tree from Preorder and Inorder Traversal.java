/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode
 * left; TreeNode right; TreeNode(int x) { val = x; } }
 */
class Solution {
    int preIndex = 0;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        HashMap<Integer, Integer> inorderMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }
        TreeNode root = constructor(preorder, inorder, 0, inorder.length, inorderMap);
        return root;
    }

    private TreeNode constructor(int[] preorder, int[] inorder, int inLeft, int inRight,
            HashMap<Integer, Integer> inorderMap) {
        if (inLeft == inRight)
            return null;
        int rootVal = preorder[preIndex];
        TreeNode root = new TreeNode(rootVal);
        int index = inorderMap.get(rootVal);
        preIndex++;
        root.left = constructor(preorder, inorder, inLeft, index, inorderMap);
        root.right = constructor(preorder, inorder, index + 1, inRight, inorderMap);
        return root;
    }
}