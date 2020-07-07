import java.util.*;

class ConvertSortedArrayToBST {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // recursion
    public TreeNode sortedArrayToBSTI(int[] nums) {
        if (nums.length == 0)
            return null;
        return builder(0, nums.length - 1, nums);
    }

    private TreeNode builder(int start, int end, int[] nums) {
        if (start > end)
            return null;
        if (start == end)
            return new TreeNode(nums[start]);
        int mid = (start + end) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = builder(start, mid - 1, nums);
        root.right = builder(mid + 1, end, nums);
        return root;
    }

    // Iteration
    static class MyTreeNode {
        TreeNode root;
        int start;
        int end;

        MyTreeNode(TreeNode r, int s, int e) {
            this.root = r;
            this.start = s;
            this.end = e;
        }
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums.length == 0) {
            return null;
        }
        Deque<MyTreeNode> rootStack = new ArrayDeque<>();
        int start = 0;
        int end = nums.length;
        int mid = (start + end) >>> 1;
        TreeNode root = new TreeNode(nums[mid]);
        TreeNode curRoot = root;
        rootStack.push(new MyTreeNode(root, start, end));
        while (end - start > 1 || !rootStack.isEmpty()) {
            // Consider the left subtree
            while (end - start > 1) {
                mid = (start + end) >>> 1; // Current root node
                end = mid;// End of the left subtree
                mid = (start + end) >>> 1;// mid of the left subtree
                curRoot.left = new TreeNode(nums[mid]);
                curRoot = curRoot.left;
                rootStack.push(new MyTreeNode(curRoot, start, end));
            }
            // pop and consider the right subtree
            MyTreeNode myNode = rootStack.pop();
            // start, end and mid of current node
            start = myNode.start;
            end = myNode.end;
            mid = (start + end) >>> 1;
            start = mid + 1; // start of the right subtree
            curRoot = myNode.root; // current root
            if (start < end) {
                mid = (start + end) >>> 1; // mid of the right subtree
                curRoot.right = new TreeNode(nums[mid]);
                curRoot = curRoot.right;
                rootStack.push(new MyTreeNode(curRoot, start, end));
            }

        }

        return root;
    }
}