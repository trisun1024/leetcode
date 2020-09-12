import java.util.*;

class WordPattern {

    // Double HashMap. Time = O(N); Space = O(2N);
    public boolean wordPatternI(String pattern, String str) {
        if (pattern.length() == 0 && str.length() == 0) {
            return true;
        }
        if (pattern.length() == 0 || str.length() == 0) {
            return false;
        }
        String[] strArray = str.split(" ");
        if (strArray.length != pattern.length()) {
            return false;
        }
        Map<Character, String> map = new HashMap<>();
        Map<String, Character> rev = new HashMap<>();
        for (int i = 0; i < strArray.length; i++) {
            char c = pattern.charAt(i);
            String word = strArray[i];
            if (!map.containsKey(c) && !rev.containsKey(word)) {
                map.put(c, word);
                rev.put(word, c);
            } else if (map.containsKey(c) && !map.get(c).equals(word)) {
                return false;
            } else if (rev.containsKey(word) && !rev.get(word).equals(c)) {
                return false;
            }
        }
        return true;
    }

}