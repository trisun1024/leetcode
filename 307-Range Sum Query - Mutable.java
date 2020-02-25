import java.util.*;

// prefix solution 
class NumArray {

    private int[] prefixSum;
    private int[] nums;

    public NumArray(int[] nums) {
        this.nums = nums;
        prefixSum = new int[nums.length + 1];
        for (int i = 1; i <= nums.length; ++i) {
            prefixSum[i] = nums[i - 1] + prefixSum[i - 1];
        }
    }

    public void update(int i, int val) {
        int prevVal = nums[i];
        nums[i] = val;
        for (int j = i + 1; j < prefixSum.length; ++j) {
            prefixSum[j] += val - prevVal;
        }
    }

    public int sumRange(int i, int j) {
        return prefixSum[j + 1] - prefixSum[i];
    }

}

// bit solution
class NumArrayII {
    int[] nums;
    int[] BIT;
    int n;

    public NumArrayII(int[] nums) {
        this.nums = nums;

        n = nums.length;
        BIT = new int[n + 1];
        for (int i = 0; i < n; i++)
            init(i, nums[i]);
    }

    public void init(int i, int val) {
        i++;
        while (i <= n) {
            BIT[i] += val;
            i += (i & -i);
        }
    }

    void update(int i, int val) {
        int diff = val - nums[i];
        nums[i] = val;
        init(i, diff);
    }

    public int getSum(int i) {
        int sum = 0;
        i++;
        while (i > 0) {
            sum += BIT[i];
            i -= (i & -i);
        }
        return sum;
    }

    public int sumRange(int i, int j) {
        return getSum(j) - getSum(i - 1);
    }
}

// Tree
class NumArrayIII {
    SegmentTree segmentTree;

    public NumArrayIII(int[] nums) {
        segmentTree = new SegmentTree(nums);
    }

    public void update(int i, int val) {
        segmentTree.update(segmentTree.root, i, i, val);
    }

    public int sumRange(int i, int j) {
        return segmentTree.rsq(segmentTree.root, i, j);
    }
}

/**
 * Your NumArray object will be instantiated and called as such: NumArray obj =
 * new NumArray(nums); obj.update(i,val); int param_2 = obj.sumRange(i,j);
 */

class SegmentTree {
    class Node {
        int lo, hi;
        Node left, right;
        int sum;

        public Node(int lo, int hi) {
            this.lo = lo;
            this.hi = hi;
            this.left = null;
            this.right = null;
            this.sum = 0;
        }

        public int size() {
            return hi - lo + 1;
        }
    }

    public Node root;
    private int[] array;

    public SegmentTree(int[] array) {
        this.array = Arrays.copyOf(array, array.length);
        root = build(0, array.length - 1);
    }

    private Node build(int lo, int hi) {
        if (lo > hi)
            return null;
        Node node = new Node(lo, hi);
        if (lo == hi) {
            node.sum = array[lo];
            return node;
        }

        int mid = lo + (hi - lo) / 2;
        node.left = build(lo, mid);
        node.right = build(mid + 1, hi);
        node.sum = node.left.sum + node.right.sum;
        return node;
    }

    public void update(Node root, int lo, int hi, int value) {
        if (root == null)
            return;

        if (contains(lo, hi, root.lo, root.hi)) {
            root.sum = root.size() * value;
            update(root.left, lo, hi, value);
            update(root.right, lo, hi, value);
            return;
        }

        if (intersects(lo, hi, root.lo, root.hi)) {
            update(root.left, lo, hi, value);
            update(root.right, lo, hi, value);
            root.sum = root.left.sum + root.right.sum;
        }
    }

    public int rsq(Node root, int lo, int hi) {
        if (root == null)
            return 0;
        if (contains(lo, hi, root.lo, root.hi))
            return root.sum;
        if (intersects(lo, hi, root.lo, root.hi))
            return rsq(root.left, lo, hi) + rsq(root.right, lo, hi);
        return 0;
    }

    private boolean contains(int from1, int to1, int from2, int to2) {
        return from2 >= from1 && to2 <= to1;
    }

    private boolean intersects(int from1, int to1, int from2, int to2) {
        return from1 <= from2 && to1 >= from2 // (.[..)..] or (.[...]..)
                || from1 >= from2 && from1 <= to2; // [.(..]..) or [..(..)..
    }
}