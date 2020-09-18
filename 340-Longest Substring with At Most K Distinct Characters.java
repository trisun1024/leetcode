import java.util.*;

class LongestSubstringWithAtMostKDistinctCharacters {

    // Sliding Window + HashMap. Time = O(N*K); Space = O(K);
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        int len = s.length();
        if (len * k == 0) {
            return 0;
        }
        // sliding window
        int left = 0;
        int right = 0;
        // Map store rightmost postion
        Map<Character, Integer> map = new HashMap<>();
        // result
        int max = 0;
        while (right < len) {
            char c = s.charAt(right);
            map.put(c, right++);
            if (map.size() == k + 1) {
                int index = Collections.min(map.values());
                map.remove(s.charAt(index));
                left = index + 1;
            }
            max = Math.max(max, right - left);
        }
        return max;
    }

    // Sliding Window + Ordered Dictionary. Time = O(N); Space = O(K);
    public int lengthOfLongestSubstringKDistinctI(String s, int k) {
        int n = s.length();
        if (n * k == 0) {
            return 0;
        }
        int max = 0;
        LinkedHashMap<Character, Integer> map = new LinkedHashMap<>(k + 1);
        int left = 0;
        int right = 0;
        while (right < n) {
            char c = s.charAt(right);
            if (map.containsKey(c)) {
                map.remove(c);
            }
            map.put(c, right++);
            if (map.size() == k + 1) {
                Map.Entry<Character, Integer> leftMost = map.entrySet().iterator().next();
                map.remove(leftMost.getKey());
                left = leftMost.getValue() + 1;
            }
            max = Math.max(max, right - left);
        }
        return max;
    }

    // Use array to store
    public int lengthOfLongestSubstringKDistinctII(String s, int k) {
        int max = 0;
        int n = s.length();
        if (n * k == 0) {
            return max;
        }
        int[] count = new int[256];
        int distinctCount = 0;
        int left = 0;
        int right = 0;
        while (right < n) {
            if (count[s.charAt(right)]++ == 0) {
                distinctCount++;
            }
            while (distinctCount > k) {
                if (--count[s.charAt(left)] == 0) {
                    distinctCount--;
                }
                left++;
            }
            max = Math.max(max, right - left + 1);
            right++;
        }
        return max;
    }
}