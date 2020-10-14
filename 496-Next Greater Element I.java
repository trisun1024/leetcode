import java.util.*;

class NextGreaterElementI {


    // Stack. Time = O(M+N); Space = O(M+N);
    public int[] nextGreaterElementI(int[] findNums, int[] nums) {
        Deque<Integer> stack = new ArrayDeque<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        int[] res = new int[findNums.length];
        for (int i = 0; i < nums.length; i++) {
            while (!stack.isEmpty() && nums[i] > stack.peekFirst())
                map.put(stack.pollFirst(), nums[i]);
            stack.offerFirst(nums[i]);
        }
        while (!stack.isEmpty())
            map.put(stack.pollFirst(), -1);
        for (int i = 0; i < findNums.length; i++) {
            res[i] = map.get(findNums[i]);
        }
        return res;
    }

    // HashMap
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums2.length; i++) {
            map.put(nums2[i], i);
        }
        for (int i = 0; i < nums1.length; i++) {
            int ori = nums1[i];
            int index = map.get(nums1[i]);
            while (index + 1 < nums2.length) {
                if (nums2[index + 1] > nums1[i]) {
                    nums1[i] = nums2[index + 1];
                    break;
                }
                index++;
            }
            if (ori == nums1[i]) {
                nums1[i] = -1;
            }
        }
        return nums1;
    }
}