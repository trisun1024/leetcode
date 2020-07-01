class JumpGameII {

    // DP
    public int jump(int[] nums) {
        int len = nums.length;
        int[] M = new int[len];
        M[0] = 0;
        for (int i = 1; i < len; i++) {
            M[i] = -1;
            for (int j = i - 1; j >= 0; j--) {
                if (j + nums[j] >= i && M[j] != -1) {
                    M[i] = M[j] + 1;
                }
            }
        }
        return M[len - 1];
    }

    // Greedy
    public int jumpII(int[] nums) {
        if (nums.length == 1) {
            return 0;
        }
        int jump = 0;
        int cur = 0;
        int next = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > cur) {
                jump++;
                if (cur == next) {
                    return -1;
                }
                cur = next;
            }
            next = Math.max(next, i + nums[i]);
        }
        return jump;
    }
}