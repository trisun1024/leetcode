import extensions.TreeNode;
import java.util.*;

class FindModeInBinarySearchTree {

    // Recursion.
    public int[] findModeII(TreeNode root) {
        Map<Integer, Integer> freq = new HashMap<>();
        int[] counts = new int[2]; // 0-max occurred count 1-max occurred numbers
        helper(root, freq, counts);
        int[] res = new int[counts[1]];
        int k = 0;
        for (Integer key : freq.keySet()) {
            if (freq.get(key) == counts[0]) {
                res[k++] = key;
            }
        }
        return res;
    }

    private void helper(TreeNode root, Map<Integer, Integer> freq, int[] counts) {
        if (root == null) {
            return;
        }
        int cur = root.val;
        freq.put(cur, freq.getOrDefault(cur, 0) + 1);
        if (freq.get(cur) == counts[0]) {
            counts[1]++;
        }
        if (freq.get(cur) > counts[0]) {
            counts[0] = freq.get(cur);
            counts[1] = 1;
        }
        helper(root.left, freq, counts);
        helper(root.right, freq, counts);
    }

    // Recursion.
    public int[] findMode(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        int[] counts = new int[] { 1, Integer.MIN_VALUE }; // current counts and maxcounts
        inorder(root, res, counts, null);
        int[] ans = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            ans[i] = res.get(i);
        }
        return ans;
    }

    private void inorder(TreeNode root, List<Integer> list, int[] counts, TreeNode prev) {
        if (root == null) {
            return;
        }
        inorder(root.left, list, counts, prev);
        if (prev != null) {
            if (prev.val == root.val) {
                counts[0]++;
            } else {
                counts[0] = 1;
            }
        }
        if (counts[0] > counts[1]) {
            list.clear();
            counts[1] = counts[0];
            list.add(root.val);
        } else if (counts[0] == counts[1]) {
            list.add(root.val);
        }
        inorder(root.right, list, counts, root);
    }

    // Iteration.
    public int[] findModeI(TreeNode root) {
        if (root == null) {
            return new int[0];
        }
        List<Integer> list = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root;
        TreeNode prev = null;
        int count = 0;
        int maxCount = 0;
        while (!stack.isEmpty() || cur != null) {
            while (cur != null) {
                stack.offerFirst(cur);
                cur = cur.left;
            }
            cur = stack.pollFirst();
            if (prev == null || cur.val != prev.val) {
                count = 1;
            } else {
                count++;
            }
            if (count > maxCount) {
                maxCount = count;
                list.clear();
                list.add(cur.val);
            } else if (count == maxCount) {
                list.add(cur.val);
            }
            prev = cur;
            cur = cur.right;
        }
        // output res from list to array
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }
}