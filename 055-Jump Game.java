class JumpGame {

    // DP
    public boolean canJump(int[] nums) {
        if (nums.length == 1) {
            return true;
        }
        boolean[] can = new boolean[nums.length];
        for (int i = nums.length - 2; i >= 0; i--) {
            if (i + nums[i] >= nums.length - 1) {
                can[i] = true;
            } else {
                for (int j = nums[i]; j >= 1; j--) {
                    if (can[j + i]) {
                        can[i] = true;
                        break;
                    }
                }
            }
        }
        return can[0];
    }

    // Greedy
    public boolean canJumpII(int[] nums) {
        if (nums.length == 1) {
            return true;
        }
        int cur = 0;
        int next = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > cur) {
                if (cur == next) {
                    return false;
                }
                cur = next;
            }
            next = Math.max(next, i + nums[i]);
        }
        return true;
    }
}