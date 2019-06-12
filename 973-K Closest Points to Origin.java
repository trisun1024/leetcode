// Min Heap
class Solution {
    public int[][] kClosest(int[][] points, int K) {
        if (points.length <= K)
            return points;

        PriorityQueue<int[]> q = new PriorityQueue<>(Comparator.comparing(a -> -a[0] * a[0] - a[1] * a[1]));
        for (int[] point : points) {
            q.add(point);
            if (q.size() > K) {
                q.poll();
            }
        }

        int[][] result = new int[K][2];
        int i = 0;
        while (!q.isEmpty()) {
            int[] point = q.poll();
            result[i][0] = point[0];
            result[i][1] = point[1];
            i++;
        }
        return result;
    }
}

//
class Solution1 {
    public int[][] kClosest(int[][] points, int K) {
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