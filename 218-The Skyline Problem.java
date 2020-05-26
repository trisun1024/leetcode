import java.util.*;

class Solution {
    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<List<Integer>> res = new ArrayList<>();
        if (buildings.length == 0) {
            return res;
        }
        List<int[]> points = new ArrayList<>();
        for (int[] b : buildings) {
            points.add(new int[] { b[0], -b[2] });
            points.add(new int[] { b[1], b[2] });
        }
        Collections.sort(points, (a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]);
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> (b - a));
        maxHeap.offer(0);
        int prev = 0;
        for (int[] p : points) {
            if (p[1] < 0) {
                maxHeap.offer(-p[1]);
            } else {
                maxHeap.remove(p[1]);
            }
            int cur = maxHeap.peek();
            if (prev != cur) {
                res.add(Arrays.asList(new Integer[] { p[0], cur }));
                prev = cur;
            }
        }
        return res;
    }
}