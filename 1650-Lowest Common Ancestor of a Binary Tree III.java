
class LowestCommonAncestorOfBinaryTreeIII {

    public Node lowestCommonAncestor(Node p, Node q) {
        int depthOfP = getHeight(p);
        int depthOfQ = getHeight(q);
        if (depthOfP <= depthOfQ) {
            return mergeNode(p, q, depthOfQ - depthOfP);
        } else {
            return mergeNode(q, p, depthOfP - depthOfQ);
        }
    }

    private int getHeight(Node root) {
        int height = 0;
        while (root != null) {
            height++;
            root = root.parent;
        }
        return height;
    }

    private Node mergeNode(Node higher, Node lower, int diff) {
        while (diff > 0) {
            lower = lower.parent;
            diff--;
        }
        while (higher != lower) {
            lower = lower.parent;
            higher = higher.parent;
        }
        return lower;
    }
}

class Node {
    Node parent;
    Node left;
    Node right;
    int val;

    Node(int v) {
        this.val = v;
    }
}