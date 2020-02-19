import java.util.*;

class Solution {

    // transform tree 
    public int pathSum(int[] nums) {
        TreeNode root = buildTree(nums);
        int[] ans = new int[] { 0 };
        dfs(root, 0, ans);
        return ans[0];
    }

    private void dfs(TreeNode root, int sum, int[] ans) {
        if (root == null) {
            return;
        }
        sum += root.val;
        if (root.left == null && root.right == null) {
            ans[0] += sum;
        } else {
            dfs(root.left, sum, ans);
            dfs(root.right, sum, ans);
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

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int v) {
            this.val = v;
        }
    }


    // direct traversal
    int ans = 0;
    Map<Integer, Integer> values;

    public int pathSumII(int[] nums) {
        values = new HashMap();
        for (int num : nums)
            values.put(num / 10, num % 10);

        dfsII(nums[0] / 10, 0);
        return ans;
    }

    public void dfsII(int node, int sum) {
        if (!values.containsKey(node))
            return;
        sum += values.get(node);

        int depth = node / 10, pos = node % 10;
        int left = (depth + 1) * 10 + 2 * pos - 1;
        int right = left + 1;

        if (!values.containsKey(left) && !values.containsKey(right)) {
            ans += sum;
        } else {
            dfs(left, sum);
            dfs(right, sum);
        }
    }
}