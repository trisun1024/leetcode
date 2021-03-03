import java.util.*;

class MinimumCostToConnectSticks{

    // Queue. Time = O(N*log(N));
    public int connectSticks(int[] sticks) {
        int totalCost = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i : sticks) {
            pq.offer(i);
        }

        while (pq.size() > 1) {
            int s1 = pq.poll();
            int s2 = pq.poll();

            int cost = s1 + s2;
            totalCost += cost;
            pq.offer(cost);
        }
        return totalCost;
    }
}
