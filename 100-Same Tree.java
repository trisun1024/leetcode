/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode
 * left; TreeNode right; TreeNode(int x) { val = x; } }
 */
class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null)
            return true;
        if (p == null || q == null)
            return false;
        if (p.val != q.val)
            return false;
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
/* 
class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        return (p==null || q==null) ? (p==null && q==null) : (p.val==q.val) ? isSameTree(p.left, q.left) && isSameTree(p.right, q.right) : false;
    }
}
*/

/* 
Check p | q == null, if p & q ==null then true;
else check p.val == q.val, if true, then check left or right; if false; return false;
*/