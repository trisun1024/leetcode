import java.util.*;
import extensions.TreeNode;

class LeavesOfBinaryTree {

    // DFS without sorting. Time = O(N); Space = O(N);
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        getHeight(res, root);
        return res;
    }

    private int getHeight(List<List<Integer>> res, TreeNode root) {
        if (root == null) {
            return -1;
        }

        int height = 1 + Math.max(getHeight(res, root.left), getHeight(res, root.right));

        if (height == res.size()) {
            res.add(new ArrayList<>());
        }

        res.get(height).add(root.val);
        root.left = root.right = null;
        return height;
    }
}