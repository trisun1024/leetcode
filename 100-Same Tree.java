import java.util.*;

class SameTree {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // Recursion
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null)
            return true;
        if (p == null || q == null)
            return false;
        if (p.val != q.val)
            return false;
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    // Iteration
    public boolean isSameTreeI(TreeNode p, TreeNode q) {
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(p);
        queue.offer(q);
        while(!queue.isEmpty()) {
            p = queue.poll();
            q = queue.poll();
            if(p == null && q == null) {
                continue;
            }
            if(p==null || q==null || p.val != q.val) {
                return false;
            }
            queue.offer(p.left);
            queue.offer(q.left);
            queue.offer(p.right);
            queue.offer(q.right);
        }
        return true;
    }
}
/*
 * class Solution { public boolean isSameTree(TreeNode p, TreeNode q) { return
 * (p==null || q==null) ? (p==null && q==null) : (p.val==q.val) ?
 * isSameTree(p.left, q.left) && isSameTree(p.right, q.right) : false; } }
 */

/*
 * Check p | q == null, if p & q ==null then true; else check p.val == q.val, if
 * true, then check left or right; if false; return false;
 */