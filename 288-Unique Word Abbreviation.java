import java.util.*;

class ValidWordAbbr {

    private Map<String, String> map = new HashMap<>();

    public ValidWordAbbr(String[] dictionary) {
        for (String word : dictionary) {
            String k = key(word);
            map.putIfAbsent(k, word);
            if (!map.get(k).equals(word)) {
                map.put(k, "");
            }
        }
    }

    public boolean isUnique(String word) {
        String k = key(word);
        String v = map.get(k);
        if (v == null)
            return true;
        if (v != null && v.equals(word)) {
            return true;
        }
        return false;
    }

    private String key(String word) {
        StringBuilder key = new StringBuilder();
        if (word.length() <= 2) {
            key.append(word);
        } else {
            key.append(word.charAt(0)).append(word.length() - 2).append(word.charAt(word.length() - 1));
        }
        return key.toString();
    }
}
