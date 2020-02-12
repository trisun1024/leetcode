import java.util.*;

class WordDistance {

    Map<String, List<Integer>> mapper;

    public WordDistance(String[] words) {
        this.mapper = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            List<Integer> tmp = this.mapper.getOrDefault(words[i], new ArrayList<>());
            tmp.add(i);
            this.mapper.put(words[i], tmp);
        }
    }

    public int shortest(String word1, String word2) {
        List<Integer> dis1 = this.mapper.get(word1);
        List<Integer> dis2 = this.mapper.get(word2);
        if (dis1 == null || dis2 == null) {
            return -1;
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < dis1.size(); i++) {
            for (int j = 0; j < dis2.size(); j++) {
                min = Math.min(min, Math.abs(dis1.get(i) - dis2.get(j)));
            }
        }
        return min;
    }
}

/**
 * Your WordDistance object will be instantiated and called as such:
 * WordDistance obj = new WordDistance(words); int param_1 =
 * obj.shortest(word1,word2);
 */