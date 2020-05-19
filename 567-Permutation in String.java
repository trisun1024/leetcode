class Solution {

    // use 1D array
    public boolean checkInclusion(String s1, String s2) {
        if (s1 == null)
            return s2 == null;
        if (s1.equals(s2))
            return true;
        if (s1.length() > s2.length())
            return false;
        int[] cnt = new int[26];
        for (char c : s1.toCharArray()) {
            cnt[c - 'a']++;
        }
        int l = 0;
        int r = 0;
        int len = s1.length();
        int count = len;
        while (r < s2.length()) {
            if (cnt[s2.charAt(r++) - 'a']-- > 0) {
                count--;
            }
            if (count == 0)
                return true;
            if (r - l == len && cnt[s2.charAt(l++) - 'a']++ >= 0) {
                count++;
            }
        }
        return false;
    }
}