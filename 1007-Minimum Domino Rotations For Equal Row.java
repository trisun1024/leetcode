
class MinimumDominoRotationsForEqualRow {

    // Majority Vote. Time = O(N); Space = O(1);
    public int minDominoRotations(int[] A, int[] B) {
        int aMajority = vote(A);
        int bMajority = vote(B);
        int a = count(aMajority, A, B);
        int b = count(bMajority, A, B);
        if (a == -1) {
            return b;
        }
        if (b == -1) {
            return a;
        }
        return Math.min(a, b);
    }

    private int vote(int[] A) {
        int m = A[0];
        int cnt = 0;
        for (int i = 1; i < A.length; i++) {
            if (cnt == 0) {
                m = A[i];
            } else if (A[i] == m) {
                cnt++;
            } else {
                cnt--;
            }
        }
        return m;
    }

    private int count(int m, int[] A, int[] B) {
        int cnt = 0;
        int pair = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] != m && B[i] != m) {
                return -1;
            } else if (A[i] == m && B[i] == m) {
                pair++;
            } else if (A[i] == m) {
                cnt++;
            }
        }
        return Math.min(cnt, A.length - pair - cnt);
    }
}