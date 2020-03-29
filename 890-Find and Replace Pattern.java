import java.util.*;

class Solution {

    public List<String> findAndReplacePattern(String[] words, String pattern) {
        List<String> res = new ArrayList<>();
        for (String word : words) {
            if (match(word, pattern)) {
                res.add(word);
            }
        }
        return res;
    }

    // One Map
    private boolean match(String word, String pattern) {
        Map<Character, Character> map = new HashMap<>();
        // loop for find a mapping
        for (int i = 0; i < word.length(); i++) {
            char w = word.charAt(i);
            char p = pattern.charAt(i);
            if (!map.containsKey(w)) {
                map.put(w, p);
            }
            if (map.get(w) != p) {
                return false;
            }
        }
        // check all the mapping is 1-1
        boolean[] seen = new boolean[26];
        for (char c : map.values()) {
            if (seen[c - 'a']) {
                return false;
            }
            seen[c - 'a'] = true;
        }
        return true;
    }
}