// 1ms
class Solution {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        if (s == null || s.length() < 1) {
            return 0;
        }

        int index = 0;
        int max = 1; // Longest is at least 1 character.

        int[] counts = new int[256];
        int distincts = 0; // Number of distinct characters currently in the substring.

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i); // current character

            if (counts[c] == 0) {
                distincts++;
                counts[c]++;

                // outbound 
                while (distincts > 2) {
                    char current = s.charAt(index);

                    counts[current]--;
                    if (counts[current] == 0) {
                        distincts--;
                    }

                    index++;
                }
            } else {
                counts[c]++;
            }

            max = Math.max(max, i - index + 1);
        }

        return max;
    }
}