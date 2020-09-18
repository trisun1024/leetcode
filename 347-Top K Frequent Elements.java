import java.util.Map;

import java.util.*;

class TopKFrequentElements {

    // Time = O(N*log(K) + N); Space = O(K + N);
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int i : nums) {
            freq.put(i, freq.getOrDefault(i, 0) + 1);
        }
        int[] res = new int[k];
        PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>(k,
                new Comparator<Map.Entry<Integer, Integer>>() {
                    @Override
                    public int compare(Map.Entry<Integer, Integer> e1, Map.Entry<Integer, Integer> e2) {
                        if (e1.getValue().equals(e2.getValue())) {
                            return 0;
                        }
                        return e1.getValue() < e2.getValue() ? -1 : 1;
                    }
                });
        for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            pq.offer(entry);
            if (pq.size() > k) {
                pq.poll();
            }
        }
        for (int i = k - 1; i >= 0; i--) {
            res[i] = pq.poll().getKey();
        }
        return res;
    }
}