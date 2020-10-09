import java.util.*;
import java.util.stream.Collectors;

class MatchsticksToSquare {

    // DFS. Time = O(4^N); Space = O(N);
    public boolean makesquareI(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        int len = nums.length;
        int perimeter = 0;
        for (int i = 0; i < len; i++) {
            perimeter += nums[i];
        }
        int squareLen = perimeter / 4;
        if (squareLen * 4 != perimeter) {
            return false;
        }
        List<Integer> arr = Arrays.stream(nums).boxed().collect(Collectors.toList());
        Collections.sort(arr, Collections.reverseOrder());
        return dfs(arr, 0, new int[4], squareLen);
    }

    private boolean dfs(List<Integer> nums, int index, int[] sums, int squareLen) {
        if (index == nums.size()) {
            return sums[0] == sums[1] && sums[1] == sums[2] && sums[2] == sums[3];
        }
        int element = nums.get(index);
        for (int i = 0; i < 4; i++) {
            if (sums[i] + element <= squareLen) {
                sums[i] += element;
                if (dfs(nums, index + 1, sums, squareLen)) {
                    return true;
                }
                sums[i] -= element;
            }
        }
        return false;
    }

    // DP.
    public boolean makesquare(int[] nums) {
        // Empty matchsticks.
        if (nums == null || nums.length == 0) {
            return false;
        }

        // Find the perimeter of the square (if at all possible)
        int L = nums.length;
        int perimeter = 0;
        for (int i = 0; i < L; i++) {
            perimeter += nums[i];
        }
        // Possible side of our square depending on the total sum of all the
        // matchsticks. 
        int possibleSquareSide = perimeter / 4;
        if (possibleSquareSide * 4 != perimeter) {
            return false;
        }
        // The memoization cache to be used during recursion.
        HashMap<Pair<Integer, Integer>, Boolean> memo = new HashMap<Pair<Integer, Integer>, Boolean>();
        ;

        return recurse(nums, possibleSquareSide, (1 << L) - 1, 0, memo);
    }

    // Main DP function.
    public boolean recurse(int[] nums, int possibleSquareSide, Integer mask, Integer sidesDone,
            HashMap<Pair<Integer, Integer>, Boolean> memo) {
        int total = 0;
        int L = nums.length;

        // The memo key for this recursion
        Pair<Integer, Integer> memoKey = new Pair(mask, sidesDone);

        // Find out the sum of matchsticks used till now.
        for (int i = L - 1; i >= 0; i--) {
            if ((mask & (1 << i)) == 0) {
                total += nums[L - 1 - i];
            }
        }

        // If the sum if divisible by our square's side, then we increment our number of
        // complete sides formed variable.
        if (total > 0 && total % possibleSquareSide == 0) {
            sidesDone++;
        }

        // Base case.
        if (sidesDone == 3) {
            return true;
        }

        // Return precomputed results
        if (memo.containsKey(memoKey)) {
            return memo.get(memoKey);
        }

        boolean ans = false;
        int c = total / possibleSquareSide;

        // Remaining vlength in the current partially formed side.
        int rem = possibleSquareSide * (c + 1) - total;

        // Try out all remaining options (that are valid)
        for (int i = L - 1; i >= 0; i--) {
            if (nums[L - 1 - i] <= rem && (mask & (1 << i)) > 0) {
                if (recurse(nums, possibleSquareSide, mask ^ (1 << i), sidesDone, memo)) {
                    ans = true;
                    break;
                }
            }
        }

        // Cache the computed results.
        memo.put(memoKey, ans);
        return ans;
    }

}
