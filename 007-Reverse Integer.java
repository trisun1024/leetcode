class ReverseInteger {
    public int reverse(int x) {
        long y = 0;
        boolean flag = x < 0;
        x = Math.abs(x);
        while (x > 0) {
            y = y * 10 + x % 10;
            x = x / 10;
        }
        y = flag ? y *= -1 : y;
        return y > Integer.MAX_VALUE || y < Integer.MIN_VALUE ? 0 : (int) y;
    }

    // early stop version
    public int reverseII(int x) {
        int y = 0;
        while (x != 0) {
            int p = x % 10;
            x = x / 10;
            if (y > Integer.MAX_VALUE / 10) {
                return 0;
            }
            if (y < Integer.MIN_VALUE / 10) {
                return 0;
            }
            y = y * 10 + p;
        }
        return y;
    }
}
