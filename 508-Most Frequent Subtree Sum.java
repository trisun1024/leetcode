import extensions.TreeNode;
import java.util.*;

class MostFrequentSubtreeSum {

    public int[] findFrequentTreeSum(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        int[] max = new int[] { 0 };
        Map<Integer, Integer> mapper = new HashMap<>();
        helper(root, mapper, max, res);
        int[] ans = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            ans[i] = res.get(i);
        }
        return ans;
    }

    private int helper(TreeNode root, Map<Integer, Integer> mapper, int[] max, List<Integer> res) {
        if (root == null) {
            return 0;
        }
        int leftSum = helper(root.left, mapper, max, res);
        int rightSum = helper(root.right, mapper, max, res);
        int sum = leftSum + rightSum + root.val;
        mapper.put(sum, mapper.getOrDefault(sum, 0) + 1); 
        if (mapper.get(sum) > max[0]) {
            max[0] = mapper.get(sum);
            res.clear();
            res.add(sum);
        } else if (mapper.get(sum) == max[0]) {
            res.add(sum);
        }
        return sum;
    }

}