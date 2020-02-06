class Solution {
    public int partitionDisjoint(int[] A) {
        int len = A.length;
        int[] maxL = new int[len];
        int[] minR = new int[len];
        // find max
        int max = A[0];
        for (int i = 0; i < len; i++) {
            max = Math.max(max, A[i]);
            maxL[i] = max;
        }
        // find min
        int min = A[len - 1];
        for (int i = len - 1; i >= 0; i--) {
            min = Math.min(min, A[i]);
            minR[i] = min;
        }

        for (int i = 1; i < len; i++) {
            if (maxL[i - 1] <= minR[i]) {
                return i;
            }
        }
        return -1;
    }
}