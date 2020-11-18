import extensions.TreeNode;
import java.util.*;

class BinaryTreeTilt {

    // Recursion. Time = O(N); Space = O(N);
    public int findTilt(TreeNode root) {
        int[] tilt = new int[] { 0 };
        helper(root, tilt);
        return tilt[0];
    }

    private int helper(TreeNode root, int[] tilt) {
        if (root == null) {
            return 0;
        }
        int left = helper(root.left, tilt);
        int right = helper(root.right, tilt);
        tilt[0] += Math.abs(left - right);
        return left + right + root.val;
    }

    // Iteration. Time = O(N); 
    public int findTiltI(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int tilt = 0;
        Deque<TreeNode> stack = new ArrayDeque<>();
        Map<TreeNode, Integer> map = new HashMap<>();
        stack.offerFirst(root);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.peekFirst();
            // if left and right == null or map store the sum.
            if ((cur.left == null || map.containsKey(cur.left)) && (cur.right == null || map.containsKey(cur.right))) {
                cur = stack.pollFirst();
                int left = map.containsKey(cur.left) ? map.get(cur.left) : 0;
                int right = map.containsKey(cur.right) ? map.get(cur.right) : 0;
                // update tilt value
                tilt += Math.abs(left - right);
                // store the current node and sum.
                map.put(cur, left + right + cur.val);
            }
            // input left node
            else if (cur.left != null && !map.containsKey(cur.left)) {
                stack.offerFirst(cur.left);
            }
            // input right node
            else if (cur.right != null && !map.containsKey(cur.right)) {
                stack.offerFirst(cur.right);
            }
        }
        return tilt;
    }
}
