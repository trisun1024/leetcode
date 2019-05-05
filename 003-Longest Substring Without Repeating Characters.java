public class Solution {
    public int lengthOfLongestSubstring(String s) {
        int len = s.length();
        int i = 0, j = 0, res = 0;
        Set<Character> subset = new HashSet<>();
        while (i < len && j < len) {
            if (!subset.contains(s.charAt(j))) {
                subset.add(s.charAt(j++));
                res = Math.max(res, j - 1);
            } else {
                subset.remove(s.charAt(i++));
            }
        }
        return res;
    }
}