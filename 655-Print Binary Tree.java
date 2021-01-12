import extensions.TreeNode;
import java.util.*;

class PrintBinaryTree {

    // Recursion.
    public List<List<String>> printTree(TreeNode root) {
        List<List<String>> res = new LinkedList<>();
        int width = getWidth(root);
        helper(root, res, width, 0, 0, width - 1);
        return res;
    }

    private int getWidth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(getWidth(root.left), getWidth(root.right)) * 2 + 1;
    }

    private void helper(TreeNode root, List<List<String>> res, int width, int curHeight, int left, int right) {
        if (root == null) {
            return;
        }
        if (res.size() <= curHeight) {
            List<String> temp = new ArrayList<>();
            for (int i = 0; i < width; i++) {
                temp.add("");
            }
            res.add(temp);
        }
        List<String> cur = res.get(curHeight);
        int mid = left + (right - left) / 2;
        cur.set(mid, Integer.toString(root.val));
        helper(root.left, res, width, curHeight + 1, left, mid - 1);
        helper(root.right, res, width, curHeight + 1, mid + 1, right);
    }

    // Iteration.
    public List<List<String>> printTreeI(TreeNode root) {
        List<List<String>> res = new LinkedList<>();
        int width = getWidth(root);
        Queue<TreeNode> queue = new ArrayDeque<>();
        int depth = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                if (res.size() <= depth) {
                    List<String> temp = new ArrayList<>();
                    for (int j = 0; j < width; j++) {
                        temp.add("");
                    }
                    res.add(temp);
                }
            }
            depth++;
        }
        return res;
    }

}