import java.util.*;

class LongestSubstringwithAtLeastKRepeatingCharacters {

    // Divide and Conquer. Time = O(N^2); Space = O(N);
    public int longestSubstring(String s, int k) {
        return helper(s, 0, s.length(), k);
    }

    private int helper(String s, int start, int end, int k) {
        if (end < k) {
            return 0;
        }
        int[] countMap = new int[26];
        // update the countMap with the count of each character
        for (int i = start; i < end; i++) {
            countMap[s.charAt(i) - 'a']++;
        }
        for (int mid = start; mid < end; mid++) {
            if (countMap[s.charAt(mid) - 'a'] >= k) {
                continue;
            }
            int midNext = mid + 1;
            while (midNext < end && countMap[s.charAt(midNext) - 'a'] < k) {
                midNext++;
            }
            return Math.max(helper(s, start, mid, k), helper(s, midNext, end, k));
        }
        return (end - start);
    }

    // Two Pointers.
    public int longestSubstringI(String s, int k) {
        int maxLen = Integer.MIN_VALUE;
        char[] ch = s.toCharArray();
        int[] count = new int[26];
        // Array to store first occurence of character
        int[] minIndex = new int[26];
        // Array to store last occurence of character
        int[] maxIndex = new int[26];
        Arrays.fill(minIndex, -1);
        for (int i = 0; i < ch.length; i++) {
            int val = ch[i] - 'a';
            count[val]++;
            if (minIndex[val] == -1) {
                minIndex[val] = i;
            }
            maxIndex[val] = i;
            if (count[val] >= k) {
                boolean flag = false;
                int max = -1;
                int min = ch.length;

                for (int j = 0; j < 26; j++) {
                    if (count[j] != 0 && count[j] < k) {
                        max = Math.max(max, maxIndex[j]);
                        min = Math.min(min, minIndex[j]);
                    }
                }

                if (min == ch.length && max == -1) {
                    maxLen = Math.max(maxLen, i + 1);
                    continue;
                }

                for (int j = 0; j < 26; j++) {
                    if (count[j] != 0 && maxIndex[j] > max && minIndex[j] < min) {
                        flag = true;
                        break;
                    }
                }
                if (flag) {
                    continue;
                }
                int diff = i - max;
                maxLen = Math.max(maxLen, diff);
            }
        }
        return maxLen < k ? 0 : maxLen;
    }

}