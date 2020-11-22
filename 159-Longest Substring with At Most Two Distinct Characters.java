import java.util.*;

class LongestSubstringWithMostTwoDistinct {

    // HashMap.
    public int lengthOfLongestSubstringTwoDistinctI(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        if (s.length() < 3) {
            return s.length();
        }
        int left = 0;
        int right = 0;
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        int maxLen = 2;
        while (right < s.length()) {
            map.put(s.charAt(right), right++);
            if (map.size() == 3) {
                int deleteIndex = Collections.min(map.values());
                map.remove(s.charAt(deleteIndex));
                left = deleteIndex + 1;
            }
            maxLen = Math.max(maxLen, right - left);
        }
        return maxLen;
    }

    public int lengthOfLongestSubstringTwoDistinct(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        if (s.length() < 3) {
            return s.length();
        }
        int[] map = new int[128];
        int counter = 0;
        int left = 0, right = 0;
        int maxLength = 0;
        while (right < s.length()) {
            if (map[s.charAt(right++)]++ == 0) {
                counter++;
            }
            while (counter > 2)
                if (map[s.charAt(left++)]-- == 1)
                    counter--;

            maxLength = Math.max(maxLength, right - left);
        }
        return maxLength;
    }
}