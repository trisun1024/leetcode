import java.util.*;

class ShuffleArray {

    private int[] ori;
    private int[] nums;
    private Random rand;

    public ShuffleArray(int[] nums) {
        this.nums = nums;
        this.ori = nums.clone();
        this.rand = new Random();
    }

    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        this.nums = this.ori.clone();
        return this.nums;
    }

    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        for (int i = 0; i < nums.length; i++) {
            int j = randRange(i, nums.length);
            swap(nums, i, j);
        }
        return this.nums;
    }

    private int randRange(int min, int max) {
        return rand.nextInt(max - min) + min;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}

/**
 * Your Solution object will be instantiated and called as such: Solution obj =
 * new Solution(nums); int[] param_1 = obj.reset(); int[] param_2 =
 * obj.shuffle();
 */