
class NonNegativeIntegersWithoutConsecutiveOnes {

    // Brute Force DFS.
    public int findIntegersI(int num) {
        return dfs(0, 0, num, false);
    }

    private int dfs(int i, int sum, int num, boolean prev) {
        if (sum > num) {
            return 0;
        }
        if (1 << i > num) {
            return 1;
        }
        if (prev) {
            return dfs(i + 1, sum, num, false);
        }
        return dfs(i + 1, sum, num, false) + dfs(i + 1, sum + (1 << i), num, true);
    }

    // Bit Manipulation.
    public int findIntegers(int num) {
        int[] bits = new int[32];
        bits[0] = 1;
        bits[1] = 2;
        for (int i = 2; i < bits.length; i++) {
            bits[i] = bits[i - 1] + bits[i - 2];
        }
        int i = 30;
        int sum = 0;
        int prevBit = 0;
        while (i >= 0) {
            if ((num & (1 << i)) != 0) {
                sum += bits[i];
                if (prevBit == 1) {
                    sum--;
                    break;
                }
                prevBit = 1;
            } else {
                prevBit = 0;
            }
            i--;
        }
        return sum + 1;
    }
}