class RangeAddition {

    // Brute Force.
    public int[] getModifiedArray(int length, int[][] updates) {
        int[] arr = new int[length];
        for (int[] up : updates) {
            for (int i = up[0]; i <= up[1]; i++) {
                arr[i] = arr[i] + up[2];
            }
        }
        return arr;
    }

    public int[] getModifiedArrayI(int length, int[][] updates) {

        int[] result = new int[length];

        for (int[] range : updates) {
            int from = range[0];
            int to = range[1];
            int val = range[2];

            result[from] += val;
            if (to < length - 1)
                result[to + 1] -= val;
        }

        for (int i = 1; i < length; i++)
            result[i] += result[i - 1];

        return result;
    }
}