
class FlippingAnImage {

    // Direct. Time = O(N); Space = O(1);
    public int[][] flipAndInvertImage(int[][] A) {
        int cols = A[0].length;
        for (int[] row : A) {
            for (int i = 0; i < (cols + 1) / 2; i++) {
                int temp = row[i] ^ 1;
                row[i] = row[cols - i - 1] ^ 1;
                row[cols - i - 1] = temp;
            }
        }
        return A;
    }
}