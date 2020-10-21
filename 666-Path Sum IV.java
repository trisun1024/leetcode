import java.util.*;
import extensions.TreeNode;

class PathSumIV {

    // transform tree
    public int pathSumI(int[] nums) {
        TreeNode root = buildTree(nums);
        int[] ans = new int[] { 0 };
        helper(root, 0, ans);
        return ans[0];
    }

    private void helper(TreeNode root, int sum, int[] ans) {
        if (root == null) {
            return;
        }
        sum += root.val;
        if (root.left == null && root.right == null) {
            ans[0] += sum;
        } else {
            helper(root.left, sum, ans);
            helper(root.right, sum, ans);
        }
    }

    private TreeNode buildTree(int[] nums) {
        TreeNode root = new TreeNode(nums[0] % 10);
        for (int num : nums) {
            if (num == nums[0]) {
                continue;
            }
            int depth = num / 100;
            int pos = num / 10 % 10;
            int val = num % 10;
            pos--;
            TreeNode cur = root;
            for (int d = depth - 2; d >= 0; d--) {
                if (pos < 1 << d) {
                    if (cur.left == null) {
                        cur.left = new TreeNode(val);
                    }
                    cur = cur.left;
                } else {
                    if (cur.right == null) {
                        cur.right = new TreeNode(val);
                    }
                    cur = cur.right;
                }
                pos %= 1 << d;
            }
        }
        return root;
    }

    // Direct DFS.
    public int pathSum(int[] nums) {
        Map<Integer, Integer> values = new HashMap<>();
        for (int num : nums) {
            values.put(num / 10, num % 10);
        }
        int[] ans = new int[] { 0 };
        dfs(nums[0] / 10, 0, values, ans);
        return ans[0];
    }

    public void dfs(int node, int sum, Map<Integer, Integer> values, int[] ans) {
        if (!values.containsKey(node))
            return;
        sum += values.get(node);

        int depth = node / 10, pos = node % 10;
        int left = (depth + 1) * 10 + 2 * pos - 1;
        int right = left + 1;

        if (!values.containsKey(left) && !values.containsKey(right)) {
            ans[0] += sum;
        } else {
            dfs(left, sum, values, ans);
            dfs(right, sum, values, ans);
        }
    }

    // 2D Array.
    public int pathSumII(int[] nums) {
        int[][] nodes = new int[5][8];
        for (int num : nums) {
            int level = num / 100;
            int pos = (num % 100) / 10 - 1;
            int val = num % 10;
            nodes[level][pos] = nodes[level - 1][pos / 2] + val;
        }
        int ans = 0;
        for (int i = 1; i <= 4; i++) {
            for (int j = 0; j < 8; j++) {
                if (i == 4 || i < 4 && j < 4 && nodes[i + 1][j * 2] == 0 && nodes[i + 1][j * 2 + 1] == 0) {
                    ans += nodes[i][j];
                }
            }
        }
        return ans;
    }
}