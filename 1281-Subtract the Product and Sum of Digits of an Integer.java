class SubstractProductAndSumOfDigitsOfInteger {
    public int subtractProductAndSum(int n) {
        String s = String.valueOf(n);
        int prod = 1;
        int sum = 0;
        for(char c: s.toCharArray()) {
            int i = c-'0';
            prod *= i;
            sum += i;
        }
        return prod - sum;
    }
}