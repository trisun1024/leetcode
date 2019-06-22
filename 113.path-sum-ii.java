import java.util.ArrayList;

/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode
 * left; TreeNode right; TreeNode(int x) { val = x; } }
 */
class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> sumList = new ArrayList<>();
        helper(root, sum, res, sumList);
        return res;
    }

    private void helper(TreeNode root, int sum, List<List<Integer>> res, List<Integer> sumList) {
        if(root==null) return ;
        sumList.add(root.val);
        sum -= root.val;
        if(root.left==null && root.right==null) {
            if(sum==0) {
                res.add(new ArrayList(sumList));
            }
        } else {
            if(root.left!=null) {
                helper(root.left, sum, res, sumList);
            }
            if(root.right!=null) {
                helper(root.right, sum, res, sumList);
            }
        } 
        sumList.remove(sumList.size()-1);
    }
}