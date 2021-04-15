class FibonacciNumber {

    // Recursion.
    public int fibI(int N) {
        if (N <= 1) {
            return N;
        }
        return fib(N - 1) + fib(N - 2);
    }

    // Iteration. Time = O(N);
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

    // Matrix Exponentiation. Time = O(log(N));
    public int fibII(int N) {
        if (N <= 1) {
            return N;
        }
        int[][] A = new int[][] { { 1, 1 }, { 1, 0 } };
        matrixPower(A, N - 1);

        return A[0][0];
    }

    void matrixPower(int[][] A, int N) {
        if (N <= 1) {
            return;
        }
        matrixPower(A, N / 2);
        multiply(A, A);

        int[][] B = new int[][] { { 1, 1 }, { 1, 0 } };
        if (N % 2 != 0) {
            multiply(A, B);
        }
    }

    void multiply(int[][] A, int[][] B) {
        int x = A[0][0] * B[0][0] + A[0][1] * B[1][0];
        int y = A[0][0] * B[0][1] + A[0][1] * B[1][1];
        int z = A[1][0] * B[0][0] + A[1][1] * B[1][0];
        int w = A[1][0] * B[0][1] + A[1][1] * B[1][1];

        A[0][0] = x;
        A[0][1] = y;
        A[1][0] = z;
        A[1][1] = w;
    }
}