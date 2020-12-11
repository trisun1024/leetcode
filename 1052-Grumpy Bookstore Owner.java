
class GrumpyBookstoreOwner {

    // Sliding Window.
    public int maxSatisfied(int[] customers, int[] grumpy, int X) {
        int i = 0;
        int j = 0;
        int globalMax = Integer.MIN_VALUE;
        int curGrumpyMax = 0;
        int curNonGrumpyMax = 0;
        while (j < customers.length) {
            if (grumpy[j] == 0) {
                curNonGrumpyMax += customers[j];
            } else {
                curGrumpyMax += customers[j];
            }
            if (j - i + 1 < X) {
                j++;
            } else if (j - i + 1 == X) {
                globalMax = Math.max(globalMax, curGrumpyMax);
                if (grumpy[i] == 1) {
                    curGrumpyMax -= customers[i];
                }
                i++;
                j++;
            }
        }
        return globalMax + curNonGrumpyMax;
    }

    // Linear Scan.
    public int maxSatisfiedI(int[] customers, int[] grumpy, int X) {
        int len = customers.length;
        int res = 0;
        for (int i = 0; i < len; i++) {
            res += ((grumpy[i] == 1) ? 0 : customers[i]);
        }
        for (int i = 0; i < X; i++) {
            res += grumpy[i] == 1 ? customers[i] : 0;
        }
        int temp = res;
        for (int i = 1; i + X - 1 < len; i++) {
            if (grumpy[i - 1] == 1) {
                temp -= customers[i - 1];
            }
            if (grumpy[i + X - 1] == 1) {
                temp += customers[i + X - 1];
                res = Math.max(res, temp);
            }
        }
        return res;
    }
}