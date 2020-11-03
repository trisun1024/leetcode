import java.util.*;
import extensions.TreeNode;

class AddOneRowToTree {

    // Recursion.
    public TreeNode addOneRow(TreeNode root, int v, int d) {
        if (d == 1) {
            TreeNode n = new TreeNode(v);
            n.left = root;
            return n;
        }
        insert(root, 1, v, d);
        return root;
    }

    private void insert(TreeNode root, int depth, int v, int d) {
        if (root == null) {
            return;
        }
        if (depth == d - 1) {
            TreeNode t = root.left;
            root.left = new TreeNode(v);
            root.left.left = t;
            t = root.right;
            root.right = new TreeNode(v);
            root.right.right = t;
        } else {
            insert(root.left, depth + 1, v, d);
            insert(root.right, depth + 1, v, d);
        }
    }

    // Stack.
    class Node {
        Node(TreeNode n, int d) {
            node = n;
            depth = d;
        }

        TreeNode node;
        int depth;
    }

    public TreeNode addOneRowI(TreeNode t, int v, int d) {
        if (d == 1) {
            TreeNode n = new TreeNode(v);
            n.left = t;
            return n;
        }
        Deque<Node> stack = new ArrayDeque<>();
        stack.offerFirst(new Node(t, 1));
        while (!stack.isEmpty()) {
            Node n = stack.pollFirst();
            if (n.node == null)
                continue;
            if (n.depth == d - 1) {
                TreeNode temp = n.node.left;
                n.node.left = new TreeNode(v);
                n.node.left.left = temp;
                temp = n.node.right;
                n.node.right = new TreeNode(v);
                n.node.right.right = temp;

            } else {
                stack.offerFirst(new Node(n.node.left, n.depth + 1));
                stack.offerFirst(new Node(n.node.right, n.depth + 1));
            }
        }
        return t;
    }

    // Queue.
    public TreeNode addOneRowII(TreeNode root, int v, int d) {
        if (d == 1) {
            TreeNode n = new TreeNode(v);
            n.left = root;
            return n;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int depth = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                if (depth == d - 1) {
                    TreeNode temp = cur.left;
                    cur.left = new TreeNode(v);
                    cur.left.left = temp;
                    temp = cur.right;
                    cur.right = new TreeNode(v);
                    cur.right.right = temp;
                }
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
            // early stop
            if (depth == d - 1) {
                return root;
            }
            depth++;
        }
        return root;
    }
}