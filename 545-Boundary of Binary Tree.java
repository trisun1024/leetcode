import extensions.TreeNode;
import java.util.*;

class BoundaryOfBinaryTree {

    // Get Part By Part.
    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        res.add(root.val);
        getLeft(root.left, res);
        getLeaves(root.left, res);
        getLeaves(root.right, res);
        getRight(root.right, res);
        return res;
    }

    private void getLeft(TreeNode node, List<Integer> res) {
        if (node == null || node.left == null && node.right == null) {
            return;
        }
        res.add(node.val);
        if (node.left != null) {
            getLeft(node.left, res);
        } else {
            getLeft(node.right, res);
        }
    }

    private void getRight(TreeNode node, List<Integer> res) {
        if (node == null || node.left == null && node.right == null) {
            return;
        }
        if (node.right != null) {
            getRight(node.right, res);
        } else {
            getRight(node.left, res);
        }
        res.add(node.val);
    }

    private void getLeaves(TreeNode node, List<Integer> res) {
        if (node == null) {
            return;
        }
        if (node.left == null && node.right == null) {
            res.add(node.val);
        }
        if (node.left != null) {
            getLeaves(node.left, res);
        }
        if (node.right != null) {
            getLeaves(node.right, res);
        }
    }

    // Preorder Traversal.
    public List<Integer> boundaryOfBinaryTreeI(TreeNode root) {
        List<Integer> left = new LinkedList<>(), right = new LinkedList<>(), leaves = new LinkedList<>();
        preorder(root, left, right, leaves, 0);
        left.addAll(leaves);
        left.addAll(right);
        return left;
    }

    private void preorder(TreeNode cur, List<Integer> left, List<Integer> right, List<Integer> leaves, int flag) {
        if (cur == null) {
            return;
        }
        if (isRightBoundary(flag)) {
            right.add(0, cur.val);
        } else if (isLeftBoundary(flag) || isRoot(flag)) {
            left.add(cur.val);
        } else if (isLeaf(cur)) {
            leaves.add(cur.val);
        }
        preorder(cur.left, left, right, leaves, leftChildFlag(cur, flag));
        preorder(cur.right, left, right, leaves, rightChildFlag(cur, flag));
    }

    private boolean isLeaf(TreeNode cur) {
        return (cur.left == null && cur.right == null);
    }

    private boolean isRightBoundary(int flag) {
        return (flag == 2);
    }

    private boolean isLeftBoundary(int flag) {
        return (flag == 1);
    }

    private boolean isRoot(int flag) {
        return (flag == 0);
    }

    private int leftChildFlag(TreeNode cur, int flag) {
        if (isLeftBoundary(flag) || isRoot(flag))
            return 1;
        else if (isRightBoundary(flag) && cur.right == null)
            return 2;
        else
            return 3;
    }

    private int rightChildFlag(TreeNode cur, int flag) {
        if (isRightBoundary(flag) || isRoot(flag))
            return 2;
        else if (isLeftBoundary(flag) && cur.left == null)
            return 1;
        else
            return 3;
    }

}