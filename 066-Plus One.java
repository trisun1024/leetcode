class PlusOne {
    public int[] plusOne(int[] digits) {
        digits[digits.length - 1] = digits[digits.length - 1] + 1;
        return organizer(digits);
    }

    public int[] organizer(int[] in) {
        for (int i = in.length - 1; i >= 0; i--) {
            if (in[0] == 10) {
                int[] res = new int[in.length + 1];
                res[0] = 1;
                for (int m = 1; m < in.length; m++) {
                    res[m] = 0;
                }
                return res;
            } else if (in[i] == 10) {
                in[i - 1] = in[i - 1] + 1;
                in[i] = 0;
            }
        }
        return in;
    }
}