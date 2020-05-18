import java.util.*;

class Solution {

    // use hashmap
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        int sn = s.length();
        int pn = p.length();
        if (sn < pn) {
            return res;
        }
        Map<Character, Integer> pCount = new HashMap<>();
        Map<Character, Integer> sCount = new HashMap<>();
        for (char c : p.toCharArray()) {
            pCount.put(c, pCount.getOrDefault(c, 0) + 1);
        }
        for (int i = 0; i < sn; i++) {
            char c = s.charAt(i);
            sCount.put(c, sCount.getOrDefault(c, 0) + 1);
            if (i >= pn) {
                c = s.charAt(i - pn);
                if (sCount.get(c) == 1) {
                    sCount.remove(c);
                } else {
                    sCount.put(c, sCount.get(c) - 1);
                }
            }
            if (pCount.equals(sCount)) {
                res.add(i - pn + 1);
            }
        }
        return res;
    }

    // use int array
    public List<Integer> findAnagramsII(String s, String p) {
        List<Integer> res = new ArrayList<>();
        int sn = s.length();
        int pn = p.length();
        if (sn < pn) {
            return res;
        }
        int[] map = new int[26];
        for (char c : p.toCharArray()) {
            map[c - 'a']++;
        }
        int size = 0;
        for (int i : map) {
            if (i > 0) {
                size++;
            }
        }
        int match = 0;
        for (int i = 0; i < sn; i++) {
            char c = s.charAt(i);
            int count = map[c - 'a'];
            if (count > 1) {
                map[c - 'a']--;
            } else {
                match++;
            }
            if (i >= pn) {
                c = s.charAt(i - p.length());
                count = map[c - 'a'];
                map[c - 'a']++;
                if (count == 0) {
                    match--;
                }
            }
            if (match == size) {
                res.add(i - pn + 1);
            }
        }
        return res;
    }
}