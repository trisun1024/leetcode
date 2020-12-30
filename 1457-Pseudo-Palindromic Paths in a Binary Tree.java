import extensions.TreeNode;
import java.util.*;

class PseudoPalindromicPathsInBinaryTree {

    // Recursion. Time = O(N); Space = O(H);
    public int pseudoPalindromicPaths(TreeNode root) {
        int[] count = new int[] { 0 };
        helper(root, 0, count);
        return count[0];
    }

    private void helper(TreeNode root, int path, int[] count) {
        if (root == null) {
            return;
        }
        path = path ^ (1 << root.val);
        if (root.left == null && root.right == null) {
            if ((path & (path - 1)) == 0) {
                count[0]++;
            }
        }
        helper(root.left, path, count);
        helper(root.right, path, count);
    }

    // Recursion + HashSet.
    public int pseudoPalindromicPathsII(TreeNode root) {
        int[] count = new int[] { 0 };
        Set<Integer> path = new HashSet<>();
        preorder(root, path, count);
        return count[0];
    }

    private void preorder(TreeNode root, Set<Integer> path, int[] count) {
        if (root == null) {
            return;
        }
        int val = root.val;
        if (path.contains(val)) {
            path.remove(val);
        } else {
            path.add(val);
        }
        if (root.left == null && root.right == null) {
            if (path.size() < 2) {
                count[0]++;
            }
        }
        preorder(root.left, path, count);
        preorder(root.right, path, count);
        if (path.contains(val)) {
            path.remove(val);
        } else {
            path.add(val);
        }
    }

    // Iteration. Time = O(N); Space = O(H);
    public int pseudoPalindromicPathsI(TreeNode root) {
        int count = 0, path = 0;

        Deque<Pair<TreeNode, Integer>> stack = new ArrayDeque<>();
        stack.push(new Pair<>(root, 0));
        while (!stack.isEmpty()) {
            Pair<TreeNode, Integer> p = stack.pop();
            TreeNode node = p.getKey();
            path = p.getValue();

            if (node != null) {
                // compute occurences of each digit
                // in the corresponding register
                path = path ^ (1 << node.val);
                // if it's a leaf check if the path is pseudo-palindromic
                if (node.left == null && node.right == null) {
                    // check if at most one digit has an odd frequency
                    if ((path & (path - 1)) == 0) {
                        ++count;
                    }
                } else {
                    stack.push(new Pair<>(node.right, path));
                    stack.push(new Pair<>(node.left, path));
                }
            }
        }
        return count;
    }
}