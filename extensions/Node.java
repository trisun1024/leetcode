package extensions;

public class Node {
    public int val;
    public Node left;
    public Node right;
    public Node parent;

    public Node() {
        
    }

    public Node(int v) {
        this.val = v;
    }

    public Node(int v, Node left, Node right, Node parent) {
        this.val = v;
        this.left = left;
        this.right = right;
        this.parent = parent;
    }
}
