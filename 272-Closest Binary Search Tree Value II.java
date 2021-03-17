import extensions.*;
import java.util.*;

class ClosestBSTValueII {

    // Inorder Traverse + Iteration (Stack). Time = O(N*log(K)); Space = O(K);
    public List<Integer> closestKValuesII(TreeNode root, double target, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root;
        while (!stack.isEmpty() || cur != null) {
            while (cur != null) {
                stack.offerFirst(cur);
                cur = cur.left;
            }
            cur = stack.pollFirst();
            if (minHeap.size() < k) {
                minHeap.offer(cur.val);
            } else {
                if (Math.abs(minHeap.peek() - target) > Math.abs(cur.val - target)) {
                    minHeap.poll();
                    minHeap.offer(cur.val);
                } else {
                    break;
                }
            }
            cur = cur.right;
        }
        return new ArrayList<>(minHeap);
    }

    // Inorder Traverse + Recursion. Time = O(N*log(K)); Space = O(K+H);
    public List<Integer> closestKValuesI(TreeNode root, double target, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>(
                (o1, o2) -> Math.abs(o1 - target) > Math.abs(o2 - target) ? -1 : 1);
        inorder(root, target, k, heap);
        return new ArrayList<>(heap);
    }

    private void inorder(TreeNode root, double target, int k, PriorityQueue<Integer> heap) {
        if (root == null) {
            return;
        }
        inorder(root.left, target, k, heap);
        heap.add(root.val);
        if (heap.size() > k) {
            heap.remove();
        }
        inorder(root.right, target, k, heap);
    }

    // Quick Select. Time = O(N) Worst = O(N^2); Space = O(N);
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        List<Integer> nums = new ArrayList<>();
        inorder(root, nums);
        quickselect(nums, 0, nums.size() - 1, k, target);
        return nums.subList(0, k);
    }

    private void inorder(TreeNode root, List<Integer> nums) {
        if (root == null) {
            return;
        }
        inorder(root.left, nums);
        nums.add(root.val);
        inorder(root.right, nums);
    }

    private void quickselect(List<Integer> nums, int left, int right, int kSmallest, double target) {
        /*
         * Sort a list within left..right till kth less close element takes its place.
         */

        // base case: the list contains only one element
        if (left >= right)
            return;

        // select a random pivot_index
        Random randomNum = new Random();
        int pivotIndex = left + randomNum.nextInt(right - left);

        // find the pivot position in a sorted list
        pivotIndex = partition(nums, left, right, pivotIndex, target);

        // if the pivot is in its final sorted position
        if (kSmallest == pivotIndex) {
            return;
        } else if (kSmallest < pivotIndex) {
            // go left
            quickselect(nums, left, pivotIndex - 1, kSmallest, target);
        } else {
            // go right
            quickselect(nums, pivotIndex + 1, right, kSmallest, target);
        }
    }

    private int partition(List<Integer> nums, int left, int right, int pivotIndex, double target) {
        double pivotDist = dist(nums, target, pivotIndex);
        // 1. move pivot to end
        swap(nums, pivotIndex, right);
        int storeIndex = left;
        // 2. move more close elements to the left
        for (int i = left; i <= right; i++) {
            if (dist(nums, target, i) < pivotDist) {
                swap(nums, storeIndex, i);
                storeIndex++;
            }
        }
        // 3. move pivot to its final place
        swap(nums, storeIndex, right);
        return storeIndex;
    }

    private void swap(List<Integer> nums, int a, int b) {
        int tmp = nums.get(a);
        nums.set(a, nums.get(b));
        nums.set(b, tmp);
    }

    private double dist(List<Integer> nums, double target, int idx) {
        return Math.abs(nums.get(idx) - target);
    }

}
