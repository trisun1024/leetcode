import extensions.TreeNode;
import java.util.*;

class SerializeAndDeserializeBinaryTree {
    class Codec {

        private final String SEP = ",";
        private final String NULL = "null";

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            if (root == null) {
                return "";
            }
            StringBuilder sb = new StringBuilder();
            Queue<TreeNode> queue = new ArrayDeque<>();
            queue.offer(root);
            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();
                if (node != null) {
                    sb.append(node.val);
                    queue.offer(node.left);
                    queue.offer(node.right);
                } else {
                    sb.append(NULL);
                }
                sb.append(SEP);
            }
            return sb.toString();
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            if (data == null || data.length() == 0) {
                return null;
            }
            String[] arr = data.split(",");
            if (arr.length == 0) {
                return null;
            }
            TreeNode root = new TreeNode(Integer.parseInt(arr[0]));
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            int index = 1;
            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    TreeNode cur = queue.poll();
                    for (int j = index; j < index + 2 && j < arr.length; j++) {
                        if (arr[j].equals(NULL)) {
                            if (j % 2 == 1) {
                                cur.left = null;
                            } else {
                                cur.right = null;
                            }
                        } else {
                            TreeNode next = new TreeNode(Integer.parseInt(arr[j]));
                            queue.offer(next);
                            if (j % 2 == 1) {
                                cur.left = next;
                            } else {
                                cur.right = next;
                            }
                        }
                    }
                    index += 2;
                }
            }
            return root;
        }
    }

    // Pre-Order.
    class CodecI {

        private final String SPLITOR = ",";
        private final String NULL = "N";

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            StringBuilder sb = new StringBuilder();
            preorder(root, sb);
            return sb.toString();
        }

        private void preorder(TreeNode root, StringBuilder sb) {
            if (root == null) {
                sb.append(NULL).append(SPLITOR);
                return;
            }
            sb.append(root.val).append(SPLITOR);
            preorder(root.left, sb);
            preorder(root.right, sb);
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            String[] pre = data.split(SPLITOR);
            int[] index = new int[] { 0 };
            return build(pre, index);
        }

        private TreeNode build(String[] preorder, int[] cur) {
            if (cur[0] >= preorder.length) { // this base case is redundent since we garantee each node is complete
                return null;
            } else if (preorder[cur[0]].equals(NULL)) {
                cur[0]++;
                return null;
            }
            TreeNode root = new TreeNode(Integer.valueOf(preorder[cur[0]++]));
            root.left = build(preorder, cur);
            root.right = build(preorder, cur);
            return root;
        }
    }

}