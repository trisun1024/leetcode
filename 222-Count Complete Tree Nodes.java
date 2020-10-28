import extensions.TreeNode;

class CountCompleteTreeNodes {

    // DFS Linear. Time = O(N);
    public int countNodes(TreeNode root) {
        return root == null ? 0 : 1 + countNodes(root.right) + countNodes(root.left);
    }

    // Binary Search. Time = O(log^2(N))
    public int countNodesII(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int depth = computeDepth(root);
        if (depth == 0) {
            return 1;
        }
        int left = 1;
        int right = (int) Math.pow(2, depth) - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (exists(mid, depth, root)) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return (int) Math.pow(2, depth) - 1 + left;
    }

    private int computeDepth(TreeNode root) {
        int depth = 0;
        while (root.left != null) {
            root = root.left;
            ++depth;
        }
        return depth;
    }

    private boolean exists(int index, int depth, TreeNode root) {
        int left = 0;
        int right = (int) Math.pow(2, depth) - 1;
        for (int i = 0; i < depth; i++) {
            int mid = left + (right - left) / 2;
            if (index <= mid) {
                root = root.left;
                right = mid;
            } else {
                root = root.right;
                left = mid + 1;
            }
        }
        return root != null;
    }
}