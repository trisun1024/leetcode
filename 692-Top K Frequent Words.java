import java.util.*;

class TopKFrequentWords {

    // Time = O(N*log(N)); Space = O(N);
    public List<String> topKFrequentII(String[] words, int k) {
        Map<String, Integer> map = new HashMap<>();
        for (String s : words) {
            map.put(s, map.getOrDefault(s, 0) + 1);
        }
        PriorityQueue<Map.Entry<String, Integer>> maxHeap = new PriorityQueue<>(
                new Comparator<Map.Entry<String, Integer>>() {
                    @Override
                    public int compare(Map.Entry<String, Integer> e1, Map.Entry<String, Integer> e2) {
                        if (e1.getValue() == e2.getValue()) {
                            return e1.getKey().compareTo(e2.getKey());
                        }
                        return e1.getValue() < e2.getValue() ? 1 : -1;
                    }
                });
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            maxHeap.offer(entry);
        }
        List<String> res = new ArrayList<>();
        while (k > 0 && !maxHeap.isEmpty()) {
            res.add(maxHeap.poll().getKey());
            k--;
        }
        return res;
    }

    // Use MinHeap to reduce the space to O(K)
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> freq = new HashMap<>();
        for (String s : words) {
            freq.put(s, freq.getOrDefault(s, 0) + 1);
        }
        PriorityQueue<Map.Entry<String, Integer>> minHeap = new PriorityQueue<>(
                new Comparator<Map.Entry<String, Integer>>() {
                    @Override
                    public int compare(Map.Entry<String, Integer> e1, Map.Entry<String, Integer> e2) {
                        if (e1.getValue().equals(e2.getValue())) {
                            return e2.getKey().compareTo(e1.getKey());
                        }
                        return e1.getValue().compareTo(e2.getValue());
                    }
                });
        for (Map.Entry<String, Integer> entry : freq.entrySet()) {
            minHeap.offer(entry);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
        List<String> res = new ArrayList<>();
        while (!minHeap.isEmpty()) {
            res.add(minHeap.poll().getKey());
        }
        Collections.reverse(res);
        return res;
    }

}