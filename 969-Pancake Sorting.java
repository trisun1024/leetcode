import java.util.*;

class PancakeSorting {

    // Similar to bubble-sort. Time = O(N^2); Space = O(N);
    public List<Integer> pancakeSort(int[] A) {
        List<Integer> res = new ArrayList<>();
        for (int i = A.length; i > 0; i--) {
            int index = find(A, i);
            if (index == i - 1) {
                continue;
            }
            if (index != 0) {
                res.add(index + 1);
                flip(A, index + 1);
            }
            res.add(i);
            flip(A, i);
        }
        return res;
    }

    private int find(int[] A, int target) {
        for (int i = 0; i < A.length; i++) {
            if (A[i] == target) {
                return i;
            }
        }
        return -1;
    }

    private void flip(int[] sublist, int k) {
        int i = 0;
        while (i < k / 2) {
            int temp = sublist[i];
            sublist[i] = sublist[k - i - 1];
            sublist[k - i - 1] = temp;
            i++;
        }
    }

}