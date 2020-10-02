import java.util.*;

class MinimumMovesToEqualArrayElements {

    // Sorting. Time = O(N*log(N)); Space = O(1);
    public int minMoves(int[] nums) {
        Arrays.sort(nums);
        int move = 0;
        for (int i = nums.length - 1; i > 0; i--) {
            move += nums[i] - nums[0];
        }
        return move;
    }

    // DP. Time = O(N*log(N)); Space = O(1);
    public int minMovesI(int[] nums) {
        Arrays.sort(nums);
        int moves = 0;
        for (int i = 1; i < nums.length; i++) {
            int diff = (moves + nums[i]) - nums[i - 1];
            nums[i] += moves;
            moves += diff;
        }
        return moves;
    }

    // Math. Time = O(N); Space = O(1);
    public int minMovesII(int[] nums) {
        int moves = 0, min = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            moves += nums[i];
            min = Math.min(min, nums[i]);
        }
        return moves - min * nums.length;
    }
}