import java.util.*;

class SubstringOfAllWords {

    // Consider two-map solution
    // use wordSet as our checking list, seen as our result list.
    // compare both list to retrieve the output.
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new LinkedList<Integer>();
        if (s.length() == 0 || words.length == 0 || words == null)
            return res;

        HashMap<String, Integer> wordSet = new HashMap<String, Integer>();
        for (String w : words) {
            wordSet.put(w, wordSet.getOrDefault(w, 0) + 1);
        }

        int strLen = s.length(), wordsLen = words.length, wordLen = words[0].length();
        for (int i = 0; i < strLen - wordsLen * wordLen + 1; i++) {
            Map<String, Integer> seen = new HashMap<>();
            int j = 0;
            while (j < wordsLen) {
                String word = s.substring(i + j * wordLen, i + (j + 1) * wordLen);
                if (wordSet.containsKey(word)) {
                    seen.put(word, seen.getOrDefault(word, 0) + 1);
                    if (seen.get(word) > wordSet.getOrDefault(word, 0)) {
                        break;
                    }
                } else {
                    break;
                }
                j++;
            }
            if (j == wordsLen)
                res.add(i);
        }
        return res;
    }
}
