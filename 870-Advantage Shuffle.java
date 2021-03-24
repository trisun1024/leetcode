import java.util.*;

class AdvantageShuffle {

    // Greedy.
    public int[] advantageCount(int[] A, int[] B) {
        // sort the array A and B
        int[] sortedA = A.clone();
        Arrays.sort(sortedA);
        int[] sortedB = B.clone();
        Arrays.sort(sortedB);

        // assigned[b] = list of a that are assigned to beat b
        Map<Integer, Deque<Integer>> assigned = new HashMap<>();
        for (int b : B) {
            assigned.put(b, new LinkedList<>());
        }

        // remaining = list of a that are not assigned to any b
        Deque<Integer> remaining = new LinkedList<>();

        // populate (assigned, remaining) appropriately
        // sortedB[j] is always the smallest unassigned element in B
        int j = 0;
        for (int a : sortedA) {
            if (a > sortedB[j]) {
                assigned.get(sortedB[j++]).add(a);
            } else {
                remaining.add(a);
            }
        }

        // Reconstruct the answer from annotations (assigned, remaining)
        int[] ans = new int[B.length];
        for (int i = 0; i < B.length; ++i) {
            // if there is some a assigned 1470to b...
            if (assigned.get(B[i]).size() > 0) {
                ans[i] = assigned.get(B[i]).pop();
            } else {
                ans[i] = remaining.pop();
            }
        }
        return ans;
    }

    //
    public int[] advantageCountI(int[] A, int[] B) {
        // sort array A.
        Arrays.sort(A);
        int n = A.length;
        // create used boolean array
        boolean[] used = new boolean[n];
        // res
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        // traverse
        for (int i = 0; i < n; i++) {
            // binary search the closest value of A greater than B[i]
            int index = binarySearch(A, B[i]);
            int start = 0, end = n - 1;
            int ind = -1;
            while (start <= end) {
                int mid = (start + end) / 2;
                if (A[mid] > B[i]) {
                    ind = mid;
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            }
            // if found, used the closest unused A value
            if (ind != -1) {
                for (int j = ind; j < n; j++) {
                    if (!used[j]) {
                        used[j] = true;
                        ans[i] = A[j];
                        break;
                    }
                }
            }
        }
        int j = 0;
        for (int i = 0; i < n; i++) {
            if (ans[i] == -1) {
                while (j < n) {
                    if (!used[j]) {
                        ans[i] = A[j];
                        j++;
                        break;
                    }
                    j++;
                }
            }
        }
        return ans;
    }

    private int binarySearch(int[] array, int target) {
        int left = 0, right = array.length - 1;
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            if (array[mid] > target) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return array[right] > target ? right : array[left] > target ? left : -1;
    }
}