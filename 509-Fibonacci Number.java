class FibonacciNumber {

    public int fib(int N) {
        int a = 0;
        if (N == 0) {
            return a;
        }
        int b = 1;
        while (N > 1) {
            int c = a + b;
            a = b;
            b = c;
            N--;
        }
        return b;
    }
}