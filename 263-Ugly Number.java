class UglyNumber {

    // Count all.
    public boolean isUgly(int num) {
        int[] dividors = { 2, 3, 5 };
        if (num < 1) {
            return false;
        }
        for (int d : dividors) {
            while (num % d == 0) {
                num = num / d;
            }
        }
        return num == 1;
    }
}