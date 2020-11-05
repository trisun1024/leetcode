import extensions.TreeNode;
import java.util.*;

class AverageOfLevelsInBinaryTree {

    // Level Order BFS. Time = O(N);
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            double sum = 0;
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                sum += cur.val;
                if (cur.left != null)
                    queue.offer(cur.left);
                if (cur.right != null)
                    queue.offer(cur.right);
            }
            res.add(sum / size);
        }
        return res;
    }

    // DFS.
    public List<Double> averageOfLevelsI(TreeNode root) {
        List<Node> temp = new ArrayList<>();
        helper(root, temp, 0);
        List<Double> result = new LinkedList<>();
        for (int i = 0; i < temp.size(); i++) {
            result.add(temp.get(i).sum / temp.get(i).count);
        }
        return result;
    }

    public void helper(TreeNode root, List<Node> temp, int level) {
        if (root == null)
            return;
        if (level == temp.size()) {
            Node node = new Node((double) root.val, 1);
            temp.add(node);
        } else {
            temp.get(level).sum += root.val;
            temp.get(level).count++;
        }
        helper(root.left, temp, level + 1);
        helper(root.right, temp, level + 1);
    }

    class Node {
        double sum;
        int count;

        Node(double d, int c) {
            sum = d;
            count = c;
        }
    }
}
