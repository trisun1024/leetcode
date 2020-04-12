import java.util.*;

class Solution {
    public int lastStoneWeight(int[] stones) {
        if (stones == null || stones.length == 0) {
            return 0;
        }
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                if (a == b) {
                    return 0;
                }
                return a < b ? 1 : -1;
            }
        });
        for (int i : stones) {
            maxHeap.offer(i);
        }
        while (maxHeap.size() > 1) {
            Integer a = maxHeap.poll();
            Integer b = maxHeap.poll();
            if (a != b) {
                maxHeap.offer(Math.abs(a - b));
            } else {
                maxHeap.offer(0);
            }
        }
        return maxHeap.peek();
    }
}