import java.util.*;

class MeetingScheduler {

    // Two Pointers. Time = O(M*log(M) + N*log(N));
    public List<Integer> minAvailableDuration(int[][] slots1, int[][] slots2, int duration) {
        List<Integer> res = new ArrayList<>();
        Arrays.sort(slots1, (a, b) -> a[0] - b[0]);
        Arrays.sort(slots2, (a, b) -> a[0] - b[0]);
        int p1 = 0, p2 = 0;
        while (p1 < slots1.length && p2 < slots2.length) {
            int left = Math.max(slots1[p1][0], slots2[p2][0]);
            int right = Math.min(slots1[p1][1], slots2[p2][1]);
            if (right - left >= duration) {
                res.add(left);
                res.add(left + duration);
                break;
            }
            if (slots1[p1][1] < slots2[p2][1]) {
                p1++;
            } else {
                p2++;
            }
        }
        return res;
    }

    // Heap. Time = O((M+N)*log(M+N));
    public List<Integer> minAvailableDurationI(int[][] slots1, int[][] slots2, int duration) {
        PriorityQueue<int[]> timeslots = new PriorityQueue<>((slot1, slot2) -> slot1[0] - slot2[0]);
        for (int[] slot : slots1) {
            if (slot[1] - slot[0] >= duration)
                timeslots.offer(slot);
        }
        for (int[] slot : slots2) {
            if (slot[1] - slot[0] >= duration)
                timeslots.offer(slot);
        }
        List<Integer> res = new ArrayList<>();
        while (timeslots.size() > 1) {
            int[] slot1 = timeslots.poll();
            int[] slot2 = timeslots.peek();
            if (slot1[1] >= slot2[0] + duration) {
                res.add(slot2[0]);
                res.add(slot2[0] + duration);
                break;
            }
        }
        return res;
    }
}