import java.util.*;

class Solution {
    public String frequencySort(String s) {
        if (s.length() <= 1) {
            return s;
        }
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        List<Character> list = new ArrayList<>(map.keySet());
        Collections.sort(list, new Comparator<Character>() {
            @Override
            public int compare(Character c1, Character c2) {
                if (map.get(c1) == map.get(c2)) {
                    return 0;
                }
                return map.get(c1) < map.get(c2) ? 1 : -1;
            }
        });
        StringBuilder sb = new StringBuilder();
        for (Character c : list) {
            int num = map.get(c);
            while (num > 0) {
                sb.append(c);
                num--;
            }
        }
        return sb.toString();
    }

    // use array to save space
    public String frequencySortII(String s) {
        if (s.length() <= 1) {
            return s;
        }
        int[] map = new int[128];
        char[] array = s.toCharArray();
        for (char c : array) {
            map[c]++;
        }
        char[] result = new char[s.length()];
        for (int i = 0; i < s.length();) {
            int max = 0;
            int index = 0;
            for (int j = 0; j < 128; j++) {
                if (map[j] > max) {
                    max = map[j];
                    index = j;
                }
            }
            while (map[index] > 0) {
                result[i++] = (char) index;
                map[index]--;
            }
        }
        return new String(result);
    }
}