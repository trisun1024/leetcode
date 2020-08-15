import java.util.*;

class CountOfSmallerNumbersAfterSelf {

    // brute force
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int count = 0;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] > nums[j]) {
                    count++;
                }
            }
            res.add(count);
        }
        return res;
    }

    public List<Integer> countSmallerI(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if (nums.length == 0) {
            return res;
        }
        res.add(0);

        int len = nums.length;
        int lastNum = nums[len - 1];

        Node root = new Node(lastNum);

        for (int i = len - 2; i >= 0; i--) {
            res.add(insert(root, nums[i]));
        }
        Collections.reverse(res);
        return res;
    }

    public int insert(Node root, int num) {
        Node node = root;
        int rightCounts = 0;
        while (true) {
            if (num > node.val) {
                rightCounts += node.count;
                if (node.right == null) {
                    node.right = new Node(num);
                    break;
                } else {
                    node = node.right;
                }

            } else {
                node.count++;
                if (node.left == null) {
                    node.left = new Node(num);
                    break;
                } else {
                    node = node.left;
                }
            }
        }
        return rightCounts;
    }

    static class Node {
        int val;
        int count;
        Node left;
        Node right;

        Node(int val) {
            this.val = val;
            this.count = 1;
        }
    }
}