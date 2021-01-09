import extensions.TreeNode;

class DeleteNodeInBST {

    // Recursion
    public TreeNode deleteNode(TreeNode root, int key) {
        // base case
        if (root == null) {
            return null;
        }
        if (key < root.val) {
            root.left = deleteNode(root.left, key);
        } else if (key > root.val) {
            root.right = deleteNode(root.right, key);
        } else if (key == root.val) {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            } else if (root.right.left == null) {
                root.right.left = root.left;
                return root.right;
            } else {
                // find the smallest value in right tree, return the smallest
                TreeNode newRoot = deleteSmallest(root.right);
                newRoot.left = root.left;
                newRoot.right = root.right;
                return newRoot;
            }
        }
        return root;
    }

    private TreeNode deleteSmallest(TreeNode root) {
        while (root.left.left != null) {
            root = root.left;
        }
        TreeNode smallest = root.left;
        root.left = root.left.right;
        return smallest;
    }

    // Recursion II.
    public TreeNode deleteNodeII(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        if (root.val > key) {
            root.left = deleteNode(root.left, key);

        } else if (root.val < key) {
            root.right = deleteNode(root.right, key);

        } else {
            // find the largest node in the left, and put it as the root
            if (root.left == null) {
                return root.right;
            }
            if (root.right == null) {
                return root.left;
            }
            TreeNode left = root.left;
            TreeNode cur = left;
            while (cur.right != null) {
                cur = cur.right;
            }
            root.val = cur.val;
            root.left = deleteNode(left, cur.val);

        }
        return root;
    }

    // Iteration
    public TreeNode deleteNodeI(TreeNode root, int key) {
        TreeNode preroot = new TreeNode(0);
        preroot.right = root;

        TreeNode parent = preroot;
        TreeNode curr = root;
        boolean right = true;
        boolean found = false;

        while (!found) {
            if (curr == null) {
                break;
            }

            if (key < curr.val) {
                parent = curr;
                curr = curr.left;
                right = false;
            } else if (key > curr.val) {
                parent = curr;
                curr = curr.right;
                right = true;
            } else {
                if (curr.left == null && curr.right == null) {
                    if (right) {
                        parent.right = null;
                    } else {
                        parent.left = null;
                    }
                } else if (curr.left == null && curr.right != null) {
                    swap(curr, false);
                } else {
                    swap(curr, true);
                }
                found = true;
            }
        }

        return preroot.right;
    }

    private void swap(TreeNode target, boolean left) {
        TreeNode parent = target;
        boolean right = !left;
        TreeNode curr = left ? target.left : target.right;
        while ((left ? curr.right : curr.left) != null) {
            parent = curr;
            curr = left ? curr.right : curr.left;
            right = left;
        }

        target.val = curr.val;
        if (right) {
            parent.right = left ? curr.left : curr.right;
        } else {
            parent.left = left ? curr.left : curr.right;
        }
    }
}