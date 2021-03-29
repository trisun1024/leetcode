import extensions.TreeNode;
import java.util.*;

class FlipBinaryTreeToMatchPreorderTraversal {

    // DFS. Time = O(N); Space = O(H);
    public List<Integer> flipMatchVoyage(TreeNode root, int[] voyage) {
        List<Integer> res = new ArrayList<>();
        int[] index = new int[] { 0 };
        dfs(root, index, res, voyage);
        if (!res.isEmpty() && res.get(0) == -1) {
            res.clear();
            res.add(-1);
        }
        return res;
    }

    private void dfs(TreeNode root, int[] index, List<Integer> res, int[] voyage) {
        if (root == null) {
            return;
        }
        if (root.val != voyage[index[0]++]) {
            res.clear();
            res.add(-1);
            return;
        }
        if (index[0] < voyage.length && root.left != null && root.left.val != voyage[index[0]]) {
            res.add(root.val);
            dfs(root.right, index, res, voyage);
            dfs(root.left, index, res, voyage);
        } else {
            dfs(root.left, index, res, voyage);
            dfs(root.right, index, res, voyage);
        }
    }

    // DFS.
    public List<Integer> flipMatchVoyageII(TreeNode root, int[] voyage) {
        List<Integer> res = new ArrayList<>();
        if (!dfs(root, voyage, new int[] { 0 }, res)) {
            res = new ArrayList<>();
            res.add(-1);
        }
        return res;
    }

    private boolean dfs(TreeNode node, int[] v, int[] i, List<Integer> res) {
        if (node == null) {
            return true;
        }
        if (node.val != v[i[0]++]) {
            return false;
        }
        if (node.left != null && node.left.val != v[i[0]]) {
            res.add(node.val);
            return dfs(node.right, v, i, res) && dfs(node.left, v, i, res);
        }
        return dfs(node.left, v, i, res) && dfs(node.right, v, i, res);
    }

    // Stack.
    public List<Integer> flipMatchVoyageI(TreeNode root, int[] voyage) {
        List<Integer> res = new ArrayList<>();
        int i = 0;
        Deque<TreeNode> s = new LinkedList<>();
        s.offerFirst(root);
        while (s.size() > 0) {
            TreeNode node = s.pollFirst();
            if (node == null) {
                continue;
            }
            if (node.val != voyage[i++]) {
                return Arrays.asList(-1);
            }
            if (node.right != null && node.right.val == voyage[i]) {
                if (node.left != null) {
                    res.add(node.val);
                }
                s.offerFirst(node.left);
                s.offerFirst(node.right);
            } else {
                s.offerFirst(node.right);
                s.offerFirst(node.left);
            }
        }
        return res;
    }
}