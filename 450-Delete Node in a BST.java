import extensions.TreeNode;

class DeleteNodeInBST {

    // Recursion
    public TreeNode deleteNode(TreeNode root, int key) {
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

    public TreeNode deleteNodeII(TreeNode root, int key) {
        if (root == null) {
            return root;
        }
        if (key > root.val) {
            root.right = deleteNode(root.right, key);
        } else if (key < root.val) {
            root.left = deleteNode(root.left, key);
        } else {
            if (root.left == null && root.right == null) {
                root = null;
            } else if (root.right != null) {
                root.val = successor(root);
                root.right = deleteNode(root.right, root.val);
            } else {
                root.val = predecessor(root);
                root.left = deleteNode(root.left, root.val);
            }
        }
        return root;
    }

    /*
     * One step right and then always left
     */
    public int successor(TreeNode root) {
        root = root.right;
        while (root.left != null)
            root = root.left;
        return root.val;
    }

    /*
     * One step left and then always right
     */
    public int predecessor(TreeNode root) {
        root = root.left;
        while (root.right != null)
            root = root.right;
        return root.val;
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