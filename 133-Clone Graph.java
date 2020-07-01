import java.util.*;

class CloneGraph {
    static class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    // BFS-1
    public Node cloneGraphII(Node node) {
        if (node == null) {
            return null;
        }
        Map<Node, Node> lookup = new HashMap<>();
        Node copy = new Node(node.val);
        lookup.put(node, copy);
        for (Node nei : node.neighbors) {
            if (!lookup.containsKey(nei)) {
                Node copyNei = new Node(nei.val);
                copy.neighbors.add(copyNei);
                lookup.put(nei, copyNei);
            }
        }
        return copy;
    }

    // DFS
    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }
        Map<Node, Node> lookup = new HashMap<>();
        return helper(node, lookup);
    }

    private Node helper(Node node, Map<Node, Node> lookup) {
        if (node == null) {
            return null;
        }
        if (lookup.containsKey(node)) {
            return lookup.get(node);
        }
        Node copy = new Node(node.val);
        lookup.put(node, copy);
        for (Node nei : node.neighbors) {
            copy.neighbors.add(helper(nei, lookup));
        }
        return copy;
    }
}