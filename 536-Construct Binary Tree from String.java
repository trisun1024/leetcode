import java.util.*;

import extensions.TreeNode;

/*
* Example:
* Input: s = "4(2(3)(1))(6(5))"
* Output: [4,2,6,3,1,5]
*/

class ConstructBinaryTreeFromString {

    // Recursion.
    public TreeNode str2tree(String s) {
        if (s.length() == 0) {
            return null;
        }
        // read the first numerics value for root node
        int i = 0;
        for (; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == ')') {
                break;
            }
        }
        TreeNode root = new TreeNode(Integer.parseInt(s.substring(0, i)));
        if (i == s.length()) {
            return root;
        }
        // find the parenthesis pair for left substree
        int start = i, count = 0;
        for (; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                count++;
            } else if (c == ')') {
                count--;
            }
            if (count == 0) {
                break;
            }
        }
        // recursively constrcut substree
        root.left = str2tree(s.substring(start + 1, i));
        root.right = str2tree(s.substring(Math.min(s.length() - 1, i + 2), s.length() - 1));
        return root;
    }

    // Iteration.
    public TreeNode str2treeI(String s) {
        if (s.length() == 0) {
            return null;
        }
        // Stack
        Deque<TreeNode> stack = new ArrayDeque<>();
        int sign = 1;
        int i = 0;
        while (i < s.length()) {
            char c = s.charAt(i);
            if (c == ')') {
                TreeNode child = stack.pollFirst();
                TreeNode parent = stack.peekFirst();
                if (parent.left == null) {
                    parent.left = child;
                } else {
                    parent.right = child;
                }
                i++;
            } else if (c == '-') {
                sign = -1;
                i++;
            } else if (c == '(') {
                i++;
            } else {
                int num = 0;
                while (i < s.length() && s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                    num = num * 10 + s.charAt(i) - '0';
                    i++;
                }
                num *= sign;
                sign = 1;
                stack.offerFirst(new TreeNode(num));
            }

        }
        return stack.isEmpty() ? null : stack.peekFirst();
    }
}