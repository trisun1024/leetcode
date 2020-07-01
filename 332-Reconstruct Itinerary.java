import java.util.*;

class ReconstructItinerary {

    // Hierholzer's Algorithm. Time = O(E*log(E/V)); Space = O(E+V)
    public List<String> findItinerary(List<List<String>> tickets) {
        // build graph
        Map<String, LinkedList<String>> graph = new HashMap<>();
        for (List<String> t : tickets) {
            String start = t.get(0);
            String end = t.get(1);
            if (!graph.containsKey(start)) {
                graph.put(start, new LinkedList<>());
            }
            graph.get(start).add(end);
        }
        // sort dest
        for (String key : graph.keySet()) {
            LinkedList<String> list = graph.get(key);
            Collections.sort(list);
        }
        LinkedList<String> res = new LinkedList<>();
        dfs(graph, "JFK", res);
        return res;
    }

    private void dfs(Map<String, LinkedList<String>> graph, String origin, LinkedList<String> res) {
        // Visit all the outgoing edges first.
        if (graph.containsKey(origin)) {
            LinkedList<String> destList = graph.get(origin);
            while (!destList.isEmpty()) {
                // while we visit the edge, we trim it off from graph.
                String dest = destList.pollFirst();
                dfs(graph, dest, res);
            }
        }
        // add the airport to the head of the itinerary
        res.offerFirst(origin);
    }

    // BFS 
    public List<String> findItineraryII(List<List<String>> tickets) {
        LinkedList<String> res  = new LinkedList<>();
        Map<String, PriorityQueue<String> > map = new HashMap<>();
        for(List<String> t : tickets) {
            String start = t.get(0);
            String end = t.get(1);
            if(!map.containsKey(start)) {
                map.put(start, new PriorityQueue<>());
            }
            map.get(start).offer(end);
        }
        helper(map, "JFK", res);
        return res;
    }
    private void helper(Map<String , PriorityQueue<String>> map, String cur, LinkedList<String> res) {
        PriorityQueue<String> pq = map.get(cur);
        while(pq!=null && !pq.isEmpty()) {
            helper(map, pq.poll(), res);
        }
        res.offerFirst(cur);
    }
}