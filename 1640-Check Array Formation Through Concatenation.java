
import java.util.*;

class CheckArrayFormationThroughConcatenation {

    // HashSet. Time = O(N); Space = O(N);
    public boolean canFormArray(int[] arr, int[][] pieces) {
        int n = arr.length;
        Map<Integer, int[]> map = new HashMap<>();
        for (int[] p : pieces) {
            map.put(p[0], p);
        }

        int i = 0;
        while (i < n) {
            if (!map.containsKey(arr[i])) {
                return false;
            }
            int[] target = map.get(arr[i]);
            for (int x : target) {
                if (x != arr[i]) {
                    return false;
                }
                i++;
            }
        }
        return true;
    }

    // Binary Search. Time = O(N*log(N)); Space = O(1);
    public boolean canFormArrayI(int[] arr, int[][] pieces) {
        int n = arr.length;
        Arrays.sort(pieces, (a, b) -> a[0] - b[0]);

        int i = 0;
        while (i < n) {
            int found = bs(pieces, arr[i]);
            if (found == -1) {
                return false;
            }
            int[] target = pieces[found];
            for (int x : target) {
                if (x != arr[i]) {
                    return false;
                }
                i++;
            }
        }
        return true;
    }

    private int bs(int[][] pieces, int target) {
        int left = 0;
        int right = pieces.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (pieces[mid][0] == target) {
                return mid;
            } else if (pieces[mid][0] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
}