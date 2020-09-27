
class NthDigit {

    /*
     * len: one digit numbers 1 to 9
     * 
     * count: how many numbers in between
     * 
     * start: the range start at what number
     *
     * eg. 1 - 9 inculdes 9 one digits; 10 - 99 includes 90 two digits; 100 - 999
     * includes 900 three digits;
     * 
     */
    public int findNthDigit(int n) {
        int len = 1;
        long count = 9;
        int start = 1;
        while (n > len * count) {
            n -= len * count;
            len += 1;
            count *= 10;
            start *= 10;
        }
        start += (n - 1) / len;
        String s = String.valueOf(start);
        return Character.getNumericValue(s.charAt(n - 1) % len);
    }
}