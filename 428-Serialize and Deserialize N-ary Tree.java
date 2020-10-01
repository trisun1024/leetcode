import java.util.*;

class SerializeDeserializeNaryTree {
    class Node {
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

    class Codec {
        // Encodes a tree to a single string.
        // Encodes a tree to a single string.
        public String serialize(Node root) {
            StringBuilder sb = new StringBuilder();
            dfs_serialize(root, sb);
            return sb.toString();
        }

        void dfs_serialize(Node root, StringBuilder sb) {
            if (root == null)
                return;

            // append value; (char) root.val + '0' will convert root.val to a single char no
            // matter how large is the val
            sb.append((char) (root.val + '0'));
            // sb.append((char) root.children.size() + '0');//append children size()
            sb.append((char) (root.children.size() + '0'));

            for (Node n : root.children) {
                dfs_serialize(n, sb);
            }
        }

        // Decodes your encoded data to tree.
        private int mIdx;

        public Node deserialize(String data) {
            if (data == null || data.length() == 0)
                return null;
            mIdx = 0;
            return dfs_deserialize(data, new int[1]);
        }

        Node dfs_deserialize(String data, int[] index) {
            if (index[0] >= data.length())
                return null;

            int node_val = data.charAt(mIdx++) - '0';
            // index[0]++;
            int child_ct = data.charAt(mIdx++) - '0';

            Node root = new Node(node_val, new ArrayList<Node>());
            for (int i = 0; i < child_ct; i++) {
                // index[0]++;
                root.children.add(dfs_deserialize(data, index));
            }
            return root;
        }
    }
}