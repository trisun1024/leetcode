import java.util.*;

class NRepeatedElementInSize2NArray {

    // Count.
    public int repeatedNTimes(int[] A) {
        Map<Integer, Integer> count = new HashMap<>();
        for (int x : A) {
            count.put(x, count.getOrDefault(x, 0) + 1);
        }

        for (int k : count.keySet()) {
            if (count.get(k) > 1) {
                return k;
            }
        }
        throw null;
    }

    // Compare.
    public int repeatedNTimesI(int[] A) {
        for (int k = 1; k <= 3; ++k) {
            for (int i = 0; i < A.length - k; ++i) {
                if (A[i] == A[i + k]) {
                    return A[i];
                }
            }
        }
        throw null;
    }
}