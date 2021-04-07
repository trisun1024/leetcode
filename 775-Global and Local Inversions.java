
class GlobalAndLocalInversions {

    // Minimum Spot.
    public boolean isIdealPermutation(int[] A) {
        int n = A.length;
        int min = n;
        for (int i = n - 1; i >= 2; i--) {
            min = Math.min(min, A[i]);
            if (A[i - 2] > min) {
                return false;
            }
        }
        return true;
    }

    // Linear Scan.
    public boolean isIdealPermutationI(int[] A) {
        for (int i = 0; i < A.length; i++) {
            if (Math.abs(A[i] - i) > 1) {
                return false;
            }
        }
        return true;
    }
}