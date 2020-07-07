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

    // 
    public int[] plusOneI(int[] digits) {
        int n = digits.length;
        if (n == 0) {
            return digits;
        }
        digits[n - 1] = digits[n - 1] + 1;
        int rem = 0;
        for (int i = digits.length - 1; i >= 0; i--) {
            int sum = digits[i] + rem;
            rem = sum / 10;
            digits[i] = sum % 10;
        }
        if (rem != 0) {
            int[] arr = new int[n + 1];
            for (int i = 0; i < arr.length; i++) {
                if (i == 0) {
                    arr[i] = rem;
                } else {
                    arr[i] = digits[i - 1];
                }
            }
            return arr;
        }
        return digits;
    }
}