
import extensions.*;

class InorderSuccessorBSTII {

    // iteration to the result
    public Node inorderSuccessor(Node node) {
        Node res = null;
        // case 1. the potential result in the right node
        Node p = node.right;
        while (p != null) {
            res = p;
            p = p.left;
        }
        if (res != null) {
            return res;
        }
        // case 2.
        p = node;
        while (p != null) {
            if (p.parent != null && p.parent.left == p) {
                return p.parent;
            }
            p = p.parent;
        }
        return null;
    }
}