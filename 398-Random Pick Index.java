import java.util.*;

class RandomPickIndex {

    class Solution {
        private Map<Integer, List<Integer>> map;
        private Random rand;

        public Solution(int[] nums) {
            this.rand = new Random();
            this.map = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                if (!map.containsKey(nums[i])) {
                    map.put(nums[i], new ArrayList<>());
                }
                map.get(nums[i]).add(i);
            }
        }

        public int pick(int target) {
            List<Integer> list = map.get(target);
            int r = rand.nextInt(list.size());
            return list.get(r);
        }
    }

    // Store array
    class Solution1 {

        private int[] nums;
        private Random rand;

        public Solution1(int[] nums) {
            this.nums = nums;
            this.rand = new Random();
        }

        public int pick(int target) {
            int n = this.nums.length;
            int count = 0;
            int idx = 0;
            for (int i = 0; i < n; i++) {
                if (this.nums[i] == target) {
                    count++;
                    if (rand.nextInt(count) == 0) {
                        idx = i;
                    }
                }
            }
            return idx;
        }
    }
}

/**
 * Your Solution object will be instantiated and called as such: Solution obj =
 * new Solution(nums); int param_1 = obj.pick(target);
 */