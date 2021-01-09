import java.util.*;

class FindRootOfNaryTree {

    static class Node {
        int val;
        List<Node> children;

        Node() {
            this.children = new ArrayList<>();
        }

        Node(int val) {
            this.val = val;
            this.children = new ArrayList<>();
        }

        Node(int val, List<Node> children) {
            this.val = val;
            this.children = children;
        }
    }

    // Déjà-Vu. Time = O(N); Space = O(N);
    public Node findRoot(List<Node> tree) {
        // set that contains all the child nodes.
        Set<Integer> seen = new HashSet<>();
        // add all the child nodes into the set
        for (Node node : tree) {
            for (Node child : node.children) {
                // we could either add the value or the node itself.
                seen.add(child.val);
            }
        }
        // find the node that is not in the child node set.
        Node root = null;
        for (Node node : tree) {
            if (!seen.contains(node.val)) {
                root = node;
                break;
            }
        }
        return root;
    }

    // You Only Look Once. Time = O(N);
    public Node findRootI(List<Node> tree) {
        Integer valueSum = 0;
        for (Node node : tree) {
            // the value is added as a parent node
            valueSum += node.val;
            for (Node child : node.children)
                // the value is deducted as a child node.
                valueSum -= child.val;
        }

        Node root = null;
        // the value of the root node is `valueSum`
        for (Node node : tree) {
            if (node.val == valueSum) {
                root = node;
                break;
            }
        }
        return root;
    }
}