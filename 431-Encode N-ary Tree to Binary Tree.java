import java.util.*;
import extensions.TreeNode;

class EncodeNaryTreetoBinaryTree {

    static class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    // BFS. 
    class Pair<U, V> {
        public U first;
        public V second;

        public Pair(U first, V second) {
            this.first = first;
            this.second = second;
        }
    }

    class Codec {
        // Encodes an n-ary tree to a binary tree.
        public TreeNode encode(Node root) {
            if (root == null) {
                return null;
            }
            TreeNode newRoot = new TreeNode(root.val);
            Pair<TreeNode, Node> head = new Pair<>(newRoot, root);
            Queue<Pair<TreeNode, Node>> queue = new ArrayDeque<>();
            queue.offer(head);
            while(!queue.isEmpty()) {
                Pair<TreeNode, Node> cur = queue.poll();
                TreeNode bNode  = cur.first;
                Node nNode = cur.second;
                // Encoding the children nodes into a list of TreeNode.
                TreeNode prevBNode = null, headBNode = null;
                for (Node nChild : nNode.children) {
                    TreeNode newBNode = new TreeNode(nChild.val);
                    if (prevBNode == null) {
                        headBNode = newBNode;
                    } else {
                        prevBNode.right = newBNode;
                    }
                    prevBNode = newBNode;

                    Pair<TreeNode, Node> nextEntry = new Pair<TreeNode, Node>(newBNode, nChild);
                    queue.offer(nextEntry);
                }

                // Attach the list of children to the left node.
                bNode.left = headBNode;
            }
            return newRoot;
        }

        // Decodes your binary tree to an n-ary tree.
        public Node decode(TreeNode root) {
            if (root == null) {
                return null;
            }
            Node newRoot = new Node(root.val, new ArrayList<Node>());

            // adding the first element to kickoff the loop
            Queue<Pair<Node, TreeNode>> queue = new ArrayDeque<Pair<Node, TreeNode>>();
            Pair<Node, TreeNode> head = new Pair<Node, TreeNode>(newRoot, root);
            queue.offer(head);

            while (queue.size() > 0) {
                Pair<Node, TreeNode> entry = queue.poll();
                Node nNode = entry.first;
                TreeNode bNode = entry.second;

                // Decoding the children list
                TreeNode firstChild = bNode.left;
                TreeNode sibling = firstChild;
                while (sibling != null) {
                    Node nChild = new Node(sibling.val, new ArrayList<Node>());
                    nNode.children.add(nChild);

                    // prepare the decoding the children of the child, by standing in the queue.
                    Pair<Node, TreeNode> nextEntry = new Pair<Node, TreeNode>(nChild, sibling);
                    queue.offer(nextEntry);

                    sibling = sibling.right;
                }
            }

            return newRoot;
        }
    }

    // DFS
    class CodecI {

        // Encodes an n-ary tree to a binary tree.
        public TreeNode encode(Node root) {
            if (root == null) {
                return null;
            }

            TreeNode newRoot = new TreeNode(root.val);

            // Encode the first child of n-ary node to the left node of binary tree.
            if (root.children.size() > 0) {
                Node firstChild = root.children.get(0);
                newRoot.left = this.encode(firstChild);
            }

            // Encoding the rest of the sibling nodes.
            TreeNode sibling = newRoot.left;
            for (int i = 1; i < root.children.size(); ++i) {
                sibling.right = this.encode(root.children.get(i));
                sibling = sibling.right;
            }

            return newRoot;
        }

        // Decodes your binary tree to an n-ary tree.
        public Node decode(TreeNode root) {
            if (root == null) {
                return null;
            }

            Node newRoot = new Node(root.val, new ArrayList<Node>());

            // Decoding all the children nodes
            TreeNode sibling = root.left;
            while (sibling != null) {
                newRoot.children.add(this.decode(sibling));
                sibling = sibling.right;
            }

            return newRoot;
        }
    }

}