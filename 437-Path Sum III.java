import extensions.*;
import java.util.*;

class PathSumIII {

    // Recursion.
    public int pathSum(TreeNode root, int sum) {
        int[] count = new int[] { 0 };
        // store key-value sum and number of path with this sum.
        Map<Integer, Integer> height = new HashMap<>();
        preorder(root, 0, sum, height, count);
        return count[0];
    }

    private void preorder(TreeNode root, int curSum, int sum, Map<Integer, Integer> height, int[] count) {
        if (root == null) {
            return;
        }
        curSum += root.val;
        if (curSum == sum) {
            count[0]++;
        }
        count[0] += height.getOrDefault(curSum - sum, 0);
        height.put(curSum, height.getOrDefault(curSum, 0) + 1);
        preorder(root.left, curSum, sum, height, count);
        preorder(root.right, curSum, sum, height, count);
        height.put(curSum, height.get(curSum) - 1);
    }
}