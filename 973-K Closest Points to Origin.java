import java.util.*;

class Solution {

    // PriorityQueue Time = O(N*log(N)); Space = O(K);
    public int[][] kClosest(int[][] points, int K) {
        if (points.length <= K)
            return points;

        PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparing(a -> -a[0] * a[0] - a[1] * a[1]));
        for (int[] point : points) {
            minHeap.add(point);
            if (minHeap.size() > K) {
                minHeap.poll();
            }
        }

        int[][] result = new int[K][2];
        int i = 0;
        while (!minHeap.isEmpty()) {
            int[] point = minHeap.poll();
            result[i][0] = point[0];
            result[i][1] = point[1];
            i++;
        }
        return result;
    }

    // Sorting
    public int[][] kClosestIII(int[][] points, int K) {
        Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                if ((a[0] * a[0] + a[1] * a[1]) == (b[0] * b[0] + b[1] * b[1])) {
                    return 0;
                }
                return (a[0] * a[0] + a[1] * a[1]) < (b[0] * b[0] + b[1] * b[1]) ? -1 : 1;
            }
        });
        int[][] res = new int[K][2];
        int i = 0;
        while (K > 0) {
            res[i][0] = points[i][0];
            res[i][1] = points[i][1];
            K--;
            i++;
        }
        return res;
    }

    // Binary Search
    public int[][] kClosestII(int[][] points, int K) {
        int left = 0;
        int right = points.length - 1;
        while (left <= right) {
            int mid = kClosest(points, left, right);
            if (mid == K)
                break;
            else if (mid < K)
                left = mid + 1;
            else
                right = mid - 1;
        }
        return Arrays.copyOfRange(points, 0, K);
    }

    public int kClosest(int[][] A, int left, int right) {
        int[] pivot = A[left];
        while (left < right) {
            while (left < right && compare(A[right], pivot) >= 0)
                right--;
            A[left] = A[right];
            while (left < right && compare(A[left], pivot) <= 0)
                left++;
            A[right] = A[left];
        }
        A[left] = pivot;
        return left;
    }

    public int compare(int[] n1, int[] n2) {
        return n1[0] * n1[0] + n1[1] * n1[1] - n2[0] * n2[0] - n2[1] * n2[1];
    }
}