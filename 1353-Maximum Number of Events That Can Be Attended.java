import java.util.*;

class MaximumNumberOfEventsThatCanBeAttended {

    // Brute Force Solution: Set.
    public int maxEventsI(int[][] events) {
        Arrays.sort(events, (a, b) -> a[1] == b[1] ? a[0] - b[0] : a[1] - b[1]);
        Set<Integer> set = new HashSet<>();
        for (int[] e : events) {
            for (int i = e[0]; i <= e[1]; i++) {
                if (set.add(i)) {
                    break;
                }
            }
        }
        return set.size();
    }

    // Heap.
    public int maxEvents(int[][] events) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        Arrays.sort(events, (a, b) -> a[0] - b[0]);
        int i = 0;
        int res = 0;
        int curDay = 0;
        int n = events.length;
        while (!pq.isEmpty() || i < n) {
            if (pq.isEmpty()) {
                curDay = events[i][0];
            }
            while (i < n && events[i][0] <= curDay) {
                pq.offer(events[i++][1]);
            }
            pq.poll();
            res++;
            curDay++;
            while (!pq.isEmpty() && pq.peek() < curDay) {
                pq.poll();
            }
        }
        return res;
    }

}