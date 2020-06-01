import java.util.*;

class Solution {
    public List<String> topKFrequent(String[] words, int k) {
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
}