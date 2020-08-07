import extensions.*;
import java.util.*;

class ClosestBSTValueII {

    // Stack O(N)
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        Queue<Integer> list = new LinkedList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root;
        while (!stack.isEmpty() || cur != null) {
            while (cur != null) {
                stack.offerFirst(cur);
                cur = cur.left;
            }
            cur = stack.pollFirst();
            if (list.size() < k) {
                list.offer(cur.val);
            } else {
                if (Math.abs(list.peek() - target) > Math.abs(cur.val - target)) {
                    list.poll();
                    list.offer(cur.val);
                } else {
                    break;
                }
            }
            cur = cur.right;
        }
        return (List<Integer>) list;
    }
}