import java.util.*;

class AnalyzeUserWebsiteVisitPattern {

    public List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {
        Map<String, List<Node>> map = new HashMap<>();
        for (int i = 0; i < username.length; i++) {
            if (!map.containsKey(username[i])) {
                map.put(username[i], new ArrayList<>());
            }
            map.get(username[i]).add(new Node(timestamp[i], website[i]));
        }

        // sort List<Node> && calculate freq
        // tracking variable
        String ans = null;
        // frequency map
        Map<String, Integer> freq = new HashMap<>();
        for (String key : map.keySet()) {
            // sort list
            List<Node> list = map.get(key);
            Collections.sort(list, (a, b) -> (a.time - b.time));
            // store visit list
            Set<String> visited = new HashSet<>();
            for (int i = 0; i < list.size(); i++) {
                for (int j = i + 1; j < list.size(); j++) {
                    for (int k = j + 1; k < list.size(); k++) {
                        String w = list.get(i).website + "," + list.get(j).website + "," + list.get(k).website;
                        if (!visited.contains(w)) {
                            freq.put(w, freq.getOrDefault(w, 0) + 1);
                            visited.add(w);
                        }
                        if (ans == null || freq.get(w) > freq.get(ans)
                                || (freq.get(w) == freq.get(ans) && w.compareTo(ans) < 0)) {
                            ans = w;
                        }
                    }
                }
            }
        }

        // get all
        String[] split = ans.split(",");
        List<String> res = new ArrayList<>();
        for (String s : split) {
            res.add(s);
        }
        return res;
    }

    static class Node {
        int time;
        String website;

        Node(int time, String website) {
            this.time = time;
            this.website = website;
        }
    }
}