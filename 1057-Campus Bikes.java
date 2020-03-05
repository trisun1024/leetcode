import java.util.*;

class Solution {
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        int w = workers.length, b = bikes.length;
        int[] wo = new int[w], bi = new int[b];
        Arrays.fill(wo, -1);
        Arrays.fill(bi, -1);
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return a[0] != b[0] ? a[0] - b[0] : (a[1] != b[1] ? a[1] - b[1] : (a[2] - b[2]));
            }
        });
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < b; j++) {
                int[] worker = workers[i];
                int[] bike = bikes[j];
                int dist = Math.abs(worker[0] - bike[0]) + Math.abs(worker[1] - bike[1]);
                pq.offer(new int[] { dist, i, j });
            }
        }
        int assigned = 0;
        while (!pq.isEmpty() && assigned < w) {
            int[] s = pq.poll();
            if (wo[s[1]] == -1 && bi[s[2]] == -1) {
                wo[s[1]] = s[2];
                bi[s[2]] = s[1];
                assigned++;
            }
        }
        return wo;
    }
}