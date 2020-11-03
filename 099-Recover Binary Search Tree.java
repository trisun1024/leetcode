import java.util.*;
import extensions.TreeNode;

// Sort an Almost Sorted Array Where Two Elements Are Swapped
class RecoverBinarySearchTree {

    public TreeNode recover(TreeNode root) {
        // sanity check
        if (root == null)
            return null;
        // step 1, find the two nodes
        List<TreeNode> inOrder = new ArrayList<>();
        // do inorder traversal
        inOrderTraversal(root, inOrder);
        // sort this list
        List<TreeNode> sortedList = new ArrayList<>();
        for (TreeNode node : inOrder) {
            sortedList.add(node);
        }
        Collections.sort(sortedList, new Comparator<TreeNode>() {
            @Override
            public int compare(TreeNode node1, TreeNode node2) {
                if (node1.val == node2.val)
                    return 0;
                return node1.val < node2.val ? -1 : 1;
            }
        });
        // find the difference
        TreeNode[] wrongNodes = new TreeNode[2];
        for (int i = 0; i < sortedList.size(); i++) {
            if (inOrder.get(i) != sortedList.get(i)) {
                if (wrongNodes[0] == null) {
                    wrongNodes[0] = sortedList.get(i);
                } else {
                    wrongNodes[1] = sortedList.get(i);
                }
            }
        }
        // step 2, find preNodes
        TreeNode dummyRoot = new TreeNode(0);
        dummyRoot.left = root;
        TreeNode[] preNodes = new TreeNode[2];
        findPreNodes(dummyRoot, root, wrongNodes, preNodes);
        // step 3, swap
        TreeNode[] leftNodes = new TreeNode[2], rightNodes = new TreeNode[2];
        leftNodes[0] = wrongNodes[0].left;
        leftNodes[1] = wrongNodes[1].left;
        rightNodes[0] = wrongNodes[0].right;
        rightNodes[1] = wrongNodes[1].right;
        // disconnect
        wrongNodes[0].left = null;
        wrongNodes[0].right = null;
        wrongNodes[1].left = null;
        wrongNodes[1].right = null;
        if (preNodes[0].left == wrongNodes[0]) {
            preNodes[0].left = wrongNodes[1];
        } else {
            preNodes[0].right = wrongNodes[1];
        }
        wrongNodes[1].left = leftNodes[0];
        wrongNodes[1].right = rightNodes[0];
        if (preNodes[1].left == wrongNodes[1]) {
            preNodes[1].left = wrongNodes[0];
        } else {
            preNodes[1].right = wrongNodes[0];
        }
        wrongNodes[0].left = leftNodes[1];
        wrongNodes[0].right = rightNodes[1];
        return dummyRoot.left;
    }

    private void findPreNodes(TreeNode pre, TreeNode root, TreeNode[] wrongNodes, TreeNode[] preNodes) {
        if (root == null)
            return;
        if (root == wrongNodes[0])
            preNodes[0] = pre;
        if (root == wrongNodes[1])
            preNodes[1] = pre;
        // go left, right
        findPreNodes(root, root.left, wrongNodes, preNodes);
        findPreNodes(root, root.right, wrongNodes, preNodes);
    }

    private void inOrderTraversal(TreeNode root, List<TreeNode> inOrder) {
        if (root == null)
            return;
        // go left
        inOrderTraversal(root.left, inOrder);
        inOrder.add(root);
        inOrderTraversal(root.right, inOrder);
    }

    // Recursion
    TreeNode x = null, y = null, pred = null;

    public void recoverTreeII(TreeNode root) {
        findTwoSwapped(root);
        swap(x, y);
    }

    public void findTwoSwapped(TreeNode root) {
        if (root == null)
            return;
        findTwoSwapped(root.left);
        if (pred != null && root.val < pred.val) {
            y = root;
            if (x == null)
                x = pred;
            else
                return;
        }
        pred = root;
        findTwoSwapped(root.right);
    }

    // Morris Inorder Traversal Time = O(N) Space = O(1)
    public void swap(TreeNode a, TreeNode b) {
        int tmp = a.val;
        a.val = b.val;
        b.val = tmp;
    }

    public void recoverTree(TreeNode root) {
        // predecessor is a Morris predecessor.
        // In the 'loop' cases it could be equal to the node itself predecessor == root.
        // pred is a 'true' predecessor,
        // the previous node in the inorder traversal.
        TreeNode x = null, y = null, pred = null, predecessor = null;

        while (root != null) {
            // If there is a left child
            // then compute the predecessor.
            // If there is no link predecessor.right = root --> set it.
            // If there is a link predecessor.right = root --> break it.
            if (root.left != null) {
                // Predecessor node is one step left
                // and then right till you can.
                predecessor = root.left;
                while (predecessor.right != null && predecessor.right != root)
                    predecessor = predecessor.right;

                // set link predecessor.right = root
                // and go to explore left subtree
                if (predecessor.right == null) {
                    predecessor.right = root;
                    root = root.left;
                }
                // break link predecessor.right = root
                // link is broken : time to change subtree and go right
                else {
                    // check for the swapped nodes
                    if (pred != null && root.val < pred.val) {
                        y = root;
                        if (x == null)
                            x = pred;
                    }
                    pred = root;

                    predecessor.right = null;
                    root = root.right;
                }
            }
            // If there is no left child
            // then just go right.
            else {
                // check for the swapped nodes
                if (pred != null && root.val < pred.val) {
                    y = root;
                    if (x == null)
                        x = pred;
                }
                pred = root;

                root = root.right;
            }
        }
        swap(x, y);
    }
}