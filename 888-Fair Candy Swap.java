import java.util.*;

class FairCandySwap {
    public int[] fairCandySwap(int[] A, int[] B) {
        int sum1 = 0;
        int sum2 = 0;
        Set<Integer> set2 = new HashSet<>();
        for (int i : A) {
            sum1 += i;
        }
        for (int i : B) {
            sum2 += i;
            set2.add(i);
        }
        int diff = (sum2 - sum1) / 2;
        for (int i : A) {
            if (set2.contains(i + diff)) {
                return new int[] { i, i + diff };
            }
        }
        return new int[] { -1, -1 };
    }
}