import java.util.*;

class ConcatenatedWords {

    // use HashMap to store the words
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        List<String> res = new ArrayList<>();
        Set<String> dict = new HashSet<>();
        for (int k = 0; k < words.length; k++) {
            String word = words[k];
            if (word.equals(""))
                continue;
            dict.add(word);
        }
        for (String w : words) {
            if (w.equals(""))
                continue;
            if (helper(w, 0, dict))
                res.add(w);
        }
        return res;
    }

    private boolean helper(String word, int index, Set<String> dict) {
        // termination condition
        if (index > 0 && dict.contains(word)) {
            return true;
        }
        for (int i = 0; i < word.length() - 1; i++) {// word[0,n]=word[0,i]+word[i+1,n]
            if (dict.contains(word.substring(0, i + 1)) && helper(word.substring(i + 1), index + 1, dict)) {
                return true;
            }
        }
        return false;
    }
}