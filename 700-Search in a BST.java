import extensions.*;
class SearchInBST {
 

    // recursion 
    public TreeNode searchBST(TreeNode root, int val) {
        if(root==null || root.val == val) {
            return root;
        }
        TreeNode left = searchBST(root.left, val);
        TreeNode right = searchBST(root.right, val);
        return right != null ? right : left;
    }

    // iteration
    public TreeNode searchBSTII(TreeNode root , int val) {
        TreeNode cur = root;
        while(cur!=null) {
            if(cur.val == val) {
                return cur;
            } else if(cur.val < val){
                cur = cur.right;
            } else {
                cur = cur.left;
            }
        }
        return null;
    }
}