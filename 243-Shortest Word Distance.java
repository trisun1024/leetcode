class Solution {
    public int shortestDistance(String[] words, String word1, String word2) {
        int len1 = -1;
        int len2 = -1;
        int min = words.length;
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(word1)) {
                len1 = i;
            } else if (words[i].equals(word2)) {
                len2 = i;
            }
            if (len1 != -1 && len2 != -1) {
                min = Math.min(min, Math.abs(len1 - len2));
            }
        }
        return min;
    }
}