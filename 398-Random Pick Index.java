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

        int[] nums;
        Random rnd;

        public Solution1(int[] nums) {
            this.nums = nums;
            this.rnd = new Random();
        }

        public int pick(int target) {
            int result = -1;
            int count = 0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] != target)
                    continue;
                if (rnd.nextInt(++count) == 0)
                    result = i;
            }

            return result;
        }
    }
}

/**
 * Your Solution object will be instantiated and called as such: Solution obj =
 * new Solution(nums); int param_1 = obj.pick(target);
 */