import java.util.*;

class LongestSubstringwithAtLeastKRepeatingCharacters {

    // Divide and conqure.
    public int longestSubstring(String s, int k) {
        if (s.length() == 0 || k > s.length())
            return 0;
        if (k == 1) {
            return s.length();
        }
        char[] arr = s.toCharArray();
        return longestSubstring(arr, 0, arr.length - 1, k);
    }

    private int longestSubstring(char[] arr, int start, int end, int k) {
        if (end - start < k - 1) {
            return 0;
        }
        // check if the arr is with at least k repeating characters
        int[] freqs = new int[26];
        Arrays.fill(freqs, -1);
        int failed = 0;
        for (int i = start; i <= end; ++i) {
            if (freqs[arr[i] - 'a'] == -1) {
                failed++;
                freqs[arr[i] - 'a'] = k - 1;
            } else if (freqs[arr[i] - 'a'] > 0 && --freqs[arr[i] - 'a'] == 0) {
                failed--;
            }
        }
        // if string succeeds
        if (failed == 0) {
            return end - start + 1;
        }
        int maxLen = 0;
        int lastFailed = start - 1;
        for (int i = start; i <= end; ++i) {
            if (freqs[arr[i] - 'a'] > 0) {
                maxLen = Math.max(longestSubstring(arr, lastFailed + 1, i - 1, k), maxLen);
                lastFailed = i;
            }
        }
        maxLen = Math.max(longestSubstring(arr, lastFailed + 1, end, k), maxLen);
        return maxLen;
    }

    // Two pointers.
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
                boolean flag = true;
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
                        flag = false;
                        break;
                    }
                }
                if (!flag)
                    continue;
                int diff = i - max;
                maxLen = Math.max(maxLen, diff);
            }
        }
        return maxLen < k ? 0 : maxLen;
    }

}